/*
 * AddAttachFilter.java
 *
 */

package com.infowave.pda.kernel.filters;

import com.infowave.pda.kernel.objects.Attachment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.*;
             
/**
 * @author Alexey.Ogarkov
 */

public class AddAttachFilter implements Filter {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String BEGIN = "begin";
    private final static String ADD = "add";

    /* session Attribute Names */
    public final static String FORM_MAP = "cashed_formMap";
    private final static String FORM = "cashed_form";
    private final static String BACK_END = "backward_destination";
    public final static String ATTACH_LIST = "cashed_attachList";

    /* request parameters */
    private final static String NAME = "fileName";
    private final static String FULL_NAME = "filePath";
    private final static String AS_LINK = "flagAsLink";
    private final static String FILE_TYPE = "fileType";
    private final static String FILE_SIZE = "fileSize";
    private final static String FILE_ATTRIBS = "fileAttribs";

    /*form fields */
    private final static String FORM_FULL_NAME = "attachFullName";
    private final static String FORM_AS_LINK = "attachAsLink";
    private final static String FORM_AS_IMAGE = "attachAsImage";

    private HttpSession session;
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(AddAttachFilter.class);


    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public AddAttachFilter() {
    }

    /**
     * @param request The servlet request we are processing
     * @param chain   The filter chain we are processing
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String method = servletRequest.getParameter("usermethod");
            
            if (method != null) {
                session = servletRequest.getSession();
                if (method.equals("begin_add")) {
                    HashMap<String, String> paramMap = new HashMap<String, String>();
                    Enumeration<String> en = request.getParameterNames();
                    while (en.hasMoreElements()) {
                        String e = en.nextElement();
                        paramMap.put(e, request.getParameter(e));
                    }
                    session.setAttribute(FORM_MAP, paramMap);

                    String destination = request.getParameter("backend");

                    session.setAttribute(BACK_END, servletRequest.getHeader("referer"));

                    ArrayList<Attachment> attachments = this.setAttachmentList();
                    session.setAttribute(ATTACH_LIST, attachments);

                    LinkedList navigationStack = (NavigationFilter.NavigationStack) session.getAttribute(NavigationFilter.NAVIGATION);
                    navigationStack.addFirst(destination);
                    session.setAttribute(NavigationFilter.NAVIGATION,navigationStack);
                    servletRequest.setAttribute("browseAttachCaller", destination);
//                    RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/pda/GetFolderContent.do");
//                    dispatcher.forward(servletRequest, response);
                    ((HttpServletResponse)response).sendRedirect("/pda/GetFolderContent.do?browseAttachCaller="+destination);
                    return;
                } else if (method.equals("end_add")) {

                    session = servletRequest.getSession();

                    /*if (form == null) {
                        Map<String, String> formMap = (Map<String, String>) session.getAttribute(FORM_MAP);
                        FormBeanConfig conf = new FormBeanConfig();
                        conf.setName(request.getParameter("beanName"));
                        DynaActionFormClass formClass = DynaActionFormClass.createDynaActionFormClass(conf);
                        try {
                            form = (DynaActionForm) formClass.newInstance();
                            for (Map.Entry<String,String> entry: formMap.entrySet()) {
                                form.set(entry.getKey(),entry.getValue());                                    
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InstantiationException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }*/
                    request.setAttribute(FORM_MAP, session.getAttribute(FORM_MAP));
                    HashMap<String,String> formMap = (HashMap<String,String>) session.getAttribute(FORM_MAP);
                    if (formMap != null) {
                        String dest = formMap.get("dest");
                        LinkedList<String> navigationStack = (NavigationFilter.NavigationStack) session.getAttribute(NavigationFilter.NAVIGATION);
                        if (dest!=null) {
                            /*request.setAttribute(form.getDynaClass().getName(), form);*/
                            if (navigationStack.size()>0) {
                                while (navigationStack.size()>0) {
                                    if (navigationStack.getFirst().indexOf(dest)!=-1) {
                                        break;
                                    }
                                    navigationStack.removeFirst();
                                }
                            }
                        }
                        session.setAttribute(NavigationFilter.NAVIGATION, navigationStack);
                    }
                    ArrayList<Attachment> attachments = this.setAttachmentList();
/*                    if (request.getParameter("makenew")!=null) {
                        attachments = new ArrayList<Attachment>();
                    }*/
                    this.addAttachment(servletRequest, attachments);
                    session.setAttribute(ATTACH_LIST, attachments);
                    //session.setAttribute(BACK_END,servletRequest.getHeader("referer"));
                } else if (method.equals("end_delete")) {
                    servletRequest.setAttribute(FORM_MAP, session.getAttribute(FORM_MAP));

                    session = servletRequest.getSession();

                    ArrayList<Attachment> attachments = (ArrayList<Attachment>) session.getAttribute(ATTACH_LIST);

                    this.deleteAttachment(servletRequest, attachments);

                    request.setAttribute(ATTACH_LIST, attachments);

                } else if (method.indexOf("add_contact")!=-1) {
                    String destionation = method.substring(method.lastIndexOf("_")+1);
                    session.setAttribute("destination",destionation);
                    HashMap<String, String> paramMap = new HashMap<String, String>();
                    Enumeration<String> en = request.getParameterNames();
                    while (en.hasMoreElements()) {
                        String e = en.nextElement();
                        paramMap.put(e, request.getParameter(e));
                    }
                    session.setAttribute(FORM_MAP, paramMap);

                    String destination = request.getParameter("backend");
                    destination = URLDecoder.decode(destination,"UTF-8");
                    session.setAttribute(BACK_END, servletRequest.getHeader("referer"));

                    ArrayList<Attachment> attachments = this.setAttachmentList();
                    session.setAttribute(ATTACH_LIST, attachments);

                    LinkedList navigationStack = (NavigationFilter.NavigationStack) session.getAttribute(NavigationFilter.NAVIGATION);

                    if (destination==null) {
                        destination = servletRequest.getHeader("referer");
                    }
                    navigationStack.addFirst(destination);
                    session.setAttribute(NavigationFilter.NAVIGATION, navigationStack);
                    
                    RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/pda/RedirectToContactLookUp.do");
                    dispatcher.forward(servletRequest, response);

                    return;    
                } else if (method.equals("flush")) {
                    session.removeAttribute(ATTACH_LIST);
                    session.removeAttribute(FORM_MAP);
                } 
            }
        }
        chain.doFilter(request, response);
    }

    private ArrayList<Attachment> deleteAttachment(HttpServletRequest servletRequest, ArrayList<Attachment> attachments) {
        String fullName = servletRequest.getParameter(FULL_NAME);
        for (Attachment a : attachments) {
            if (a.getFullName() != null) {
                if (a.getFullName().equals(fullName)) {
                    attachments.remove(a);
                    break;
                }
            } else if (a.getName()!=null) {
                if (a.getName().equals(fullName)) {
                    attachments.remove(a);
                    break;
                }
            }
        }
        return attachments;
    }

    private ArrayList<Attachment> addAttachment(HttpServletRequest request, ArrayList<Attachment> attachments) {
        Attachment at = new Attachment();
        if (request.getParameter(NAME) != null) {
            at.setName(request.getParameter(NAME));
        }

        if (request.getParameter(AS_LINK) != null) {
            at.setLink((Integer.parseInt(request.getParameter(AS_LINK)) == 1));
        }

        if (request.getParameter(FULL_NAME) != null) {
            at.setFullName(request.getParameter(FULL_NAME));
        }

        if (request.getParameter(FILE_SIZE) != null) {
            at.setFileSize(request.getParameter(FILE_SIZE));
        }

        if (request.getParameter(FILE_TYPE) != null) {
            at.setFileType(request.getParameter(FILE_TYPE));
        }

        if (request.getParameter(FILE_ATTRIBS) != null) {
            at.setFileAttribs(request.getParameter(FILE_ATTRIBS));
        }

        if (attachments.contains(at)) {
            attachments.remove(at);
        }
        attachments.add(at);
        return attachments;
    }

    private ArrayList<Attachment> setAttachmentList() {
        ArrayList<Attachment> result = (ArrayList<Attachment>) session.getAttribute(ATTACH_LIST);

        if (result == null) {
            result = new ArrayList<Attachment>();
        }

        return result;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }


    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {

        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }


    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            log("AddAttachAction:Initializing filter");
        }
    }

    /**
     * Return a String representation of this object.
     */
    public String toString() {

        if (filterConfig == null) return ("AddAttachAction()");
        StringBuffer sb = new StringBuffer("AddAttachAction(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());

    }

    public static String getStackTrace(Throwable t) {

        String stackTrace = null;

        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        m_log.debug(msg);
    }
}

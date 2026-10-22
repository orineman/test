package com.infowave.c4p.web.tags;

import com.infowave.c4p.web.util.RequestRedirector;
import com.infowave.c4p.web.util.WebConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

/**
 * This tag just renders the content of an array of bytes.
 */
public class DesktopTag extends TagSupport
{
/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(DesktopTag.class);

public int doStartTag() throws JspException
{
	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	RequestRedirector redirector = null;
	HttpMethod method = null;
	try
	{
		// method = (HttpMethod) request.getAttribute(WebConstants.METHOD);
		redirector = (RequestRedirector) request.getAttribute(WebConstants.METHOD);
		method = redirector.getMethod();

		if(method == null)
		{
			m_log.error("Could the find the desktop method");
			throw new JspException("Could the find the desktop method");
		}
		HttpClient client = new HttpClient();
		int statusCode;
		m_log.debug("Method:" + method.getClass().getName() + ", Path:" + method.getPath() + ", URL:" + method.getURI() + ", Query string:" + method.getQueryString());
		for(int i = 0; i < method.getRequestHeaders().length; i++)
		{
			m_log.debug("Header: " + method.getRequestHeaders()[i].getName() + " = " + method.getRequestHeaders()[i].getValue());
		}
		// Fetch the url from the ICM
		if(method instanceof PostMethod)
		{
			m_log.debug("Method is a instance of Post");
			PostMethod post = (PostMethod) method;
			NameValuePair[] nvps = post.getParameters();
			m_log.debug("method: " + method.getClass().getName() + " " + method.getPath());
			for(int i = 0; i < nvps.length; i++)
			{
				m_log.debug("param: " + nvps[i].getName() + " = " + nvps[i].getValue());
			}
		}
		m_log.debug("Before Execute Method");
		statusCode = client.executeMethod(method);
		m_log.debug("After Execute Method, status code: " + statusCode);
		byte[] responseBody = method.getResponseBody();
		for(int i = 0; i < responseBody.length; i++)
		{
			pageContext.getOut().write(responseBody[i]);
		}
	}
	catch(Exception e)
	{
		m_log.error("Cannot display desktop page", e);
	}
	finally
	{
		if(method != null)
		{
			try
			{
				method.releaseConnection();
			}
			catch(Exception e)
			{
			}
		}
		if(redirector != null)
		{
			redirector.cleanup();
		}
	}
	// Continue processing this page
	return (SKIP_BODY);
}

/**
 * Evaluate the remainder of the current page normally.
 *
 * @throws JspException if a JSP exception occurs
 */
public int doEndTag() throws JspException
{
	return (EVAL_PAGE);
}

/**
 * Release all allocated resources.
 */
public void release()
{
	super.release();

}
}

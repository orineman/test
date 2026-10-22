// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 3/15/2005 4:24:44 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RequestRedirector.java

package com.infowave.c4p.web.util;

import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.util.StringManager;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.QCodec;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.*;

// Referenced classes of package com.infowave.c4p.web.util:
//            Base64Encoder

public class RequestRedirector
{
public static class FileInfo
{

	public String toString()
	{
		return "name: " + filename + " - fs name: " + filesystemName + " lcoal: " + file.getName();
	}

	File file;
	String filename;
	String filesystemName;
	String fieldName;

	public FileInfo(File file, String filename, String filesystemName, String fieldName)
	{
		this.filesystemName = filesystemName;
		this.file = file;
		this.filename = filename;
		this.fieldName = fieldName;
	}
}


public RequestRedirector(String redirectTo, Map headers)
{
	m_softwareVersion = null;
	m_parameters = null;
	m_headers = null;
	m_queryString = null;
	m_isMultiPart = false;
	m_valid = true;
	m_method = "get";
	m_queryString = null;
	m_parameters = null;
	m_redirectTo = redirectTo;
	m_headers = headers;
}

public RequestRedirector(HttpServletRequest request, String redirectTo, Map headers, String softwareVersion)
{
	m_parameters = null;
	m_headers = null;
	m_queryString = null;
	m_isMultiPart = false;
	m_valid = true;
	m_method = request.getMethod();
	m_queryString = request.getQueryString();
	m_redirectTo = redirectTo;
	m_headers = headers;
	m_softwareVersion = softwareVersion;
	if(FileUpload.isMultipartContent(request))
	{
		m_isMultiPart = true;
		m_valid = getMultiPartRequestParameters(request);
	}
	else
	{
		m_parameters = getParametersAsNVP(request);
	}
}

public RequestRedirector(HttpServletRequest request, String redirectTo, Map headers)
{
	m_parameters = null;
	m_headers = null;
	m_queryString = null;
	m_isMultiPart = false;
	m_valid = true;
	m_method = request.getMethod();
	m_queryString = request.getQueryString();
	m_redirectTo = redirectTo;
	m_headers = headers;
	m_softwareVersion = null;
	if(FileUpload.isMultipartContent(request))
	{
		m_isMultiPart = true;
		m_valid = getMultiPartRequestParameters(request);
	}
	else
	{
		m_parameters = getParametersAsNVP(request);
	}
}

public HttpMethod getMethod()
{
	QCodec qc = new QCodec();
	HttpMethod method;
	if("get".equalsIgnoreCase(m_method))
	{
		method = new GetMethod(m_redirectTo);
		if(m_queryString != null)
		{
			method.setQueryString(m_queryString);
		}
	}
	else if("post".equalsIgnoreCase(m_method))
	{
		if(m_isMultiPart)
		{
			m_log.debug("MultiPart post method");
			MultipartPostMethod multipartPost = new MultipartPostMethod(m_redirectTo);
			if(m_queryString != null)
			{
				multipartPost.setQueryString(m_queryString);
			}
			if(m_parameters != null)
			{
				for(int i = 0; i < m_parameters.length; i++)
				{
					if(m_parametersBeforeFiles.containsKey(m_parameters[i].getName()))
					{
						multipartPost.addParameter(m_parameters[i].getName(), m_parameters[i].getValue());
						m_log.debug("getMethod - add parameter: " + m_parameters[i].getName() + "=" + m_parameters[i].getValue());
					}
				}

			}
			if(m_uploadedFiles != null)
			{
				for(Iterator it = m_uploadedFiles.iterator(); it.hasNext();)
				{
					FileInfo info = (FileInfo) it.next();
					try
					{
						FilePart part;
						m_log.debug("MultiPart post method. File:" + info.toString());
						m_log.debug("MultiPart post method. Software version:" + m_softwareVersion);
						if(m_softwareVersion == null)
						{
							part = new FilePart(info.fieldName, info.filename, info.file, FilePart.DEFAULT_CONTENT_TYPE, "UTF-8");
						}
/*						else if(m_softwareVersion.indexOf("5.") == 0)
						{
							URLCodec coder = new URLCodec("UTF-8");
							String sEnc = qc.encode(info.filename, "UTF-8");
							String sEnc2 = coder.encode(info.filename);
							m_log.debug("MultiPart post method. Encoded1:" + sEnc);
							m_log.debug("MultiPart post method. Encoded2:" + sEnc2);
							String sDec = qc.decode(sEnc);
							String sDec2 = coder.decode(sEnc2);
							m_log.debug("MultiPart post method. Decoded:" + sDec + " -- " + sDec2);
//							part = new FilePart(info.fieldName, sEnc, info.file, FilePart.DEFAULT_CONTENT_TYPE, "UTF-8");
							part = new FilePart(info.fieldName, sEnc, info.file, FilePart.DEFAULT_CONTENT_TYPE, "UTF-8");
						}
*/						else
						{
							String sEnc = qc.encode(info.filename, "UTF-8");
							m_log.debug("MultiPart post method. Encoded1:" + sEnc);
							part = new FilePart(info.fieldName, qc.encode(info.filename, "UTF-8"), info.file, FilePart.DEFAULT_CONTENT_TYPE, "UTF-8");
						}
						multipartPost.addPart(part);
					}
					catch(FileNotFoundException e)
					{
						m_log.error("getMethod - file not found", e);
					}
					catch(EncoderException ee)
					{
						m_log.error("encoder exception", ee);
					}
				}

			}
			if(m_parameters != null)
			{
				for(int i = 0; i < m_parameters.length; i++)
				{
					if(!m_parametersBeforeFiles.containsKey(m_parameters[i].getName()))
					{
						multipartPost.addParameter(m_parameters[i].getName(), m_parameters[i].getValue());
						m_log.debug("getMethod - add parameter: " + m_parameters[i].getName() + "=" + m_parameters[i].getValue());
					}
				}

			}
			method = multipartPost;
		}
		else
		{
			PostMethod post = new PostMethod(m_redirectTo);
			if(m_parameters != null)
			{
				post.setRequestBody(m_parameters);
			}
			if(m_queryString != null)
			{
				post.setQueryString(m_queryString);
			}
			method = post;
		}
	}
	else
	{
		throw new InvalidParameterException("Invalid method: " + m_method);
	}
	if(m_headers != null)
	{
		String header;
		for(Iterator it = m_headers.keySet().iterator(); it.hasNext(); method.setRequestHeader(header, (String) m_headers.get(header)))
		{
			header = (String) it.next();
		}

	}
	return method;
}

private NameValuePair[] getParametersAsNVP(HttpServletRequest request)
{
	Enumeration e = request.getParameterNames();
	List params = new ArrayList(12);
	NameValuePair nvp;
	for(; e.hasMoreElements(); params.add(nvp))
	{
		String name = (String) e.nextElement();
		String value = request.getParameter(name);
		nvp = new NameValuePair(name, value);
		m_log.debug(name + "=" + value);
	}

	NameValuePair nvps[] = new NameValuePair[params.size()];
	for(int i = 0; i < params.size(); i++)
	{
		nvps[i] = (NameValuePair) params.get(i);
	}

	return nvps;
}

private NameValuePair[] getQueryStringForPostMethod()
{
	NameValuePair nvps[] = null;
	if(m_parameters != null)
	{
		for(int i = 0; i < m_parameters.length; i++)
		{
			;
		}
	}
	return null;
}

private boolean getMultiPartRequestParameters(HttpServletRequest request)
{
	boolean valid = false;
	try
	{
		String dir = ApplicationParameters.instance().getUploadDirectoryPath();
		DiskFileUpload upload = new DiskFileUpload();
		upload.setRepositoryPath(dir);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		List params = new ArrayList(12);
		m_uploadedFiles = new ArrayList(1);
		Iterator iter = items.iterator();
		int counter = 0;
		while(iter.hasNext())
		{
			FileItem item = (FileItem) iter.next();
			if(item.isFormField())
			{
				params.add(new NameValuePair(item.getFieldName(), item.getString()));
			}
			else
			{
				String sessionId = request.getSession().getId();
				String fieldName = item.getFieldName();
//				String fileName = item.getName(); // by lexx. For real encoding.
				String fileName = new String(item.getName().getBytes(upload.getHeaderEncoding()), "UTF-8");
				String contentType = item.getContentType();
				boolean isInMemory = item.isInMemory();
				long sizeInBytes = item.getSize();
				File uploadedFile = new File(dir + "/" + sessionId + "_" + counter++);
				item.write(uploadedFile);
				FileInfo fileInfo = new FileInfo(uploadedFile, fileName, uploadedFile.getAbsolutePath(), fieldName);
				m_uploadedFiles.add(fileInfo);
				m_log.debug("file: " + fileInfo);
			}
		}
		m_parameters = new NameValuePair[params.size()];
		for(int i = 0; i < params.size(); i++)
		{
			m_parameters[i] = (NameValuePair) params.get(i);
		}

		valid = true;
	}
	catch(Exception e)
	{
		m_valid = false;
		m_log.error("Cannot process multipart request", e);
		if(m_uploadedFiles != null)
		{
			FileInfo fileInfo;
			for(Iterator it = m_uploadedFiles.iterator(); it.hasNext(); fileInfo.file.delete())
			{
				fileInfo = (FileInfo) it.next();
			}

		}
	}
	return valid;
}

public void cleanup()
{
	if(m_uploadedFiles != null)
	{
		for(Iterator it = m_uploadedFiles.iterator(); it.hasNext();)
		{
			FileInfo info = (FileInfo) it.next();
			if(!info.file.delete())
			{
				m_log.warn("Cannot delete file:" + info.file);
			}
		}

	}
}

private static void testGet() throws Exception
{
	String url = "превед.txt";
	URLCodec coder = new URLCodec("UTF-8");
	String sResponse, sEnc;
	byte[] responseBodyByte;
	String responseBody;

	responseBodyByte = url.getBytes("UTF-8");
	String responseFake = new String(responseBodyByte, "UTF-8");
	sEnc = StringManager.htmlencode(responseFake);
	String sEnc2 = coder.encode(responseFake);
	String sEnc5 = coder.encode(url);

	QCodec qc = new QCodec();
	String sEnc3 = qc.encode(url, "UTF-8");
	String sEnc4 = qc.encode(sEnc, "UTF-8");

	url = "https://files.laplink.com/lsfiles/F:/Music/60's/Beach%20Boys%20-%20Good%20Vibrations.mp3?did=5Ai4KasZ&GetActions=1";
	url = "http://www.google.com/ popo";
	String username = "c4test";
	String password = "ignored";
	String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);
	HttpMethod method = new GetMethod(url);
	method.setRequestHeader("Authorization", authorization);
	HttpClient client = new HttpClient();
	int res = client.executeMethod(method);
	System.out.println("return: " + res);
	System.out.println(method.getResponseBodyAsString());
}

private static void testURI() throws Exception
{
	try
	{
		String url = "http://www.google.com/ popo";
		url = "f:/music/60's/foo bar/toto titi";
		URI uri = new URI(url);
		System.out.println("escapes path: " + uri.getEscapedPath());
		System.out.println("escapes uri: " + uri.getEscapedURI());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

private static void testPost() throws Exception
{
	String url = "https://files.laplink.com/lsfiles//F:/Music/60's?did=5Ai4KasZ&LLMethod=GetNavigatePage";
	url = "https://files.laplink.com/lsfiles//F:/Music/60's";
	url = "https://files.laplink.com";
	HttpMethod method = new PostMethod(url);
	String username = "vtang";
	String password = "ignored";
	String authorization = "Basic " + Base64Encoder.encode(username + ":" + password);
	method.addRequestHeader("Authorization", authorization);
	if(method instanceof PostMethod)
	{
		PostMethod post = (PostMethod) method;
		NameValuePair nvps[] = new NameValuePair[2];
		nvps[0] = new NameValuePair("did", "5Ai4KasZ");
		nvps[1] = new NameValuePair("LLMethod", "GetNavigatePage");
		post.setRequestBody(nvps);
		NameValuePair params[] = post.getParameters();
		System.out.println("method: " + method.getClass().getName() + " " + method.getPath());
		System.out.println("The parameters:");
		for(int i = 0; i < params.length; i++)
		{
			System.out.println("param: " + params[i].getName() + " = " + params[i].getValue());
		}

	}
	HttpClient client = new HttpClient();
	int res = client.executeMethod(method);
	System.out.println("return: " + res);
	java.io.InputStream is = method.getResponseBodyAsStream();
	byte buff[] = new byte[256];
	System.out.println(method.getResponseBodyAsString());
	method.releaseConnection();
}

public static void main(String arhs[])
{
	try
	{
		testGet();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

private static Log m_log;
private static final String POST = "post";
private static final String GET = "get";
private NameValuePair m_parameters[];
private String m_method;
private String m_redirectTo;
private Map m_headers;
private String m_queryString;
private boolean m_isMultiPart;
private List m_uploadedFiles;
private boolean m_valid;
private String m_softwareVersion;
private static HashMap m_parametersBeforeFiles;

static
{
	m_log = LogFactory.getLog(com.infowave.c4p.web.util.RequestRedirector.class);
	m_parametersBeforeFiles = new HashMap();
	m_parametersBeforeFiles.put("overwrite", "");
}
}
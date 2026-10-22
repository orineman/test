package com.infowave.c4p.web.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Map;

import com.infowave.c4p.web.util.*;
import com.infowave.c4p.service.dto.DesktopDTO;

/**
 * This filer is used to download a file from the desktop.
 * The only thing the portal needs to do is to forward the request to the
 * ICM and send the result in the request's response stream.
 * That's why we use a passthru to implement this functionality.
 */
public class DownloadFilter extends PassThruFilter
{
 /** The class's logger */
  private static Log m_log = LogFactory.getLog(DownloadFilter.class);

  private static final String FILE_PREFIX = "x-file";


  /**
   * This method returns the prefix this filter uses.
   * @return
   */
  protected String getPrefix()
  {
    return FILE_PREFIX;
  }

}

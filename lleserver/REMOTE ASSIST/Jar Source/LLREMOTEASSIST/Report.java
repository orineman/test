
package llremoteassist;

import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.CompanyManager;
import com.infowave.c4p.service.delegate.TechloginManager;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.web.util.Notifier;


public class Report
{
	private static final String NEW_UNUSED_REPORT_HEADER1 = "report.newunused.header.1"; // All companies added within last  
	private static final String NEW_UNUSED_REPORT_HEADER2 = "report.newunused.header.2"; // days and no activity.
	private static final String NEW_UNUSED_REPORT_NAME_HEADER = "report.newunused.header.name"; // Name
	private static final String NEW_UNUSED_REPORT_COMPID_HEADER = "report.newunused.header.companyid"; // Company ID
	private static final String NEW_UNUSED_REPORT_DATEADDED_HEADER = "report.newunused.header.dateadded"; // Date Added
	private static final String NEW_UNUSED_REPORT_XREF_HEADER = "report.newunused.header.xref";// XRef
	private static final String NEW_UNUSED_REPORT_TOTCNT_HEADER = "report.newunused.header.totcnt";// Total Count:
	private static final String NEW_UNUSED_REPORT_NO_CO = "report.newunused.noco";// *** NO companies found ***
	private static final int NAME_LEN = 32;
	private static final int COMPID_LEN = 16;
	private static final int DATEADDED_LEN = 16;
	private static final String DAYS_PARAM = "-d";
	private static final String LOCALE_PARAM = "-l";
	private static final String TYPE_PARAM = "-t";

	// report types
	private static final int TYPE_NEW_UNUSED = 1;

	// special cases of reports
	private static final int TYPE_DEFAULT = TYPE_NEW_UNUSED;	// default
	private static final int TYPE_INVALID = TYPE_NEW_UNUSED+1;	// max


    public Report()
    {
    }

	// passing in -d#days -llocale
	public static void main(String [] args)
	{
		Locale locale = new Locale( "en" );
		String days = "14";
		int reportType = TYPE_DEFAULT;
		int idx = -1;
		for( int j=0; j<args.length; j++ ) {
			idx = args[j].indexOf( DAYS_PARAM );
			if( idx != -1 ) {
				try {
					String temp = args[j].substring(idx+DAYS_PARAM.length());
					Integer.parseInt(temp);
					days = temp;
					continue;
				} catch( Exception notused1 ) {}
			}
			idx = args[j].indexOf( LOCALE_PARAM );
			if( idx != -1 ) {
				try {
					String temp = args[j].substring(idx+LOCALE_PARAM.length());
					locale = new Locale( temp );
					continue;
				} catch( Exception notused1 ) {}
			}
			/* TODO: add report type handling when more than one type of report is supported.
			idx = args[j].indexOf( TYPE_PARAM );
			if( idx != -1 ) {
				try {
					// TODO: determine if the type is numeric or named
					// Expand this once we have more than once report that is generated
					String temp = args[j].substring(idx+TYPE_PARAM.length());
					reportType = Integer.parseInt(temp);
					if( reportType <= 0 || reportType >= TYPE_INVALID ) reportType = TYPE_DEFAULT;
				} catch( Exception notused1 ) {}
			}
			*/
		}
		switch( reportType ) {
			case TYPE_NEW_UNUSED:
			default:
				runNewUnusedReport( days, locale );
				break;
		}
	}

	public static void runNewUnusedReport(String days, Locale locale)
	{
		int totalCount = 0;

		StringManager stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE, locale);
		if( stringMgrBasic == null ) {
			stringMgrBasic = StringManager.getManager(WebConstants.DEFAULT_MESSAGES_FILE);
		}

		String msgTxt = stringMgrBasic.getString(NEW_UNUSED_REPORT_HEADER1);
		String msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_HEADER2);
		msgTxt += " " +days+ " ";
		msgTxt += msgTemp;
		msgTxt += "\n";
		msgTxt += "\n";
		msgTxt += "\n";
		msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_NAME_HEADER);
		msgTxt += spacePad(msgTemp, NAME_LEN);
		msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_COMPID_HEADER);
		msgTxt += spacePad(msgTemp, COMPID_LEN);
		msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_DATEADDED_HEADER);
		msgTxt += spacePad(msgTemp, DATEADDED_LEN);
		msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_XREF_HEADER);
		msgTxt += msgTemp;
		msgTxt += "\n";

		try {
			TechloginManager techloginManager = DelegateFactory.getTechloginManager();
			//TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
			CompanyManager companyManager = DelegateFactory.getCompanyManager();
			//List companyList = companyManager.getCompaniesLikeCompanyid("%");
			List companyList = companyManager.getCompaniesActiveRecent(days);
			if (companyList != null)
			{
				int size = companyList.size();
				CompanyDTO companyDto = null;
				String companyId = "";
				String companyName = "";
				String xref = "";
				String createDatetimeStr = "";
				int count = 0;
				for (int i = 0; i < size; i++)
				{
					companyDto = (CompanyDTO)companyList.get(i);
					companyId = companyDto.getCompanyid();
					count = techloginManager.countByCompanyid( companyId );
					if( count == 0 ) {
						totalCount++;
						//List technicians = technicianManager.getTechniciansByCompanyid( companyId );
						//String techname = "";
						//String techemail = "";
						companyName = companyDto.getName();
						createDatetimeStr = getDatetimestr( companyDto.getCreatedatetime(), locale );
						xref = companyDto.getXref();
						msgTxt += spacePad(companyName, NAME_LEN);
						msgTxt += spacePad(companyId, COMPID_LEN);
						msgTxt += spacePad(createDatetimeStr, DATEADDED_LEN);
						msgTxt += xref;
						msgTxt += "\n";
					}
				}
				if( totalCount == 0 ) {
					msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_NO_CO);
					msgTxt += msgTemp;
				}
				msgTemp = stringMgrBasic.getString(NEW_UNUSED_REPORT_TOTCNT_HEADER);
				msgTxt += "\n\n" +msgTemp+ "  " +totalCount+ "\n";
				Notifier.instance().notifyNewUnusedAccounts( msgTxt, locale );

			}
		} catch( ServiceException se ) {}
	}

	private static String spacePad( String text, int count )
	{
		String val = text + pads;
		String paddedVal = val.substring(0, count);
		return paddedVal;
	}

	private static String getDatetimestr(Date datetime, Locale locale) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

		return df.format(datetime);
  }


	private static String pads = "                                                      "; // 64

	private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(llremoteassist.Report.class);
    }



}
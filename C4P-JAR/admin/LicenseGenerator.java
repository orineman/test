package com.infowave.c4p.admin;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.LicenseDAO;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.model.License;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.web.util.Notifier;
import org.apache.commons.cli.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LicenseGenerator
{

public static final String BOX_OPT = "box";
public static final String RC_OPT = "rc";
public static final String RESELLER_OPT = "reseller";
public static final String REG_CODE_OPT = "regcode";
public static final String FIXED_OPT = "fixed";
public static final String SUBSCRIPTION_OPT = "subscription";
public static final String DURATION_OPT = "duration";
public static final String MAX_USES_OPT = "maxuses";
public static final String NB_LICENSES_OPT = "nb";
public static final String HELP_OPT = "help";
public static final String CODE_OPT = "code";
public static final String OUTPUT_OPT = "output";
public static final String EMAIL_OPT = "sendto";
public static final String MSG_OPT = "msg";

private static final int REGCODE = 0;
private static final int FIXED = 1;
private static final int SUBSCRIPTION = 2;

/**
 * the reseller for which we are creating licenses
 */
private Reseller m_reseller;

/**
 * Size of the licenes' code
 */
private int m_licenseCodeSize = 0;

/**
 * The name of the file to write the output. If null we use stdout.
 */
private String m_outputFilename = null;

private int m_duration;
private int m_maxUses;
private int m_nbLicenses;
private String m_code;
private String m_sendto = null;
private int m_licenseType = -1;
private boolean m_bLicenseRC = false;
private boolean m_bLicenseBox = false;

private String m_msgText = null;

private String getLicenseCode(Reseller reseller) throws DataException, IOException
{
	LicenseCodeGenerator codeGenerator = new LicenseCodeGenerator(getLicenseCodeSize());
	LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
	String code = codeGenerator.getCode();
	while(licenseDAO.countLicenses(code, reseller.getId()) != 0)
	{
		code = codeGenerator.getCode();
	}

	return code;
}

private void createLicenses() throws DataException, IOException
{
	LicenseCodeGenerator codeGenerator = new LicenseCodeGenerator(getLicenseCodeSize());
	List licensesCreated = new ArrayList(m_nbLicenses);
	Map codesUsed = new HashMap();
	LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
	while(licensesCreated.size() < m_nbLicenses)
	{
		String code = codeGenerator.getCode();
		// Make sure this code is no taken already
		if(licenseDAO.countLicenses(code, m_reseller.getId()) != 0)
		{
			continue;
		}
		// Make sure we have not previously generated that code
		if(codesUsed.containsKey(code))
		{
			continue;
		}
		// We have a valid code, create the license and add it to our list.
		codesUsed.put(code, null);
		License license = new License();
		license.setCode(code);
		license.setReseller(m_reseller);
		license.setDuration(m_duration);
		license.setMaxUses(m_maxUses);
		license.setRegCode(false);
		license.setRevoked(false);
		license.setRc(m_bLicenseRC ? "Y" : "N");
		license.setLicenseType(m_bLicenseBox ? License.BOXED_TYPE : License.SUBSCRIPTION_TYPE);
		licensesCreated.add(license);
	}
	// now we create the licenses in the database
	licenseDAO.createLicenses(licensesCreated);

	// Generate the output
	PrintWriter out;
	if(m_outputFilename == null)
	{
		out = new PrintWriter(System.out);
	}
	else
	{
		out = new PrintWriter(new FileOutputStream(m_outputFilename));
	}
	out.println("# license info -- type = " + (m_bLicenseRC ? "RC" : "Desktop") + " " + (m_bLicenseBox ? "Boxed Product" : "Online Subscription") + ", duration " + m_duration + " days, maxuses = " + m_maxUses);
	out.println("# license codes created on " + new Date() + ": ");
	for(Iterator it = licensesCreated.iterator(); it.hasNext();)
	{
		License license = (License) it.next();
		out.println(license.getCode());
	}
	out.close();
}


private void createRegCodes() throws DataException, IOException
{
	List licensesCreated = new ArrayList(m_nbLicenses);
	LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();

	for(int i = 0; i < m_nbLicenses; i++)
	{
		License license = new License();
		license.setCode(getLicenseCode(m_reseller));
		license.setReseller(m_reseller);
		license.setDuration(m_duration);
		license.setMaxUses(m_maxUses);
		license.setRegCode(true);
		license.setRevoked(false);
		license.setFirstUseDate(new Date());	// Insisted upon by marketing
		license.setRc(m_bLicenseRC ? "Y" : "N");
		license.setLicenseType(m_bLicenseBox ? License.BOXED_TYPE : License.SUBSCRIPTION_TYPE);
		licensesCreated.add(license);
		// now we create the licenses in the database
		licenseDAO.createLicenses(licensesCreated);
	}
	System.out.println("created " + m_nbLicenses + " registration codes");

	// Generate the output
	PrintWriter out;
	if(m_outputFilename == null)
	{
		out = new PrintWriter(System.out);
	}
	else
	{
		out = new PrintWriter(new FileOutputStream(m_outputFilename));
	}
	out.println("# license info -- type = " + (m_bLicenseRC ? " RC" : " Desktop") + ", corporate" + ", duration " + m_duration + " days, maxuses = " + m_maxUses);
	out.println("# license codes created on " + new Date() + ": ");
	for(Iterator it = licensesCreated.iterator(); it.hasNext();)
	{
		License license = (License) it.next();
		out.println(license.getCode());
	}
	out.close();
}

private int getLicenseCodeSize()
{
	if(m_licenseCodeSize == 0)
	{
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer(m_reseller.getLicenseCodeFormat(), ",");
		while(tokenizer.hasMoreTokens())
		{
			String val = tokenizer.nextToken();
			int intVal = Integer.parseInt(val);
			m_licenseCodeSize += intVal;
		}
	}
	return m_licenseCodeSize;
}

private static void printUsage(String msg)
{
	if(msg != null)
	{
		System.out.println(msg);
	}
	System.out.println("Usage:");

	System.out.println("To display this message:");
	System.out.println(LicenseGenerator.class.getName() + " -" + HELP_OPT);
	System.out.println("To create registration codes:");
	System.out.println(LicenseGenerator.class.getName() + " -" + REG_CODE_OPT + " -" + RESELLER_OPT + " <reseller's name>" + " -" + CODE_OPT + " <code to use>" + " -" + NB_LICENSES_OPT + " <nb Licenses to create>" + " -" + MAX_USES_OPT + " <max number of uses>" + " -" + DURATION_OPT + " <duration>" + " [ -" + OUTPUT_OPT + " <filename>" + " [ -" + EMAIL_OPT + " <comma separated list of email addresses to send output file>] " + " [ -" + MSG_OPT + " <text to add to email message body>] " + "] -" + HELP_OPT);
	System.out.println("To create fixed term licenses:");
	System.out.println(LicenseGenerator.class.getName() + " [ -" + RC_OPT + " -" + BOX_OPT + "] -" + FIXED_OPT + " -" + RESELLER_OPT + " <reseller's name>" + " -" + NB_LICENSES_OPT + " <nb Licenses to create>" + " -" + DURATION_OPT + " <duration>" + " [ -" + OUTPUT_OPT + " <filename>" + " [ -" + EMAIL_OPT + " <comma separated list of email addresses to send output file>] " + " [ -" + MSG_OPT + " <text to add to email message body>] ]");
	System.out.println("To create subscriptions");
	System.out.println(LicenseGenerator.class.getName() + " -" + REG_CODE_OPT + " -" + RESELLER_OPT + " <reseller's name>" + " -" + NB_LICENSES_OPT + " <nb Licenses to create>" + " -" + MAX_USES_OPT + " <max number of uses>" + " -" + DURATION_OPT + " <duration>" + " [ -" + OUTPUT_OPT + " <filename>" + " [ -" + EMAIL_OPT + " <comma separated list of email addresses to send output file>] " + " [ -" + MSG_OPT + " <text to add to email message body>] ]");


}

/**
 * creates the command line options
 *
 * @return
 */
private static Options createOptions()
{
	Options options = new Options();

	options.addOption(new Option(BOX_OPT, false, "boxed license"));
	options.addOption(new Option(RC_OPT, false, "rc license"));
	options.addOption(new Option(FIXED_OPT, false, "fixed license"));
	options.addOption(new Option(REG_CODE_OPT, false, "registration code"));
	options.addOption(new Option(SUBSCRIPTION_OPT, false, "fixed license"));

	options.addOption(RESELLER_OPT, true, "reseller's name");
	options.addOption(NB_LICENSES_OPT, true, "number of licenses to create");
	options.addOption(DURATION_OPT, true, "duration in days");
	options.addOption(MAX_USES_OPT, true, "maximum number of uses");
	options.addOption(OUTPUT_OPT, true, "write the output to that file");
	options.addOption(EMAIL_OPT, true, "send the output file to that email address");
	options.addOption(MSG_OPT, true, "append text to email message body");
	options.addOption(CODE_OPT, true, "registration code");
	options.addOption(HELP_OPT, false, "display a help message");

	return options;
}

private boolean parseCommandLineParameters(String[] args) throws NumberFormatException
{
	CommandLineParser parser = new BasicParser();
	CommandLine line = null;

	try
	{
		line = parser.parse(createOptions(), args);
	}
	catch(ParseException e)
	{
		printUsage(e.getMessage());
		return false;
	}
	if(line.hasOption(HELP_OPT))
	{
		printUsage(null);
		return false;
	}
	// output filename
	if(line.hasOption(OUTPUT_OPT))
	{
		m_outputFilename = line.getOptionValue(OUTPUT_OPT);
		if(line.hasOption(EMAIL_OPT))
		{
			m_sendto = line.getOptionValue(EMAIL_OPT);
		}
		if(line.hasOption(MSG_OPT))
		{
			m_msgText = line.getOptionValue(MSG_OPT);
		}
	}

	// Find the reseller
	ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
	if(!line.hasOption(RESELLER_OPT))
	{
		printUsage("The reseller parameter is required");
		return false;
	}
	try
	{
		m_reseller = resellerDAO.getByCode(line.getOptionValue(RESELLER_OPT));
		if(m_reseller == null)
		{
			printUsage("Invalid reseller: " + line.getOptionValue(RESELLER_OPT));
			return false;
		}
	}
	catch(DataException e)
	{
		printUsage(e.getMessage());
		return false;
	}
	// Number of licenses to create
	if(!line.hasOption("nb"))
	{
		printUsage("nb parameter required");
		return false;
	}
	m_nbLicenses = Integer.parseInt(line.getOptionValue(NB_LICENSES_OPT));
	if(line.hasOption(RC_OPT))
	{
		// RC license
		m_bLicenseRC = true;
	}

	if(line.hasOption(BOX_OPT))
	{
		// boxed license
		m_bLicenseBox = true;
	}

	if(line.hasOption(REG_CODE_OPT))
	{
		// Reg Code
		LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
		m_licenseType = REGCODE;
		// code
		/* We always generate a code
			  if (!line.hasOption(CODE_OPT)) {
				 // regcode not provided so generate one
				try {
					m_code = getLicenseCode(m_reseller);
				} catch( Exception eek ) {
					 printUsage("Unable to create a license code: " + eek.toString() );
					 return false;
				}
			  } else {
				 // regcode provided on command line
				 m_code = line.getOptionValue(CODE_OPT);
			  }
			  if (m_code.length() != getLicenseCodeSize()){
				printUsage("The license code must have " + getLicenseCodeSize() + " characters");
				return false;
			  }
			  */

		// Duration
		if(!line.hasOption(DURATION_OPT))
		{
			printUsage(DURATION_OPT + " parameter required to create a registration code");
			return false;
		}
		m_duration = Integer.parseInt(line.getOptionValue(DURATION_OPT));
		// Max uses
		if(!line.hasOption(MAX_USES_OPT))
		{
			printUsage(MAX_USES_OPT + " parameter required to create a registration code");
			return false;
		}
		m_maxUses = Integer.parseInt(line.getOptionValue(MAX_USES_OPT));
	}
	else if(line.hasOption(FIXED_OPT))
	{
		// Fixed term license
		// ------------------
		m_licenseType = FIXED;
		// Max uses
		if(line.hasOption(MAX_USES_OPT))
		{
			m_maxUses = Integer.parseInt(line.getOptionValue(MAX_USES_OPT));
		}
		else
		{
			// default
			m_maxUses = 1;
		}
		// Duration
		if(!line.hasOption(DURATION_OPT))
		{
			printUsage(DURATION_OPT + " parameter required to create a registration code");
			return false;
		}
		m_duration = Integer.parseInt(line.getOptionValue(DURATION_OPT));
	}
	else if(line.hasOption(SUBSCRIPTION_OPT))
	{
		m_licenseType = SUBSCRIPTION;
		// Max uses
		if(!line.hasOption(MAX_USES_OPT))
		{
			printUsage(MAX_USES_OPT + " parameter required to create a registration code");
			return false;
		}
		m_maxUses = Integer.parseInt(line.getOptionValue(MAX_USES_OPT));
	}
	else
	{
		printUsage("You must specify a license type");
		return false;
	}
	return true;
}

public static void doit(String[] args)
{
	try
	{
		LicenseGenerator lg = new LicenseGenerator();
		if(lg.parseCommandLineParameters(args) == false)
		{
			return;
		}
		if(lg.m_licenseType == REGCODE)
		{
			lg.createRegCodes();
		}
		else
		{
			lg.createLicenses();
		}
		if(lg.m_sendto != null)
		{
			Notifier.instance().notifyLicenseCreation(lg.m_reseller.getName(), lg.m_sendto, lg.m_msgText, new Locale("en"), lg.m_outputFilename);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

public static void main(String[] args)
{
	doit(args);
}

}

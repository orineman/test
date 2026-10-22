package llremoteassist;

public interface LLRAConstants 
{

	public static final String LOGINID = "LLRAID";


	public static final int COMPANYID_MAX	= 64;
	public static final int TECHID_MAX		= 32;
	public static final int TECHCID_MAX		= 16;
	public static final int USERNAME_MAX	= 32;
	public static final int EMAIL_MAX		= 128;
	public static final int PHONE_MAX		= 32;
	public static final int MISC_MAX		= 256;
	public static final int TECHNAME_MAX	= 64;
	public static final int PWD_MIN			= 6;

	public static final int DID_SIZE		= 8;
	public static final int DID_MAX			= DID_SIZE;

  
    public static final String DID_TOKEN	= "did=";
    public static int DID_TOKEN_LEN			= DID_TOKEN.length();
    public static final String CO_TOKEN		= "co=";
    public static int CO_TOKEN_LEN			= CO_TOKEN.length();
    public static final String TECHID_TOKEN = "&tid=";
    public static int TECHID_TOKEN_LEN		= TECHID_TOKEN.length();
    public static final String TCID_TOKEN	= "tcid=";
    public static int TCID_TOKEN_LEN		= TCID_TOKEN.length();
    public static final String NAME_TOKEN	= "&name=";
    public static int NAME_TOKEN_LEN		= NAME_TOKEN.length();

	public static final String GET_DID		= "/HDGetDid";
	public static final String RELEASE_DID	= "/HDEndDid";
	public static final String LOGIN		= "/HDSession";
	public static final String LOGOUT		= "/HDFinal";
	public static final String LOGIN_STATUS	= "/LStatus";
	public static final String STATUS		= "/Status";
	public static final String TECHINFO		= "/HDTechInfo";

	public static final String ERRMSG_DID		= "no did"; 
	public static final String ERRMSG_CO		= "no companyid"; 
	public static final String ERRMSG_TECHID	= "no techid"; 
	public static final String ERRMSG_TCID		= "no techcid"; 
	public static final String ERRMSG_NAME		= "no username"; 

	public static final String EMAIL_TOKEN		= "email=";
    public static int EMAIL_TOKEN_LEN			= EMAIL_TOKEN.length();
	public static final String TECHNAME_TOKEN	= "name=";
    public static int TECHNAME_TOKEN_LEN		= TECHNAME_TOKEN.length();
	public static final String PWD_TOKEN		= "password=";
    public static int PWD_TOKEN_LEN				= PWD_TOKEN.length();
	public static final String PHONE_TOKEN		= "phone=";
    public static int PHONE_TOKEN_LEN			= PHONE_TOKEN.length();
	public static final String MISC_TOKEN		= "misc=";
    public static int MISC_TOKEN_LEN			= MISC_TOKEN.length();
	public static final String TECH_DELIM		= ";";


}
  




package llremoteassist;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class HDHttpServlet
    extends HttpServlet
{

    public HDHttpServlet() 
    {
    }
	
	public String getQueryParam(String query, String tokenName, int tokenLength, String errMsg) throws IckyRequestException
	{
		String param = "";

		int idx, idx2;
		idx = query.indexOf( tokenName );
		if( idx != -1 ) {
			idx += tokenLength;
			idx2 = query.indexOf( "&", idx );
			if( idx2 != -1 ) {
				param = query.substring( idx, idx2 );
			} else {
				param = query.substring( idx );
			}
		} else {
			throw new IckyRequestException( errMsg );
		}

		return param;
	}

	public void validateCompanyid( String companyid ) throws IckyRequestException
	{
		if( companyid.length() == 0 || companyid.length() > LLRAConstants.COMPANYID_MAX ) {
			throw new IckyRequestException( "bad companyid" );
		}
	}

	public void validateTechid( String techid ) throws IckyRequestException
	{
		if( techid.length() == 0 || techid.length() > LLRAConstants.TECHID_MAX ) {
			throw new IckyRequestException( "bad techcid" );
		}
	}

	public void validateTechcid( String techcid ) throws IckyRequestException
	{
		if( techcid.length() == 0 || techcid.length() > LLRAConstants.TECHCID_MAX ) {
			throw new IckyRequestException( "bad techcid" );
		}
	}

	public void validateDid( String did ) throws IckyRequestException
	{
		if( did.length() == 0 || did.length() != LLRAConstants.DID_MAX ) {
			throw new IckyRequestException( "bad did" );
		}
	}

	public void validatePassword( String password ) throws IckyRequestException
	{
		if( password.length() < LLRAConstants.PWD_MIN  ) {
			throw new IckyRequestException( "bad password" );
		}
	}

	public void validateName( String name ) throws IckyRequestException
	{
		if( name.length() > LLRAConstants.USERNAME_MAX ) {
			throw new IckyRequestException( "bad name" );
		}
	}

	public void validateEmail( String data ) throws IckyRequestException
	{
		if( data.length() > LLRAConstants.EMAIL_MAX ) {
			throw new IckyRequestException( "bad email" );
		}
	}

	public void validatePhone( String data ) throws IckyRequestException
	{
		if(  data.length() > LLRAConstants.PHONE_MAX ) {
			throw new IckyRequestException( "bad phone" );
		}
	}

	public void validateMisc( String data ) throws IckyRequestException
	{
		if( data.length() > LLRAConstants.MISC_MAX ) {
			throw new IckyRequestException( "bad misc" );
		}
	}

	public void validateTechname( String data ) throws IckyRequestException
	{
		if( data.length() > LLRAConstants.TECHNAME_MAX ) {
			throw new IckyRequestException( "bad techname" );
		}
	}

	public void setResponseCode( int respCode, HttpServletResponse resp )
	{
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setIntHeader("Expires", -1);
		resp.setStatus(respCode);
	}



}
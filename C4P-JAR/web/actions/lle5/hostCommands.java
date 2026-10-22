package com.infowave.c4p.web.actions.lle5;

import com.infowave.c4p.admin.LicenseCodeGenerator;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.License;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.UserRegistrationDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.actions.UserAction;
import com.infowave.c4p.web.util.LLOnyx;
import com.infowave.c4p.web.util.Notifier;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static com.infowave.c4p.web.util.XmlUtil.*;

/**
 * This classes aggregates the actions for the requesting informations by host from server:
 */
public class hostCommands extends UserAction
{
private static final int BUF_LEN = 1024;


public void testXML()
{
	try
	{
		String sData = "5da9713051fb11304a80130591b2116c04fb268bc2ca8d3635e0f4da72912c0da0dac8b2182eb3b4e0b97482437f1812af33327d814c3e102b5dfcee1e78ac0ffc80f0213e202ca949717b17861a1f7239bf0375a1f10b3db8f55efc973f3011fa4ae7423ef8fe8d1bf667fc037d03cb07f72fa745ef44477aeb8c94588db6dd472d01099984e919bc2e237f0a3427c5dafe49f67a9b1c147f02fb9ea587af267c21372907e29f32233ce9768858ef407e65382736a5b4afea31ee9db099b7feeb61a305f8388992aa33de40061b5bed116037c752e61bd1e77c3a2b57965add2c4e7fde529f9b784581abd43148fd638dc5a7be0a481a11d66c541e1105ba6111f097ea13a9eb58f3d17f6c8c7eb5b8f182216edf2fd3432986a374a5dab40cf312886813d945fc2bb27414d80f2fd44e37946292a052a558a979fa8e9f1e0f3e28d3d284e22a61fd9369feb7f55ad1c22022dff9b1549ac51b0dfbc4935b98de364fec1830b30649aafa4b10a6bbc7402c7983557b70d03e600fc570fe751643d8d8a871e6ef7f7c6802c08fc34e79c3195ea85811289806f7ea4e70fa5866b658eed8dd678ba2ed12c1ca251541c9ef27f464aaf9fb6f874ce3f4dcb5f1df0e923facd86c24181c9ed4e008f7e21bb7d623656f29d9f46570b48b87ebf27fa1ac449e32d19f62336559e4d746005a34b859ad05df674d5c619f381f529d784b434fb6c0886942e1dfd35b4f8a2e08fe30664e8dbe6c01ece17e9a5d21c2bc6a58a4ee8aa1ac97e276fe345ab0107e1a15ed2e79442e75434146e3f9b2113edd6064330acd537c75e5035115a9f6c8e804a7d905be902eb2968c06fae5ceea8deda6735e068b5900bb6bf8c1e933196bedcccee2441d61b38aaaa62b0280ddf30f99025212a6462618b959eee836efda6d39eda4964188ae93bef53335cb8ef22336d2914e8f0eb6ca9dee9c271c889d612aaf259b97b6cd14967271839f1c715de0796139085d6cde94ddf6c66db09860f1edef15dc5e1c68f3551b2fa8235c79b84ed60d74e2bf0862f32d167809d85da3665bbdbf38ab7ffbeaae5f8e8a5ad0874e9c0b701fd58375b5d07a9df3c6d11f61f2616084d64045771cbf92073a0b8335eb18f88020c103817202dc6fce7229246ebb46b47dcce8dd5761c95f4592cad889c827f0f918f1ab3c0fb0607c2e989c10fa1573f7f7e846e741928d871851a5bee41af3859b3ae451910e939d707a89fa0f46ec7796e57999e2c3963195429c84c5f6781074ceace541bf06fbfb8b575776945b9d2a4f5ae9010787d3eecb47acff9d1fb14ceeb07cfecd725d668f8a45b35c04e6e6208944cb3060982aba7202251a342fb0570037caef30a405c70a3058a71c97c72781dce9b4d707b175eb18fcc8bf14650992fae7a41a867627cca091bdf7212ac18799b7b46fb43950c65cabc39018d2812c4c3a9f191722c13b2c820a34b60253f9d23e78fdc45b123b035a8ebd369910bfeb88f74d2749ffa34658f31e8c198f13954dc6047d30e4841f38506e7899a5c820e8e22da4ae0fabe47f47ec296399cb8e2d194f9f0656a1a3c255802e3da84f3f1f8a81d68830b63457cce650bb9fcc3c7ade6ff7e09a9ea76c59f826f4a9350f5b6fa7a3f634079139fb329938ab0d59cc093fb711a4b84ce7d4a77cb792903f117c5e12cdb5971b06e79f4cacf71f2b0da795d3410f2d902e652d0549bcac162a6c4e4693c8b1da1a9489675ea4521c3d204a284162724bf3618136f783565c75992a294f9ba89213b1aee42fafa60f1e7f1c";
        String sUnencData ="31323334353637383930";
        byte[] bRecivedData = fromHexString(sData);
		String sHostKey = "30820122300d06092a864886f70d01010105000382010f003082010a0282010100d92a71fb43d476cc8e30358f23681339133df04df4789fa2b38336ef2d34bee49f1e8b20a81bae249e88af2c0f946026ea386ccf1a926738d1d3205255dcd2aa497204f48828f2ddcabd93f850b9017d15cb6e1c558ed263e1f289fbd2a0ba708d96a953f711faf1481502871b6413f62afa070110abf501cbb525f130d0d6468f5086b1fbfff54fbbc8bbe3258651f063001729bc4ea0e741079e8802428df8b97e5115e203c0b69188133f213ecfcc98b5866a7295a6867c9c35114b55979a3813a5dc7377a941819323b6c6129f93321f9941fcc5036537861c5ff86699ac5e0da06fd323d89ae9959461d91df13ade3a8eec4dac03ac4998a5ad3594fda70203010001";
		String sServerKey = "308204be020100300d06092a864886f70d0101010500048204a8308204a40201000282010100a352a8671c2be8c15978e3ecf2c1eb4e676f75d20599f1b461fe9ccc8ebf698a6ff0523d3b8ef60885c7da5510860525ebdf2d6f40c03ad1614730e7f62657a3a69d1ebc6ef3ff2a701fd503fee5c5176a3856ce1c454a0b4bbd954f600cadc906086a6e15d3057bb4b304a5e59e87a16f62e2b4dc5d700c05d66dd1170a34bf7136118e1e290622a91abad05a983d44eaa250ac6252d0d05b49fb147664461df29d27ac76f7b9d07cfc474dfe45cb12b02690f24491a86302ba675480e97ad4933a1ec0951c16b36cd1faf7111a518e76706e3e112bbbdebc81174a085a44f2806a85ea5569ddadc8275eeabbe2c0d0428fc017a7f24d835e94c78ef91124f10203010001028201003db197937b7ccaab5ed4dfa43dff2a97af52d73ac28ae856af3db29542c6e3633bed97e331804b47e4e11d844c7d93ac4371468463fd393aa67871eced7a865877339accab4cd7f27bf04a52fc126764f4e6db4378426439e81e712c4ea7fb6d982dcda64ff02a7e14401b5d5f6386f0605706343399c81d8287016c9e3b68b8ddda2055b23257cb84752cd917ee3a5ea733d24bd658d6ab89d6c476e4da344ec96e5be8ccecd12ab7af1590fb75c189d95d0307230f4d0b35d7a876e0f5f928d7af23d62ceba02f09ecf1d6c8299430259433680674d76c908689eba3d9bf34a98b655c9b71480372dbf867359224ae7d9eee6c0de69ba9ea63023c0d0792c102818100ce75bb53cb14b79c628c2fd5d011ccb90ebe208a91ec7b1f887f8d091b6dc7501d79e0ad7bdad7a813ffe53d2eae8a0236395e37d285d71750d2e156812574afb73216e53965572fbd828483f1cdcb5254cbb0426ad3016cdd30bf0eb0978ab8d49980366b6049e279cd3e361743d51b0b15b7c952fb28c2890ce93aaaa5fd8d02818100ca8323253e856a7796c0fe3c62539f07af861c877aba7fd620d1f9c0f841f8dbabe2e6caefcdc63dfe3fb5d394fdd369cab8beb39951049a21ce0f95405df532759d70fd648c0ca1521e3e084ceb5b7d03476a57df21480f4302790d53fb72649953893d46577887267bc39aba9cbf1c7a637289d9d6e7b1ec05e5b3a843b1f50281800fad00bc2bff36b1b909cbd8002459d6f3379c60e9137b85b96775ddeb273aaaea92066d25aa48158aef3308a232be67cc962e812e8adaf9fa3908c2f8ab4f60c8181584f206afc8002b98c42be7e4382c180b19ffe265894e8214ff9888e90f7f94e81739afe5ec3c334935f22e444ff9577cccdc7015782fcfbb25a92f514102818100b914066fc866f839995e823bbf853ae7344436189f5288f8b280146886bb0c6bfef85d03050ca3ec0e4c3da9c416023c42587b78fa5ab001097a2085c010dec1a1fa24c4803afa6408ffb8a3af60bdf94fa0da10baccd8e591d155be978d4b2ba3715410db13a004ac681a09741943140cca8c686ddd595d231ef4a22fdac66d02818100a2ffae3a1bd13491301d418fa2309638d211445945c4a2125f09c622d5875ce5d1ca0a1feaaa106a1954077f83c9686ec2e4fc28bd6ece90a58c7244628fc6f7eeb598ee08e0441f65b7d515f2be4576bdc331a876491f342e4064b76cb61194e6303f4bc511dac717d6f79e8ec20bd899a9687d8a0b86767142b2c616228ee0";
        String sServerPubliKey = "30820122300d06092a864886f70d01010105000382010f003082010a0282010100a352a8671c2be8c15978e3ecf2c1eb4e676f75d20599f1b461fe9ccc8ebf698a6ff0523d3b8ef60885c7da5510860525ebdf2d6f40c03ad1614730e7f62657a3a69d1ebc6ef3ff2a701fd503fee5c5176a3856ce1c454a0b4bbd954f600cadc906086a6e15d3057bb4b304a5e59e87a16f62e2b4dc5d700c05d66dd1170a34bf7136118e1e290622a91abad05a983d44eaa250ac6252d0d05b49fb147664461df29d27ac76f7b9d07cfc474dfe45cb12b02690f24491a86302ba675480e97ad4933a1ec0951c16b36cd1faf7111a518e76706e3e112bbbdebc81174a085a44f2806a85ea5569ddadc8275eeabbe2c0d0428fc017a7f24d835e94c78ef91124f10203010001";
        String sServerSentPublicKey="0602000000a40000525341310008000001000100f12411f98ec7945e834df2a717c08f42d0c0e2bbea5e27c8addd6955ea856a80f2445a084a1781bcdebb2b113e6e70768e511a11f7fad16cb3161c95c01e3a93d47ae9805467ba0263a89144f29026b012cb45fe4d47fc7cd0b9f776ac279df21d46647614fb495bd0d05262ac50a2ea443d985ad0ba1aa92206291e8e113671bf340a17d16dd6050c705ddcb4e2626fa1879ee5a504b3b47b05d3156e6a0806c9ad0c604f95bd4b0b4a451cce56386a17c5e5fe03d51f702afff36ebc1e9da6a35726f6e7304761d13ac0406f2ddfeb2505861055dac78508f68e3b3d52f06f8a69bf8ecc9cfe61b4f19905d2756f674eebc1f2ece37859c1e82b1c67a852a3";
        String sTestHostPublicKey="0602000000a40000525341310008000001000100255718120b43e7bbd1ec2591b2660587a78f8b74def67cea5f9b1d8e7c96ef89c3a9cc539cf8ff89b39a9d91d1d4ab079e8a284f9519b3a98e2b6713215447a9d50ca80c7f83dfbf14d1ababe234346c8f4abd429f69fa3002f2a6e35c4a4d2b7ea0d6a9133ec8da4b7582d9640a35744cd2b0ab0a5753ffae4c54cb2218d5a98daf26802ade8a8d0d2a27497f1e1fe299cfbbb8c4f0283e1cc256d0fbd009674595fc5df33992187fa20e0c25c848c6e5dab7e3736c84bc4c4ec08bfcf55e5228b368140d606d934c41bceeb9d08e801442632c5dfe143004597bc229e27928d27cf26fa6f9eb1a5fa2e0ec3967515948504d5d98dc0300fcbaf364e54b9ed4";
        String sTestHostPrivateKey="0602000000240000525341310008000001000100a1192ce4197ccf2a446b1ef36539054744d03b05533358fef38f192fae2643cd013728988bfc9bda6e0ed8aee62c4fcc4162f9442a12bcba04c24c5aca847a7321bc4f63170c0de38140439fb8e40b7890c17cbbf90b8cce82f63e003c03fccfd21982588ecd206bcbce1e6f96af2b243ee595d9432416099fde35e26a1a6880c1b381814de0e236e0d3bdeb83546fb8bf08dd76f8bd8c60f2f4fb0dfa8050bb5b27c1cd025f4eb97de0fb5e6bdd7a7754337e3f8ca73057abf05238da48d03af7fef96ad686ac1a493eb8813e1286dcdd92c6461450249a37669f0311377e9718ad2f56ee538fc61f920615100663157e9ccede867ad045d0e73c228ac450e8";

        //KeyPair keyPair=ClientAuthentication.GenerateKeys();

		RSAPublicKey hostKey = null;
		byte[] bKey1 = hostCommands.fromHexString(sHostKey);
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(bKey1);
			hostKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
		}
		catch(NoSuchAlgorithmException ex)
		{
			hostKey = null;
		}
		catch(InvalidKeySpecException ex)
		{
			hostKey = null;
		}

		RSAPrivateKey privateKey = null;  
		byte[] bKey2 = hostCommands.fromHexString(sServerKey);
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bKey2);
			privateKey = (RSAPrivateKey)keyFactory.generatePrivate(privateKeySpec);
		}
		catch(NoSuchAlgorithmException ex)
		{
			privateKey = null;
		}
		catch(InvalidKeySpecException ex)
		{
			privateKey = null;
		}

        RSAPublicKey publicKey = null;
        byte[] bKey3 = hostCommands.fromHexString(sServerPubliKey);
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(bKey3);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
        }
        catch(NoSuchAlgorithmException ex)
        {
            publicKey = null;
        }
        catch(InvalidKeySpecException ex)
        {
            publicKey = null;
        }

        RSAPublicKey sentPublicKey=CryptKeysConverter.msPublicKeyBlob2RSAPublicKey(fromHexString(sServerSentPublicKey));

        //byte[] encData11=ClientAuthentication.EncryptBuffer(sentPublicKey, fromHexString(sUnencData));
        //byte[] unencData11=ClientAuthentication.DecryptBuffer(privateKey, encData11);


        byte[] encData2=ClientAuthentication.EncryptBuffer(hostKey, fromHexString(sUnencData));

        //byte[] encData11=ClientAuthentication.EncryptBuffer(CryptKeysConverter.msPublicKeyBlob2RSAPublicKey(fromHexString(sTestHostPublicKey)), fromHexString(sUnencData));
        //byte[] unencData11=ClientAuthentication.DecryptBuffer(CryptKeysConverter.msPublicKeyBlob2RSAPublicKey(fromHexString(sTestHostPrivateKey)), encData11);


        //String sKK=toHexString(CryptKeysConverter.RSAPublicKey2msPublicKeyBlob(publicKey, CryptKeysConverter.KeySpecs.AT_KEYEXCHANGE));

        //byte[] unencData4=ClientAuthentication.DecryptBuffer(privateKey, fromHexString(sData));

        //String testKey=toHexString(CryptKeysConverter.RSAPublicKey2msPublicKeyBlob(CryptKeysConverter.msPublicKeyBlob2RSAPublicKey(fromHexString(sTestHostPublicKey)), CryptKeysConverter.KeySpecs.AT_KEYEXCHANGE));

        //RSAPublicKey sentPublicKey=CryptKeysConverter.msPublicKeyBlob2RSAPublicKey(fromHexString(sServerSentPublicKey));


        //byte[] encData=ClientAuthentication.EncryptBuffer(publicKey, fromHexString(sUnencData));
        //byte[] encData1=ClientAuthentication.EncryptBuffer(sentPublicKey, fromHexString(sUnencData));
        //byte[] encData2=ClientAuthentication.EncryptBuffer(publicKey, fromHexString(sUnencData));
//        byte[] encData=ClientAuthentication.EncryptBuffer(publicKey, fromHexString("102030405060"));


        //byte[] unencData=ClientAuthentication.DecryptBuffer(privateKey, encData);
        //byte[] unencData2=ClientAuthentication.DecryptBuffer(privateKey, encData2);
        byte[] unencData1=ClientAuthentication.DecryptBuffer(privateKey, fromHexString(sData));

        m_log.debug("HostCommands. ConnectHost2XML_1. Keys OK.");

		byte[] bResponse = ClientAuthentication.GenerateAuthAnswer(privateKey, hostKey, fromHexString(sData), ClientAuthentication.GetDataToSend());
		if(bResponse == null)
		{
			m_log.error("HostCommands. ConnectHost2XML_1. Decode request fails.");
		}




		String s1 = "0602000000a400005253413100080000010001007fe4dd36abef602f03a4f766029c3ecf879379c1448681242a8b0e2c77afdeb6c89ca6a7ec356a95d11f4fe61fdbe37e5f43aef719f44b1cc5928fdfcb61153d38a8a495afff766b08e33e81e6cd1212e67fdfc944c7e9c0dd2cb2999ccf5ca4ed7adad08b8d0afd9c89cdf757943f919acd7d21f34cd3ad254b190defc09616085864e09b63c8523ddd0354922fedc332e3ef3710d946d1a21a0c051058773b9f6a7ea4ed90b820c0034425f9d4b082822124472266691c901d12ad8ef60142ca71fd2df325c75cce46e883844e37387e30ddb4406bf352d52099cbb7c49c18846bd361aeb638105fc5c94c28ce83b8de2e2ef829686ed1b0e04691774539e9";
		byte[] b1 = fromHexString(s1);


		KeyPair jServerKeys = ClientAuthentication.GenerateKeys();
        //KeyPair jServerKeys = MSKeytoJKey.GenerateKeys();
		RSAPublicKey myKey = (RSAPublicKey)jServerKeys.getPublic();
		byte[] bKey = myKey.getModulus().toByteArray();
		byte[] bKey8 = myKey.getEncoded();
		String sKey = toHexString(bKey2);

		String sErr = Error2XML(ErrorCode.INVALID_USERNAME.toInt(), ErrorCode.INVALID_USERNAME.toString());
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse("d:\\_request3.xml");
		document.normalize();
		NodeList nodes = null;

		nodes = document.getElementsByTagName("UserInfo");
		// Get the list of users
		List userList = new ArrayList(3);
		if(nodes.getLength() > 0)
			nodes = nodes.item(0).getChildNodes();

		for(int i1=0;i1<nodes.getLength();i1++)
		{
			String sID = nodes.item(i1).getNodeName();
			if(nodes.item(i1).getNodeName().equals("ID"))
			{
				String sUser = nodes.item(i1).getTextContent();
				userList.add(sUser);
				m_log.debug("HostCommands. Add User:" + sUser);
			}
		}

		nodes = document.getElementsByTagName("HostInfo");
		String sDid = getXMLNodeValue(nodes, "ID");
		String sOwner = getXMLNodeValue(nodes, "Owner");
	}
	catch(Exception ex)
	{
		System.out.println(ex);
	}

}

private static Log m_log = LogFactory.getLog(hostCommands.class);

/**
 * Executes the business logic.
 *
 * @param mapping	The ActionMapping used to select this instance
 * @param actionForm The optional ActionForm bean for this request (if any)
 * @param request	The HTTP request we are processing
 * @param response   The HTTP response we are creating
 * @return a forward
 * @throws IOException	  if an input/output error occurs
 * @throws ServletException if a servlet exception occurs
 * @throws Exception
 */
public ActionForward executeLogic(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response, List errors) throws Exception
{
	HttpSession session = request.getSession();
	SessionState state = WebUtil.getSessionState(request);
//    state.refresh(request);
//	String sBody2 = getRequestData(request.getInputStream());
//	m_log.debug("+++HostCommandsHostCommands. Begin:" + sBody2);

	String sID = "";
	String sResponse = "";
	Document document = null;
	NodeList nodes = null;
	try
	{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		document = builder.parse(request.getInputStream());
		document.normalizeDocument();
		m_log.debug("HostCommands. Request:" + XML2String(document));

		nodes = document.getElementsByTagName("Command");
		sID = getXMLNodeValue(nodes, "ID");
		m_log.debug("HostCommands. Command:" + sID);
	}
	catch(Exception ex)
	{
		m_log.error("HostCommands. catch:" + ex);
		sResponse = Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
		ActionForward actionForward = null;
		response.setHeader("content-type", "text/xml; charset=utf-8");
		response.setHeader("content-length", Integer.toString(sResponse.length()));
		response.getWriter().write(sResponse);
		response.flushBuffer();
		return actionForward;
	}

	response.setStatus(404);

	try
	{
		if(sID.equals("0"))
		{// First time Host registration.
			sResponse = HostRegistration(document, request);
			m_log.debug("HostCommands. Response:" + sResponse);
            if (sResponse.indexOf("1012") !=-1 || sResponse.indexOf("1015")!=-1) {
                response.setStatus(403);    
            } else if(sResponse.equals("") || sResponse.indexOf("LLEError") >= 0)
				response.setStatus(400);
			else
				response.setStatus(200);
		}
		else if(sID.equals("1"))
		{// initial connection procedure between Host and Server
			String sStage = getXMLNodeValue(nodes, "Stage");
			String sData, sHostVersion, sProtocolVersion, sDid;
			if(sStage.equals("1"))
			{//Stage 1.
				nodes = document.getElementsByTagName("Request");
				sData = getXMLNodeValue(nodes, "Data");
				nodes = document.getElementsByTagName("HostInfo");
				sHostVersion = getXMLNodeValue(nodes, "Version");
				sProtocolVersion = getXMLNodeValue(nodes, "ProtocolVersion");
				sDid = getXMLNodeValue(nodes, "ID");

				sResponse = ConnectHost2XML_1(sData, sDid, sHostVersion, sProtocolVersion, state);
				if(sResponse.equals(""))
					response.setStatus(400);
				else if(sResponse.indexOf("LLEError") >= 0)
					response.setStatus(500);
				else
					response.setStatus(200);
			}
			else if(sStage.equals("2"))
			{//Stage 2.
				nodes = document.getElementsByTagName("HostInfo");
				sDid = getXMLNodeValue(nodes, "ID");

				nodes = document.getElementsByTagName("Response");
				sData = getXMLNodeValue(nodes, "Data");
				sResponse = ConnectHost2XML_2(sData, sDid, state);
				if(sResponse.equals(""))
					response.setStatus(400);
				else if(sResponse.indexOf("LLEError") >= 0)
					response.setStatus(500);
				else
				{
					sResponse = ""; // it's not need in additional words
					Desktop desktop = null;
					DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
					LicenseCodeGenerator lg = new LicenseCodeGenerator(32);
					String ssid = lg.getCode();
					desktop = desktopDAO.getByDid(sDid);
					desktop.setSsid(ssid);
					desktopDAO.update(desktop);
					response.addHeader("SSID", ssid);
//					Cookie cookie = new Cookie("SSID", session.getId());
//					cookie.setPath(request.getContextPath());
//					response.addCookie(cookie);
					m_log.debug("HostCommands. SSID:" + ssid);
					response.setStatus(200);
				}
			}
		}
		else if(sID.equals("3"))
		{// Add Users Having Access to the Host
			nodes = document.getElementsByTagName("HostInfo");
			String sDid = getXMLNodeValue(nodes, "ID");
			String sOwner = getXMLNodeValue(nodes, "Owner");
			m_log.debug("HostCommands. DID:" + sDid + " Owner:" + sOwner);

			nodes = document.getElementsByTagName("UserInfo");
			if(nodes.getLength() > 0)
			{
				nodes = nodes.item(0).getChildNodes();
				// Get the list of users
				List userList = new ArrayList(3);
				for(int i1=0;i1<nodes.getLength();i1++)
				{
					if(nodes.item(i1).getNodeName().equals("ID"))
					{
						String sUser = nodes.item(i1).getTextContent();
						userList.add(sUser);
						m_log.debug("HostCommands. Add User:" + sUser);
					}
				}
				sResponse = AddDesktopAccess(sDid, sOwner, userList);
				if(sResponse.indexOf("LLEError") >= 0)
					response.setStatus(400);
				else
					response.setStatus(200);
			}
			else
				response.setStatus(400);
		}
	}
	catch(Exception ex)
	{
		m_log.error("+++HostCommands. Command handler catch:" + ex);
	}

	ActionForward actionForward = null;
	response.setHeader("content-type", "text/xml; charset=utf-8");
	response.setHeader("content-length", Integer.toString(sResponse.length()));
	response.getWriter().write(sResponse);
	response.flushBuffer();
	return actionForward;
}


private String HostRegistration(Document document, HttpServletRequest request)
{
	String sResponse = "";
	String sUsername = "";
	String sFirstname = "";
	String sLastname = "";
	String sPassword = "";
	String sEmail = "";
	String sSecAnswer = "";
	String sSecQuestion = "";
	String sCountryCode = "";
	String sLicense = "";
	String sLocale = "";
	String sDid = "";
	String sHostname = "";
	String sHostKey = "", sServerKeyForMe = "", sServerKeyForClient = "";
	NodeList nodes = null;
	LLOnyx onyx = new LLOnyx();
	List errors = new ArrayList(3);

	int iState = 0; // create new account

	try
	{
		nodes = document.getElementsByTagName("UserInfo");
		sUsername = getXMLNodeValue(nodes, "ID").toLowerCase();
		m_log.debug("HostCommands. Username:" + sUsername);

		sFirstname = getXMLNodeValue(nodes, "FirstName");
		m_log.debug("HostCommands. Firstname:" + sFirstname);

		sLastname = getXMLNodeValue(nodes, "LastName");
		m_log.debug("HostCommands. Lastname:" + sLastname);

		sPassword = getXMLNodeValue(nodes, "Password");
		m_log.debug("HostCommands. Password:**********");

		sEmail = getXMLNodeValue(nodes, "Email");
		m_log.debug("HostCommands. Email:" + sEmail);

		sSecAnswer = getXMLNodeValue(nodes, "SecretAnswer");
		m_log.debug("HostCommands. SecretAnswer:" + sSecAnswer);

		sSecQuestion = getXMLNodeValue(nodes, "SecretQuestion");
		m_log.debug("HostCommands. SecretQuestion:" + sSecQuestion);

		sCountryCode = getXMLNodeValue(nodes, "Country");
		m_log.debug("HostCommands. Country:" + sCountryCode);

		nodes = document.getElementsByTagName("HostInfo");

		sLocale = getXMLNodeValue(nodes, "Locale");
		m_log.debug("HostCommands. Locale:" + sLocale);

		sHostname = getXMLNodeValue(nodes, "Name");
		m_log.debug("HostCommands. Hostname:" + sHostname);

		sLicense = getXMLNodeValue(nodes, "Serial");
		m_log.debug("HostCommands. Serial:" + sLicense);

		sHostKey = getXMLNodeValue(nodes, "Key");
		m_log.debug("HostCommands. Key:" + sHostKey);

		sDid = getXMLNodeValue(nodes, "ID");
		m_log.debug("HostCommands. DID:" + sDid);

		if(sDid != null && sDid != "")
			iState = 2; // generate new RSA Key for this desktop
		else
		{
			if(sFirstname.equals("") && sLastname.equals(""))
				iState = 1; // assign new desktop to old user
			DesktopManager desktopMgr = DelegateFactory.getDesktopManager();
			sDid = desktopMgr.generateUniqueDid(8);
		}

		if(sHostKey.length() > 0)
		{
			byte[] bHostKeyBlob = fromHexString(sHostKey);
//			m_log.debug("HostCommands. Public Host Key (MS): " + sHostKey);
            RSAPublicKey jHostKey=CryptKeysConverter.msPublicKeyBlob2RSAPublicKey(bHostKeyBlob);
            //RSAPublicKey jHostKey = (RSAPublicKey)MSKeytoJKey.getPublicKey(bHostKeyBlob);
			sHostKey = toHexString(jHostKey.getEncoded());
//			m_log.debug("HostCommands. Public Host Key (RSA): " + sHostKey);

			//KeyPair jServerKeys = MSKeytoJKey.GenerateKeys();
            KeyPair jServerKeys = ClientAuthentication.GenerateKeys();
            RSAPrivateKey myPrivateKey = (RSAPrivateKey)jServerKeys.getPrivate();
			RSAPublicKey myPublicKey = (RSAPublicKey)jServerKeys.getPublic();
			sServerKeyForMe = toHexString(myPrivateKey.getEncoded());
//			m_log.debug("HostCommands. Private Server Key: " + sServerKeyForMe);
//			m_log.debug("HostCommands. Public Server Key (RSA): " + toHexString(myPublicKey.getEncoded()));
			sServerKeyForClient = toHexString(CryptKeysConverter.RSAPublicKey2msPublicKeyBlob(myPublicKey, CryptKeysConverter.KeySpecs.AT_KEYEXCHANGE));
//			m_log.debug("HostCommands. Public Server Key (MS): " + sServerKeyForClient);
		}
		else
		{
			sHostKey = "";
			sServerKeyForMe = "";
			sServerKeyForClient = "";
		}
		
//		m_log.debug("HostCommands. Try to add to DB");
		ActionForward forward = null;
//		CreateAccountForm form = (CreateAccountForm) actionForm;
		HttpSession session = request.getSession();
		SessionState state = WebUtil.getSessionState(request);

		try
		{
			if(iState == 1)
			{ // assign new desktop to old user
//				m_log.debug("HostCommands. Add Desktop To Account.0");
                try {
                    DelegateFactory.getUserManager().addDesktopToAccount(sUsername,
																	 sPassword,
																	 sDid,
																	 sHostname,
																	 sHostKey,
																	 sServerKeyForMe);
                } catch (ServiceException e) {
                    PortalError error = e.getError();
                    MessageResources messageResources = MessageResources.getMessageResources("messages");
                    return Error2XML(error.getCode().toInt(),(messageResources.getMessage(new Locale(request.getLocale().getLanguage()),"error."+error.getCode().getName())!=null?messageResources.getMessage(new Locale(request.getLocale().getLanguage()),"error."+error.getCode().getName()):error.getCode().getName()));
                }
//				m_log.debug("HostCommands. Add Desktop To Account.1");
			}
			else if(iState == 2)
			{ // generate new RSA Key for this desktop
				DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
				ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
				AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();

				try
				{
					Authentication authentication = authenticationDAO.getByUsername(sUsername);
					if(authentication == null)
					{
						m_log.error("HostCommands. Create RSA Key. Invalid Username:" + sUsername);
						PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
						throw new ServiceException(error);
					}
					m_log.debug("HostCommands. Create RSA Key. Username:" + sUsername);
					// Make sure the passwords match
					if(!DelegateFactory.getUserManager().validatePassword(authentication, sPassword))
					{
						m_log.error("HostCommands. Create RSA Key. Invalid password for Username:" + sUsername);
						PortalError error = new PortalError(ErrorCode.INVALID_PASSWORD);
						throw new ServiceException(error);
					}
					m_log.debug("HostCommands. Create RSA Key. DID:" + sDid);
					// Make sure the username is a desktop owner
					Desktop desktop = desktopDAO.getByDid(sDid);
					if(null == desktop)
					{
						m_log.error("HostCommands. Unknown DID:" + sDid);
						PortalError error = new PortalError(ErrorCode.UNKNOWN_DID_DEFINED);
						throw new ServiceException(error);
					}
					String sOwner = desktop.getOwner();
					m_log.debug("HostCommands. Create RSA Key. DID:" + sDid + ". Owner:" + sOwner);
					if(!sOwner.equalsIgnoreCase(sUsername))
					{
						m_log.error("HostCommands. Create RSA Key. Username:" + sUsername + " is not owner (" + sOwner + ") for DID:" + sDid);
						PortalError error = new PortalError(ErrorCode.NOT_OWNER_OF_DESKTOP);
						throw new ServiceException(error);
					}
					String sKey1 = desktop.getHostPublicKey();
					String sKey2 = desktop.getServerPrivateKey();
//					m_log.debug("HostCommands. Create RSA Key. Host Key:" + sKey1 + ". Server Key:" + sKey2);
					if(sKey1 != null && sKey2 != null)
					{
						m_log.error("HostCommands. Create RSA Key. Desktop:" + sDid + " already has a RSA Key");
						PortalError error = new PortalError(ErrorCode.DID_ALREADY_DEFINED);
						throw new ServiceException(error);
					}
					m_log.error("HostCommands. Create RSA Key. Updating DB");
					desktop.setHostPublicKey(sHostKey);
					desktop.setServerPrivateKey(sServerKeyForMe);
					desktopDAO.update(desktop);
				}
				catch(DataException e)
				{
					PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
					throw new ServiceException(error, e);
				}
				return CreateHost2XML(sDid, sServerKeyForClient);
			}
			else
			{ // create new account
				m_log.debug("HostCommands. Try register new user:" + sUsername);
				UserRegistrationDTO info = new UserRegistrationDTO();
				info.setUsername(sUsername);
				info.setFirstname(sFirstname);
				info.setLastname(sLastname);
				info.setPassword(sPassword);
				info.setEmail(sEmail);
				info.setResellerId(state.getReseller().getName());
				info.setSecurityAnswer(sSecAnswer);
				info.setSecurityQuestion(Integer.parseInt(sSecQuestion));
				info.setCountryCode(sCountryCode);
				info.setDid(sDid);
				info.setPcName(sHostname);
				info.setHostKey(sHostKey);
				info.setServerKey(sServerKeyForMe);

				Locale userLocale = null;
				String locale = sLocale;
				if(locale == null || locale.equals(""))
				{
					userLocale = state.getReseller().getDefaultLocale();
				}
				else
				{
					userLocale = new Locale(locale);
				}
				info.setLocale(userLocale);

				DelegateFactory.getUserManager().RegisterUser(info);
				Notifier.instance().notifyAccountCreation(state.getReseller().getName(), sEmail, userLocale);
				m_log.debug("HostCommands. Register User:" + sUsername);
			}
		}
		catch(ServiceException e)
		{
            // Add the error to our list so that the user gets a message.
			errors.add(e.getError());
			// If this error is not a user error, we log it
			ErrorCode code = e.getError().getCode();
			/*if(!code.equals(ErrorCode.USERNAME_ALREADY_EXISTS))
			{*/   //Alexey Ogarkov for error representation on HOST UI
				m_log.error("Could not create account", e);
				MessageResources messageResources = MessageResources.getMessageResources("messages");
                return Error2XML(code.toInt(),(messageResources.getMessage(new Locale(request.getLocale().getLanguage()),"error."+code.getName())!=null?messageResources.getMessage(new Locale(request.getLocale().getLanguage()),"error."+code.getName()):code.getName()));
			/*}*/
		}

//		m_log.debug("HostCommands. Register User.100");   321312
		// if there is a normal license -- set that into the database
		String code = sLicense;
//		String rccode = "";
		UserDTO userDTO = null;
		LicenseManager licenseMgr = null;
		if(code != null && code.length() > 0)
		{
			UserManager userManager = DelegateFactory.getUserManager();
			userDTO = userManager.getUserDTO(sUsername);
			licenseMgr = DelegateFactory.getLicenseManager();
		}

		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		Desktop desktop = desktopDAO.getByDid(sDid);

		if(!((code != null && code.length() > 0) && userDTO != null && licenseMgr != null))
		{
			// update onyx with the account information only if license info isn't getting updated
			// If the onyx update fails, allow the user to continue
			try
			{
				onyx.UpdateUserInfo(sUsername);
			}
			catch(Exception ignored)
			{
				m_log.error("Unable to update ONYX for " + sUsername);
//				return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
			}
		}

		if(code != null && code.length() > 0 && userDTO != null && licenseMgr != null && desktop != null)
		{
			// update onyx with the license information
			// If the onyx update fails, allow the user to continue
			try
			{
				onyx.UpdateLicenseInfo(sUsername, code);
			}
			catch(Exception logged)
			{
				LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
				License license1 = licenseDAO.getSingleByCode(code, state.getReseller().getId());
				if(license1 != null && license1.getLicenseType() == license1.BOXED_TYPE)
				{
					m_log.error("ONYX BOXED UPDATE FAILURE FOR LICENSECODE " + code);
				}
				else
				{
					m_log.error("Unable to update ONYX for " + sUsername + ", code = " + code);
				}
				m_log.error("Cannot update ONYX (3)", logged);
			}

			try
			{
				List freedUpLicenses = licenseMgr.setLicenseCode(userDTO.getId(), desktop.getId(), code, false);
			}
			catch(ServiceException e)
			{
				// Add the error to our list so that the user gets a message.
				errors.add(e.getError());
				// If this error is not a user error, we log it
				ErrorCode ecode = e.getError().getCode();
				if(!ecode.equals(ErrorCode.INVALID_LICENSE_NUMBER))
				{
					m_log.error("Could not set license", e);
				}
//				forward = super.findForward(request, mapping, "success.licenseexception");
				return Error2XML(ecode.toInt(), ecode.toString());
			}
		}
		sResponse = CreateHost2XML(sDid, sServerKeyForClient);
	}
	catch(Exception ex)
	{
		m_log.error("+++HostCommands. catch:" + ex);
		return Error2XML(ex.hashCode(), ex.getMessage());
	}
	return sResponse;
}

public String CreateHost2XML(String sDid, String sServerKey)
{
	String sResponse = "";
	try
	{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.newDocument();
		Element elResponse = document.createElement("LLEResponse");

		Element elHostInfo = document.createElement("HostInfo");
		Element elDID = document.createElement("ID");
		elDID.setTextContent(sDid);
		elHostInfo.appendChild(elDID);
		elResponse.appendChild(elHostInfo);

		Element elServerInfo = document.createElement("ServerInfo");
		Element elKey = document.createElement("Key");
		elKey.setTextContent(sServerKey);
		elServerInfo.appendChild(elKey);
		elResponse.appendChild(elServerInfo);

		document.appendChild(elResponse);
		document.normalizeDocument();
		sResponse = XML2String(document);
	}
	catch(Exception ex)
	{
		m_log.error("+++HostCommands. CreateHost2XML. catch:" + ex);
		return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
	}
	return sResponse;
}


public String ConnectHost2XML_1(String sData, String sDid, String sHostVersion, String sProtocolVersion, SessionState state)
{
	String sResponse = "";
	String sServerData = null;
	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		Desktop desktop = desktopDAO.getByDid(sDid);
		if(desktop == null)
		{
			m_log.error("HostCommands. ConnectHost2XML_1. Requested unknown DID");
			return Error2XML(ErrorCode.UNKNOWN_DID_DEFINED.toInt(), ErrorCode.UNKNOWN_DID_DEFINED.toString());
		}

		DesktopManager desktopMgr = DelegateFactory.getDesktopManager();
		if (sHostVersion != null && sProtocolVersion != null)
			desktopMgr.setSoftwareVersion(sDid, sHostVersion);

		byte[] bRecivedData = fromHexString(sData);
		RSAPrivateKey serverKey = desktop.getRealServerPrivateKey();
		if(null == serverKey)
		{
			m_log.error("HostCommands. ConnectHost2XML_1. Can't get Server Key");
			return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
		}
		RSAPublicKey hostKey = desktop.getRealHostPublicKey();
		if(null == hostKey)
		{
			m_log.error("HostCommands. ConnectHost2XML_1. Can't get Host Key");
			return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
		}
//		m_log.debug("HostCommands. ConnectHost2XML_1. Keys OK.");
		byte[] bRSAbuffer = ClientAuthentication.GetDataToSend();
		state.setRSAbuffer(bRSAbuffer);
		byte[] bResponse = ClientAuthentication.GenerateAuthAnswer(serverKey, hostKey, bRecivedData, bRSAbuffer);
		if(bResponse == null)
		{
			m_log.error("HostCommands. ConnectHost2XML_1. Decode request fails.");
			return Error2XML(ErrorCode.INVALID_PASSWORD.toInt(), ErrorCode.INVALID_PASSWORD.toString());
		}

//		m_log.debug("HostCommands. ConnectHost2XML_1. GenerateAuthAnswer OK.");
		sServerData = toHexString(bResponse);
//		m_log.debug("HostCommands. ConnectHost2XML_1. Generate Answer: " + sServerData);

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.newDocument();
		Element elResponse = document.createElement("LLEResponse");
		Element elConnection = document.createElement("Connection");
/*
		Element elRequest = document.createElement("Request");
		Element elRequestData = document.createElement("Data");
		elRequestData.setTextContent(sData);
		elRequest.appendChild(elRequestData);
		elConnection.appendChild(elRequest);
*/
		Element elResponse2 = document.createElement("Response");
		Element elResponseData = document.createElement("Data");
		elResponseData.setTextContent(sServerData);
		elResponse2.appendChild(elResponseData);
		elConnection.appendChild(elResponse2);

		elResponse.appendChild(elConnection);
		document.appendChild(elResponse);
		document.normalizeDocument();
		sResponse = XML2String(document);
	}
	catch(DataException ex)
	{
		m_log.error("HostCommands. ConnectHost2XML_1. DataException:" + ex);
		return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
	}
	catch(Exception ex)
	{
		m_log.error("+++HostCommands. ConnectHost2XML_1. Exception:" + ex);
		return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
	}
	return sResponse;
}


public String ConnectHost2XML_2(String sData, String sDid, SessionState state)
{
	String sResponse = "";
	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		Desktop desktop = desktopDAO.getByDid(sDid);
		if(desktop == null)
		{
			m_log.error("HostCommands. Invalid DID:" + sDid);
			return Error2XML(ErrorCode.UNKNOWN_DID_DEFINED.toInt(), ErrorCode.UNKNOWN_DID_DEFINED.toString());
		}
		byte[] bRecivedData = fromHexString(sData);
		boolean isHandShakeValid = ClientAuthentication.CheckAuthData(desktop.getRealServerPrivateKey(), bRecivedData, state.getRSAbuffer());
		if(false == isHandShakeValid)
		{
			m_log.error("HostCommands. Handshake was failed. DID:" + sDid);
			return Error2XML(ErrorCode.INVALID_PASSWORD.toInt(), ErrorCode.INVALID_PASSWORD.toString());
		}
		sResponse = "welcome";
		m_log.debug("HostCommands. Handshake succeeded. DID:" + sDid);
	}
	catch(Exception ex)
	{
		m_log.error("+++HostCommands. catch:" + ex);
		return Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
	}
	return sResponse;
}


public String AddDesktopAccess(String sDid, String sOwner, List userList)
{
	String sResponse = "";
	try
	{
		try
		{
			DesktopManager desktopManager = DelegateFactory.getDesktopManager();
			m_log.debug("AddDesktopAccess");
			desktopManager.resetForeignAccounts(sDid, sOwner, userList);
		}
		catch(ServiceException e)
		{
			PortalError error = e.getError();
			if(error.getCode().equals(ErrorCode.NOT_OWNER_OF_DESKTOP))
			{
				m_log.debug(sOwner + " does not own desktop " + sDid);
			}
			else if(error.getCode().equals(ErrorCode.UNKNOWN_DID_DEFINED))
			{
				m_log.debug("unknow did " + sDid);
			}
			else if(error.getCode().equals(ErrorCode.INVALID_USERNAME))
			{
				m_log.debug(sOwner + " at least one username is invalid: " + userList);
			}
			else if(error.getCode().equals(ErrorCode.INVALID_PARAMETERS))
			{
				m_log.debug("Invalid parameters " + sDid + " " + sOwner + " " + userList);
			}
			else
			{
				m_log.debug("could not complete the operation", e);
			}
			return Error2XML(error.getCode().toInt(), error.getCode().toString());
		}
		sResponse = "";
	}
	catch(Exception ex)
	{
		m_log.error("+++ClientCommands. catch:" + ex);
		return "";
	}
	return sResponse;
}

public static String toHexString(byte[] data)
{
	String sRet = "";
	int bytecon = 0;	//to get unsigned byte representation

	if(data == null )
		return null;
	if(data.length == 0)
		return "";

	for(int i = 1; i <= data.length; i++)
	{
		bytecon = data[i - 1] & 0xFF;   // byte-wise AND converts signed byte to unsigned.
		if(bytecon < 16)
		{
			sRet += "0" + Integer.toHexString(bytecon).toLowerCase();   // pad on left if single hex digit.
		}
		else
		{
			sRet += Integer.toHexString(bytecon).toLowerCase();   // pad on left if single hex digit.
		}
	}
	return sRet;
}

public static byte[] fromHexString(String sdata)
{// each byte was converted to 2 hex sign
	byte[] bRet = new byte[sdata.length() / 2];
	String sTemp;
	for(int i = 0; i < sdata.length(); i+=2)
	{
		sTemp = sdata.substring(i, i+2);
		bRet[i/2] = (byte)Integer.parseInt(sTemp, 16);
	}
	return bRet;
}


public static void main(String[] args)
{
	try
	{
		hostCommands command = new hostCommands();
		command.testXML();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


}
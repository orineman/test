package com.infowave.c4p.web.actions.lle5;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Dmitry Malenok
 * Date: 04.07.2007
 * Time: 14:53:22
 * Class containing client authentification functions.
 */
public class ClientAuthentication
{
	private static Log m_log = LogFactory.getLog(hostCommands.class);

	private static final int DEFAULTKEYLENGTH=2048;
    private static final int DATAFIRSTSTAGESIZE=1024;

    public static KeyPair GenerateKeys(){return GenerateKeys(DEFAULTKEYLENGTH);}
    public static KeyPair GenerateKeys(int nKeyLen)
    {
        try
        {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(nKeyLen);
            return generator.generateKeyPair();
        }
        catch(NoSuchAlgorithmException ex)
        {
            return null;
        }
    }

    public static byte[] GetDataToSend()
    {
        byte[] buf=new byte[DATAFIRSTSTAGESIZE];

        Random randGenerator=new Random();
        randGenerator.nextBytes(buf);

        return buf;
    }

    public static byte[] GenerateAuthAnswer(RSAPrivateKey serverPrivateKey, RSAPublicKey hostPublicKey,
                                            byte[] receivedData, byte[] dataToSend)
    {
        byte[] Res=null;
		m_log.debug("GenerateAuthAnswer.0");
        ByteArrayOutputStream baOutStream=new ByteArrayOutputStream();
        DataOutputStream dataStream=new DataOutputStream(baOutStream);

        try
        {
            byte[] decryptedMessage=DecryptBuffer(serverPrivateKey, receivedData);
			m_log.debug("GenerateAuthAnswer. Decrypted Buffer:" + hostCommands.toHexString(decryptedMessage));

            dataStream.write(decryptedMessage);
            dataStream.write(dataToSend);

            dataStream.flush();
            dataStream.close();

            byte[] tempBuf=baOutStream.toByteArray();

            Res=EncryptBuffer(hostPublicKey, tempBuf);
        }
        catch(IOException e)
        {
			m_log.debug("GenerateAuthAnswer. Catch:" + e);

			e.printStackTrace();
		}

        return Res;
    }

    public static boolean CheckAuthData(RSAPrivateKey serverPrivateKey, byte[] receivedData, byte[] dataToSend)
    {
        byte[] decryptedMessage=DecryptBuffer(serverPrivateKey, receivedData);

        return Arrays.equals(decryptedMessage, dataToSend);
    }

    public static byte[] EncryptBuffer(RSAPublicKey key, byte[] data)
    {
        byte[] Res=null;
        ByteArrayOutputStream baOutStream=new ByteArrayOutputStream();
        DataOutputStream dataStream=new DataOutputStream(baOutStream);

        try
        {
            Cipher rsaCipher=Cipher.getInstance("RSA");

            rsaCipher.init(Cipher.ENCRYPT_MODE, key);

            int nKeyLen=rsaCipher.getBlockSize();
            nKeyLen=key.getModulus().bitLength()/Byte.SIZE-11;

            int nCryptedLen=data.length;

            while(nCryptedLen>0)
            {
                int nCryptBufLen=nKeyLen;
                if(nCryptBufLen>nCryptedLen)
                    nCryptBufLen=nCryptedLen;

                byte[] buf=rsaCipher.doFinal(data, data.length-nCryptedLen, nCryptBufLen);

                nCryptedLen-=nCryptBufLen;
				ReverseMemory(buf);

                dataStream.write(buf);
            }

            dataStream.flush();
            dataStream.close();

            return baOutStream.toByteArray();
        }
        catch(BadPaddingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(IllegalBlockSizeException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(NoSuchPaddingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(InvalidKeyException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public static byte[] DecryptBuffer(RSAPrivateKey key, byte[] data)
    {
        byte[] Res=null;
        ByteArrayOutputStream baOutStream=new ByteArrayOutputStream();
        DataOutputStream dataStream=new DataOutputStream(baOutStream);

        try
        {
            Cipher rsaCipher=Cipher.getInstance("RSA");

            rsaCipher.init(Cipher.DECRYPT_MODE, key);

            int nKeyLen=key.getModulus().bitLength()/Byte.SIZE;

            int nCryptedLen=data.length;

            while(nCryptedLen>0)
            {
                int nCryptBufLen=nKeyLen;
                if(nCryptBufLen>nCryptedLen)
                    nCryptBufLen=nCryptedLen;

                byte[] rdata=new byte[nCryptBufLen];

                System.arraycopy(data, data.length-nCryptedLen, rdata, 0, nCryptBufLen);
                ReverseMemory(rdata);

                byte[] buf=rsaCipher.doFinal(rdata);

                nCryptedLen-=nCryptBufLen;

                dataStream.write(buf);
            }

            dataStream.flush();
            dataStream.close();

            return baOutStream.toByteArray();
        }
        catch(BadPaddingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(IllegalBlockSizeException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(NoSuchPaddingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch(InvalidKeyException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    private static void ReverseMemory(byte[] pBuffer)
    {
        byte b;
        int iLength=pBuffer.length;
        for(int i=0; i<iLength/2; i++)
        {
            b=pBuffer[i];
            pBuffer[i]=pBuffer[iLength-i-1];
            pBuffer[iLength-i-1]=b;
        }
    }
}

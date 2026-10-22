package com.infowave.c4p.web.actions.lle5;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.io.*;
import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: Dmitry Malenok
 * Date: 03.07.2007
 * Time: 19:01:33
 * Utility class to convert CryptoAPI PUBLICKEYBLOB file to Java RSAPublicKey object and backward.
 */
public class CryptKeysConverter
{
    private static final byte PUBLICKEYBLOB=0x06;
    private static final byte CUR_BLOB_VERSION=0x02;
    private static final short RESERVED=0x0000;
    private static final int CALG_RSA_KEYX=0x0000a400;
    private static final int CALG_RSA_SIGN=0x00002400;
    private static final String MAGIC="RSA1";     // 0x31415352
    private static final int[] KEYSPECS={0, CALG_RSA_KEYX, CALG_RSA_SIGN};

    public enum KeySpecs
    {
        AT_KEYEXCHANGE(1),
        AT_SIGNATURE(2);

        private int m_nValue;

        public int getValue(){return m_nValue;}

        KeySpecs(int nValue)
        {
            m_nValue=nValue;
        }
    }

    public static RSAPublicKey msPublicKeyBlob2RSAPublicKey(byte[] msPublicKeyBlob)
    {
        DataInputStream dis=null;
        int jint=0; // int to build Java int from little-endian ordered byte data
        int bitlen=0;
        int pubexp=0;

        try
        {
            //------ Read the "BLOBHEADER" fields -------------
            //displayData(msPublicKeyBlob) ;
            ByteArrayInputStream bis=new ByteArrayInputStream(msPublicKeyBlob);
            dis=new DataInputStream(bis);
            if(dis.readByte()!=PUBLICKEYBLOB||dis.readByte()!=CUR_BLOB_VERSION||dis.readShort()!=RESERVED)
                return null;

            for(int i=0; i<4; i++)
                jint+=dis.readUnsignedByte()*(int)Math.pow(256, i);

            if(jint!=CALG_RSA_KEYX&&jint!=CALG_RSA_SIGN)
                return null;

            //------ Read the RSAPUBKEY struct members ---------
            StringBuffer magic=new StringBuffer(4);
            for(int i=1; i<=4; i++)
                magic.append((char)dis.readByte());

            if(!magic.toString().equals(MAGIC))
                return null;

            for(int i=0; i<4; i++)
                bitlen+=dis.readUnsignedByte()*(int)Math.pow(256, i);

            for(int i=0; i<4; i++)
                pubexp+=dis.readUnsignedByte()*(int)Math.pow(256, i);

            //----- Finally, get the modulus data, and reverse bytes to get big-endian value --------
            byte[] modulus=new byte[bitlen/8]; //should be this many bytes left
            int modbytes=dis.read(modulus);
            if(modbytes!=(bitlen/8))
                return null;

            ReverseMemory(modulus);  //reverse bytes to put in big-endian order

            //----- Create the PublicKey from modulus and public exponent --------
            RSAPublicKeySpec pubKeySpec=new RSAPublicKeySpec(new BigInteger(1, modulus), BigInteger.valueOf(pubexp));
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            return (RSAPublicKey)keyFactory.generatePublic(pubKeySpec);
        }
        catch(Exception exc)
        {
            return null;
        }
        finally
        {
            try
            {
                dis.close();
            }
            catch(Exception exc)
            {
                  ;
            }
        }
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

    public static byte[] RSAPublicKey2msPublicKeyBlob(RSAPublicKey pubKey, KeySpecs keySpec)
    {
        BigInteger mod=pubKey.getModulus();
        byte[] modulus=mod.toByteArray();
        int bytelen=modulus.length;

        if(modulus[0]==0)
            bytelen--;

        int bitlen=bytelen*Byte.SIZE;

        ByteArrayOutputStream baOutStream=new ByteArrayOutputStream();
        DataOutputStream dataStream=new DataOutputStream(baOutStream);
        byte[] res=null;

        try
        {
            dataStream.write(PUBLICKEYBLOB);
            dataStream.write(CUR_BLOB_VERSION);
            dataStream.writeShort(RESERVED);
            writeLEInt(KEYSPECS[keySpec.getValue()], dataStream);
            dataStream.writeBytes(MAGIC);
            writeLEInt(bitlen, dataStream);
            int pubExp=Integer.parseInt(pubKey.getPublicExponent().toString());
            writeLEInt(pubExp, dataStream);

            ReverseMemory(modulus);

            dataStream.write(modulus, 0, bytelen);

            dataStream.flush();
            dataStream.close();

            res=baOutStream.toByteArray();            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return res;
    }

    private static void writeLEInt(int i, OutputStream out) throws IOException
    {
        out.write(i&0xFF);
        out.write((i>>>8)&0xFF);
        out.write((i>>>16)&0xFF);
        out.write((i>>>24)&0xFF);
    }
}

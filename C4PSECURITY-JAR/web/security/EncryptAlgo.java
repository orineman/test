package com.infowave.c4p.web.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 23.10.2007
 * Time: 18:27:53
 * To change this template use File | Settings | File Templates.
 */
public final class EncryptAlgo {

    private static Log m_log;

    static {
        m_log = LogFactory.getLog(com.infowave.c4p.web.security.EncryptAlgo.class);
    }

    private static final String ALGORITHM = "AES";

    public byte[] encrypt(byte[] string, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, IOException, ShortBufferException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return this.crypt(string, cipher);
    }

    public byte[] decrypt(byte[] string, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, IOException, ShortBufferException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return this.crypt(string, cipher);
    }

    /**
     * @param string the string to encode.
     * @return a hexidecimal character sequence representing the AES
     */
    public byte[] crypt(byte[] string, Cipher cipher) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        return cipher.doFinal(string);
/*
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);

        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;

        ByteArrayInputStream in = new ByteArrayInputStream(string);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLenght = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLenght);
            } else more = false;
        }

        if (inLength > 0) {
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        }

        out.write(outBytes);

*//*
String res = new String(out.toByteArray());

        return res;
*//*

        return out.toByteArray();*/

    }

    public SecretKey genKey(byte[] seed) throws NoSuchAlgorithmException {
        KeyGenerator gen = KeyGenerator.getInstance(this.ALGORITHM);
        SecureRandom random = new SecureRandom(seed);
        gen.init(random);
        return gen.generateKey();
/*
        KeyPair keyPair = pairgen.generateKeyPair();
        m_log.debug("private key:"+keyPair.getPrivate());
        m_log.debug("public key:"+keyPair.getPublic());
        return keyPair;*/
    }

    /**
     * Convenience method to convert a byte array to a hex string.
     *
     * @param bs the byte[] to convert
     * @return String the converted byte[]
     */
    public String toHexString(byte bs[]) {
        StringBuffer ret = new StringBuffer(bs.length);
        for (int i = 0; i < bs.length; i++) {
            String hex = Integer.toHexString(0x0100 + (bs[i] & 0x00FF)).substring(1);
            ret.append((hex.length() < 2 ? "0" : "") + hex);
        }
        return ret.toString();
    }

    /**
     * Retrieves a byte sequence representing the HEX String of the
     * specified byte sequence.
     *
     * @param hex the String to reverce
     * @return byte[] from HEX String
     */
    public byte[] toBinArray(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }

    public static void main(String[] arg) throws NoSuchAlgorithmException, IllegalBlockSizeException, IOException, InvalidKeyException, ShortBufferException, NoSuchPaddingException, BadPaddingException {
        for (int i = 0; i < 100; i++) {
            com.infowave.c4p.web.security.EncryptAlgo algo = new com.infowave.c4p.web.security.EncryptAlgo();
            String text = "alexog+tolstyak";
            byte[] textBytes = text.getBytes();
            byte[] seed = SecureRandom.getSeed(64);
            SecretKey kp = algo.genKey(seed);

            byte[] enc = algo.encrypt(textBytes, kp);
            byte[] finalEnc = new byte[seed.length+enc.length];
            System.arraycopy(seed, 0, finalEnc, 0, seed.length);
            System.arraycopy(enc, 0, finalEnc, seed.length, enc.length);
            String encStr = URLEncoder.encode(new String(finalEnc, "ISO-8859-1"), "UTF-8");
            System.out.println(new String(enc));
            System.out.println(encStr);


            byte[] bsEnc = URLDecoder.decode(encStr, "UTF-8").getBytes("ISO-8859-1");
            System.out.println(new String(bsEnc));
            System.out.println();

            byte[] bsSeed = new byte[64];
            System.arraycopy(bsEnc, 0, bsSeed, 0, 64);
            byte[] bsData = new byte[bsEnc.length-64];
            System.arraycopy(bsEnc, 64, bsData, 0, bsEnc.length-64);

            System.out.println(new String(kp.getEncoded()));
            String hexString = algo.toHexString(kp.getEncoded());
            System.out.println(hexString);
            byte[] hexByte = algo.toBinArray(hexString);
            System.out.println(new String(hexByte));

            SecretKey hexKP = algo.genKey(bsSeed);
            System.out.println();
            byte[] res = algo.decrypt(bsData, hexKP);
            System.out.println(new String(res));
            System.out.println();
            System.out.println("________________________________________________________");
            if (new String(res).equals(text)) {
                System.out.println("OK!!!");
            } else {
                System.out.println("FALSE!!!");
                return;
            }
            System.out.println("________________________________________________________");
            System.out.println("________________________________________________________");

        }
    }
}
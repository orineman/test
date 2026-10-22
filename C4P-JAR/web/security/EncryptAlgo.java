package com.infowave.c4p.web.security;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;


/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 23.10.2007
 * Time: 18:27:53
 * To change this template use File | Settings | File Templates.
 */
public final class EncryptAlgo {

    /** Field keyBytes  */
    public static final byte[] keyBytes = new byte[]{0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd,
            (byte) 0xef};
    /** Field ivBytes  */
    public static final byte[] ivBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01};

    private Cipher cipher;
    private SecretKeySpec key;
    private IvParameterSpec ivSpec;
    /**
     * Constructor EncryptAlgo creates a new EncryptAlgo instance.
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.NoSuchProviderException
     * @throws javax.crypto.NoSuchPaddingException
     */
    public EncryptAlgo() throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        key = new SecretKeySpec(keyBytes, "DES");
        ivSpec = new IvParameterSpec(ivBytes);
        cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");

    }

    /**
     * Method encryptCookieString ...
     *
     * @param s of type String
     * @return String
     * @throws InvalidKeyException when
     * @throws InvalidAlgorithmParameterException when
     * @throws ShortBufferException when
     * @throws IllegalBlockSizeException when
     * @throws BadPaddingException when
     */
    public String encryptCookieString(String s) throws InvalidKeyException,
                                                       InvalidAlgorithmParameterException,
                                                       ShortBufferException,
                                                       IllegalBlockSizeException,
                                                       BadPaddingException {

        byte[] input = s.getBytes();

        System.out.println("input : " + new String(input));

        // encryption pass
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);
        System.out.println("cipher: " + new String(cipherText) + " bytes: " + ctLength);
        byte[] ctLengthByte = intToByteArray(ctLength);

        byte[] finalB = new byte[cipherText.length +ctLengthByte.length];

        System.arraycopy(ctLengthByte, 0, finalB, 0, ctLengthByte.length);
        System.arraycopy(cipherText, 0, finalB, ctLengthByte.length, cipherText.length);

        System.out.println("end user cookie byte array:" + new String(finalB));

        String cookie_text = toHexString(finalB);

        System.out.println("Cokkie text "+cookie_text);

        return cookie_text;
    }

    /**
     * Method decryptCookieString ...
     *
     * @param s of type String
     * @return String
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.BadPaddingException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.ShortBufferException
     */
    public String decryptCookieString(String s) throws InvalidKeyException,
                                                       InvalidAlgorithmParameterException,
                                                       ShortBufferException,
                                                       IllegalBlockSizeException,
                                                       BadPaddingException {

        byte[] cookieByte =toBinArray(s);

        System.out.println("end user cookie byte array:" + s);

        byte[] cookieCtLength = new byte[4];

        System.arraycopy(cookieByte,0,cookieCtLength,0,4);

        byte[] cookieUserText = new byte[cookieByte.length-4];

        System.arraycopy(cookieByte,4,cookieUserText,0,cookieUserText.length);

        int coInt =  byteArrayToInt(cookieCtLength);

        System.out.println("cioher: " + new String(cookieUserText) + " bytes: " + coInt);

        // decryption pass
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] plainText = new byte[cipher.getOutputSize(coInt)];
        int ptLength = cipher.update(cookieUserText, 0, coInt, plainText, 0);
        ptLength += cipher.doFinal(plainText, ptLength);
        byte[] result = new byte[ptLength];
        System.arraycopy(plainText,0,result,0,ptLength);
        System.out.println("plain : " + new String(result) + " bytes: " + ptLength);

        return new String(result);
    }

    /**
     * Convenience method to convert a byte array to a hex string.
     *
     * @param bs the byte[] to convert
     * @return String the converted byte[]
     */
    public static String toHexString(byte bs[]) {
        StringBuffer ret = new StringBuffer(bs.length);
        for (byte b : bs) {
            String hex = Integer.toHexString(0x0100 + (b & 0x00FF)).substring(1);
            ret.append(hex.length() < 2 ? "0" : "").append(hex);
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
    public static byte[] toBinArray(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }

    /**
     * Method byteArrayToInt ...
     *
     * @param b of type byte[]
     * @return int
     */
    public static int byteArrayToInt(byte[] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }


    /**
     * Method intToByteArray ...
     *
     * @param value of type int
     * @return byte[]
     */
    public static byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }


    /**
     * Method main ...
     *
     * @param arg of type String[]
     * @throws NoSuchAlgorithmException when
     * @throws IllegalBlockSizeException when
     * @throws IOException when
     * @throws InvalidKeyException when
     * @throws ShortBufferException when
     * @throws NoSuchPaddingException when
     * @throws BadPaddingException when
     * @throws NoSuchProviderException when
     * @throws InvalidAlgorithmParameterException when
     */
    public static void main(String[] arg) throws NoSuchAlgorithmException, IllegalBlockSizeException, IOException, InvalidKeyException, ShortBufferException, NoSuchPaddingException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException {
        EncryptAlgo algo = new EncryptAlgo();

        String cookie = algo.encryptCookieString("aasdasdaaasdad");
        System.out.println("Result:"+algo.decryptCookieString(cookie));
    }
}

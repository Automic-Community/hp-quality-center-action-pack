/**
 * 
 */
package com.automic.hpqc.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.automic.hpqc.exception.AutomicRuntimeException;

/**
 * @author sumitsamson
 * 
 */
public final class AESEncryptDecrypt {

    private static final byte[] KEY = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63

    , 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79

    };

    private AESEncryptDecrypt() {
    }

    public static String encrypt(String strToEncrypt) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            strToEncrypt = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new AutomicRuntimeException("Error ocurred during encryption", e);
        }
        return strToEncrypt;
    }

    public static String decrypt(String strToDecrypt) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            strToDecrypt = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new AutomicRuntimeException("Error ocurred during decryption", e);
        }
        return strToDecrypt;
    }

}

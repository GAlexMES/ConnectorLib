package de.szut.dqi12.cheftrainer.connectorlib.cipher;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * The CipherFactory should be used to easy encrypt/decrypt Strings with the
 * given algorithm and key.
 * 
 * @author Alexander Brennecke
 *
 */
@SuppressWarnings("restriction")
public class CipherFactory {

	private Key key = null;
	private String algorithm = null;

	private final static String RSA_ALGORITHM = "RSA";
	private final static String AES_ALGORITHM = "AES";

	private byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private IvParameterSpec ivspec = new IvParameterSpec(iv);

	/**
	 * Constructer
	 * 
	 * @param k
	 *            key for the decode/encode
	 * @param algorithm
	 *            chosen algorithm (must be RSA or AES)
	 */
	public CipherFactory(Key k, String algorithm) {
		this.key = k;
		this.algorithm = algorithm;
	}

	/**
	 * generates an encrypted output stream with the defined key and algorithm
	 * 
	 * @param os
	 *            the output stream that should be encrypted
	 * @return and encrypted output stream
	 * @throws Exception 
	 */
	public OutputStream encryptOutputStream(OutputStream os) throws Exception {
		// encode stream with RSA
		Cipher cipher;
		cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		os = new CipherOutputStream(os, cipher);
		return os;
	}

	/**
	 * Generates an decrypted InputStream with the defined key and algorithm
	 * 
	 * @param is
	 *            the InputStream, that should be decrypted
	 * @return an decrypted InputStream
	 * @throws Exception 
	 */
	public InputStream decryptInputStream(InputStream is) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		is = new CipherInputStream(is, cipher);
		return is;
	}

	/**
	 * Encryptes a single string with the defined algorithm and key
	 * 
	 * @param text
	 *            the text, that should be encrypted
	 * @return the input paramater encrypted
	 * @throws Exception 
	 */
	public String encrypt(String text) throws Exception {
		Cipher cipher;
		if (algorithm.equals(RSA_ALGORITHM)) {
			cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} else if (algorithm.equals(AES_ALGORITHM)) {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
		} else {
			throw new Exception("Invalid Algorithmus");
		}

		byte[] encrypted = cipher.doFinal(text.getBytes());

		BASE64Encoder myEncoder = new BASE64Encoder();
		String encodedText = myEncoder.encode(encrypted);

		return encodedText;
	}

	/**
	 * Decrypts an single String with the defined algorithm and key
	 * 
	 * @param encodedString
	 *            the String, that should be decrypted
	 * @return the decrypted string og the given parameter
	 * @throws Exception
	 */
	public String decrypt(String encodedString) throws Exception {

		// BASE64 String to Byte-Array
		BASE64Decoder myDecoder = new BASE64Decoder();
		byte[] crypted = myDecoder.decodeBuffer(encodedString);

		// decode
		Cipher cipher;
		if (algorithm.equals(RSA_ALGORITHM)) {
			cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
		} else if (algorithm.equals(AES_ALGORITHM)) {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
		} else {
			throw new Exception("Invalid Algorithmus");
		}

		byte[] cipherData = cipher.doFinal(crypted);
		return new String(cipherData);
	}

	/**
	 * This function creates a MD5 hash for the given String
	 * @param message the String that should be hashed
	 * @return the hashed String
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest mg;
		try {
			mg = MessageDigest.getInstance("MD5");
			byte[] passwordByte = message.getBytes("UTF-8");
			byte[] paswordHashByte = mg.digest(passwordByte);
			return new String(paswordHashByte, StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
	}

	// GETTER AND SETTE

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
}

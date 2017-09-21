package com.example.crudexample.utils.helper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.crudexample.exception.OperationFailedException;
import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.exception.helper.ResponseCode;
import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.constant.AppConstant;

public class Encryption {
	private static Encryption mInstance;

	private static Cipher cipher;
	private final SecretKeySpec key;
	private final AlgorithmParameterSpec spec;

	private final String errorCodePrefix = APIGroupCode.GENERIC_TECHNICAL.getGroupCode()
			+ APIGroupCode.GENERIC_TECHNICAL.getApiCode() + AppConstant.API_CODE_ERROR;

	/**
	 * Constructor
	 * 
	 * @param encKey
	 */
	private Encryption(final String encKey) {
		// hash password with SHA-256 and crop the output to 128-bit for key
		MessageDigest digest;

		try {
			digest = MessageDigest.getInstance("SHA-256");

			digest.update(encKey.getBytes("UTF-8"));

			byte[] keyBytes = new byte[32];
			System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);

			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			key = new SecretKeySpec(keyBytes, "AES");

			spec = getIV();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException e) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
					ResponseCode.INVALID_ENCRYPTION, e.getMessage());

			throw new OperationFailedException(exceptionDetail);
		}
	}

	public static Encryption getInstance(final String encKey) {
		if (mInstance == null || cipher == null) {
			mInstance = new Encryption(encKey);
		}

		return mInstance;
	}

	/**
	 * Method to generate IV
	 * 
	 * @return IV
	 */
	private AlgorithmParameterSpec getIV() {
		byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00 };

		IvParameterSpec ivParameterSpec;
		ivParameterSpec = new IvParameterSpec(ivBytes);

		return ivParameterSpec;
	}

	/**
	 * Encrypt input string
	 * 
	 * @param plainText
	 * @return encrypted string
	 */
	public String encrypt(String plainText) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, spec);

			byte[] encrypted;
			encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));

			return new String(Base64.encodeBase64(encrypted), "UTF-8");
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
					ResponseCode.INVALID_ENCRYPTION, e.getMessage());

			throw new OperationFailedException(exceptionDetail);
		}
	}

	/**
	 * Decrypt encrypted string
	 * 
	 * @param cryptedText
	 * @return decrypted string
	 */
	public String decrypt(String cryptedText) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, spec);
			byte[] bytes = Base64.decodeBase64(cryptedText);
			byte[] decrypted;
			decrypted = cipher.doFinal(bytes);

			String decryptedText;
			decryptedText = new String(decrypted, "UTF-8");

			return decryptedText;
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
					ResponseCode.INVALID_ENCRYPTION, e.getMessage());

			throw new OperationFailedException(exceptionDetail);
		}
	}
}

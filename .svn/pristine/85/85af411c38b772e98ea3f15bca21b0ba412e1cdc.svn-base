package y1j2x34.utils;

import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.DigestInputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * @author yangjianxin
 */
public class DigestUtils {
	private static final String KEY_MD5 = "MD5";
	private static final String KEY_SHA = "SHA-1";
	private static final String KEY_MAC = "HmacMD5";
	private static MessageDigest create(String type){
		try {
			return MessageDigest.getInstance(type);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String md5(String text){
		byte[] digest = create(KEY_MD5).digest(text.getBytes());
		return byteToHex(digest);
	}
	/**
	 * 可以用于计算文件和其它任何输入流的md5值
	 * @param input
	 * @return
	 */
	public static String md5(InputStream input){
		return md5(input, 1024, null);
	}
	public static interface CallBack{
		void call(byte[] buff);
	}
	public static String md5(InputStream input,int bufSize,CallBack cb){
		if(bufSize <= 0)
			throw new IllegalArgumentException("the value of 'bufSize' must be greater then 0:"+bufSize);
		if(cb == null){
			cb = new CallBack() {
				public void call(byte[] buff) {}
			};
		}
		try {
			MessageDigest mdigest = create(KEY_MD5);
			DigestInputStream digestInput = new DigestInputStream(input, mdigest);
			try {
				byte[] buff = new byte[bufSize];
				while(digestInput.read(buff) > 0){
					mdigest = digestInput.getMessageDigest();
					cb.call(buff);
				}
				return byteToHex(mdigest.digest());
			} finally{
				digestInput.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sha1(byte[] data){
		byte[] digest = create(KEY_SHA).digest(data);
		return byteToHex(digest);
	}
	public static String sha1(String text){
		return sha1(text.getBytes());
	}
	/**
	 * 初始化Mac密钥
	 * @return
	 * @throws Exception
	 */
	/*public static String initMacKey() throws Exception{
		KeyGenerator key = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secret = key.generateKey();
		
		return base64Encode(secret.getEncoded());
	}*/
	/**
	 * hmac加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	/*public static byte[] hmac(byte[] data,String key) throws Exception{
		SecretKey secretkey = new SecretKeySpec(base64Encode(key).getBytes(), KEY_MAC);
		Mac mac = Mac.getInstance(secretkey.getAlgorithm());
		mac.init(secretkey);
		return mac.doFinal();
	}
	public static String base64Encode(byte[] data){
		return new BASE64Encoder().encode(data);
	}
	public static String base64Encode(String text){
		return base64Encode(text.getBytes());
	}
	public static byte[] base64Decode(byte[] data){
		try {
			return new BASE64Decoder().decodeBuffer(new ByteArrayInputStream(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String base64Decode(String text){
		return new String(base64Decode(text.getBytes()));
	}*/
	
	/**
	 *  将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @param pos 起点
	 * @param len 长度
	 * @return
	 */
	public static String byteToHex(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	private final static char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private final static Map<Character,Integer> idxMap = new HashMap<Character,Integer>();
	static{
		for(int i=0;i<Digit.length;i++){
			idxMap.put(Digit[i], i);
		}
	}
	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
	/**
	 * 将十六进制字符串转为字节数组，
	 * @param hexString
	 * @return
	 */
	public static byte[] hexTobytes(String hexString){
		char[] cs = hexString.toUpperCase().toCharArray();
		byte[] rs = new byte[cs.length >> 1];
		for(int i=0;i<cs.length;i+=2){
			char c1 = cs[i];
			char c2 = cs[i+1];
			Integer i1 = idxMap.get(c1);
			if(i1 == null){
				throw new IllegalArgumentException(c1+"");
			}
			Integer i2 = idxMap.get(c2);
			if(i2 == null){
				throw new IllegalArgumentException(c2+"");
			}
			
			byte b = (byte)((i1<<4) | i2);
			rs[i>>1] = b;
		}
		return rs;
	}
	/**
	 * 加密字符串
	 * @param b
	 * @return
	 */
	/*public static String x(String b){
		char[] b64 = base64Encode(b).toCharArray();
		for(int i=0;i<b64.length;i++){
			b64[i] = (char)(b64[i]&13 + 32);
		}
		return sha1(new String(b64));
	}*/
	/**
	 * 随机字符串
	 * @deprecated 功能未完美实现，暂时不可用
	 * <hr/>
	 * @param format 格式 <br/>
	 * 	<ul>例：
	 *  <li> 范围：<p>[a-z] 表示在a-z的范围内生成	,生成 16进制："[0-9a-f]"</p></li>
	 *  <li> 格式化：<p>字符串(%s{length})，整数(%d{length}),小数(%f{total,decimalLength}),数字字母下划线(%w{length}),任意字符(.或者空)，与%w相反%W{length}</p></li>
	 *  </ul>
	 *  456as_asdaszsdsa_4523->[0-9a-f]
	 * @see RandStr
	 * @see RandStr#random(String)
	 * @return
	 */
	public static String random(String format){
		
		return null;
	}
	/**
	 * 对称加密算法
	 * @see SymDes
	 * @see SymDESede
	 * @see SymAES
	 * @see SymIDEA
	 * @see SymPBE
	 * @author y1j2x34
	 *
	 */
	public static abstract class SymmetricEncryption{
		protected String keyAlgorithm;
		protected String cipherAlgorithm;
		
		protected Key key;
		/**
		 * @param key 加密密钥
		 * @param keyAlgorithm 算法名称
		 * @param cipherAlgorithm 算法名/加密模式/填充模式
		 * @throws RuntimeException 还原密钥异常时抛出
		 */
		public SymmetricEncryption(byte[] key,String keyAlgorithm,String cipherAlgorithm) {
			this.keyAlgorithm = keyAlgorithm;
			this.cipherAlgorithm = cipherAlgorithm;
			initKey(key);
		}
		public SymmetricEncryption(byte[] key) {
			initKey(key);
		}
		SymmetricEncryption() {}
		
		protected void initKey(byte[] key){
			try {
				this.key = toKey(key);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		/**
		 * 还原密钥
		 * @param key
		 * @return
		 */
		public abstract Key toKey(byte[] key) throws Exception;
		
		public abstract byte[] encrypt(byte[] data) throws Exception;
		
		public abstract byte[] decrypt(byte[] cipherData) throws Exception;
		
		public String getKeyAlgorithm() {
			return keyAlgorithm;
		}
		public String getCipherAlgorithm() {
			return cipherAlgorithm;
		}
		public Key getKey() {
			return key;
		}
	}
	/**
	 * DES加密
	 * @author y1j2x34
	 *
	 */
	public static class SymDes extends SymmetricEncryption{
		private static final String KEY_ALGORITHM = "DES";
		private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
		public SymDes(byte[] key) {
			super(key,KEY_ALGORITHM,CIPHER_ALGORITHM);
		}
		public SymDes(byte[] key,String keyAlgorithm,String cipherAlgorithm) {
			super(key,keyAlgorithm,cipherAlgorithm);
		}
		SymDes() {}
		public static byte[] createSecretKey(int keysize) throws Exception{
			if(keysize < 0){
				throw new IllegalArgumentException("navigave keysize:"+keysize);
			}
			KeyGenerator keyGnerator = KeyGenerator.getInstance(SymDes.KEY_ALGORITHM);
			keyGnerator.init(keysize);
			SecretKey secretKey = keyGnerator.generateKey();
			return secretKey.getEncoded();
		}
		@Override
		public Key toKey(byte[] key) throws Exception {
			DESKeySpec des = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
			SecretKey secretKey = keyFactory.generateSecret(des);
			return secretKey;
		}

		@Override
		public byte[] encrypt(byte[] data) throws Exception {
			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(data);
		}

		@Override
		public byte[] decrypt(byte[] cipherData) throws Exception {
			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(cipherData);
		}
	}
	/**
	 * DESede算法--三重DES
	 * @author y1j2x34
	 *
	 */
	public static final class SymDESede extends SymDes{
		
		public static final int KEY_SIZE_1 = 112;
		public static final int KEY_SIZE_2 = 168;
		
		private static final String KEY_ALGORITHM = "DESede";  
		//算法名称/加密模式/填充方式  
		private static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
		private int keysize;
		/**
		 * @param key
		 * @param keysize in 112 or 168
		 */
		public SymDESede(byte[] key,int keysize) {
			if(keysize != KEY_SIZE_1 && keysize != KEY_SIZE_2){
				throw new IllegalArgumentException("keysize must");
			}
			super.keyAlgorithm = KEY_ALGORITHM;
			super.cipherAlgorithm = CIPHER_ALGORITHM;
			this.keysize = keysize;
			super.initKey(key);
		}
		public SymDESede(byte[] key){
			this(key,KEY_SIZE_1);
		}
		
		//算法名称  
		@Override
		public Key toKey(byte[] key) throws Exception {
			/*DESedeKeySpec des = new DESedeKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
			SecretKey secretKey = keyFactory.generateSecret(des);*/
			KeyGenerator generator = KeyGenerator.getInstance(keyAlgorithm);
			generator.init(keysize, new SecureRandom(key));
			SecretKey secretKey = generator.generateKey();
			return secretKey;
		}
	}
	/**
	 * AES算法
	 * @author y1j2x34
	 *
	 */
	public static final class SymAES extends SymDes{
		private static final String KEY_ALGORITHM = "AES";  
		private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"; 
		public SymAES(byte[] key) {
			super(key,KEY_ALGORITHM,CIPHER_ALGORITHM);
		}
		@Override
		public Key toKey(byte[] key) throws Exception {
			//SecretKey secretKey = new SecretKeySpec(key, keyAlgorithm);
			KeyGenerator generator = KeyGenerator.getInstance(keyAlgorithm);
			generator.init(128, new SecureRandom(key));
			SecretKey secretKey = generator.generateKey();
			return secretKey;
		}
	}
	/**
	 * 基本口令加密 PBE
	 * @author y1j2x34
	 *
	 */
	public static final class SymPBE extends SymmetricEncryption{
		private static final String ALGORITHM = "PBEWITHMD5andDES";
		private final byte[] salt;
		//迭代次数
		private final int iterationCount;
		/**
		 * @param password
		 * @param salt
		 * @see #salt()
		 */
		public SymPBE(byte[] password,byte[] salt,int iterationCount) {
			super(password);
			this.salt = salt;
			this.iterationCount = iterationCount;
		}
		/**
		 * 
		 * @param password 加密密码
		 * @param salt 盐 {@link #salt()}
		 * @see #salt()
		 */
		public SymPBE(String password,byte[] salt,int iterationCount) {
			this(password.getBytes(),salt,iterationCount);
		}
		/**
		 * 盐初始化，
		 * 盐的长度必须为8字节
		 * @return
		 */
		public static byte[] salt(){
			//安全随机数
			SecureRandom random = new SecureRandom();
			return random.generateSeed(8);
		}
		@Override
		public Key toKey(byte[] key) throws Exception {
			return toKey(new String(key));
		}
		public Key toKey(String password) throws Exception{
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey secretKey = keyFactory.generateSecret(keySpec);
			return secretKey;
		}
		@Override
		public byte[] encrypt(byte[] data) throws Exception {
			Key key = getKey();
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterationCount);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
			return cipher.doFinal(data);
		}

		@Override
		public byte[] decrypt(byte[] cipherData) throws Exception {
			Key key = getKey();
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterationCount);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
			return cipher.doFinal(cipherData);
		}
		public String getCipherAlgorithm() {
			throw new UnsupportedOperationException();
		}
		@Override
		public String getKeyAlgorithm() {
			throw new UnsupportedOperationException();
		}
		public int getIterationCount() {
			return iterationCount;
		}
	}
	/**
	 * 
	 * @author y1j2x34
	 */
	public static final class SymRC4 extends SymmetricEncryption{
		
		public SymRC4(byte[] key) {
			super(key,"RC4","");
		}
		
		@Override
		public Key toKey(byte[] key) throws Exception {
			KeyGenerator keyGener = KeyGenerator.getInstance(keyAlgorithm);
			keyGener.init(128,new SecureRandom(key));
			return keyGener.generateKey();
		}
		@Override
		public byte[] encrypt(byte[] data) throws Exception {
			Cipher cipher = Cipher.getInstance(keyAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(data);
		}
		
		@Override
		public byte[] decrypt(byte[] cipherData) throws Exception {
			Cipher cipher = Cipher.getInstance(keyAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(cipherData);
		}
	}
	/**
	 * IDEA
	 * @author y1j2x34
	 * @deprecated
	 */
	public static final class SymIDEA extends SymDes{
//		private static final String KEY_ALGORITHM = "IDEA";  
//		private static final String CIPHER_ALGORITHM = "IDEA/ECB/PKCS5Padding";
//		public SymIDEA(byte[] key) {
//			super(key,KEY_ALGORITHM,CIPHER_ALGORITHM);
//		}
//		public SymIDEA(byte[] key, String keyAlgorithm, String cipherAlgorithm) {
//			super(key, keyAlgorithm, cipherAlgorithm);
//		}
//		@Override
//		public Key toKey(byte[] key) throws Exception {
//			return new SecretKeySpec(key, keyAlgorithm);
//		}
	}
	//! 使用了密码md5密文的8为作为DES解密key
	/**
	 * @author 杨建新
	 *
	 */
	public static final class DES{
		private static final Logger log = Logger.getLogger(DES.class.getSimpleName());
		/**
		 * ECB加密模式
		 */
		public static final int MODE_ECB = 0;
		/**
		 * cbc加密模式 PKCS5Padding填充
		 */
		public static final int MODE_CBC = 1;
		
		private final byte[] mPassword;
		private final Charset charset;
		
		private final int mode;
		
		/**
		 * 默认使用UTF-8编码，ECB模式
		 * @param password
		 * @see #DES(String, String, int)
		 */
		public DES(String password) {
			this(password,"UTF-8",MODE_ECB);
		}
		/**
		 * 默认使用ECB模式
		 * @param password
		 * @param encoding
		 * @see #DES(String,String,int)
		 */
		public DES(String password,String encoding){
			this(password,encoding,MODE_ECB);
		}
		/**
		 * 
		 * @param password	8位加密密码
		 * @param encoding	原文编码
		 * @param mode	加密模式 {@link DES#MODE_ECB} , {@link DES#MODE_CBC}
		 */
		public DES(String password,String encoding,int mode){
			if(password == null){
				throw new IllegalArgumentException("password == null");
			}
			if(password.length() != 8){
				throw new IllegalArgumentException("length of password must be 8");
			}
			charset = Charset.forName(encoding);
			mPassword  = password.getBytes(charset);
			this.mode = mode;
		}
		private byte[] des(int opmode,byte[] data) throws Exception{
			switch(mode){
			case MODE_ECB:
				return des_ecb(opmode, data);
			case MODE_CBC:
				return des_cbc(opmode, data);
			default:
				throw new IllegalArgumentException("mode = "+mode);
			}
		}
		private byte[] des_ecb(int opmode,byte[] data) throws Exception{
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(opmode, new SecretKeySpec(mPassword, "DES"));
			return cipher.doFinal(data);
		}
		private byte[] des_cbc(int opmode,byte[] data) throws Exception{
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec deskeyspec = new DESKeySpec(mPassword);
			SecretKeyFactory keyfac = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyfac.generateSecret(deskeyspec);
			IvParameterSpec iv = new IvParameterSpec(mPassword);
			cipher.init(opmode, key, iv);
			return cipher.doFinal(data);
		}
		public byte[] encrypt(byte[] data) throws Exception{
			return des(Cipher.ENCRYPT_MODE,data);
		}
		public byte[] decrypt(byte[] cipherData) throws Exception{
			return des(Cipher.DECRYPT_MODE,cipherData);
		}
		public String encrypt(String data) throws Exception{
			log.info("加密原串为："+data);
			String ostr = URLEncoder.encode(data,charset.name());
			String enRst = byteToHex(encrypt(ostr.getBytes(charset)));
			return enRst;
		}
		public String decrypt(String ciphertext) throws Exception{
			String cleartext = URLDecoder.decode(new String(decrypt(hexTobytes(ciphertext))),charset.name());
			log.info("解密串："+ciphertext);
			log.info("解密key："+new String(mPassword,charset));
			log.info("解密后的明文："+cleartext);
			return cleartext;
		}
		
		public int getMode() {
			return mode;
		}
		public Charset getCharset() {
			return charset;
		}
	}
}

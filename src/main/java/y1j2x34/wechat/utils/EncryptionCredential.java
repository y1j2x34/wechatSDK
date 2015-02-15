package y1j2x34.wechat.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.nio.charset.Charset;

import y1j2x34.utils.DigestUtils;
import y1j2x34.utils.DigestUtils.SymAES;
import y1j2x34.utils.DigestUtils.SymDes;
import y1j2x34.wechat.Credential;
/**
 * 微信开发者凭据信息加密解密处理
 * @author yangjianxin
 * @date 2014-12-11 下午4:01:11
 */
public class EncryptionCredential extends Credential implements Serializable{
	private static class CredentialProxy implements Serializable{
		private static final long serialVersionUID = 1L;
		private String data;
		public CredentialProxy(EncryptionCredential target) {
			String original = target.appId+"|"+target.appSecret+"|"+target.token;
			Charset iso88591 = Charset.forName("iso8859-1");
			SymDes des = new SymDes("upowersoft".getBytes(iso88591));
			try {
				this.data = DigestUtils.byteToHex(des.encrypt(original.getBytes(iso88591)));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		private Object readResolve() throws ObjectStreamException,IOException{
			Charset iso88591 = Charset.forName("iso8859-1");
			SymDes des = new SymDes("upowersoft".getBytes(iso88591));
			try {
				data = new String(des.decrypt(DigestUtils.hexTobytes(data)));
			} catch (Exception e) {
				throw new IOException("数据恢复错误！",e);
			}
			
			int prev = 0,idx=data.indexOf('|');
			String appId = data.substring(prev, idx);
			prev = idx+1;
			idx = data.indexOf('|',prev);
			String appSecret = data.substring(prev,idx);
			prev = idx + 1;
			idx = data.length();
			String token = data.substring(prev, idx);
			return new EncryptionCredential(appId, appSecret,token);
		}
	}
	
	private final String appId,appSecret,token;
	
	private EncryptionCredential(String appId,String appSecret,String token) {
		this.appId = appId;
		this.appSecret = appSecret;
		this.token = token;
	}
	
	private Object writeReplace() throws ObjectStreamException{
		return new CredentialProxy(this);
	}
	
	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public String getAppSecret() {
		return appSecret;
	}
	public String getToken() {
		return token;
	}
	/**
	 * 创建凭据信息密文
	 * @param appId
	 * @param appSecret
	 * @param token
	 * @param password 加密密码
	 * @return
	 */
	public static String create(String appId,String appSecret,String token,String password){
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		EncryptionCredential ec = new EncryptionCredential(appId, appSecret,token);
		try {
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			oout.writeObject(ec);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		SymAES aes = new SymAES(password.getBytes(Charset.forName("iso8859-1")));
		try {
			 return DigestUtils.byteToHex(aes.encrypt(bout.toByteArray()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 还原开发者凭据信息
	 * @param cipher
	 * @param password 还原密码
	 * @return
	 */
	public static EncryptionCredential restore(String cipher,String password){
		SymAES aes = new SymAES(password.getBytes(Charset.forName("iso8859-1")));
		try {
			byte[] data = DigestUtils.hexTobytes(cipher);
			byte[] d = aes.decrypt(data);
			ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(d));
			return (EncryptionCredential) oin.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

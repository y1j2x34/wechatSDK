package y1j2x34.wechat.utils;

import java.nio.charset.Charset;
import java.util.Map;

import y1j2x34.utils.DigestUtils;
import net.sf.json.JSONObject;

public class NoticeKeyUtils {
	private static final Charset UTF8 = Charset.forName("utf-8");
	public static final String createKey(Map<String,String> params,byte[] password){
		params.put("time", String.valueOf(System.currentTimeMillis()));
		String json = JSONObject.fromObject(params).toString();
		DigestUtils.SymDes des = new DigestUtils.SymDes(password);
		try {
			byte[] cipher = des.encrypt(json.getBytes(UTF8));
			return DigestUtils.byteToHex(cipher);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static final Map<String,String> restoreKey(String key,byte[] password){
		DigestUtils.SymDes des = new DigestUtils.SymDes(password);
		String result = null;
		try {
			result = new String(des.decrypt(key.getBytes(UTF8)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)JSONObject.fromObject(result);
		return map;
	}
}

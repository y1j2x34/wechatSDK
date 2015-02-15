package y1j2x34.wechat.utils;

/**
 * 模板工具
 * @author yangjianxin
 * @date 2014-10-20
 */
public class Template {
	private String mPref;
	private String mSuff;
	private String mTpl;

	/**
	 * 默认内容域使用${...}包围
	 * 
	 * @param tpl
	 */
	public Template(String tpl) {
		this(tpl, "${", "}");
	}

	/**
	 * 指定内容域格式
	 * 
	 * @param tpl
	 * @param pref
	 *            前缀
	 * @param suff
	 *            后缀
	 */
	public Template(String tpl, String pref, String suff) {
		mTpl = tpl;
		mPref = pref;
		mSuff = suff;
	}

	public void set(String name, String content) {
		if (name == null)
			return;
		// String#replaceAll is too slow
		StringBuilder sb = new StringBuilder();
		int prevIndex = 0;
		int tplLength = mTpl.length();
		int sufLength = mSuff.length();
		int prefLength = mPref.length();
		int lastTplIndex = tplLength - 1;
		int nameLength = name.length();
		while (true) {
			int nameIndex = mTpl.indexOf(name, prevIndex);
			if (nameIndex == -1) {
				sb.append(substring(mTpl, prevIndex));
				break;
			} else if (nameIndex != prevIndex) {
				sb.append(substring(mTpl, prevIndex, nameIndex - prefLength));
			} else {
				sb.append(substring(mTpl, nameIndex, nameIndex + nameLength));
				prevIndex += nameLength;
				continue;
			}
			if (nameIndex < prefLength) {
				sb.append(substring(mTpl, 0, nameIndex + nameLength));
				prevIndex = nameIndex + nameLength;
				break;
			}
			int nameRight = nameIndex + nameLength;
			int sufRight = nameRight + sufLength;
			if (sufRight > tplLength) {
				sb.append(substring(mTpl, prevIndex));
				break;
			}
			String left = mTpl.substring(nameIndex - prefLength, nameIndex);
			if (mPref.equals(left)) {
				String right = mTpl.substring(nameRight, sufRight);
				if (mSuff.equals(right)) {
					sb.append(content);
					prevIndex = sufRight;
					continue;
				}
			}
			sb.append(substring(mTpl, nameIndex - prefLength, nameRight));
			prevIndex = nameRight;
		}
		mTpl = sb.toString();
	}

	private static String substring(String s, int begin, int end) {
		return s.substring(begin, end) + "";// 避免JDK6中的bug
	}

	private static String substring(String s, int fromIndex) {
		return s.substring(fromIndex) + "";
	}

	public String getContent() {
		return mTpl;
	}
}

package y1j2x34.wechat.state;

public class WechatStateMatchers {
	private WechatStateMatchers(){}
	private static class Congruent extends WechatStateMatcher<Object>{
		public Congruent(Object value) {
			super(value);
		}
		@Override
		public boolean match(Object cond) {
			return super.value == cond;
		}
	}
	private static class Equals extends WechatStateMatcher<Object>{
		public Equals(Object value) {
			super(value);
		}
		@Override
		public boolean match(Object cond) {
			return value == cond || (value != null && value.equals(cond)) ;
		}
	}
	private static class Regex extends WechatStateMatcher<String>{
		public Regex(String value) {
			super(value);
			
		}
		@Override
		public boolean match(String cond) {
			if(super.value == null){
				return super.value == cond; // null matchs null
			}else if(cond == null){
				return false;
			}else{
				return cond.matches(value);
			}
		}
	}
	/**
	 * 全等匹配
	 * @param value
	 * @return
	 */
	public static WechatStateMatcher<Object> congruent(Object value){
		return new Congruent(value);
	}
	/**
	 * equals 匹配
	 * @param value
	 * @return
	 */
	public static WechatStateMatcher<Object> eq(Object value){
		return new Equals(value);
	}
	/**
	 * 正则匹配
	 * @param regex
	 * @return
	 */
	public static WechatStateMatcher<String> reg(String regex){
		return new Regex(regex);
	}
}

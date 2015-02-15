package y1j2x34.wechat.message.mass;

import java.io.Serializable;
/**
 * 用于设定消息的接收者
 * @author y1j2x34
 *
 */
public class MassFilter implements Serializable{
	private static final long serialVersionUID = 1L;
	private int group_id;
	private boolean is_to_all = false;
	public MassFilter() {}
	public MassFilter(int group_id){
		this.group_id = group_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public boolean isIs_to_all() {
		return is_to_all;
	}
	public void setIs_to_all(boolean is_to_all) {
		this.is_to_all = is_to_all;
	}
}
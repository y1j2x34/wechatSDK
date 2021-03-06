package y1j2x34.wechat.pojo;

import java.io.Serializable;
/**
 * <table border="1" cellspacing="0" cellpadding="4" align="center" width="640px" style="background-color:#EEE">
<tbody><tr>
<th style="width:320px">字段
</th>
<th>说明
</th></tr>
<tr>
<td> total
</td>
<td> 关注该公众账号的总用户数
</td></tr>
<tr>
<td> count
</td>
<td> 拉取的OPENID个数，最大值为10000
</td></tr>
<tr>
<td> data
</td>
<td> 列表数据，OPENID的列表
</td></tr>
<tr>
<td> next_openid
</td>
<td> 拉取列表的后一个用户的OPENID
</td></tr>
</tbody></table>
 * @author 杨建新
 *
 */
public class ConcernedUsers implements Serializable{
	private static final long serialVersionUID = -5685039674087864950L;
	
	private int total;
	private int count;
	private CUsr_Data data;
	private String next_openid;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public CUsr_Data getData() {
		return data;
	}
	public void setData(CUsr_Data data) {
		this.data = data;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
}

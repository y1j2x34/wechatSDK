package y1j2x34.wechat.message.resp;

import java.util.List;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 响应消息——图文消息
 * <hr/>
 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E8%A2%AB%E5%8A%A8%E5%93%8D%E5%BA%94%E6%B6%88%E6%81%AF#.E5.9B.9E.E5.A4.8D.E5.9B.BE.E6.96.87.E6.B6.88.E6.81.AF">发送被动响应消息#回复图文消息</a>
 * <hr/>
 * @author yangjianxin
 * @see RespBaseMessage
 */
public class RespNewsMessage extends RespBaseMessage {

	private static final long serialVersionUID = -6405596092593767525L;
	
	private int ArticleCount;
	
	private List<Article> Articles;
	public RespNewsMessage() {
		setMsgType(MessageUtils.RESP_MESSAGE_TYPE_NEWS);
	}
	/**
	 * @return 图文消息个数，限制为10条以内
	 * @see #setArticleCount(int)
	 */
	public int getArticleCount() {
		return ArticleCount;
	}
	/**
	 * @param articleCount
	 * @see #getArticleCount()
	 */
	public void setArticleCount(int articleCount) {
		this.ArticleCount = articleCount;
	}
	/**
	 * @return 多条图文消息,默认第一个item为大图
	 * @see #setArticles(List)
	 */
	public List<Article> getArticles() {
		return Articles;
	}
	/**
	 * @param articles
	 * @see #getArticles()
	 */
	public void setArticles(List<Article> articles) {
		this.Articles = articles;
	}
}

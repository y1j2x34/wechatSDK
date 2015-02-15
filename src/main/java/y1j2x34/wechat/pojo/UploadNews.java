package y1j2x34.wechat.pojo;

import java.io.Serializable;
import java.util.List;

import y1j2x34.wechat.utils.WeChatUtil;
/**
 * 上传图文消息素材--图文消息
 * @author yangjianxin
 * @see WeChatUtil#uploadNews(UploadNews, AccessToken)
 */
public class UploadNews implements Serializable{

	private static final long serialVersionUID = 1L;
	//图文消息，支持1到10条图文
	private List<UploadArticle> articles;

	public List<UploadArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<UploadArticle> articles) {
		this.articles = articles;
	}
	
}

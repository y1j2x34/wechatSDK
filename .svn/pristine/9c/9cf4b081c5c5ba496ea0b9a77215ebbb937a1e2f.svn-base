package y1j2x34.wechat.message.svc;

import java.util.ArrayList;
import java.util.List;

public class SvNewsMessage extends SvBaseMessage{

	private static final long serialVersionUID = -8101619473979667908L;
	
	public static class SvNews{
		private List<SvArticle> articles = new ArrayList<SvArticle>(10);
		public List<SvArticle> getArticles() {
			return articles;
		}
		public void setArticles(List<SvArticle> articles) {
			this.articles = articles;
		}
	}
	private SvNews news = new SvNews();
	public SvNews getNews() {
		return news;
	}
	public void addArticel(SvArticle article){
		news.articles.add(article);
	}
}

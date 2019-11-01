package kr.co.ohjooyeo.vo;

public class WorshipAdVO {
	private String worshipId;
	private int churchId;
	private int adId;
	private int order;
	private String title;
	private String content;
	
	public WorshipAdVO() {
	}

	public WorshipAdVO(String worshipId, int churchId, int adId, int order, String title, String content) {
		super();
		this.worshipId = worshipId;
		this.churchId = churchId;
		this.adId = adId;
		this.order = order;
		this.title = title;
		this.content = content;
	}

	public String getWorshipId() {
		return worshipId;
	}

	public void setWorshipId(String worshipId) {
		this.worshipId = worshipId;
	}

	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "WorshipAdVO [worshipId=" + worshipId + ", churchId=" + churchId + ", adId=" + adId + ", order=" + order
				+ ", title=" + title + ", content=" + content + "]";
	}
}

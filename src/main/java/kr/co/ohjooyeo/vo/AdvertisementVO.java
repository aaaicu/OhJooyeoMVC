package kr.co.ohjooyeo.vo;

public class AdvertisementVO {
	private String worshipId;
	private int advertisementId;
	private int order;
	private String title;
	private String content;
	
	public AdvertisementVO(String worshipId, int advertisementId, int order, String title, String content) {
		super();
		this.worshipId = worshipId;
		this.advertisementId = advertisementId;
		this.order = order;
		this.title = title;
		this.content = content;
	}
	public AdvertisementVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getWorshipId() {
		return worshipId;
	}
	public void setWorshipId(String worshipId) {
		this.worshipId = worshipId;
	}
	public int getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
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
		return "AdvertisementVO [worshipId=" + worshipId + ", advertisementId=" + advertisementId + ", order=" + order
				+ ", title=" + title + ", content=" + content + "]";
	}
	
	
	
}

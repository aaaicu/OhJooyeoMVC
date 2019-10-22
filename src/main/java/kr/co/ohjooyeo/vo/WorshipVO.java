package kr.co.ohjooyeo.vo;

public class WorshipVO {
	private String worshipId;
	private String worshipDate;
	private String mainPresenter;
	private String nextPresenter;
	private String nextPrayer;
	private String nextOffer;
	private String adVersion;
	private String orderVersion;
	private String churchId;
	
	public WorshipVO() {
		super();
	}

	public WorshipVO(String worshipId, String worshipDate, String mainPresenter, String nextPresenter,
			String nextPrayer, String nextOffer, String adVersion, String orderVersion, String churchId) {
		super();
		this.worshipId = worshipId;
		this.worshipDate = worshipDate;
		this.mainPresenter = mainPresenter;
		this.nextPresenter = nextPresenter;
		this.nextPrayer = nextPrayer;
		this.nextOffer = nextOffer;
		this.adVersion = adVersion;
		this.orderVersion = orderVersion;
		this.churchId = churchId;
	}

	public String getWorshipId() {
		return worshipId;
	}

	public void setWorshipId(String worshipId) {
		this.worshipId = worshipId;
	}

	public String getWorshipDate() {
		return worshipDate;
	}

	public void setWorshipDate(String worshipDate) {
		this.worshipDate = worshipDate;
	}

	public String getMainPresenter() {
		return mainPresenter;
	}

	public void setMainPresenter(String mainPresenter) {
		this.mainPresenter = mainPresenter;
	}

	public String getNextPresenter() {
		return nextPresenter;
	}

	public void setNextPresenter(String nextPresenter) {
		this.nextPresenter = nextPresenter;
	}

	public String getNextPrayer() {
		return nextPrayer;
	}

	public void setNextPrayer(String nextPrayer) {
		this.nextPrayer = nextPrayer;
	}

	public String getNextOffer() {
		return nextOffer;
	}

	public void setNextOffer(String nextOffer) {
		this.nextOffer = nextOffer;
	}

	public String getAdVersion() {
		return adVersion;
	}

	public void setAdVersion(String adVersion) {
		this.adVersion = adVersion;
	}

	public String getOrderVersion() {
		return orderVersion;
	}

	public void setOrderVersion(String orderVersion) {
		this.orderVersion = orderVersion;
	}

	public String getChurchId() {
		return churchId;
	}

	public void setChurchId(String churchId) {
		this.churchId = churchId;
	}

	@Override
	public String toString() {
		return "WorshipVO [worshipId=" + worshipId + ", worshipDate=" + worshipDate + ", mainPresenter=" + mainPresenter
				+ ", nextPresenter=" + nextPresenter + ", nextPrayer=" + nextPrayer + ", nextOffer=" + nextOffer
				+ ", adVersion=" + adVersion + ", orderVersion=" + orderVersion + ", churchId=" + churchId + "]";
	}
	
	
	
}


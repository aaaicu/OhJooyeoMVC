package kr.co.ohjooyeo.vo;

public class WorshipOrderVO {
	private int churchId;
	private String worshipId;
	private int orderId;
	private int order;
	private int type;
	private String title;
	private String detail;
	private String presenter;
	private int standupYn;
	
	public WorshipOrderVO() {
	}

	public WorshipOrderVO(int churchId, String worshipId, int orderId, int order, int type, String title, String detail,
			String presenter, int standupYn) {
		super();
		this.churchId = churchId;
		this.worshipId = worshipId;
		this.orderId = orderId;
		this.order = order;
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.presenter = presenter;
		this.standupYn = standupYn;
	}

	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	public String getWorshipId() {
		return worshipId;
	}

	public void setWorshipId(String worshipId) {
		this.worshipId = worshipId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}

	public int getStandupYn() {
		return standupYn;
	}

	public void setStandupYn(int standupYn) {
		this.standupYn = standupYn;
	}

	@Override
	public String toString() {
		return "WorshipOrderVO [churchId=" + churchId + ", worshipId=" + worshipId + ", orderId=" + orderId + ", order="
				+ order + ", type=" + type + ", title=" + title + ", detail=" + detail + ", presenter=" + presenter
				+ ", standupYn=" + standupYn + "]";
	}

	
}

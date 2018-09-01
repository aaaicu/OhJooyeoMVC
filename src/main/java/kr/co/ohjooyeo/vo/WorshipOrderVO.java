package kr.co.ohjooyeo.vo;

public class WorshipOrderVO {
	private String worshipId;
	private int orderId;
	private int order;
	private String title;
	private String detail;
	private String presenter;
	public WorshipOrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WorshipOrderVO(String worshipId, int orderId, int order, String title, String detail, String presenter) {
		super();
		this.worshipId = worshipId;
		this.orderId = orderId;
		this.order = order;
		this.title = title;
		this.detail = detail;
		this.presenter = presenter;
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
	@Override
	public String toString() {
		return "WorshipOrderVO [worshipId=" + worshipId + ", orderId=" + orderId + ", order=" + order + ", title="
				+ title + ", detail=" + detail + ", presenter=" + presenter + "]";
	}
	
	
	
}

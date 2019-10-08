package kr.co.ohjooyeo.vo;


public class ChurchVO {
	private String address ;
	private double latitude ;
	private double longitude ;
	private String name ;
	private String description ;
	private int churchId;
	
	public ChurchVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChurchVO(String address, double latitude, double longitude, String name, String description, int churchId) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.description = description;
		this.churchId = churchId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	@Override
	public String toString() {
		return "ChurchVO [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ ", description=" + description + ", churchId=" + churchId + "]";
	}
	
	
	
}

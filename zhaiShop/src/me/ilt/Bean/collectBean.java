package me.ilt.Bean;

public class collectBean {
	private int userId;
	private int goodsId;
	private String goodsName;
	private String goodsProPic;
	private double goodsPrice;
	private String time;
	
	
	public collectBean() {
	}
	public collectBean(int userId, int goodsId, String goodsName,
			String goodsProPic, double goodsPrice, String time) {
		super();
		this.userId = userId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsProPic = goodsProPic;
		this.goodsPrice = goodsPrice;
		this.time = time;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsProPic() {
		return goodsProPic;
	}
	public void setGoodsProPic(String goodsProPic) {
		this.goodsProPic = goodsProPic;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "collectBean [userId=" + userId + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", goodsProPic=" + goodsProPic
				+ ", goodsPrice=" + goodsPrice + ", time=" + time + "]";
	}
	
	
}

package me.ilt.Bean;

public class shoppingCart {
	private int userId;
	private int goodsId;
	private int num;
	private double goodsPrice;
	public shoppingCart(int userId, int goodsId, int num, double goodsPrice) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.num = num;
		this.goodsPrice = goodsPrice;
	}
	
	public shoppingCart() {
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	@Override
	public String toString() {
		return "shoppingCart [userId=" + userId + ", goodsId=" + goodsId + ", num="
				+ num + ", goodsPrice=" + goodsPrice + "]";
	}

}

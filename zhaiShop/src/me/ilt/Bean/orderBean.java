package me.ilt.Bean;

import java.util.List;

public class orderBean {
private String id; //����ID
private int userId; //�û�ID
private double total; //�����ܶ�
private int addressId; //�ջ���ַID
private String remarks;  //�������
private String time;  //�µ�ʱ��
private int state;  //��ǰ״̬
private List<orderItemBean> itemList;//������Ʒ����
public orderBean() {
	super();
}
public orderBean(String id, int userId, double total, int addressId,
		String remarks, String time, int state) {
	super();
	this.id = id;
	this.userId = userId;
	this.total = total;
	this.addressId = addressId;
	this.remarks = remarks;
	this.time = time;
	this.state = state;
}

public List<orderItemBean> getItemList() {
	return itemList;
}
public void setItemList(List<orderItemBean> itemList) {
	this.itemList = itemList;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}

public int getAddressId() {
	return addressId;
}
public void setAddressId(int addressId) {
	this.addressId = addressId;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}

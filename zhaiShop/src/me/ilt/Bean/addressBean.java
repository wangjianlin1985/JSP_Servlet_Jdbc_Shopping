package me.ilt.Bean;

public class addressBean {
int id;
String province; //ʡ
String city; //��
String area; //��
String address; //��ϸ��ַ
String phone;  //�ֻ���
String username; //�ջ�������
int msg;//�Ƿ���Ĭ��
int userId; //�û�ID

public addressBean() {
}


public addressBean(String province, String city, String area, String address,
		String phone, String username, int msg, int userId) {
	super();
	this.province = province;
	this.city = city;
	this.area = area;
	this.address = address;
	this.phone = phone;
	this.username = username;
	this.msg = msg;
	this.userId = userId;
}


public int getMsg() {
	return msg;
}
public void setMsg(int msg) {
	this.msg = msg;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}


@Override
public String toString() {
	return "addressBean [id=" + id + ", province=" + province + ", city="
			+ city + ", area=" + area + ", address=" + address + ", phone="
			+ phone + ", username=" + username + ", msg=" + msg + ", userId="
			+ userId + "]";
}

}

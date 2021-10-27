package com.RMSSpringBoot;

import java.sql.Date;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="my_pals_deleted")
public class DeletedFriends {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long del_id;

private Long myp_id;
private String name;
private String reason;
private Long contactInfo;
private Date delDate;


public Long getDel_id() {
	return del_id;
}
public void setDel_id(Long del_id) {
	this.del_id = del_id;
}
public Long getMyp_id() {
	return myp_id;
}
public void setMyp_id(Long myp_id) {
	this.myp_id = myp_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public Long getContactInfo() {
	return contactInfo;
}
public void setContactInfo(Long contactInfo) {
	this.contactInfo = contactInfo;
}
public Date getDelDate() {
	return delDate;
}
public void setDelDate(Date delDate) {
	this.delDate = delDate;
}






}

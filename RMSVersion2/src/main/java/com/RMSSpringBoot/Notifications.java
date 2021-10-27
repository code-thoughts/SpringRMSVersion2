package com.RMSSpringBoot;

import java.sql.Date;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="my_pals_notifications")
public class Notifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long not_id;
	
	private Long myp_id;
	private String myp_name;
	private Date myp_lstcontacted;
	private Long NumOfDaysSinceLastContact;
	
	
	public Long getNumOfDaysSinceLastContact() {
		return NumOfDaysSinceLastContact;
	}
	public void setNumOfDaysSinceLastContact(Long numOfDaysSinceLastContact) {
		NumOfDaysSinceLastContact = numOfDaysSinceLastContact;
	}
	public Long getMyp_id() {
		return myp_id;
	}
	public void setMyp_id(Long myp_id) {
		this.myp_id = myp_id;
	}
	
	public Long getNot_id() {
		return not_id;
	}
	public void setNot_id(Long not_id) {
		this.not_id = not_id;
	}
	
	public String getMyp_name() {
		return myp_name;
	}
	public void setMyp_name(String myp_name) {
		this.myp_name = myp_name;
	}
	public Date getMyp_lstcontacted() {
		return myp_lstcontacted;
	}
	public void setMyp_lstcontacted(Date myp_lstcontacted) {
		this.myp_lstcontacted = myp_lstcontacted;
	}
	
	
	
}

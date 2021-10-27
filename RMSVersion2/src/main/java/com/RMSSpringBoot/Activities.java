package com.RMSSpringBoot;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="my_pals_activities") 

public class Activities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long act_id;
	
	private Long myp_id;
	private String nameOfAct;
	private Date date;
	private String actNotes;
	private Long valholder;
	private String actPartner;
	
	
	public String getActPartner() {
		return actPartner;
	}
	public void setActPartner(String actPartner) {
		this.actPartner = actPartner;
	}
	public Long getValholder() {
		return valholder;
	}
	public void setValholder(Long valholder) {
		this.valholder = valholder;
	}
	
	public Long getAct_id() {
		return act_id;
	}
	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}
	public Long getMyp_id() {
		return myp_id;
	}
	public void setMyp_id(Long myp_id) {
		this.myp_id = myp_id;
	}
	public String getNameOfAct() {
		return nameOfAct;
	}
	public void setNameOfAct(String nameOfAct) {
		this.nameOfAct = nameOfAct;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getActNotes() {
		return actNotes;
	}
	public void setActNotes(String actNotes) {
		this.actNotes = actNotes;
	}
	

}

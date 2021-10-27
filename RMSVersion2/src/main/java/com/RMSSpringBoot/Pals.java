package com.RMSSpringBoot;

import java.sql.Blob;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import org.springframework.data.annotation.Transient;
//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="my_pals")
public class Pals {
	
	@Id
	//@Column(name="myp_id", nullable = false, columnDefinition = "int default 100" )
	//@Column(name = "myColumn", nullable = false, columnDefinition = "int default 100")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long myp_id;
	
	
	private String name;
	private String nickName;
	private String profession;
	private String religion;
	private String likes;
	private String dislikes;
	private String mainPOfConnection;
	private String lenOfFriendship;
	private String argOrFights;
	private String reasonForArgOrFights;
	private String resolution;
	private String personalityType;
	private Date lastContacted;
	private Date birthday;
	private String doYouHelpThem;
	private Long NumberofDaysSinceLastContact;
	
	
	

	



	@Column(nullable = true, length = 64)
	private String picture;
	
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
	this.picture = picture;
}
	
	//private String PhotosImagePath;

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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getDislikes() {
		return dislikes;
	}
	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}
	public String getMainPOfConnection() {
		return mainPOfConnection;
	}
	public void setMainPOfConnection(String mainPOfConnection) {
		this.mainPOfConnection = mainPOfConnection;
	}
	public String getLenOfFriendship() {
		return lenOfFriendship;
	}
	public void setLenOfFriendship(String lenOfFriendship) {
		this.lenOfFriendship = lenOfFriendship;
	}
	public String getArgOrFights() {
		return argOrFights;
	}
	public void setArgOrFights(String argOrFights) {
		this.argOrFights = argOrFights;
	}
	public String getReasonForArgOrFights() {
		return reasonForArgOrFights;
	}
	public void setReasonForArgOrFights(String reasonForArgOrFights) {
		this.reasonForArgOrFights = reasonForArgOrFights;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getPersonalityType() {
		return personalityType;
	}
	public void setPersonalityType(String personalityType) {
		this.personalityType = personalityType;
	}
	public Date getLastContacted() {
		return lastContacted;
	}
	public void setLastContacted(Date lastContacted) {
		this.lastContacted = lastContacted;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDoYouHelpThem() {
		return doYouHelpThem;
	}
	public void setDoYouHelpThem(String doYouHelpThem) {
		this.doYouHelpThem = doYouHelpThem;
	}
	
	public Long getNumberofDaysSinceLastContact() {
		return NumberofDaysSinceLastContact;
	}
	public void setNumberofDaysSinceLastContact(Long numberofDaysSinceLastContact) {
		NumberofDaysSinceLastContact = numberofDaysSinceLastContact;
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (picture == null || myp_id == null) return null;
         
        //<img th:src="@{/images/rmsLogo.png}" class="p-1"/>
        //System.out.println("/pals-picture in pals model/" + myp_id + "/" + picture);
        //return "/pals-picture/" + myp_id + "/" + picture;
        return "/images/" + picture;
    }
	
	@Transient
	public Long getDaysSinceContact() 
	{
		LocalDate localDateToDay = LocalDate.now();
		  //get days last contacted before
        LocalDate dateDB = lastContacted.toLocalDate();
		long diffDays = Duration.between(dateDB.atStartOfDay(), localDateToDay.atStartOfDay()).toDays();
		return diffDays;
		
	}
	

}

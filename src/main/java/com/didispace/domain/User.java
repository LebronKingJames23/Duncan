package com.didispace.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name="tab_visitor")
public class User implements Serializable{
	
	@Id
    @GeneratedValue
    @Column(name="SN")
	private int num;
	 @Column
	private String name;
	 @Column
	private String gender;
	 @Column(name="staff_name")
	 private String staffname;
	 public User() {
		 super();
	 }
	public User(int num, String name) {
	this.num=num;
	this.name=name;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [num=" + num + ", name=" + name + ", gender=" + gender + ", staffname=" + staffname + "]";
	}

	 
}

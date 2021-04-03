package com.example.org.content;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Student implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long rollNo;
	private String name;
	private String department;
	private Double attendance;
	
	public Student() {
		super();
	}
	
	
	public Student(Long rollNo, String name, String department, Double attendance) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.department = department;
		this.attendance = attendance;
	}

	@XmlAttribute
	public Long getRollNo() {
		return rollNo;
	}


	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}

	@XmlAttribute
	public Double getAttendance() {
		return attendance;
	}


	public void setAttendance(Double attendance) {
		this.attendance = attendance;
	}


	

}

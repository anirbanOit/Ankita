package com.example.crudexample.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
    private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "name", updatable = true)
	private String name;
	
	@NotNull
	@Min(value = 1)
	@Column(name = "age", updatable = true)
    private int age;	
	
	@NotNull
	@Size(max = 255)
	@Column(name = "address", updatable = true)
    private String address;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "zip", updatable = true)
	private String zip;
    
    public Employee() {
	}
    
    /**
     * 
     * @param name
     * @param age
     * @param address
     * @param zip
     */
	public Employee(String name, int age,String address,String zip) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.zip = zip;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}

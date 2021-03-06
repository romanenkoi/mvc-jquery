package com.romanenko.entity;


import java.io.Serializable;

public class User implements Serializable {
	

	private String id;
	
	private String firstName;
	private String lastName;
	
	private String username;
	private String password;
	

	private Role role;

	public User (){
        new User(null, null, null, null);
    }

    public User(String firstName, String lastName, String username, String password) {
       new User(firstName, lastName, username, password, null);
    }


    public User(String firstName, String lastName, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

    public boolean isNew() {
        return (this.id == null);
    }
}

package com.romanenko.entity;



public class Role {
	

	private String id;
	private String description;
    private Integer role;
	
	public Role() {
        new Role(null, null);
    }
    public Role(String description, Integer role){
        new Role(null, description, role);
    }

    public Role(String id, String description, Integer role) {
        this.id = id;
        this.description = description;
        this.role = role;
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

package com.blog.template.enums;

public enum ConfirmationStatusEnum {
	
	PENDING				(1, "PENDING",				"PENDING"),
	APPROVED_BY_ADMIN	(2, "APPROVED_BY_ADMIN", 	"APPROVED BY ADMIN"),
	REJECTED_BY_ADMIN	(3, "REJECTED_BY_ADMIN", 	"REJECTED BY ADMIN");
	
	
	private int code;
	private String label;
	private String description;

	private ConfirmationStatusEnum(int code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    } 
	
	public int getCode() {
        return code;
    }
 
    public String getLabel() {
        return label;
    }

	public String getDescription() {
		return description;
	}

	public static ConfirmationStatusEnum getStatusById(int id) {
	    for(ConfirmationStatusEnum status : values()) {
	        if(status.code == id) 
	        	return status;
	    }
	    return null;
	 }
	
}

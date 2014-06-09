package com.cws.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * A DTO object which is used as a form object
 * in create person and edit person forms.
 * @author 
 */
public class PersonDTO {
    
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
    
    @NotEmpty
    private String lastScreen;
    
    @NotEmpty
    private String validityCheck;
    
    @NotEmpty
    private String validityCheckValue;

    public PersonDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
    public String getLastScreen() {
        return lastScreen;
    }

    public void setLastScreen(String lastScreen) {
        this.lastScreen = lastScreen;
    }
    
 
    public String getValdityCheck() {
        return validityCheck;
    }

    public void setValdityCheck(String validityCheck) {
        this.validityCheck = validityCheck;
    }
    
    ////////////////
    
    public String getValdityCheckValue() {
        return validityCheckValue;
    }

    public void setValdityCheckValue(String validityCheckValue) {
        this.validityCheckValue = validityCheckValue;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

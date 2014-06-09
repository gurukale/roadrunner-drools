package com.cws.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * An entity class which contains the information of a single person.
 * @author 
 */
@Entity
@Table(name = "persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "creation_time", nullable = false)
    private Date creationTime;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "last_screen", nullable = false)
    private String lastScreen;
    
    @Column(name = "validity_Check", nullable = false)
    private String validityCheck;
    
    @Column(name = "validity_Check_Value", nullable = false)
    private String validityCheckValue;
    
    @Column(name = "modification_time", nullable = false)
    private Date modificationTime;
    
    @Version
    private long version = 0;

    public Long getId() {
        return id;
    }

    /**
     * Gets a builder which is used to create Person objects.
     * @param firstName The first name of the created user.
     * @param lastName  The last name of the created user.
     * @return  A new Builder instance.
     */
    public static Builder getBuilder(String firstName, String lastName,String lastScreen,String validityCheck,String validityCheckValue ) {
        return new Builder(firstName, lastName,lastScreen,validityCheck,validityCheckValue);
    }
    
    public Date getCreationTime() {
        return creationTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getLastScreen() {
        return lastScreen;
    }
    public String getValidityCheck() {
        return validityCheck;
    }
    public String getValidityCheckValue() {
        return validityCheckValue;
    }


    /**
     * Gets the full name of the person.
     * @return  The full name of the person.
     */
    @Transient
    public String getName() {
        StringBuilder name = new StringBuilder();
        
        name.append(firstName);
        name.append(" ");
        name.append(lastName);
        
        return name.toString();
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }

    public void update(String firstName, String lastName,String lastScreen, String validityCheck, String validityCheckValue ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastScreen=lastScreen;
        this.validityCheck=validityCheck;
        this.validityCheckValue=validityCheckValue;
    }
    
    @PreUpdate
    public void preUpdate() {
        modificationTime = new Date();
    }
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        creationTime = now;
        modificationTime = now;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * A Builder class used to create new Person objects.
     */
    public static class Builder {
        Person built;

        /**
         * Creates a new Builder instance.
         * @param firstName The first name of the created Person object.
         * @param lastName  The last name of the created Person object.
         */
        Builder(String firstName, String lastName, String lastScreen,String validityCheck,String validityCheckValue) {
            built = new Person();
            built.firstName = firstName;
            built.lastName = lastName;
            built.lastScreen=lastScreen;
            built.validityCheck=validityCheck;
            built.validityCheckValue=validityCheckValue;
        }

        /**
         * Builds the new Person object.
         * @return  The created Person object.
         */
        public Person build() {
            return built;
        }
    }

    /**
     * This setter method should only be used by unit tests.
     * @param id
     */
    protected void setId(Long id) {
        this.id = id;
    }
}

package com.cws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "orders")
public class Order {

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

	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;

	@Column(name = "propositionCheck", nullable = false)
	private Boolean propositionCheck;

	@Column(name = "extraCheck", nullable = false)
	private Boolean extraCheck;

	@Column(name = "giftCheck", nullable = false)
	private Boolean giftCheck;

	@Column(name = "insurance", nullable = false)
	private Boolean insurance;

	@Column(name = "proofsValid", nullable = false)
	private Boolean proofsValid;

	@Column(name = "creditCheckValid", nullable = false)
	private Boolean creditCheckValid;

	@Column(name = "fraudCheckValid", nullable = false)
	private Boolean fraudCheckValid;

	@Version
	private long version = 0;

	/**
	 * Gets a builder which is used to create Person objects.
	 * 
	 * @param firstName
	 *            The first name of the created user.
	 * @param lastName
	 *            The last name of the created user.
	 * @return A new Builder instance.
	 */
	public static Builder getBuilder(String firstName, String lastName,
			String lastScreen, boolean propositionCheck, boolean extraCheck,
			boolean giftCheck, boolean insurance, boolean proofsValid,
			boolean creditCheckValid, boolean fraudCheckValid) {
		return new Builder(firstName, lastName, lastScreen, propositionCheck,
				extraCheck, giftCheck, insurance, proofsValid,
				creditCheckValid, fraudCheckValid);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime
	 *            the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the lastScreen
	 */
	public String getLastScreen() {
		return lastScreen;
	}

	/**
	 * @param lastScreen
	 *            the lastScreen to set
	 */
	public void setLastScreen(String lastScreen) {
		this.lastScreen = lastScreen;
	}

	
	/**
	 * @return the modificationTime
	 */
	public Date getModificationTime() {
		return modificationTime;
	}

	/**
	 * @param modificationTime
	 *            the modificationTime to set
	 */
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	/**
	 * @return the propositionCheck
	 */
	public Boolean getPropositionCheck() {
		return propositionCheck;
	}

	/**
	 * @param propositionCheck
	 *            the propositionCheck to set
	 */
	public void setPropositionCheck(Boolean propositionCheck) {
		this.propositionCheck = propositionCheck;
	}

	/**
	 * @return the extraCheck
	 */
	public Boolean getExtraCheck() {
		return extraCheck;
	}

	/**
	 * @param extraCheck
	 *            the extraCheck to set
	 */
	public void setExtraCheck(Boolean extraCheck) {
		this.extraCheck = extraCheck;
	}

	/**
	 * @return the giftCheck
	 */
	public Boolean getGiftCheck() {
		return giftCheck;
	}

	/**
	 * @param giftCheck
	 *            the giftCheck to set
	 */
	public void setGiftCheck(Boolean giftCheck) {
		this.giftCheck = giftCheck;
	}

	/**
	 * @return the insurance
	 */
	public Boolean getInsurance() {
		return insurance;
	}

	/**
	 * @param insurance
	 *            the insurance to set
	 */
	public void setInsurance(Boolean insurance) {
		this.insurance = insurance;
	}

	/**
	 * @return the proofsValid
	 */
	public Boolean getProofsValid() {
		return proofsValid;
	}

	/**
	 * @param proofsValid
	 *            the proofsValid to set
	 */
	public void setProofsValid(Boolean proofsValid) {
		this.proofsValid = proofsValid;
	}

	/**
	 * @return the creditCheckValid
	 */
	public Boolean getCreditCheckValid() {
		return creditCheckValid;
	}

	/**
	 * @param creditCheckValid
	 *            the creditCheckValid to set
	 */
	public void setCreditCheckValid(Boolean creditCheckValid) {
		this.creditCheckValid = creditCheckValid;
	}

	/**
	 * @return the fraudCheckValid
	 */
	public Boolean getFraudCheckValid() {
		return fraudCheckValid;
	}

	/**
	 * @param fraudCheckValid
	 *            the fraudCheckValid to set
	 */
	public void setFraudCheckValid(Boolean fraudCheckValid) {
		this.fraudCheckValid = fraudCheckValid;
	}

	/**
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
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
    
	public static class Builder {
		Order built;

		/**
		 * Creates a new Builder instance.
		 * 
		 * @param firstName
		 *            The first name of the created Person object.
		 * @param lastName
		 *            The last name of the created Person object.
		 */
		Builder(String firstName, String lastName, String lastScreen,

		boolean propositionCheck, boolean extraCheck, boolean giftCheck,
				boolean insurance, boolean proofsValid,
				boolean creditCheckValid, boolean fraudCheckValid) {
			built = new Order();
			built.firstName = firstName;
			built.lastName = lastName;
			built.lastScreen = lastScreen;
			built.propositionCheck = propositionCheck;
			built.extraCheck = extraCheck;
			built.giftCheck = giftCheck;

			built.insurance = insurance;
			built.proofsValid = proofsValid;
			built.creditCheckValid = creditCheckValid;
			built.fraudCheckValid = fraudCheckValid;

		}

		/**
		 * Builds the new Person object.
		 * 
		 * @return The created Person object.
		 */
		public Order build() {
			return built;
		}
	}

}

package com.cws.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A DTO object which is used as a form object in create person and edit person
 * forms.
 * 
 * @author
 */
public class OrderDTO {

	private Long id;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String lastScreen;

	private boolean propositionCheck;
	private boolean extraCheck;
	private boolean giftCheck;
	private boolean insurance;
	private boolean proofsValid;
	private boolean creditCheckValid;
	private boolean fraudCheckValid;

	public OrderDTO() {

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

	/**
	 * @return the propositionCheck
	 */
	public boolean isPropositionCheck() {
		return propositionCheck;
	}

	/**
	 * @param propositionCheck
	 *            the propositionCheck to set
	 */
	public void setPropositionCheck(boolean propositionCheck) {
		this.propositionCheck = propositionCheck;
	}

	/**
	 * @return the extraCheck
	 */
	public boolean isExtraCheck() {
		return extraCheck;
	}

	/**
	 * @param extraCheck
	 *            the extraCheck to set
	 */
	public void setExtraCheck(boolean extraCheck) {
		this.extraCheck = extraCheck;
	}

	/**
	 * @return the giftCheck
	 */
	public boolean isGiftCheck() {
		return giftCheck;
	}

	/**
	 * @param giftCheck
	 *            the giftCheck to set
	 */
	public void setGiftCheck(boolean giftCheck) {
		this.giftCheck = giftCheck;
	}

	/**
	 * @return the insurance
	 */
	public boolean isInsurance() {
		return insurance;
	}

	/**
	 * @param insurance
	 *            the insurance to set
	 */
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	/**
	 * @return the proofsValid
	 */
	public boolean isProofsValid() {
		return proofsValid;
	}

	/**
	 * @param proofsValid
	 *            the proofsValid to set
	 */
	public void setProofsValid(boolean proofsValid) {
		this.proofsValid = proofsValid;
	}

	/**
	 * @return the creditCheckValid
	 */
	public boolean isCreditCheckValid() {
		return creditCheckValid;
	}

	/**
	 * @param creditCheckValid
	 *            the creditCheckValid to set
	 */
	public void setCreditCheckValid(boolean creditCheckValid) {
		this.creditCheckValid = creditCheckValid;
	}

	/**
	 * @return the fraudCheckValid
	 */
	public boolean isFraudCheckValid() {
		return fraudCheckValid;
	}

	/**
	 * @param fraudCheckValid
	 *            the fraudCheckValid to set
	 */
	public void setFraudCheckValid(boolean fraudCheckValid) {
		this.fraudCheckValid = fraudCheckValid;
	}

	// //////////////

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

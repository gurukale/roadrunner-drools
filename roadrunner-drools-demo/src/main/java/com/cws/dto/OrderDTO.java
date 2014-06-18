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

	private String nextScreen;
	
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

	/**
	 * @return the nextScreen
	 */
	public String getNextScreen() {
		return nextScreen;
	}

	/**
	 * @param nextScreen the nextScreen to set
	 */
	public void setNextScreen(String nextScreen) {
		this.nextScreen = nextScreen;
	}


	// //////////////

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
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

	
	public static class Builder {
		OrderDTO built;

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
			built = new OrderDTO();
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
		public OrderDTO build() {
			return built;
		}
	}

	
}

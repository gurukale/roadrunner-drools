/**
 * 
 */
package com.cws.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author gurunath.maruti.kale
 * 
 */
public class MobileNumberDTO {
	@NotEmpty
	private String network;

	@NotEmpty
	private String imei;

	@NotEmpty
	private String prefix;

	private boolean luhnCheckDone;

	/**
	 * 
	 */
	public MobileNumberDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the network
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 *            the network to set
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the luhnCheckDone
	 */
	public boolean isLuhnCheckDone() {
		return luhnCheckDone;
	}

	/**
	 * @param luhnCheckDone
	 *            the luhnCheckDone to set
	 */
	public void setLuhnCheckDone(boolean luhnCheckDone) {
		this.luhnCheckDone = luhnCheckDone;
	}

}

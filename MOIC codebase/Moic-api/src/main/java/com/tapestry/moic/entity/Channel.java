package com.tapestry.moic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Class Channel.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.CHANNEL)
public class Channel implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@Column(name = MoicConstant.CHANNEL_ID)
	private Integer	channelId;
	
	@Column(name = MoicConstant.CHANNEL_NAME)
	private String	channelName;

	@Column(name = MoicConstant.BUSINESS_UNIT)
	private String businessUnit;

	public Channel() {
		
	}
	
	/**
	 * @param channelId
	 * @param channelName
	 * @param businessUnit
	 */
	public Channel(Integer channelId, String channelName, String businessUnit) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.businessUnit = businessUnit;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}
	
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", channelName=" + channelName + ", businessUnit=" + businessUnit
				+ "]";
	}
}
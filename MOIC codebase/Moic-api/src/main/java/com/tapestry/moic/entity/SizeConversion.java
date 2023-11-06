package com.tapestry.moic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.SIZE_CONVERSION)
public class SizeConversion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = MoicConstant.SIZE_ID)
	private Integer sizeId;
	
	@Column(name = MoicConstant.US_SIZE, length = 10)
	private String usSize;
	
	@Column(name = MoicConstant.UK_SIZE, length = 10)
	private String ukSize;
	
	@Column(name = MoicConstant.EU_SIZE, length = 10)
	private String euSize;

	/**
	 * 
	 */
	public SizeConversion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sizeId
	 * @param usSize
	 * @param ukSize
	 * @param euSize
	 */
	public SizeConversion(Integer sizeId, String usSize, String ukSize, String euSize) {
		super();
		this.sizeId = sizeId;
		this.usSize = usSize;
		this.ukSize = ukSize;
		this.euSize = euSize;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public String getUsSize() {
		return usSize;
	}

	public void setUsSize(String usSize) {
		this.usSize = usSize;
	}

	public String getUkSize() {
		return ukSize;
	}

	public void setUkSize(String ukSize) {
		this.ukSize = ukSize;
	}

	public String getEuSize() {
		return euSize;
	}

	public void setEuSize(String euSize) {
		this.euSize = euSize;
	}

	@Override
	public String toString() {
		return "SizeConversion [sizeId=" + sizeId + ", usSize=" + usSize + ", ukSize=" + ukSize + ", euSize=" + euSize
				+ "]";
	}

	
}

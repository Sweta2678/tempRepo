package com.tapestry.moic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

/**
 * The Class Segment.
 *
 * @version 0.0.1
 */
@Entity
@Table(name = MoicConstant.SEGMENT)
public class Segment implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@Column(name = MoicConstant.SEGMENT_ID)
	private Integer	segmentId;
	
	@Column(name = MoicConstant.SEGMENT_CODE)
	private String	segmentCode;

	public Segment() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param segmentId
	 * @param segmentCode
	 */
	public Segment(Integer segmentId, String segmentCode) {
		super();
		this.segmentId = segmentId;
		this.segmentCode = segmentCode;
	}

	public Integer getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(Integer segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentCode() {
		return segmentCode;
	}

	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}

	@Override
	public String toString() {
		return "Segment [segmentId=" + segmentId + ", SegmentCode=" + segmentCode + "]";
	}
}
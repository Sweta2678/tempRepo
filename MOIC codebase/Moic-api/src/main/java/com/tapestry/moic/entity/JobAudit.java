package com.tapestry.moic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tapestry.moic.constant.MoicConstant;

@Entity
@Table(name = MoicConstant.JOB_AUDIT)
public class JobAudit implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = MoicConstant.JOB_ID)
	private Long jobId;
	
	@Column(name = MoicConstant.JOB_NAME)
	private String jobName;
	
	@Column(name = MoicConstant.JOB_STAUTS)
	private String jobStatus;
	
	@Column(name = MoicConstant.EXECUTION_DATE_TIME)
	private Date executionDateTime; 
	
	@Column(name = MoicConstant.FILE_NAME)
	private String fileName;
	
	@Column(name = MoicConstant.FILE_STATUS)
	private String fileStatus;
	
	@Column(name = MoicConstant.RECORDS_COUNT)
	private Integer recordsCount;
	
	@Column(name = MoicConstant.EXCEPTION_MESSAGE, columnDefinition = "TEXT")
	private String exceptionMessage;

	@Column(name = MoicConstant.DATA_SUMMARY)
	private String dataSummary;
	
	
	public JobAudit() {
		super();
	}


	public JobAudit(Long jobId, String jobName, String jobStatus, Date executionDateTime, String fileName,
			String fileStatus, Integer recordsCount, String exceptionMessage, String dataSummary) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobStatus = jobStatus;
		this.executionDateTime = executionDateTime;
		this.fileName = fileName;
		this.fileStatus = fileStatus;
		this.recordsCount = recordsCount;
		this.exceptionMessage = exceptionMessage;
		this.dataSummary = dataSummary;
	}


	public Long getJobId() {
		return jobId;
	}


	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getJobStatus() {
		return jobStatus;
	}


	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}


	public Date getExecutionDateTime() {
		return executionDateTime;
	}


	public void setExecutionDateTime(Date executionDateTime) {
		this.executionDateTime = executionDateTime;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileStatus() {
		return fileStatus;
	}


	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}


	public Integer getRecordsCount() {
		return recordsCount;
	}


	public void setRecordsCount(Integer recordsCount) {
		this.recordsCount = recordsCount;
	}


	public String getExceptionMessage() {
		return exceptionMessage;
	}


	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	

	public String getDataSummary() {
		return dataSummary;
	}


	public void setDataSummary(String dataSummary) {
		this.dataSummary = dataSummary;
	}


	@Override
	public String toString() {
		return "JobAudit [jobId=" + jobId + ", jobName=" + jobName + ", jobStatus=" + jobStatus + ", executionDateTime="
				+ executionDateTime + ", fileName=" + fileName + ", fileStatus=" + fileStatus + ", recordsCount="
				+ recordsCount + ", exceptionMessage=" + exceptionMessage + ", dataSummary=" + dataSummary + "]";
	}
	
}

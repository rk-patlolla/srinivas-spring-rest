package com.springrest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "group_details")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(value=Include.NON_NULL)
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id")
	@JsonIgnore
	private int groupId;

	//@NotEmpty(message = "Group Name should not empty ")
	@Column(unique = true, name = "group_name")
	@JsonProperty("GroupName")

	private String groupName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	@CreatedDate
	@JsonIgnore
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@JsonIgnore
	@LastModifiedDate
	private Date updatedDate;

	@Column(name = "status_flag", updatable = false)
	@JsonIgnore
	private boolean status;

	@CreatedBy
	@Column(name="created_by",nullable = false, updatable = false)
	@JsonIgnore
	private String createdBy;

	@LastModifiedBy
	@Column(name="modified_by",nullable = false)
	@JsonIgnore
	private String modifiedBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", status=" + status + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + "]";
	}

	

}

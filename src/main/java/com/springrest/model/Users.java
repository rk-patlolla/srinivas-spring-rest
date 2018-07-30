package com.springrest.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "group_users_details")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(value=Include.NON_NULL)
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	@JsonIgnore
	private int userId;

	@NotEmpty(message = "User Name should not empty ")
	@Column(unique = true, name = "user_name")
	@JsonProperty("userName")
	private String userName;

	@NotEmpty
	@Email
	@Column(unique = true, name = "user_email")
	private String userEmail;

	@NotEmpty()
	@Size(min = 10, max = 10, message = "please enter 10 digit Mobile Number")
	@Column(unique = true, name = "user_mobile")
	private String userMobile;

	@Column(name = "user_role")
	private String role;
	
	@Column(name = "user_password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_date", updatable = false)
	@JsonIgnore
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@JsonIgnore
	@LastModifiedDate
	private Date updatedDate;
	@Column(name = "status_flag")
	@JsonIgnore
	private boolean status;
	@CreatedBy
	@JsonIgnore
	@Column(name = "created_by", nullable = false, updatable = false)
	private String createdBy;

	@LastModifiedBy
	@JsonIgnore
	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "group_id", updatable = false)
	@JsonIgnoreProperties(value= {"groupId","createdDate","updatedDate","createdBy","modifiedBy","status"})
	private Group groupModel;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Group getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(Group groupModel) {
		this.groupModel = groupModel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + ", role=" + role + ", password=" + password + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", status=" + status + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", groupModel=" + groupModel + "]";
	}

}
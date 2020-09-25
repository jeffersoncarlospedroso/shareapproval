package br.com.dev2j.shareapproval.api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
public class Upload implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String path;
	
	@NotNull
	private String description;

	private Date uploadDate;
	
	private Date approvalDate;
	
			
	private boolean waitingApproval;
	
	private boolean Approved;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userUploaderId", nullable = false)
	@JsonIgnoreProperties({"uploads", "type", "enabled", "authorities", "password", "email", "datacadastro", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
	private User userUploader;

	public Upload() {
	}

	public Upload(Long id, @NotNull String path, @NotNull String description, Date uploadDate, Date approvalDate,
			boolean waitingApproval, boolean approved, User userUploader) {
		super();
		this.id = id;
		this.path = path;
		this.description = description;
		this.uploadDate = uploadDate;
		this.approvalDate = approvalDate;
		this.waitingApproval = waitingApproval;
		Approved = approved;
		this.userUploader = userUploader;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public boolean isWaitingApproval() {
		return waitingApproval;
	}

	public void setWaitingApproval(boolean waitingApproval) {
		this.waitingApproval = waitingApproval;
	}

	public boolean isApproved() {
		return Approved;
	}

	public void setApproved(boolean approved) {
		Approved = approved;
	}

	public User getUserUploader() {
		return userUploader;
	}

	public void setUserUploader(User userUploader) {
		this.userUploader = userUploader;
	}



}

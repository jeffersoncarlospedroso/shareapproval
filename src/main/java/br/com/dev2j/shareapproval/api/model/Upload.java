package br.com.dev2j.shareapproval.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
public class Upload implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Type(type="uuid-char")
    @Column(name = "id", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
    private UUID id;

	@NotNull
	private String path;
	
	@NotNull
	private String fileName;
	
	@NotNull
	private String description;

	private Date uploadDate;
	
	private Date approvalDate;
			
	private boolean waitingApproval;
	
	private boolean approved;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userUploaderId", nullable = false)
	@JsonIgnoreProperties({"uploads", "type", "enabled", "authorities", "password", "email", "datacadastro", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
	private User userUploader;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userApprovalId", nullable = true)
	@JsonIgnoreProperties({"uploads", "type", "enabled", "authorities", "password", "email", "datacadastro", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
	private User userApproval;
	
	private String approvalObservation;

	public Upload() {
		
	}

	public Upload(UUID id, @NotNull String path, @NotNull String fileName, @NotNull String description, Date uploadDate,
			Date approvalDate, boolean waitingApproval, boolean approved, User userUploader, User userApproval,
			String approvalObservation) {
		super();
		this.id = id;
		this.path = path;
		this.fileName = fileName;
		this.description = description;
		this.uploadDate = uploadDate;
		this.approvalDate = approvalDate;
		this.waitingApproval = waitingApproval;
		this.approved = approved;
		this.userUploader = userUploader;
		this.userApproval = userApproval;
		this.approvalObservation = approvalObservation;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public User getUserUploader() {
		return userUploader;
	}

	public void setUserUploader(User userUploader) {
		this.userUploader = userUploader;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public User getUserApproval() {
		return userApproval;
	}

	public void setUserApproval(User userApproval) {
		this.userApproval = userApproval;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprovalObservation() {
		return approvalObservation;
	}

	public void setApprovalObservation(String approvalObservation) {
		this.approvalObservation = approvalObservation;
	}

}

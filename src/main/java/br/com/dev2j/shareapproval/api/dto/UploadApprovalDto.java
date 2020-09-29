package br.com.dev2j.shareapproval.api.dto;
import javax.validation.constraints.NotNull;


import java.io.Serializable;
import java.util.UUID;

public class UploadApprovalDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private UUID id;
	
	@NotNull
	private String approvalObservation;
	
	@NotNull
	private Boolean approved;

	

	public UploadApprovalDto(@NotNull UUID id, @NotNull String approvalObservation, @NotNull Boolean approved) {
		super();
		this.id = id;
		this.approvalObservation = approvalObservation;
		this.approved = approved;
	}

	
	
	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getApprovalObservation() {
		return approvalObservation;
	}

	public void setApprovalObservation(String approvalObservation) {
		this.approvalObservation = approvalObservation;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
  
}

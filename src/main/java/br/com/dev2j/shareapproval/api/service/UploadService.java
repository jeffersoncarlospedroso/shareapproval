package br.com.dev2j.shareapproval.api.service;

import java.util.List;

import br.com.dev2j.shareapproval.api.dto.UploadApprovalDto;
import br.com.dev2j.shareapproval.api.dto.UploadFileDto;
import br.com.dev2j.shareapproval.api.model.Upload;

public interface UploadService {

	Upload sendFile(UploadFileDto file);
	Upload getFile(String email);
	List<Upload> waitingApproval(Boolean approved);
	void approvalUpload(UploadApprovalDto uploadApprovalDTO);

}

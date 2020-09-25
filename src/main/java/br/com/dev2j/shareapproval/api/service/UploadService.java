package br.com.dev2j.shareapproval.api.service;

import br.com.dev2j.shareapproval.api.dto.UploadFileDto;
import br.com.dev2j.shareapproval.api.model.Upload;

public interface UploadService {

	Upload sendFile(UploadFileDto file);
	Upload getFile(String email);
	Upload approveUpload(String email);
	

}

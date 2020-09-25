package br.com.dev2j.shareapproval.api.dto;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;



public class UploadFileDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private MultipartFile file;
	

	private String fileName;

	private String description;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

  
	
	
 
}

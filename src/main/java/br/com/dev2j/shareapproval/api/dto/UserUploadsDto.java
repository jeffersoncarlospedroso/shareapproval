package br.com.dev2j.shareapproval.api.dto;

import java.io.Serializable;
import java.util.Set;
import br.com.dev2j.shareapproval.api.model.Upload;


public class UserUploadsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
	private Set<Upload> uploads;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Set<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}



	
 
}

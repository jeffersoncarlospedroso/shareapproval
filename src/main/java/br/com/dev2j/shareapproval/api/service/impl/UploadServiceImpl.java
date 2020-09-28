package br.com.dev2j.shareapproval.api.service.impl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.dev2j.shareapproval.api.dto.UploadFileDto;
import br.com.dev2j.shareapproval.api.model.Upload;
import br.com.dev2j.shareapproval.api.repository.UploadRepository;
import br.com.dev2j.shareapproval.api.service.UploadService;
import br.com.dev2j.shareapproval.api.utils.AuthenticationUtils;


@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadRepository repository;
    
   
    @Autowired
    private ModelMapper mapper;


	@Override
	public Upload sendFile(UploadFileDto file) {
		
		 try {
		       
		       String path =  saveUploadedFile(file.getFile());
		        
		        Upload up = new Upload();
		        up.setId(null);
		        up.setApprovalDate(null);
		        up.setDescription(file.getDescription());
		        up.setPath(path);
		        
		        up.setUploadDate(new Date());
		        up.setUserUploader(AuthenticationUtils.getAuthenticatedUser());
		        up.setWaitingApproval(true);
		        up.setFileName(file.getFileName());
		        repository.save(up);
		        return up;
		       
		    } catch (IOException e) {
		        return null;
		    }
		
	}

	private String saveUploadedFile(MultipartFile file) throws IOException {
	    if (!file.isEmpty()) {
	    	
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get("" + file.getOriginalFilename());
	        Files.write(path, bytes);
	        return path.toString();
	    }
		return null;
	}

	@Override
	public Upload getFile(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Upload approveUpload(String email) {
		// TODO Auto-generated method stub
		return null;
	}
    


 
	


}

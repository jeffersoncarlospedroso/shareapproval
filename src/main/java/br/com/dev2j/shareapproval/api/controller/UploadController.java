package br.com.dev2j.shareapproval.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dev2j.shareapproval.api.dto.UploadApprovalDto;
import br.com.dev2j.shareapproval.api.dto.UploadFileDto;
import br.com.dev2j.shareapproval.api.model.Upload;
import br.com.dev2j.shareapproval.api.service.UploadService;
import br.com.dev2j.shareapproval.api.utils.AuthenticationUtils;



@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

		
    	@GetMapping(value = "/waitingApproval/{approved}")
    	public ResponseEntity<List<Upload>> waitingApproval(@PathVariable Boolean approved) {
    		
    				
    		AuthenticationUtils.checkApprovalPermission();
    		
    		return ResponseEntity.ok(uploadService.waitingApproval(approved)); 
	  }
	 
    
	  @PostMapping
	  public ResponseEntity<?> save(@ModelAttribute UploadFileDto file) {
		  Upload upload = uploadService.sendFile(file);
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(upload.getId()).toUri();
		  return ResponseEntity.created(uri).build();
	  }
	
	    @PatchMapping(value = "/approve")
	    public ResponseEntity<Void> changePasswordToken(@RequestBody final UploadApprovalDto uploadApprovalDTO) {
	    	
	    	AuthenticationUtils.checkApprovalPermission();
	    	uploadService.approvalUpload(uploadApprovalDTO);
	    	
	    	return ResponseEntity.ok().build();
	    }
    
}

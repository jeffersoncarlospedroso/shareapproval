package br.com.dev2j.shareapproval.api.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.dev2j.shareapproval.api.dto.UploadFileDto;
import br.com.dev2j.shareapproval.api.model.Upload;
import br.com.dev2j.shareapproval.api.service.UploadService;


@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

	/*
	 * @GetMapping(value = "/self") public ResponseEntity<UserDto> self() { User
	 * user = AuthenticationUtils.getAuthenticatedUser(); return
	 * ResponseEntity.ok(userService.findById(user.getId())); }
	 */
	  @PostMapping
	  public ResponseEntity<?> save(@ModelAttribute UploadFileDto file) {
		  Upload upload = uploadService.sendFile(file);
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(upload.getId()).toUri();
		  return ResponseEntity.created(uri).build();
	  }
	

    
}

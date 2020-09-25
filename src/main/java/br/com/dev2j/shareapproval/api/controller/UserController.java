package br.com.dev2j.shareapproval.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.dev2j.shareapproval.api.dto.UserDto;
import br.com.dev2j.shareapproval.api.dto.UserNewDto;
import br.com.dev2j.shareapproval.api.dto.UserUploadsDto;
import br.com.dev2j.shareapproval.api.model.User;
import br.com.dev2j.shareapproval.api.service.UserService;
import br.com.dev2j.shareapproval.api.utils.AuthenticationUtils;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/self")
    public ResponseEntity<UserDto> self() {
        User user = AuthenticationUtils.getAuthenticatedUser();
        return ResponseEntity.ok(userService.findById(user.getId()));
    }
    
    @GetMapping(value = "/selfUploads")
    public ResponseEntity<UserUploadsDto> selfUploads() {
        User user = AuthenticationUtils.getAuthenticatedUser();
        return ResponseEntity.ok(userService.findUploadsById(user.getId()));
    }
    
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid final UserNewDto userNewDto) {
		User user = userService.createUserAdmin(userNewDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

    
}

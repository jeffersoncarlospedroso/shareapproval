package br.com.dev2j.shareapproval.api.service;

import br.com.dev2j.shareapproval.api.dto.UserDto;
import br.com.dev2j.shareapproval.api.dto.UserNewDto;
import br.com.dev2j.shareapproval.api.dto.UserUploadsDto;
import br.com.dev2j.shareapproval.api.model.User;

public interface UserService {

    UserDto findById(final Long id);
    
    UserUploadsDto findUploadsById(final Long id);
    
    User createUserAdmin(final UserNewDto userNewDto);
    User createUserApproval(final UserNewDto userNewDto);
    User createUserUploader(final UserNewDto userNewDto);
    

}

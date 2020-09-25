package br.com.dev2j.shareapproval.api.service.impl;


import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.dev2j.shareapproval.api.dto.UserDto;
import br.com.dev2j.shareapproval.api.dto.UserNewDto;
import br.com.dev2j.shareapproval.api.dto.UserUploadsDto;
import br.com.dev2j.shareapproval.api.model.User;
import br.com.dev2j.shareapproval.api.repository.UserRepository;
import br.com.dev2j.shareapproval.api.service.UserService;
import br.com.dev2j.shareapproval.api.utils.Roles;
import br.com.dev2j.shareapproval.api.utils.UserType;
import br.com.dev2j.shareapproval.exception.DataIntegrityException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto findById(final Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Usuario nao encontrado com o id informado. " + id));
        return mapper.map(user, UserDto.class);
    }


	
	private User createUsuario(final User user, final UserType type) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setType(type);
		user.setActivationCode(createActivationCode());
		user.setEnabled(true);
		userRepository.save(user);
		return user;
	}
	
	private String createActivationCode() {
		return UUID.randomUUID().toString();
	}



	@Override
	public User createUserAdmin(UserNewDto userNewDto) {
		User usuario = mapper.map(userNewDto, User.class);
		usuario.addRole(Roles.ADMIN);
        return createUsuario(usuario, UserType.ADMIN);
	}


	@Override
	public User createUserApproval(UserNewDto userNewDto) {
		User usuario = mapper.map(userNewDto, User.class);
		usuario.addRole(Roles.APPROVAL);
        return createUsuario(usuario, UserType.APPROVAL);
	}


	@Override
	public User createUserUploader(UserNewDto userNewDto) {
		User usuario = mapper.map(userNewDto, User.class);
		usuario.addRole(Roles.UPLOADER);
        return createUsuario(usuario, UserType.UPLOADER);
	}



	@Override
	public UserUploadsDto findUploadsById(final Long id) {
		 User user = userRepository.findById(id)
	                .orElseThrow(() -> new DataIntegrityException("Usuario nao encontrado - " + id));
	        return mapper.map(user, UserUploadsDto.class);
	}


	


}

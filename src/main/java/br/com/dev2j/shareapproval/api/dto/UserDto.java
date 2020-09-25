package br.com.dev2j.shareapproval.api.dto;

import java.io.Serializable;

import br.com.dev2j.shareapproval.api.utils.UserType;


public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String email;
    private UserType usertype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
 
}

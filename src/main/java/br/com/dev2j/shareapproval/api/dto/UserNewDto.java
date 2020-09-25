package br.com.dev2j.shareapproval.api.dto;

import javax.validation.constraints.NotNull;

import br.com.dev2j.shareapproval.api.utils.UserType;

import java.io.Serializable;

public class UserNewDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String email;
	
	@NotNull
	private String login;

	@NotNull
	private String password;

    @NotNull
    private UserType type;


	public UserNewDto(@NotNull String email, @NotNull String login, @NotNull String password, @NotNull UserType type) {
		super();
		this.email = email;
		this.login = login;
		this.password = password;
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

  
}

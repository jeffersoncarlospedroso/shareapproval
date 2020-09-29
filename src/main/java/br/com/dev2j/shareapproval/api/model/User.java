package br.com.dev2j.shareapproval.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.dev2j.shareapproval.api.utils.Roles;
import br.com.dev2j.shareapproval.api.utils.UserType;



@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String password;
	
	private Date datacadastro;
	
	@JsonIgnore
	private String activationCode;
		
	private boolean enabled;
	
	@NotNull
	private Integer type;
	
	@OneToMany(mappedBy="userUploader", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("userUploader")
	private Set<Upload> uploads;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ROLES")
	private List<String> roles = new ArrayList<>();

	public User() {
	}


	public User(Long id, @NotNull String email, @NotNull String password, Date datacadastro, String activationCode,
			boolean enabled, @NotNull Integer type, Set<Upload> uploads, List<String> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.datacadastro = datacadastro;
		this.activationCode = activationCode;
		this.enabled = enabled;
		this.type = type;
		this.uploads = uploads;
		this.roles = roles;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public UserType getType() {
		return UserType.toEnum(type);
	}

	public void setType(UserType type) {
		this.type = type.getId();
	}

	public void addRole(Roles role) {
		this.roles.add(role.getDesc());
	}
	
	

	public List<String> getRoles() {
		return roles;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Date getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		
		Date data = new Date();
		this.datacadastro = data;
	}
	
	
	public Set<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles == null) {
			return Collections.emptyList();
		}
		return roles.stream().map(role -> (GrantedAuthority) () -> role).collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

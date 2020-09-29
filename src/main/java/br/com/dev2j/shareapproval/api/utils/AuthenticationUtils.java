package br.com.dev2j.shareapproval.api.utils;

import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.dev2j.shareapproval.api.model.User;
import br.com.dev2j.shareapproval.exception.AuthorizationException;


public class AuthenticationUtils {

	public static User getAuthenticatedUser() {
		return (User) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.orElseThrow(() -> new AuthorizationException("Acess denied."));
	}


	public static User checkAndGetAuthenticatedUser(final Long userId) {
		User user = getAuthenticatedUser();

		if (userId != null && !userId.equals(user.getId())) {
			throw new AuthorizationException("Nao eh possivel atualizar item com id diferente do usuario autenticado.");
		}

		return user;
	}

	public static User checkApprovalPermission() {
		User user = getAuthenticatedUser();
		System.out.println("Role - "+ user.getType());

		if ( (user.getType().equals(UserType.ADMIN)) || (user.getType().equals(UserType.APPROVAL))) {
			return user;
		}else {
			throw new AuthorizationException("Nao eh visualizar item com esse perfil.");
		}

		
	}

}

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


	public static User checkAndGetAuthenticatedUser(final Long usuarioId) {
		User usuario = getAuthenticatedUser();

		if (usuarioId != null && !usuarioId.equals(usuario.getId())) {
			throw new AuthorizationException("Nao eh possivel atualizar item com id diferente do usuario autenticado.");
		}

		return usuario;
	}


}

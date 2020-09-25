package br.com.dev2j.shareapproval.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(WebSecurityConfiguration.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired 
    private TokenStore tokenStore;

    
    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientID;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;
    
 //   @Bean
 //   public TokenStore tokenStore() {
 //       return new InMemoryTokenStore();
 //   }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	clients
	        .inMemory()
	        .withClient(clientID)
	     //   .resourceIds("ythoteis-api")
	        .secret(passwordEncoder.encode(clientSecret))
	        .scopes("read", "write")
	        .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
	        .authorities("ROLE_AGENTE")
	        .accessTokenValiditySeconds(172800)
	        .refreshTokenValiditySeconds(25920000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
 //       endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        endpoints.tokenStore(this.tokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailsService);

    }

  
}
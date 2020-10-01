package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig
  extends AuthorizationServerConfigurerAdapter {
  static final String CLIENT_ID = System.getenv("CLIENT_ID");
  static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
  static final String GRANT_TYPE_PASSWORD = "password";
  static final String SCOPE_READ = "read";
  static final String SCOPE_WRITE = "write";
  static final String TRUST = "trust";
  static final int ACCESS_TOKEN_VALIDITY_SECONDS = -1;
  static final String AUTHORIZATION_CODE = "authorization_code";

  private final TokenStore tokenStore;

  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  public AuthorizationServerConfig(
    TokenStore tokenStore,
    AuthenticationManager authenticationManager,
    PasswordEncoder passwordEncoder
  ) {
    this.tokenStore = tokenStore;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients)
    throws Exception {
    clients
      .inMemory()
      .withClient(CLIENT_ID)
      .secret(passwordEncoder.encode(CLIENT_SECRET))
      .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE)
      .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
      .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
      .tokenStore(tokenStore)
      .authenticationManager(authenticationManager);
    endpoints.pathMapping("/oauth/token", "/login");
  }
}

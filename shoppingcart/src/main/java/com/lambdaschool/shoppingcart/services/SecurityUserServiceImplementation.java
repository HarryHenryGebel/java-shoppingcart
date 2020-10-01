package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceFoundException;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "securityUserService")
public class SecurityUserServiceImplementation implements UserDetailsService {
  private final UserRepository userRepository;

  public SecurityUserServiceImplementation(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String s)
    throws ResourceFoundException {
    User user = userRepository.findAllByUsername(s);
    if (user == null) {
      throw new ResourceFoundException("Username or password is incorrect");
    }
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      user.getAuthority()
    );
  }
}

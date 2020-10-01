package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findAllByUsername(String s);
}

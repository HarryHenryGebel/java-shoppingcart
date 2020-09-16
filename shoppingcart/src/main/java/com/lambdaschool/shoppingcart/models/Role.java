package com.lambdaschool.shoppingcart.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends Auditable {
  @Id
  @GeneratedValue
  private long roleId;

  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();

  public Role() {}

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

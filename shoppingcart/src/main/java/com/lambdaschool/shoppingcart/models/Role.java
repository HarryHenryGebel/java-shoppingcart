package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties(value = "role", allowSetters = true)
  private Set<UserRoles> users = new HashSet<>();

  public Role() {}

  public Set<UserRoles> getUsers() {
    return users;
  }

  public void setUsers(Set<UserRoles> users) {
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

package com.lambdaschool.shoppingcart.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends Auditable {
  @Id
  @GeneratedValue
  private long roleId;

  private long name;

  public Role() {}

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public long getName() {
    return name;
  }

  public void setName(long name) {
    this.name = name;
  }
}

package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles extends Auditable implements Serializable {
  @Id
  @ManyToOne
  @JoinColumn(name = "userid")
  @JsonIgnoreProperties(value = "roles", allowSetters = true)
  private User user;

  @Id
  @ManyToOne
  @JoinColumn(name = "role_id")
  @JsonIgnoreProperties(value = "users", allowSetters = true)
  private Role role;

  public UserRoles() {}

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public int hashCode() {
    return 14;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || this.getClass() != object.getClass()) return false;
    UserRoles that = (UserRoles) object;
    if (
      (this.user == null || that.user == null) ||
      (this.role == null || that.role == null)
    ) return false;
    return (
      this.user.getUserid() == that.user.getUserid() &&
      this.role.getRoleId() == that.role.getRoleId()
    );
  }
}

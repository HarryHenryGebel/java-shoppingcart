package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userid;

  @Column(nullable = false, unique = true)
  private String username;

  private String comments;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnoreProperties(value = "user", allowSetters = true)
  private List<Cart> carts = new ArrayList<>();

  @ManyToMany
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "userid"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  public User() {}

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public List<Cart> getCarts() {
    return carts;
  }

  public void setCarts(List<Cart> carts) {
    this.carts = carts;
  }
}

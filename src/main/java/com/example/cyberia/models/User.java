package com.example.cyberia.models;

import com.example.cyberia.models.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Column(unique = true, updatable = false)
    private String email;
    @Getter
    private String phoneNumber;
    @Getter
    private String name;
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn
    //private Image avatar;
    @Getter
    private String legalAddress;
    private boolean active;
    private String activationCode;
    @Column(length = 1000)
    private String password;

    @Getter
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    mappedBy = "user")
    private List<Tour> tours = new ArrayList<>();

    public void addTourToUser(Tour tour) {
        tour.setUser(this);
        tours.add(tour);
    }
    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }
    public boolean isSeller() {
        return roles.contains(Role.ROLE_SELLER);
    }

    //public Image getAvatar() {
       // return avatar;
    //}

    //public void setAvatar(Image avatar) {
     //   this.avatar = avatar;
    //}

    public void setProducts(List<Tour> tours) {
        this.tours = tours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    // security config

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

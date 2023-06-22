package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "user_tb",uniqueConstraints = @UniqueConstraint(
        name = "username",
        columnNames = "username"
))
public class User implements Serializable, UserDetails {

    private static final Long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role_aux",
            joinColumns = @JoinColumn(
                    name = "id_user",
                    referencedColumnName = "id_user"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_role",
                    referencedColumnName = "id_role"
            )
    )
    private List<Role>roleList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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
        return true;
    }
}

package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<User>userList;
}

package com.roadmap.expense_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roadmap.expense_tracker.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Expense> expenses;
    @Enumerated(EnumType.STRING)
    private Role role;

}

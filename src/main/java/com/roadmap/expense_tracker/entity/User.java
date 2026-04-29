package com.roadmap.expense_tracker.entity;

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
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

}

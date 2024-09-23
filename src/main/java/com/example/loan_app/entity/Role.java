package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = PathDb.ROLES)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private ERole role;
}

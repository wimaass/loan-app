package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = PathDb.USER)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AuditEntity{
    private String email;
    private String password;
    private List<Role> roles;
}

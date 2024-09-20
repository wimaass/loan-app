package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class Role extends AuditEntity{
    private ERole role;
}

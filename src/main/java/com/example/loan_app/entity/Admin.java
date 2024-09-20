package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = PathDb.ADMIN)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin extends AuditEntity{
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String status;

    @OneToOne
    private User user;
}

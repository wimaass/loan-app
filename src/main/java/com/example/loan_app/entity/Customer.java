package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Date;

@Entity
@Table(name = PathDb.CUSTOMER)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends AuditEntity{
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
    @Enumerated(EnumType.STRING)
    private User user;
}

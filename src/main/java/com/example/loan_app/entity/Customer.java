package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private Date dateOfBirth;

    private String phone;
    private String status;

//    private AppUser;
}

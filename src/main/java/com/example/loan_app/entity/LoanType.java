package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = PathDb.LOAN_TYPE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanType extends AuditEntity{
    private String type;
    private Double maxLoan;
}

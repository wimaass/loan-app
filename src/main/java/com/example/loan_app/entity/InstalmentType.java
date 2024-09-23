package com.example.loan_app.entity;

import com.example.loan_app.constant.PathDb;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = PathDb.INSTALMENT_TYPE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private EInstalmentType instalmentType;
}

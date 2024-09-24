package com.example.loan_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
public enum EInstalmentType {
    ONE_MONTH(1),
    THREE_MONTHS(3),
    SIX_MONTHS(6),
    NINE_MONTHS(9),
    TWELVE_MONTHS(12)
    ;
    private final int month;

}

package com.example.loan_app.constant;

public class PathApi {
    public static final String LOAN_APP = "/api/v1";
    public static final String GET_ID = "/{id}";

    public static final String CUSTOMER_API = LOAN_APP + "/customers";

    public static final String AUTHENTICATE_API = LOAN_APP + "/auth";
    public static final String SIGN_UP_API= "/signup";
    public static final String SIGN_IN = "/signin";

    public static final String INSTALMENT_TYPE_API = LOAN_APP + "/instalment-types";

    public static final String LOAN_TYPE_API = LOAN_APP + "/loan-types";

    public static final String TRANSACTION_API = LOAN_APP + "/transactions";

}

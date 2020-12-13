package com.example.api_crud_mock;

public class User {
    private String id;
    private String MSSV;
    private String NAME;
    private String EMAIL;


    public User(String id, String MSSV, String NAME, String EMAIL) {
        this.id = id;
        this.MSSV = MSSV;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

}

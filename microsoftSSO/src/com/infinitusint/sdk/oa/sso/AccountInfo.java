package com.infinitusint.sdk.oa.sso;

import java.io.Serializable;

/**
 * 用户帐号信息
 */
public class AccountInfo implements Serializable {

    private String Account;

    private String Name;

    private String UPN;

    private String TokenTime;

    private  String EmployeeID;

    private String Email;

    private String Random;


    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUPN() {
        return UPN;
    }

    public void setUPN(String UPN) {
        this.UPN = UPN;
    }

    public String getTokenTime() {
        return TokenTime;
    }

    public void setTokenTime(String tokenTime) {
        TokenTime = tokenTime;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRandom() {
        return Random;
    }

    public void setRandom(String random) {
        Random = random;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "Account='" + Account + '\'' +
                ", Name='" + Name + '\'' +
                ", UPN='" + UPN + '\'' +
                ", TokenTime='" + TokenTime + '\'' +
                ", EmployeeID='" + EmployeeID + '\'' +
                ", Email='" + Email + '\'' +
                ", Random='" + Random + '\'' +
                '}';
    }
}

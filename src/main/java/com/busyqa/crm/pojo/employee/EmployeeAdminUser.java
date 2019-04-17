package com.busyqa.crm.pojo.employee;

import javax.persistence.*;

@Entity
@Table(name = "JobPostUser")
public class EmployeeAdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String password;


    @Enumerated(EnumType.ORDINAL)
    private EmployeeRole role;


    public EmployeeAdminUser() {
    }

    public EmployeeAdminUser(String userName, String password, EmployeeRole role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeAdminUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }


}

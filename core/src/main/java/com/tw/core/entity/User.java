package com.tw.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;
    private String password;
    private Employee employee;

    public User() {

    }

    public User(String name, String password, Employee employee) {
        this.name = name;
        this.password = password;
        this.employee = employee;
    }

    public User(int id, String name, String password, Employee employee) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.employee = employee;
    }

    @OneToOne
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

package com.tw.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    private int id;
    private String name;
    private Employee employee;

    public Course() {
    }

    public Course(String name, Employee employee) {
        this.name = name;
        this.employee = employee;
    }

    public Course(int id, String name, Employee employee) {
        this.id = id;
        this.name = name;
        this.employee = employee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

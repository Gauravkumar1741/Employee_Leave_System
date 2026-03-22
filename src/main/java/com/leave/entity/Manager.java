package com.leave.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Manager {

    @Id
    @GeneratedValue
    private int managerId;

    private String name;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees = new ArrayList<>();

    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Employee> getEmployees() { return employees; }
}
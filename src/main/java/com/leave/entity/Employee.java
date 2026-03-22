package com.leave.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int empId;

    private String name;

    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<LeaveRequest> leaves = new ArrayList<>();

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }

    public List<LeaveRequest> getLeaves() { return leaves; }
}
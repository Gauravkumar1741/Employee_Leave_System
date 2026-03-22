package com.leave.service;

import com.leave.entity.*;
import com.leave.util.JPAUtil;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

public class LeaveService {

    public void addManager(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Manager m = new Manager();
        m.setName(name);

        em.persist(m);
        em.getTransaction().commit();
        em.close();

        System.out.println("Manager added with ID: " + m.getManagerId());
    }

    public void addEmployee(String name, int managerId) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Manager m = em.find(Manager.class, managerId);

        if (m == null) {
            System.out.println("Manager not found!");
            em.getTransaction().rollback();
            em.close();
            return;
        }

        Employee e = new Employee();
        e.setName(name);
        e.setManager(m);

        em.persist(e);
        em.getTransaction().commit();
        em.close();

        System.out.println("Employee added with ID: " + e.getEmpId());
    }

    public void applyLeave(int empId, LocalDate start, LocalDate end) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Employee e = em.find(Employee.class, empId);

        if (e == null) {
            System.out.println("Employee not found!");
            em.getTransaction().rollback();
            em.close();
            return;
        }

        LeaveRequest lr = new LeaveRequest();
        lr.setEmployee(e);
        lr.setStartDate(start);
        lr.setEndDate(end);
        lr.setStatus("PENDING");

        em.persist(lr);
        em.getTransaction().commit();
        em.close();

        System.out.println("Leave applied successfully. ID: " + lr.getLeaveId());
    }

    public void updateLeaveStatus(int leaveId, String status) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        LeaveRequest lr = em.find(LeaveRequest.class, leaveId);

        if (lr == null) {
            System.out.println("Leave request not found!");
            em.getTransaction().rollback();
            em.close();
            return;
        }

        lr.setStatus(status.toUpperCase());

        em.getTransaction().commit();
        em.close();

        System.out.println("Leave " + status + " successfully.");
    }

    public void viewLeaveHistory(int empId) {
        EntityManager em = JPAUtil.getEntityManager();

        List<LeaveRequest> list = em.createQuery(
                "from LeaveRequest where employee.empId=:id", LeaveRequest.class)
                .setParameter("id", empId)
                .getResultList();

        list.forEach(l ->
                System.out.println("LeaveID: " + l.getLeaveId()
                        + " | From: " + l.getStartDate()
                        + " | To: " + l.getEndDate()
                        + " | Status: " + l.getStatus())
        );

        em.close();
    }
}
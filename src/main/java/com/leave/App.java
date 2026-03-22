package com.leave;

import com.leave.service.LeaveService;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LeaveService service = new LeaveService();

        while (true) {

            System.out.println("\n1.Add Manager");
            System.out.println("2.Add Employee");
            System.out.println("3.Apply Leave");
            System.out.println("4.Approve Leave");
            System.out.println("5.Reject Leave");
            System.out.println("6.View Leave History");
            System.out.println("7.Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    sc.nextLine();
                    System.out.print("Manager Name: ");
                    service.addManager(sc.nextLine());
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Employee Name: ");
                    String ename = sc.nextLine();
                    System.out.print("Manager ID: ");
                    int mid = sc.nextInt();
                    service.addEmployee(ename, mid);
                    break;

                case 3:
                    System.out.print("Employee ID: ");
                    int eid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Start Date (yyyy-mm-dd): ");
                    LocalDate start = LocalDate.parse(sc.nextLine());

                    System.out.print("End Date (yyyy-mm-dd): ");
                    LocalDate end = LocalDate.parse(sc.nextLine());

                    service.applyLeave(eid, start, end);
                    break;

                case 4:
                    System.out.print("Leave ID: ");
                    service.updateLeaveStatus(sc.nextInt(), "APPROVED");
                    break;

                case 5:
                    System.out.print("Leave ID: ");
                    service.updateLeaveStatus(sc.nextInt(), "REJECTED");
                    break;

                case 6:
                    System.out.print("Employee ID: ");
                    service.viewLeaveHistory(sc.nextInt());
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}
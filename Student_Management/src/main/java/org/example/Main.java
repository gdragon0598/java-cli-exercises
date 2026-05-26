package org.example;

import service.StudentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");

            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search By ID");
            System.out.println("5. Search By Name");
            System.out.println("6. Display All Students");
            System.out.println("0. Exit");

            System.out.println("Choose:");

            int choice;

            try {

                choice = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {

                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    studentService.addStudent();
                    break;

                case 2:
                    studentService.updateStudent();
                    break;

                case 3:
                    studentService.deleteStudent();
                    break;

                case 4:
                    studentService.searchById();
                    break;

                case 5:
                    studentService.searchByName();
                    break;

                case 6:
                    studentService.displayAllStudents();
                    break;

                case 0:
                    System.out.println("Exit program.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
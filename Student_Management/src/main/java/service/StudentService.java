package service;

import model.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {

    private ArrayList<Student> studentList;
    private Scanner scanner = new Scanner(System.in);
    private int currentId = 1;

    public StudentService() {
        studentList = new ArrayList<>();

        studentList.add(new Student("S01", "Nguyen Van A", 20, 8.5));
        studentList.add(new Student("S02", "Tran Thi B", 21, 7.2));
        studentList.add(new Student("S03", "Le Van C", 19, 9.1));
        studentList.add(new Student("S04", "Pham Thi D", 22, 6.8));
        studentList.add(new Student("S05", "Hoang Van E", 20, 8.0));

        currentId = studentList.size() + 1;
    }

    private String generateId() {
        return String.format("S%02d", currentId++);
    }

    public void addStudent() {
        String id = generateId();
        System.out.println("ID: " + id);

        String name = inputName();
        int age = inputAge();
        double gpa = inputGpa();

        studentList.add(new Student(id, name, age, gpa));

        System.out.println("Add success.");
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("List empty.");
            return;
        }

        System.out.printf("%-10s %-20s %-10s %-10s%n",
                "ID", "NAME", "AGE", "GPA");

        System.out.println("----------------------------------------------");

        for (Student s : studentList) {
            System.out.printf("%-10s %-20s %-10d %-10.2f%n",
                    s.getId(), s.getName(), s.getAge(), s.getGpa());
        }
    }

    public void searchById() {
        System.out.print("Enter id: ");
        String id = scanner.nextLine();

        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                System.out.println(s);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void searchByName() {
        System.out.print("Enter name: ");
        String keyword = scanner.nextLine();

        boolean found = false;

        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void updateStudent() {
        System.out.print("Enter id: ");
        String id = scanner.nextLine();

        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {

                System.out.println("Updating student...");

                s.setName(inputName());
                s.setAge(inputAge());
                s.setGpa(inputGpa());

                System.out.println("Update success.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void deleteStudent() {
        System.out.print("Enter id: ");
        String id = scanner.nextLine();

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id)) {
                studentList.remove(i);
                System.out.println("Delete success.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private String inputName() {
        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            if (name.matches("[a-zA-Z ]+")) {
                return name;
            }

            System.out.println("Name must contain letters only.");
        }
    }

    private int inputAge() {
        while (true) {
            try {
                System.out.print("Enter age: ");
                int age = Integer.parseInt(scanner.nextLine());

                if (age < 1 || age > 100) {
                    System.out.println("Age must be 1-100");
                    continue;
                }

                return age;

            } catch (Exception e) {
                System.out.println("Invalid age.");
            }
        }
    }

    private double inputGpa() {
        while (true) {
            try {
                System.out.print("Enter GPA: ");
                double gpa = Double.parseDouble(scanner.nextLine());

                if (gpa < 0 || gpa > 10) {
                    System.out.println("GPA must be 0-10");
                    continue;
                }

                return gpa;

            } catch (Exception e) {
                System.out.println("Invalid GPA.");
            }
        }
    }
}
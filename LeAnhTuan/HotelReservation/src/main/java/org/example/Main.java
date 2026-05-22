package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Customer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Bắt đầu lấy dữ liệu bằng JPA / Hibernate ---");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotel_pu");
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "select c from Customer c";

            List<Customer> customers = em.createQuery(jpql, Customer.class).getResultList();

            System.out.println("\nDanh sách Customer lấy từ Entity:");
            System.out.println(customers);
            System.out.println("----------------------------------------");
            customers.forEach(c -> {
                System.out.println("Name: " + c.getName() + " | Id: " + c.getId());
            });
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
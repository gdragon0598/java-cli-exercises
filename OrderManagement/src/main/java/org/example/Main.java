package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/OrderManagement",
                        "postgres",
                        "12345"
                )
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();

        var conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Connect DB SUCCESS");
        } else {
            System.out.println("Connect DB FAILED");
        }
    }
}
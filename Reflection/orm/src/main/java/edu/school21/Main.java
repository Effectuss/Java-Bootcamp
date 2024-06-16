package edu.school21;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.annotations.OrmColumn;
import edu.school21.db.DbConfig;
import edu.school21.entities.User;
import edu.school21.manager.OrmManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            DbConfig dbConfig = new DbConfig();

            HikariConfig hikariConfig = configureHikari(dbConfig);

            try (HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
                 OrmManager ormManager = new OrmManager(hikariDataSource)) {

                User user = new User(1L, "asd", "asd", 3);

                System.out.println(user.getFirstName());
                String fieldName = "firstName";

                OrmColumn ormColumn = User.class.getDeclaredField(fieldName).getAnnotation(OrmColumn.class);

                if (ormColumn != null) {
                    System.out.println("Column name: " + ormColumn.name());
                } else {
                    System.out.println("No OrmColumn annotation found on field: " + fieldName);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static HikariConfig configureHikari(DbConfig dbConfig) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbConfig.getUrl());
        hikariConfig.setUsername(dbConfig.getUsername());
        hikariConfig.setPassword(dbConfig.getPassword());
        return hikariConfig;
    }
}

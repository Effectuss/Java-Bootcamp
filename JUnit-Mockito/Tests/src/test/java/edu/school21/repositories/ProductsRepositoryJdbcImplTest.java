package edu.school21.repositories;


import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import repositories.ProductRepository;
import repositories.ProductRepositoryJdbcImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase db;
    private ProductRepository productRepository;

    // TODO создать норм объекты
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = List.of();
    final Product EXPECTED_FIND_BY_ID_PRODUCT;
    final Product EXPECTED_UPDATED_PRODUCT;
    final Product EXPECTED_SAVE_PRODUCT;
    final List<Product> EXPECTED_PRODUCTS_AFTER_DELETE = List.of();
    final


    @BeforeEach
    void init() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productRepository = new ProductRepositoryJdbcImpl(db);
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }
}

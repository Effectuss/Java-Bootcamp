package edu.school21.repositories;


import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import repositories.ProductRepository;
import repositories.ProductRepositoryJdbcImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase db;
    private ProductRepository productRepository;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = List.of(
            Product.builder().id(1L).name("Tomato").price(new BigDecimal("23.30")).build(),
            Product.builder().id(2L).name("Orange").price(new BigDecimal("30.33")).build(),
            Product.builder().id(3L).name("Rice").price(new BigDecimal("33.20")).build(),
            Product.builder().id(4L).name("Computer").price(new BigDecimal("993999.22")).build(),
            Product.builder().id(5L).name("Laptop").price(new BigDecimal("33333.99")).build()
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT =
            Product.builder().id(2L).name("Orange").price(new BigDecimal("30.33")).build();

    final Product EXPECTED_UPDATED_PRODUCT =
            Product.builder().id(3L).name("Rice").price(new BigDecimal("40.00")).build();

    final Product EXPECTED_SAVE_PRODUCT =
            Product.builder().id(6L).name("Phone").price(new BigDecimal("30000.00")).build();

    final List<Product> EXPECTED_PRODUCTS_AFTER_DELETE = List.of(
            Product.builder().id(1L).name("Tomato").price(new BigDecimal("23.30")).build(),
            Product.builder().id(2L).name("Orange").price(new BigDecimal("30.33")).build(),
            Product.builder().id(3L).name("Rice").price(new BigDecimal("33.20")).build(),
            Product.builder().id(5L).name("Laptop").price(new BigDecimal("33333.99")).build()
    );

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

    @Test
    void testFindAllProducts() throws SQLException {
        assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS, productRepository.findAll());
    }

    @Test
    void testFindByIdProduct() throws SQLException {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productRepository.findById(2L).get());
        assertEquals(Optional.empty(), productRepository.findById(15L));
    }

    @Test
    void testUpdateProduct() throws SQLException {
        productRepository.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT, productRepository.findById(3L).get());
    }

    @Test
    void testSaveProduct() throws SQLException {
        productRepository.save(EXPECTED_SAVE_PRODUCT);
        assertEquals(EXPECTED_SAVE_PRODUCT, productRepository.findById(6L).get());
    }

    @Test
    void testDeleteProduct() throws SQLException {
        productRepository.delete(4L);
        assertIterableEquals(EXPECTED_PRODUCTS_AFTER_DELETE, productRepository.findAll());
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }
}

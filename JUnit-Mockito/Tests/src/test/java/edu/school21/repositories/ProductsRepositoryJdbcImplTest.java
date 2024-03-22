package edu.school21.repositories;


import edu.school21.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    // TODO создать норм объекты
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L,"asd",new BigDecimal("11.22"));
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L,"asd",new BigDecimal("11.22"));
}

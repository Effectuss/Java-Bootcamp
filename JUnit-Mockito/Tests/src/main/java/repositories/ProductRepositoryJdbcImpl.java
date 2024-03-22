package repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImpl implements ProductRepository {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM products WHERE id = ?;";
    public static final String UPDATE_PRODUCT_QUERY = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
    public static final String SAVE_PRODUCT_QUERY = "INSERT INTO products (id, name, price) VALUES (?,?, ?);";
    public static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE id = ?;";
    public static final String FIND_ALL_BY_ID_QUERY = "SELECT * FROM products;";
    private final DataSource dataSource;

    public ProductRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ID_QUERY)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(Product.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getBigDecimal("price"))
                            .build());
                }
                return products;
            }
        }
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        try (Connection collections = dataSource.getConnection();
             PreparedStatement preparedStatement = collections.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(Product.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getBigDecimal("price"))
                            .build());
                }
            }
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void save(Product product) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PRODUCT_QUERY)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setBigDecimal(3, product.getPrice());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}

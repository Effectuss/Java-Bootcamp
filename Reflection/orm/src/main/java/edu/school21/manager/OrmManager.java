package edu.school21.manager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class OrmManager implements AutoCloseable {
    private final Connection connection;

    public OrmManager(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
        dropEntityTables();
        initialize();
    }

    private void dropEntityTables() {
    }

    private void initialize() {
        createTable();
    }

    private void createTable() {
    }

    public void save(Object entity) {
    }

    public void update(Object entity) {
    }

    public <T> T findById(Long id, Class<T> clazz) {
        return null;
    }

    public void begin() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }

}

package edu.school21.manager;

import edu.school21.annotations.OrmEntity;
import edu.school21.manager.exception.OrmManagerException;
import edu.school21.utils.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public final class OrmManager implements AutoCloseable {
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS ";

    private final Connection connection;
    private final List<Class<?>> ormAnnotatedEntities;
    private final List<String> entityTableName;

    public OrmManager(DataSource dataSource, String entityPackageName) throws OrmManagerException {
        try {
            this.connection = dataSource.getConnection();
            this.ormAnnotatedEntities = getOrmEntities(entityPackageName);
            this.entityTableName = getEntityTableNames(this.ormAnnotatedEntities);
            dropEntityTables();
            initialize();
        } catch (Exception e) {
            throw new OrmManagerException("The ORM manager can't be created: " + e.getMessage(), e);
        }
    }

    private List<Class<?>> getOrmEntities(String entityPackageName) {
        Reflections reflections = new Reflections(entityPackageName, Scanners.TypesAnnotated);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(OrmEntity.class);
        return new ArrayList<>(annotatedClasses);
    }

    private void dropEntityTables() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (String tableName : entityTableName) {
                String sql = DROP_TABLE_QUERY + tableName + ";";
                log.info(sql);
                statement.addBatch(sql);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.error("Failed to drop tables: {}", e.getMessage(), e);
            throw e;
        }
    }

    private List<String> getEntityTableNames(List<Class<?>> ormAnnotatedEntities) {
        List<String> tables = new ArrayList<>();
        for (Class<?> entity : ormAnnotatedEntities) {
            OrmEntity ormEntity = entity.getAnnotation(OrmEntity.class);
            if (ormEntity != null) {
                tables.add(ormEntity.table());
            }
        }
        return tables;
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

    public void begin() throws OrmManagerException {
        try {
            if (DatabaseUtils.isConnectionAvailable(connection)) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new OrmManagerException("The transaction cant be start!", e);
        }
    }

    public void commit() throws OrmManagerException {
        try {
            if (DatabaseUtils.isConnectionAvailable(connection)) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new OrmManagerException("The commit of transaction cant be complete!", e);
        }
    }

    public void rollback() throws OrmManagerException {
        try {
            if (DatabaseUtils.isConnectionAvailable(connection)) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new OrmManagerException("The rollback of transaction can't be complete!", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (DatabaseUtils.isConnectionAvailable(connection)) {
            connection.close();
        }
    }

}

package edu.school21.manager;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;
import edu.school21.manager.exception.OrmManagerException;
import edu.school21.utils.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public final class OrmManager implements AutoCloseable {
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS ";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS ";

    private final Connection connection;
    private final List<Class<?>> ormAnnotatedEntities;

    public OrmManager(DataSource dataSource, String entityPackageName) throws OrmManagerException {
        try {
            this.connection = dataSource.getConnection();
            this.ormAnnotatedEntities = getOrmEntities(entityPackageName);
            dropEntityTables();
            createEntityTables();
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
            for (Class<?> entity : ormAnnotatedEntities) {
                OrmEntity ormEntity = entity.getAnnotation(OrmEntity.class);
                if (ormEntity != null) {
                    String sql = DROP_TABLE_QUERY + ormEntity.table() + " CASCADE;";
                    log.info(sql);
                    statement.addBatch(sql);
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.error("Failed to drop tables: {}", e.getMessage(), e);
            throw e;
        }
    }

    private void createEntityTables() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (Class<?> entity : ormAnnotatedEntities) {
                OrmEntity ormEntity = entity.getAnnotation(OrmEntity.class);
                if (ormEntity != null) {
                    StringBuilder sql = new StringBuilder(CREATE_TABLE_QUERY + ormEntity.table() + " (");
                    for (Field field : entity.getDeclaredFields()) {
                        OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                        OrmColumnId ormColumnId = field.getAnnotation(OrmColumnId.class);
                        if (ormColumnId != null) {
                            sql.append(field.getName()).append(" SERIAL PRIMARY KEY, ");
                        } else if (ormColumn != null) {
                            sql.append(ormColumn.name()).append(" ").append(getSqlType(field, ormColumn)).append(", ");
                        }
                    }
                    sql.delete(sql.length() - 2, sql.length()).append(");");
                    log.info(sql.toString());
                    statement.addBatch(sql.toString());
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.error("Failed to create tables: {}", e.getMessage(), e);
            throw e;
        }
    }

    private String getSqlType(Field field, OrmColumn ormColumn) {
        Class<?> type = field.getType();
        if (type == String.class) {
            return "VARCHAR(" + ormColumn.length() + ")";
        } else if (type == int.class || type == Integer.class) {
            return "INTEGER";
        } else if (type == double.class || type == Double.class) {
            return "DOUBLE";
        } else if (type == boolean.class || type == Boolean.class) {
            return "BOOLEAN";
        } else if (type == long.class || type == Long.class) {
            return "BIGINT";
        }
        throw new IllegalArgumentException("Unsupported field type: " + type);
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

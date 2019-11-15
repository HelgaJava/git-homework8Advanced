package ru.itpark.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
    void set(PreparedStatement statement) throws SQLException;
}

package ru.itpark.util;

import org.w3c.dom.ls.LSOutput;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {


    public static <T> List<T> executeQuery(String url, String sql, RowMapper<T> mapper) throws SQLException{
        try(
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            List<T> result = new LinkedList<>();
            while(resultSet.next()){
                result.add(mapper.map(resultSet));
            }
            return result;
        }

    }

    public static <T> T execute(String url, String sql, PreparedStatementExecutor<T> executor) {
        try(
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ) {
            return executor.execute(statement);
        } catch (SQLException e){
          e.printStackTrace();
          throw new RuntimeException();
        }


    }

    public static <T> List<T> executeQueryByParameter(String url, String sql, PreparedStatementSetter setter, RowMapper<T> mapper) {
        return  execute(url, sql, stmt->{
            setter.set(stmt);
            try(ResultSet resultSet = stmt.executeQuery();) {
                List<T> result = new LinkedList<>();

                while (resultSet.next()) {
                    result.add(mapper.map(resultSet));
                }
                return result;
            }

            });


    }
 public static int executeInsert(String url, String sql, PreparedStatementSetter setter) {
        return  execute(url, sql, stmt->{
            setter.set(stmt);
            stmt.executeUpdate();
            try(ResultSet resultKey = stmt.getGeneratedKeys();) {
               if(resultKey.next()){
                   return resultKey.getInt(1);
               }
            throw new RuntimeException();
            }

            });


    }


}

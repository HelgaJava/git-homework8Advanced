package ru.itpark.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class HouseService {
    private List<House> houses = new LinkedList<>();


    public List<House> add(String textSql, String url) throws SQLException {

        houses = JdbcTemplate.executeQuery(
                url,
                textSql,
                resultSet -> new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                )
        );
        return houses;
    }

    public List<House> add(String url, int price) {
        String textSql = "select id, price, district, underground from houses where price>=?";

        houses = JdbcTemplate.executeQueryByParameter(
                url,
                textSql,
                stmtp -> stmtp.setInt(1, price),
                resultSet -> new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                )
        );
        return houses;
    }

    public House insertHouse(String url, House house) {
        String textSql = "insert into houses (price, district, underground) values(?,?,?);";
        int id = JdbcTemplate.executeInsert(url, textSql, stmt -> {
            stmt.setInt(1, house.getPrice());
            stmt.setString(2, house.getDistrict());
            stmt.setString(3, house.getUnderground());
        });
        house.setId(id);
        return house;
    }

    public List<House> sortByPrice() {
        houses.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
        return houses;
    }

    public List<House> sortByDistrict() {
        houses.sort((o1, o2) -> o1.getDistrict().compareTo(o2.getDistrict()));
        return houses;
    }

}

package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.service.HouseService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        HouseService houses = new HouseService();
        houses.add
                ("select id, price, district, underground from houses",
                        "jdbc:sqlite:dbHouses");
        {

            List<House> sortHouse = houses.sortByDistrict();
            int indexTo = 1;
            System.out.println("Вывод списка с сортировкой по наименованию района:");
            for (int i = 0; i < sortHouse.size(); i++) {
                System.out.println(sortHouse.subList(i, indexTo));
                indexTo++;

            }
        }

        {
            List<House> sortHouse = houses.sortByPrice();
            int indexTo = 1;
            System.out.println("Вывод списка с сортировкой по цене:");
            for (int i = 0; i < sortHouse.size(); i++) {
                System.out.println(sortHouse.subList(i, indexTo));
                indexTo++;

            }
        }

        List<House> housesSearh = new HouseService().add(
                "select id, price, district, underground from houses where district = 'Вахитовский'",
                "jdbc:sqlite:dbHouses");
        {
            int indexTo = 1;
            System.out.println("Вывод c отбором по району:");
            for (int i = 0; i < housesSearh.size(); i++) {
                System.out.println(housesSearh.subList(i, indexTo));
                indexTo++;

            }
        }


        HouseService housesByPrice = new HouseService();
        housesByPrice.add("jdbc:sqlite:dbHouses", 4000000);

        {
            List<House> sortHouse = housesByPrice.sortByPrice();
            int indexTo = 1;
            System.out.println("Вывод списка с отбором по цене с сортировкой по цене:");
            for (int i = 0; i < sortHouse.size(); i++) {
                System.out.println(sortHouse.subList(i, indexTo));
                indexTo++;

            }
        }

        House newhouse = houses.insertHouse("jdbc:sqlite:dbHouses", new House(0, 4_750_000, "Приволжский", "Дубравная"));
        System.out.println("Новая запись: "+newhouse);
    }



}

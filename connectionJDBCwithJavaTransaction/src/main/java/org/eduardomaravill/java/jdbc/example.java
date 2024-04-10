package org.eduardomaravill.java.jdbc;

import org.eduardomaravill.java.jdbc.models.User;
import org.eduardomaravill.java.jdbc.service.Service;
import org.eduardomaravill.java.jdbc.service.ServiceImpl;

import java.sql.SQLException;

public class example {
    public static void main(String[] args) throws SQLException {
        Service service = new ServiceImpl();
        System.out.println("======= Read all data =======");
        service.readAll().forEach(System.out::println);
        System.out.println("======= Read one data =======");
        System.out.println(service.byId(3L));
        System.out.println("======= Simulate error =======");
        service.save(new User("addsfdsfdsfdsfds" +
                "dsfdsfdsfdsfsdffdsfds+dsfdsfdsfff" +
                "sdfdsfdsfdsfdsfdsfsdfdsfds" +
                "dsfdsfdsfdsfdsfdsfdsf+dsfdsfdsfsdfdsfsdff" +
                "sdfdsfsdfdsfdsfdsfsdfdsavasva","78999"));
    }
}

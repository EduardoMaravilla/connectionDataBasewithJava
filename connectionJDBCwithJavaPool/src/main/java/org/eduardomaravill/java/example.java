package org.eduardomaravill.java;

import org.eduardomaravill.java.models.User;
import org.eduardomaravill.java.repository.Repository;
import org.eduardomaravill.java.repository.TRepositorioImpl;

public class example {
    public static void main(String[] args) {
        System.out.println("==========Read all data======");
        Repository<User> repository= new TRepositorioImpl();
        repository.readAll().forEach(System.out::println);
        System.out.println("==========Read one data======");
        System.out.println(repository.byId(5L));
        /*System.out.println("==========Insert one data======");
        repository.save(new User("user8","password8"));
        System.out.println("==========Update one data======");
        User u = repository.byId(6L);
        u.setUsername("user6*****");
        u.setPassword("password6++++");
        repository.save(u);
        repository.readAll().forEach(System.out::println);*/
    }
}

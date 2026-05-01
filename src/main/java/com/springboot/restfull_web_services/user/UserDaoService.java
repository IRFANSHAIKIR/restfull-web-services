package com.springboot.restfull_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int count = 0;

    static {
        users.add(new User(++count, "Irfan",  LocalDate.now().minusYears(30)) );
        users.add(new User(++count, "Imran",  LocalDate.now().minusYears(31)) );
        users.add(new User(++count, "Javeed",  LocalDate.now().minusYears(34)) );
    }

    public List<User> findAll(){
        return  users;
    }

    public User saveUser(User user){
        user.setId(++count);
        users.add(user);
        return  user;
    }

    public User findUserById(int id){
        return  users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void deleteUser(int id) {

        users.removeIf(user -> user.getId() == id);
    }
}

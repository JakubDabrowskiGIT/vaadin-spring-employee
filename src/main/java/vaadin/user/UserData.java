package vaadin.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import vaadin.security.SecurityConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserData {

    private static List<User> users;

    public List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<>();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            users.add(new User(0, "login1", bCryptPasswordEncoder.encode("password"), "email@email.com", LocalDate.of(2016, 12, 9)));
            users.add(new User(1, "login10", bCryptPasswordEncoder.encode("password"), "kuba@email.com", LocalDate.of(2018, 3, 3)));
            users.add(new User(2, "asd123", bCryptPasswordEncoder.encode("password"), "email@email.com", LocalDate.of(2016, 1, 18)));
            users.add(new User(3, "Kuba", bCryptPasswordEncoder.encode("password"), "email@email.com", LocalDate.of(2011, 9, 23)));
        }
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(User user) {
        User userTemp = getEmployeesById(user.getId());
        userTemp.setEmail(user.getEmail());
        userTemp.setPassword(user.getPassword());
        userTemp.setLogin(user.getLogin());
    }

    private User getEmployeesById(int id) {
        return users.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}
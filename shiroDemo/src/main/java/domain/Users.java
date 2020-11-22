package domain;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String name;
    private String password;

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

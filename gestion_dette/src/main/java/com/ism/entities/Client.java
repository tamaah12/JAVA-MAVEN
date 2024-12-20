package com.ism.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Client {
    private Long id;
    private String Surname;
    private String Telephone;
    private String Address;
    private User user;
    private List<Dette> dettes = new ArrayList<>();

    public Client() {}

    public Client(String surname, String telephone, String address) {
        this.Surname = surname;
        this.Telephone = telephone;
        this.Address = address;
    }

    
    public String getName() {
        return Surname;
    }

    
    public void addDette(Dette dette) {
        this.dettes.add(dette);
    }

}

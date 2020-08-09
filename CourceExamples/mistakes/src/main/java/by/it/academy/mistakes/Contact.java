package by.it.academy.mistakes;

import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String name;
    private String info;


}

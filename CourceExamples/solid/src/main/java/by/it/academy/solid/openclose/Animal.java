package by.it.academy.solid.openclose;

public class Animal {
    private String name;
    private String roar;

    public Animal(String name, String roar) {
        this.name = name;
        this.roar = roar;
    }

    public String getName() {
        return name;
    }

    public String getRoar() {
        return roar;
    }
}

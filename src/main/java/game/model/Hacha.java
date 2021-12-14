package game.model;

public class Hacha {
    private String name;
    private int weight;
    private int damage;

    public Hacha(String name, int weight, int damage) {
        this.name = name;
        this.weight = weight;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + " | Peso: " + weight + " | Daño: " + damage;
    }
}

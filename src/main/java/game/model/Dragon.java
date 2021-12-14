package game.model;

/**
 * Modela un dragón. No es necesario modificar esta clase. Pero debe crear tantas clases como entidades
 * necesite para llevar a cabo esta parte del hito 3. Inclúyalas en el paquete "game.model"
 */
public class Dragon {

    private String name;
    private int life_points;
    private int reward;

    public Dragon(String name, int life, int reward) {
        this.name = name;
        this.life_points = life;
        this.reward = reward;
    }

    public int getLife() {
        return this.life_points;
    }

    public String getName() {
        return this.name;
    }

    public int getReward() {
        return reward;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + " | Vida: " + life_points + " | Recompensa: " + reward;
    }
}

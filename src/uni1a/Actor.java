package uni1a;

public class Actor {
    private String nombre;
    private String papel;

    public Actor(String nombre, String papel) {
        this.nombre = nombre;
        this.papel = papel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPapel() {
        return papel;
    }

    @Override
    public String toString() {
        return "Actor: " + nombre + " (Papel: " + papel + ")";
    }
}
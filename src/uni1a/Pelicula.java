/**
 * Class Pelicula
 */
package uni1a;
import java.util.ArrayList; // para la lista
import java.util.List;      // para la lista

// Subclase Pelicula que extiende de ContenidoAudiovisual
public class Pelicula extends ContenidoAudiovisual {
    private String estudio;
    private List<Actor> actores; // relacion de agregacion
    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
        super(titulo, duracionEnMinutos, genero);
        this.estudio = estudio;
        this.actores = new ArrayList<>(); // inicializa la lista vacia
    }

 // metodo para agregar actores a la pelicula 
    public void agregarActor(Actor actor) {
        if (actor != null) {
            this.actores.add(actor);
        }
    }
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Estudio: " + estudio);
     // mostrar la lista de actores agregados
        if (!actores.isEmpty()) {
            System.out.println("Reparto de Actores:");
            for (Actor actor : actores) {
                System.out.println("- " + actor.getNombre() + " (como " + actor.getPapel() + ")");
            }
        } else {
            System.out.println("Reparto: No hay actores asignados");
        }
        System.out.println();
    }
}
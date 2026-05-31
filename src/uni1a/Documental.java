/**
 * Class Documental
 */
package uni1a;

// Subclase Documental que extiende de ContenidoAudiovisual
public class Documental extends ContenidoAudiovisual {
    private String tema;
    private Investigador investigador; // relacion de asociacion
    
    public Documental(String titulo, int duracionEnMinutos, String genero, String tema, Investigador investigador) {
        super(titulo, duracionEnMinutos, genero);
        this.investigador = investigador;   //
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    //
    public Investigador getInvestigador() {
        return investigador;  
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }
    //
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Tema: " + this.tema);
        // verificacion para mostrar un investigador asociado si existe
        if (investigador != null) {
            System.out.println("Investigador: " + investigador.getNombre() + 
                               " (Especialidad: " + investigador.getEspecialidad() + ")");
        } else {
            System.out.println("Investigador: No asignado");
        }
        System.out.println();
    }
}
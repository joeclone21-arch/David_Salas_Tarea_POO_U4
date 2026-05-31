/**
 * Class SerieDeTV
 */
package uni1a;
import java.util.ArrayList;
import java.util.List;

// Subclase SerieDeTV que extiende de ContenidoAudiovisual
public class SerieDeTV extends ContenidoAudiovisual {
    private int temporadas;
    private List<Temporada> listaTemporadas; // relacion de composicion

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int temporadas) {
        super(titulo, duracionEnMinutos, genero);
        this.temporadas = temporadas;
        this.listaTemporadas = new ArrayList<>();
     // en composicion la serie crea sus propias temporadas al nacer
        for (int i = 1; i <= temporadas; i++) {
            //  10 episodios por defecto para cada temporada creada
            this.listaTemporadas.add(new Temporada(i, 10));
        }
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Total temporadas: " + this.temporadas);
        //muestra las temporadas creadas por composicion
        System.out.println("Listado de Temporadas:");
        for (Temporada temp : listaTemporadas) {
            System.out.println("- " + temp.toString());
        }
        System.out.println();
    }
}
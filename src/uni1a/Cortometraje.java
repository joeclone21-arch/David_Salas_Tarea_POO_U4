package uni1a;

public class Cortometraje extends ContenidoAudiovisual {
    private String festival;

    public Cortometraje(String titulo, int duracionEnMinutos, String genero, String festival) {
        super(titulo, duracionEnMinutos, genero);
        this.festival = festival;
    }
    public String getFestival() { return festival; }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del Cortometraje:");
        System.out.println("ID: " + getId());
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Genero: " + getGenero());
        System.out.println("Festival: " + festival);
        System.out.println();
    }
}
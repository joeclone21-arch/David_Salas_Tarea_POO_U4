package uni1a;

public class VideoYouTube extends ContenidoAudiovisual {
    private String canal;
    private int vistas;

    public VideoYouTube(String titulo, int duracionEnMinutos, String genero, String canal, int vistas) {
        super(titulo, duracionEnMinutos, genero);
        this.canal = canal;
        this.vistas = vistas;
    }
    public String getCanal() { return canal; }
    public int getVistas() { return vistas; }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Video de YouTube:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Canal: " + canal);
        System.out.println("Vistas: " + vistas);
        System.out.println();
    }
}
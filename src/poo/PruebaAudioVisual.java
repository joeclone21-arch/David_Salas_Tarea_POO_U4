package poo;

import uni1a.*;
import uni4a.controlador.ContenidoControlador;
import uni4a.servicio.ArchivoServicio;
import uni4a.servicio.IContenidoServicio;
import uni4a.vista.ContenidoVista;

import java.util.ArrayList;
import java.util.List;

public class PruebaAudioVisual {
    public static void main(String[] args) {
        // Inicializa los datos  del Modelo
        List<ContenidoAudiovisual> listaOriginal = new ArrayList<>();
        
        Pelicula pelicula = new Pelicula("Inception", 148, "Ciencia Ficcion", "Warner Bros");
        pelicula.agregarActor(new Actor("Leonardo DiCaprio", "Cobb"));
        
        Investigador investigador = new Investigador("Carl Sagan", "Astrofisica");
        Documental documental = new Documental("Cosmos", 45, "Ciencia", "Astronomia", investigador);
        
        SerieDeTV serie = new SerieDeTV("Game of Thrones", 60, "Fantasia", 8);
        
        listaOriginal.add(pelicula);
        listaOriginal.add(documental);
        listaOriginal.add(serie);
        listaOriginal.add(new VideoYouTube("Tutorial Java POO", 15, "Educacion", "Programacion Express", 15000));
        listaOriginal.add(new Cortometraje("Piper", 6, "Animacion", "Pixar Animation Studios"));
        
        // instancia los componentes de las capas MVC
        ContenidoVista vista = new ContenidoVista();
        IContenidoServicio servicioArchivo = new ArchivoServicio();
        
        // El Controlador toma el control total del flujo del sistema
        ContenidoControlador controlador = new ContenidoControlador(listaOriginal, vista, servicioArchivo);
        
        // Aparece el menu interactivo
        controlador.iniciarSistema();
    }
}
package poo;

import uni1a.*;
import uni4a.servicio.ArchivoServicio;
import uni4a.servicio.IContenidoServicio; // se importa la interfaz

import java.util.ArrayList;
import java.util.List;

public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("--- Ejecutando Etapa 3:Principios SOLID ---");
        
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
        
        // CREO LA INSTANCIA APUNTANDO A LA INTERFAZ (Inversion de dependencias)
        IContenidoServicio servicioArchivo = new ArchivoServicio();
        
        // Pruebo la escritura usando el objeto 'servicioArchivo'
        System.out.println("\n[Guardando datos en CSV...]");
        servicioArchivo.guardarContenidos(listaOriginal);
        
        // Pruebo la lectura usando el objeto 'servicioArchivo'
        System.out.println("\n[Cargando datos desde CSV...]");
        List<ContenidoAudiovisual> listaCargada = servicioArchivo.cargarContenidos();
        
        System.out.println("\n--- Mostrando Detalles Recuperados del Archivo ---");
        for (ContenidoAudiovisual contenido : listaCargada) {
            contenido.mostrarDetalles();
        }
    }
}
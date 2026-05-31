package poo;
import uni1a.*;
import uni4a.servicio.ArchivoServicio;

import java.util.ArrayList;
import java.util.List;

public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("--- Ejecutando Etapa 1: Manejo de Archivos ---");
        
        // 1. Se crea la lista y se llena con datos duros iniciales
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
        
        // 2. Se prueba la escritura: Guarda el estado actual del sistema en el archivo CSV
        System.out.println("\n[Guardando datos en CSV...]");
        ArchivoServicio.guardarContenidos(listaOriginal);
        
        // 3. Se prueba la  lectura: Inicializa una nueva lista cargando los datos desde el archivo CSV
        System.out.println("\n[Cargando datos desde CSV...]");
        List<ContenidoAudiovisual> listaCargada = ArchivoServicio.cargarContenidos();
        
        // 4. Se muestra los detalles de los objetos recuperados para comprobar que esta bien
        System.out.println("\n--- Mostrando Detalles Recuperados del Archivo ---");
        for (ContenidoAudiovisual contenido : listaCargada) {
            contenido.mostrarDetalles();
        }
    }
}
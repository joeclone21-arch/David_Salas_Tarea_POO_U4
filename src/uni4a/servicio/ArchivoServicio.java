package uni4a.servicio;

import uni1a.ContenidoAudiovisual;
import uni1a.Pelicula;
import uni1a.Documental;
import uni1a.SerieDeTV;
import uni1a.VideoYouTube;
import uni1a.Cortometraje;
import uni1a.Investigador;
//import uni1a.Actor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio {

    // Cambia la ruta segun donde se guarde el archivo
    private static final String RUTA_ARCHIVO = "contenidos.csv";

    
    //Escribe la lista de contenidos audiovisuales en un archivo CSV.
    
    public static void guardarContenidos(List<ContenidoAudiovisual> contenidos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            // Se escribe la cabecera del CSV
            writer.println("Tipo,ID,Titulo,Duracion,Genero,DatoEspecifico1,DatoEspecifico2");

            for (ContenidoAudiovisual contenido : contenidos) {
                String tipo = "";
                String dato1 = "N/A";
                String dato2 = "N/A";

                // Se evalua el tipo de objeto de manera limpia
                if (contenido instanceof Pelicula) {
                    tipo = "PELICULA";
                    dato1 = ((Pelicula) contenido).getEstudio(); 
                } else if (contenido instanceof Documental) {
                    tipo = "DOCUMENTAL";
                    Documental d = (Documental) contenido;
                    dato1 = d.getTema();
                    dato2 = (d.getInvestigador() != null) ? d.getInvestigador().getNombre() : "No asignado";
                } else if (contenido instanceof SerieDeTV) {
                    tipo = "SERIEDETV";
                    dato1 = String.valueOf(((SerieDeTV) contenido).getTemporadas()); 
                } else if (contenido instanceof VideoYouTube) {
                    tipo = "YOUTUBE";
                    VideoYouTube v = (VideoYouTube) contenido;
                    dato1 = v.getCanal(); 
                    dato2 = String.valueOf(v.getVistas());
                } else if (contenido instanceof Cortometraje) {
                    tipo = "CORTOMETRAJE";
                    dato1 = ((Cortometraje) contenido).getFestival();
                }

                // Se guarda en el archivo usando las variables resueltas
                writer.println(tipo + "," + 
                               contenido.getId() + "," + 
                               contenido.getTitulo() + "," + 
                               contenido.getDuracionEnMinutos() + "," + 
                               contenido.getGenero() + "," + 
                               dato1 + "," + 
                               dato2);
            }
            System.out.println("Datos guardados exitosamente en " + RUTA_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    
     // Se lee el archivo CSV y reconstruye la lista de objetos correspondientes
     
    public static List<ContenidoAudiovisual> cargarContenidos() {
        List<ContenidoAudiovisual> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No se encontró archivo previo. Iniciando lista vacia.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean esCabecera = true;

            while ((linea = br.readLine()) != null) {
                if (esCabecera) { // Salta la primera linea de titulos
                    esCabecera = false;
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length < 5) continue;

                String tipo = datos[0];
                String titulo = datos[2];
                int duracion = Integer.parseInt(datos[3]);
                String genero = datos[4];

                switch (tipo) {
                    case "PELICULA":
                        String estudio = datos[5];
                        lista.add(new Pelicula(titulo, duracion, genero, estudio));
                        break;
                    case "DOCUMENTAL":
                        String tema = datos[5];
                        String nombreInv = datos.length > 6 ? datos[6] : "Desconocido";
                        Investigador inv = new Investigador(nombreInv, "General");
                        lista.add(new Documental(titulo, duracion, genero, tema, inv));
                        break;
                    case "SERIEDETV":
                        int temporadas = Integer.parseInt(datos[5]);
                        lista.add(new SerieDeTV(titulo, duracion, genero, temporadas));
                        break;
                    case "YOUTUBE":
                        // Lee exactamente lo que se guardo en el archivo CSV
                        String canalGuardado = datos[5]; 
                        int vistasGuardadas = Integer.parseInt(datos[6].trim());
                        
                        // Pasa los datos reales extraidos al constructor
                        lista.add(new VideoYouTube(titulo, duracion, genero, canalGuardado, vistasGuardadas));
                        break;
                    case "CORTOMETRAJE":
                        lista.add(new Cortometraje(titulo, duracion, genero, "Festival"));
                        break;
                }
            }
            System.out.println("Datos cargados exitosamente desde " + RUTA_ARCHIVO);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }

        return lista;
    }
}

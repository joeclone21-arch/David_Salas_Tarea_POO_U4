package uni4a.servicio;

import uni1a.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio {

    private static final String RUTA_ARCHIVO = "contenidos.csv";
    private static final String CABECERA_CSV = "Tipo,ID,Titulo,Duracion,Genero,DatoEspecifico1,DatoEspecifico2";
    private static final String SEPARADOR = ",";

    public static void guardarContenidos(List<ContenidoAudiovisual> contenidos) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            escritor.println(CABECERA_CSV);

            for (ContenidoAudiovisual contenido : contenidos) {
                String lineaCsv = mapearContenidoALineaCsv(contenido);
                escritor.println(lineaCsv);
            }
            System.out.println("Datos guardados exitosamente en " + RUTA_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static List<ContenidoAudiovisual> cargarContenidos() {
        List<ContenidoAudiovisual> listaContenidos = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No se encontro archivo previo. Iniciando lista vacia.");
            return listaContenidos;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = lector.readLine()) != null) {
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }
                
                ContenidoAudiovisual contenido = mapearLineaCsvAObjeto(linea);
                if (contenido != null) {
                    listaContenidos.add(contenido);
                }
            }
            System.out.println("Datos cargados exitosamente desde " + RUTA_ARCHIVO);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }

        return listaContenidos;
    }

    // METODOS PEQUENOS EXTRAIDOS (Refactorizacion de Etapa 2) 

    private static String mapearContenidoALineaCsv(ContenidoAudiovisual contenido) {
        String tipo = "";
        String datoEspecifico1 = "N/A";
        String datoEspecifico2 = "N/A";

        if (contenido instanceof Pelicula) {
            tipo = "PELICULA";
            datoEspecifico1 = ((Pelicula) contenido).getEstudio();
        } else if (contenido instanceof Documental) {
            tipo = "DOCUMENTAL";
            Documental doc = (Documental) contenido;
            datoEspecifico1 = doc.getTema();
            datoEspecifico2 = (doc.getInvestigador() != null) ? doc.getInvestigador().getNombre() : "No asignado";
        } else if (contenido instanceof SerieDeTV) {
            tipo = "SERIEDETV";
            datoEspecifico1 = String.valueOf(((SerieDeTV) contenido).getTemporadas());
        } else if (contenido instanceof VideoYouTube) {
            tipo = "YOUTUBE";
            VideoYouTube video = (VideoYouTube) contenido;
            datoEspecifico1 = video.getCanal();
            datoEspecifico2 = String.valueOf(video.getVistas());
        } else if (contenido instanceof Cortometraje) {
            tipo = "CORTOMETRAJE";
            datoEspecifico1 = ((Cortometraje) contenido).getFestival();
        }

        return String.join(SEPARADOR, 
            tipo, 
            String.valueOf(contenido.getId()), 
            contenido.getTitulo(), 
            String.valueOf(contenido.getDuracionEnMinutos()), 
            contenido.getGenero(), 
            datoEspecifico1, 
            datoEspecifico2
        );
    }

    private static ContenidoAudiovisual mapearLineaCsvAObjeto(String lineaCsv) {
        String[] datos = lineaCsv.split(SEPARADOR);
        if (datos.length < 5) {
            return null;
        }

        String tipo = datos[0];
        String titulo = datos[2];
        int duracion = Integer.parseInt(datos[3]);
        String genero = datos[4];

        switch (tipo) {
            case "PELICULA":
                return new Pelicula(titulo, duracion, genero, datos[5]);
            case "DOCUMENTAL":
                String investigadorNombre = datos.length > 6 ? datos[6] : "Desconocido";
                Investigador inv = new Investigador(investigadorNombre, "General");
                return new Documental(titulo, duracion, genero, datos[5], inv);
            case "SERIEDETV":
                return new SerieDeTV(titulo, duracion, genero, Integer.parseInt(datos[5]));
            case "YOUTUBE":
                int vistas = Integer.parseInt(datos[6].trim());
                return new VideoYouTube(titulo, duracion, genero, datos[5], vistas);
            case "CORTOMETRAJE":
                return new Cortometraje(titulo, duracion, genero, datos[5]);
            default:
                return null;
        }
    }
}
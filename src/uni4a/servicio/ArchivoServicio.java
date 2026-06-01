package uni4a.servicio;

import uni1a.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio implements IContenidoServicio {

    private static final String RUTA_ARCHIVO = "contenidos.csv";
    private static final String CABECERA_CSV = "Tipo,ID,Titulo,Duracion,Genero,DatoEspecifico1,DatoEspecifico2";
    private static final String SEPARADOR = ",";

    @Override
    public void guardarContenidos(List<ContenidoAudiovisual> contenidos) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            escritor.println(CABECERA_CSV);

            for (ContenidoAudiovisual contenido : contenidos) {
                // Principio LSP e ISP: Si el objeto sabe serializarse, le pedimos su fila CSV
                if (contenido instanceof ISerializableCsv) {
                    escritor.println(((ISerializableCsv) contenido).toCsvRow());
                }
            }
            System.out.println("Datos guardados exitosamente (SOLID) en " + RUTA_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<ContenidoAudiovisual> cargarContenidos() {
        List<ContenidoAudiovisual> listaContenidos = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
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
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }

        return listaContenidos;
    }

    private ContenidoAudiovisual mapearLineaCsvAObjeto(String lineaCsv) {
        String[] datos = lineaCsv.split(SEPARADOR);
        if (datos.length < 5) return null;

        String tipo = datos[0];
        String titulo = datos[2];
        int duracion = Integer.parseInt(datos[3]);
        String genero = datos[4];

        switch (tipo) {
            case "PELICULA":
                return new Pelicula(titulo, duracion, genero, datos[5]);
            case "DOCUMENTAL":
                Investigador inv = new Investigador(datos.length > 6 ? datos[6] : "Desconocido", "General");
                return new Documental(titulo, duracion, genero, datos[5], inv);
            case "SERIEDETV":
                return new SerieDeTV(titulo, duracion, genero, Integer.parseInt(datos[5]));
            case "YOUTUBE":
                return new VideoYouTube(titulo, duracion, genero, datos[5], Integer.parseInt(datos[6].trim()));
            case "CORTOMETRAJE":
                return new Cortometraje(titulo, duracion, genero, datos[5]);
            default:
                return null;
        }
    }
}
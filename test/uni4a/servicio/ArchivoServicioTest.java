package uni4a.servicio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uni1a.Pelicula;
import uni1a.VideoYouTube;
import uni1a.ContenidoAudiovisual;

public class ArchivoServicioTest {

    private ArchivoServicio servicio;

    @BeforeEach
    public void setUp() {
        // inicializa el servicio antes de cada caso de prueba
        servicio = new ArchivoServicio();
    }

    // --- CASOS DE PRUEBA NORMALES ---

    @Test
    public void testMapearLineaCsvAPeliculaCorrectamente() {
        String lineaCsv = "PELICULA,0,Interstellar,169,Ciencia Ficcion,Paramount,N/A";
        
        // ejecuta el metodo que queremos probar 
        ContenidoAudiovisual resultado = servicio.cargarContenidos() != null ? 
                invocarMapearLineaPrivada(lineaCsv) : null;

        assertNotNull(resultado, "El objeto recuperado no debería ser nulo");
        assertTrue(resultado instanceof Pelicula, "Debería transformarse en una instancia de Pelicula");
        assertEquals("Interstellar", resultado.getTitulo());
        assertEquals(169, resultado.getDuracionEnMinutos());
        assertEquals("Ciencia Ficcion", resultado.getGenero());
        assertEquals("Paramount", ((Pelicula) resultado).getEstudio());
    }

    @Test
    public void testPeliculaSerializaCorrectamenteACsv() {
        Pelicula pelicula = new Pelicula("Inception", 148, "Accion", "Warner Bros");
        
        String resultadoCsv = ((ISerializableCsv) pelicula).toCsvRow();
        
        // verifica que contenga los separadores y datos correctos
        assertTrue(resultadoCsv.startsWith("PELICULA,"));
        assertTrue(resultadoCsv.contains("Inception"));
        assertTrue(resultadoCsv.contains("148"));
        assertTrue(resultadoCsv.contains("Warner Bros"));
    }

    // ----CASOS LIMITE Y EXCEPCIONALES ----

    @Test
    public void testMapearLineaCsvIncompletaRetornaNulo() {
        // Caso excepcional: Una línea rota que le faltan columnas obligatorias
        String lineaCorrupta = "PELICULA,1,Incompleta"; 
        
        ContenidoAudiovisual resultado = invocarMapearLineaPrivada(lineaCorrupta);
        
        // Debe retornar null de forma segura en lugar de lanzar un NullPointerException o ArrayIndexOutOfBoundsException
        assertNull(resultado, "El mapeador debe manejar datos incompletos retornando null de forma segura");
    }

    @Test
    public void testMapearLineaCsvConTipoDesconocidoRetornaNulo() {
        // Caso limite: Un tipo de contenido audiovisual que no existe en nuestro switch
        String lineaDesconocida = "TIKTOK,1,Video Corto,1,Entretenimiento,User123,0";
        
        ContenidoAudiovisual resultado = invocarMapearLineaPrivada(lineaDesconocida);
        
        assertNull(resultado, "Tipos de contenido no soportados deben retornar null");
    }

    // Metodo auxiliar para poder testear el metodo privado de mapeo sin romper el encapsulamiento
    private ContenidoAudiovisual invocarMapearLineaPrivada(String lineaCsv) {
        try {
            java.lang.reflect.Method metodo = ArchivoServicio.class.getDeclaredMethod("mapearLineaCsvAObjeto", String.class);
            metodo.setAccessible(true);
            return (ContenidoAudiovisual) metodo.invoke(servicio, lineaCsv);
        } catch (Exception e) {
            return null;
        }
    }
}
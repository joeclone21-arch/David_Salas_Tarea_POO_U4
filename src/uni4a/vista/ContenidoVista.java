package uni4a.vista;

import uni1a.ContenidoAudiovisual;
import java.util.List;
import java.util.Scanner;

public class ContenidoVista {
    private final Scanner entrada;

    public ContenidoVista() {
        this.entrada = new Scanner(System.in);
    }

    public int mostrarMenuYObtenerOpcion() {
        System.out.println("\n---------------------------------------");
        System.out.println("   SISTEMA DE GESTION AUDIOVISUAL (MVC) ");
        System.out.println("-----------------------------------------");
        System.out.println("1. Mostrar contenidos en memoria");
        System.out.println("2. Guardar contenidos actuales en el archivo CSV");
        System.out.println("3. Cargar contenidos desde el archivo CSV");
        System.out.println("4. Salir del sistema");
        System.out.print("Seleccione una opcion: ");
        
        while (!entrada.hasNextInt()) {
            System.out.print("Por favor, ingrese un numero valido: ");
            entrada.next();
        }
        return entrada.nextInt();
    }

    public void mostrarListaContenidos(List<ContenidoAudiovisual> contenidos) {
        if (contenidos.isEmpty()) {
            System.out.println("\n[!] La lista actual está vacia.");
            return;
        }
        System.out.println("\n--- DETALLES DE CONTENIDOS AUDIOVISUALES ---");
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
            System.out.println("----------------------------------------");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
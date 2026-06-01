package uni4a.controlador;

import uni1a.ContenidoAudiovisual;
import uni4a.servicio.IContenidoServicio;
import uni4a.vista.ContenidoVista;

import java.util.List;

public class ContenidoControlador {
    private List<ContenidoAudiovisual> modeloContenidos;
    private final ContenidoVista vista;
    private final IContenidoServicio servicioArchivo;

    public ContenidoControlador(List<ContenidoAudiovisual> modeloContenidos, ContenidoVista vista, IContenidoServicio servicioArchivo) {
        this.modeloContenidos = modeloContenidos;
        this.vista = vista;
        this.servicioArchivo = servicioArchivo;
    }

    public void iniciarSistema() {
        int opcion;
        do {
            opcion = vista.mostrarMenuYObtenerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 4);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                vista.mostrarListaContenidos(modeloContenidos);
                break;
            case 2:
                vista.mostrarMensaje("\n[Procesando guardado en CSV...]");
                servicioArchivo.guardarContenidos(modeloContenidos);
                vista.mostrarMensaje("[+] Operacion de guardado finalizada");
                break;
            case 3:
                vista.mostrarMensaje("\n[Leyendo y cargando datos desde CSV..]");
                modeloContenidos = servicioArchivo.cargarContenidos();
                vista.mostrarMensaje("[+] Datos cargados con exito a la memoria actual");
                break;
            case 4:
                vista.mostrarMensaje("\nSaliendo del sistema");
                break;
            default:
                vista.mostrarMensaje("\n[!] Opcion invalida. Intente de nuevo.");
        }
    }
}
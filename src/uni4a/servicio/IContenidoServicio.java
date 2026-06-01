package uni4a.servicio;

import uni1a.ContenidoAudiovisual;
import java.util.List;

public interface IContenidoServicio {
    void guardarContenidos(List<ContenidoAudiovisual> contenidos);
    List<ContenidoAudiovisual> cargarContenidos();
}


# David_Salas_Tarea_POO_U4
# Sistema de Gestión de Contenidos Audiovisuales Tarea U4

Este proyecto consiste en una aplicación desarrollada en Java que gestiona información de diversos contenidos audiovisuales mediante la aplicación de conceptos avanzados de programación orientada a objetos.

## 1. Cambios Realizados

A lo largo del desarrollo del sistema, el diseño evolucionó progresivamente a través de las siguientes etapas:

- Separación de responsabilidades: Se extrajo la lógica de persistencia de las clases de datos hacia un servicio especializado para el manejo de archivos de texto en formato CSV.
- Arquitectura limpia (SOLID): Se introdujeron abstracciones mediante interfaces para desacoplar el servicio de almacenamiento (Inversión de Dependencias) y se delegó la serialización a cada clase concreta mediante una interfaz específica de comportamiento, eliminando condicionales complejos y el uso del operador instanceof.
- Estructuración arquitectónica (MVC): Se dividió el sistema en tres capas independientes (Modelo, Vista y Controlador) para separar la lógica de negocio de la interfaz interactiva por consola.
- Control de calidad: Se integró una suite de pruebas automatizadas para validar el correcto funcionamiento de los algoritmos de mapeo y persistencia bajo escenarios normales y de excepción.

## 2. Estructura del Código

El código fuente del proyecto se organiza bajo una estructura paralela que separa el código de producción del código de pruebas unitarias:

Ruta de producción (src/):
- poo: Contiene la clase principal de arranque del sistema (PruebaAudioVisual).
- uni1a: Contiene las clases del modelo de negocio que representan los datos y entidades (ContenidoAudiovisual, Pelicula, SerieDeTV, VideoYouTube, Cortometraje, Documental, Actor, Investigador).
- uni4a.controlador: Contiene la clase mediadora (ContenidoControlador) que coordina las interacciones del usuario y la manipulación de datos.
- uni4a.servicio: Contiene las interfaces (IContenidoServicio, ISerializableCsv) y la clase concreta (ArchivoServicio) encargadas de la persistencia de datos.
- uni4a.vista: Contiene la interfaz de usuario basada en consola (ContenidoVista).

Ruta de pruebas (test/):
- uni4a.servicio: Contiene las clases de pruebas automatizadas de JUnit (ArchivoServicioTest).

## 3. Cómo Clonar y Ejecutar el Proyecto

### Clonar el repositorio
Para obtener una copia local del proyecto en su entorno de desarrollo, ejecute el siguiente comando en su terminal:
git clone https://github.com/joeclone21-arch/David_Salas_Tarea_POO_U4.git

### Ejecutar la aplicación
1. Abra su entorno de desarrollo integrado (IDE), preferiblemente Eclipse.
2. Importe el proyecto clonado como un proyecto Java existente.
3. Diríjase al paquete "poo" dentro de la carpeta "src".
4. Haga clic derecho sobre el archivo "PruebaAudioVisual.java" y seleccione la opción "Run As -> Java Application".
5. Interactúe con el sistema a través del menú desplegado en la consola de comandos.

## 4. Cómo Ejecutar las Pruebas

El proyecto cuenta con un entorno de pruebas diseñado para validar los componentes del sistema con el framework JUnit 5.

Procedimiento de ejecución:
1. Dentro del explorador del proyecto en el IDE, localice la carpeta de origen denominada "test".
2. Navegue a través del paquete "uni4a.servicio" hasta encontrar el archivo "ArchivoServicioTest.java".
3. Haga clic derecho sobre el archivo "ArchivoServicioTest.java".
4. Seleccione la opción "Run As -> JUnit Test".
5. Verifique los resultados en la pestaña dedicada de JUnit, asegurando que todos los casos normales y límites configurados se completen de manera exitosa.

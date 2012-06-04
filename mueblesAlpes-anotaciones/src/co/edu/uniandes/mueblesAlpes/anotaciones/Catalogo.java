/**
 * Nombre del paquete de Anotaciones.
 */
package co.edu.uniandes.mueblesAlpes.anotaciones;
/**
 * Importación de paquetes y clases.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
/**
 * Esta es la clase Procesador de Anotaciones.
 * Hereda de AbstractProcessor para poder implementar el método de procesamiento de anotaciones.
 * El arhivo es generado al momento de Limpiar y Construir el proyecto de aplicación que contienen el Main.
 * Esta clase busca las diferentes anotaciones y crea arreglos de Element para alcamecenar el nombre
 * y posteriormente el método.
 * Una vez los tiene, genera el archivo con los diferentes tipo de datos tomados del proocesamiento y de los
 * Element seleccionados que cumplen con estar anotatodos con las etiquetas mencionadas.
 * Soporta los dos tipos de anotaciones credas como Mueble e Identificador, para poder anotar clases y métodos.
 * @author Memo Toro
 */
@SupportedAnnotationTypes({"co.edu.uniandes.mueblesAlpes.anotaciones.Mueble","co.edu.uniandes.mueblesAlpes.anotaciones.Identificador"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class Catalogo extends AbstractProcessor {
    /**
     * Variable de Control del Número de veces de Compilación.
     */
    private static int contador=0;
    /**
     * Variable Lista para almacenar las Clases Anotadas.
     */
    private List<Element> clasesAnotadas = new ArrayList<Element>();
    /**
     * Variable de Tipo Anotacion Mueble para realizar las busquedas.
     */
    private Mueble mueble = null;
    /**
     * Variable de Tipo String para almacenar el Bombre de la Clase Anotada.
     */
    private String nombreClase = null;
    /**
     * Variable de Tipo String para almacenar el Nombre del Método Anotado.
     */
    private String nombreMetodo = null;
    /**
     * Variablo de Tipo Elemento para almacenar Una Clase tomada del Listado.
     */
    private Element claseAnotada;
    /**
     * Variable de Tipo Iterador para poder recorrer el listado.
     */
    private Iterator it = null;
    /**
     * Variable de Tipo FileWriter para establecer el archivo a generar.
     */
    private FileWriter archivoEscritura = null;
    /**
     * Variable de Tipo PrintWriter para poder realizar las impresiones de mensajes en el archivo.
     */
    private PrintWriter salida = null;
    /**
     * Variablde de Tipo entero para realizar ciclos.
     */
    private int indice=0;
    /**
     * Método heredado de Abstract Procesos y es re-definido al interior de esta clase para implementar
     * la lógica de procesamiento de anotación y generación del archivo plano.
     * @param annotations Anotaciones establecidas dentro del programa.
     * @param roundEnv Variable para analizar el ambiente de desarrollo y compilación.
     * @return boolean para verificar la ejecución del proceso.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Aumenta el contador de control.
        contador++;
        // Si el contador indica que solo se ha instaciado una vez dicha clase.
        if(contador==1){
            // Busca todos los elementos del ambiente anotados con la anotación MUEBLE.
            for (Element elementoClase : roundEnv.getElementsAnnotatedWith(Mueble.class)) {
                // Lo agrega a la lista.
                clasesAnotadas.add(elementoClase);
                // Extrae el nombre de la clase.
                nombreClase = clasesAnotadas.get(indice).getSimpleName().toString();
                // Busca todos los elementos del ambiente que esten anotados con IDENTIFICADOR.
                for (Element elementoMetodo : roundEnv.getElementsAnnotatedWith(Identificador.class)){
                    // Realiza la comparación para verificar que el nombre del método anotado pertenece a la clase seleccionada.
                    if(((TypeElement)elementoMetodo.getEnclosingElement()).getSimpleName().toString().equalsIgnoreCase(nombreClase))
                        // Guarda el nombre del método.
                        nombreMetodo = elementoMetodo.getSimpleName().toString();
                }
                // Aumento del ciclo.
                indice++;
            }
            // Bloque de control de excepciones de generación de archivos.
            try{
                // Asignación del iterador de la lista.
                it = clasesAnotadas.iterator();
                // Generación del archivo en la ruta establecida. C para el caso.
                archivoEscritura = new FileWriter("C:/catalogo-muebles.txt");
                // Asignación de variable de salida para impresión.
                salida = new PrintWriter(archivoEscritura);
                // Variables locales para control de cuantos elementos se han encontrado con la respectiva anotación.
                int contadorInterior = 0;
                int contadorExterior = 0;
                // Mientras que la lista tenga elementos.
                while(it.hasNext()){
                    // Toma el elemento de la lista.
                    claseAnotada = (Element)it.next();
                    // Captura la anotación que tiene dicha clase.
                    mueble = claseAnotada.getAnnotation(Mueble.class);
                    // Comparación para conocer el tipo de mueble
                    if(mueble.tipo().equals(TipoMueble.INTERIOR)){
                        // Aumento de variable de control
                        contadorInterior++;
                        // Verifica si es el primer elemento para poder imprimir el encabezado de cada sección.
                        if(contadorInterior==1)
                            // Imprime el valor del tipo de mueble como encabezado del arhivo.
                            salida.println(mueble.tipo().name());
                    }
                    else{
                        // Aumento de variable de control
                        contadorExterior++;
                        // Verifica si es el primer elemento para poder imprimir el encabezado de cada sección.
                         if(contadorExterior==1)
                             // Imprime el valor del tipo de mueble como encabezado del arhivo.
                            salida.println(mueble.tipo().name());
                    }
                    // Imprime en el archivo el nombre del método capturado y concatenado con el nombre de la clase anotada.
                    salida.println(nombreMetodo+"\t"+claseAnotada.getSimpleName().toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                // Devuelve FALSE si algo fallo en la compilación.
                return false;
            } finally {
                try{
                    // Cierra el archivo generado.
                    archivoEscritura.close();
                    System.out.println("Archivo Cerrado Saatisfactoriamente!!!");
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }
        }
        // Devuelve TRUE si todo se ejecuto.
        return true;
    }
}
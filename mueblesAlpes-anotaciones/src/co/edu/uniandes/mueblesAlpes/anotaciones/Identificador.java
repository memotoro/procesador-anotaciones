/**
 * Nombre del paquete de Anotaciones.
 */
package co.edu.uniandes.mueblesAlpes.anotaciones;
/**
 * Importación de paquetes y clases.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Anotación Identificador.
 * Esta anotacion aplica para el método que devuelve el identificador de cada POJO.
 * La Política de retención es a nivel de CLASS.
 * No tiene parametros implementados.
 * @author Memo Toro
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Identificador {
    //TO-DO Listar los elementos para esta interfaz.
}
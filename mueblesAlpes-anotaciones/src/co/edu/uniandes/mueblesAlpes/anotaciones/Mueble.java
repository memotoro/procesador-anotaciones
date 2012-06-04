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
 * Anotación Mueble.
 * Esta anotación aplica para las Clases de tipo Mueble.
 * Política de retención en CLASS.
 * Los parametros o elementos de la anotación son los diferentes tipos de muebles
 * que se pueden recibir. Estan contemplados como ENUM.
 * @author Memo Toro
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Mueble {
    /**
     * Elemento de Anotación de Tipo Mueble.
     * @return Tipo de Mueble (INTERIOR,EXTERIOR)
     */
    TipoMueble tipo();
}

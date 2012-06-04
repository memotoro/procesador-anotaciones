/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.banco.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Memo Toro
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Notificacion {

    TipoNotificacion tipo();

    String[] usuarios();

    String mensaje();
}

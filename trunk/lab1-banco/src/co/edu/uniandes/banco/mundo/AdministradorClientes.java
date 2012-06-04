/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.banco.mundo;

import co.edu.uniandes.banco.anotaciones.Notificacion;
import co.edu.uniandes.banco.anotaciones.TipoNotificacion;
import co.edu.uniandes.banco.mundo.pojos.Cliente;

/**
 *
 * @author Memo Toro
 */
public class AdministradorClientes {
    public void registrarCliente(Cliente cliente){
        //TO-DO Registrar cliente no corporativo
    }
    @Notificacion(mensaje="Se Ha Registrado Un Nuevo Cliente Corporativo",tipo=TipoNotificacion.EMAIL,usuarios={"correo1@gmail.com","correo1@hotmail.com"})
    public void registrarClienteCorporativo(Cliente cliente){
        //TO-DO Registrar cliente corporativo
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.banco.mundo;

import co.edu.uniandes.banco.mundo.pojos.Cliente;
/**
 *
 * @author Memo Toro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
        AdministradorClientes ac = (AdministradorClientes) Class.forName("co.edu.uniandes.banco.mundo.AdministradorClientesExtra").newInstance();
        Cliente cliente = new Cliente();
        cliente.setCedula("80808080");
        cliente.setNombres("Nombre");
        cliente.setApellidos("Apellido");
        cliente.setEdad(25);
        cliente.setSucursal("Ciudad");
        ac.registrarClienteCorporativo(cliente);        
    }
}
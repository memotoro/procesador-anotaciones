/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.banco.mundo.pojos;

/**
 *
 * @author Memo Toro
 */
public class Cliente {
    private String cedula;
    private String nombres;
    private String apellidos;
    private int edad;
    private String sucursal;

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }


    public String getApellidos() {
        return apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombres() {
        return nombres;
    }

    public String getSucursal() {
        return sucursal;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ruben
 */
public class Contacto {

    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    public Date fechaNacimiento;
    private Telefono[] telefonos;

    public Contacto() {
        telefonos = new Telefono[10];
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Telefono[] getTelefonos() {
        return telefonos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String sFechaNacimiento = formatter.format(fechaNacimiento);

        String respuesta = "\n" + apellido + ", " + nombre + "\nDNI: " + dni + "\nDirección: " + direccion + "\nFechaNacimiento: " + sFechaNacimiento;
        for (int i = 0; i < telefonos.length; i++) {
            if (telefonos[i] != null) {
                respuesta += "\n" + telefonos[i].toString();
            }
        }

        return respuesta;
    }

    public void agregarTelefono(Telefono nuevoTelefono) {
        for (int i = 0; i < telefonos.length; i++) {
            if (telefonos[i] == null) {
                telefonos[i] = nuevoTelefono;
                break;
            }
        }
    }

    public long getDiasParaCumple() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(this.fechaNacimiento);
        int mesCumple = fechaNacimiento.get(Calendar.MONTH);
        int diaCumple = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
        int anioActual = fechaActual.get(Calendar.YEAR);

        Calendar fechaCumple = Calendar.getInstance();
        fechaCumple.set(anioActual, mesCumple, diaCumple);

        if (fechaCumple.getTimeInMillis() < fechaActual.getTimeInMillis()) {
            fechaCumple.set(anioActual + 1, mesCumple, diaCumple);
        }

        long diffTime = fechaCumple.getTimeInMillis() - fechaActual.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(diffTime); 
    }
}

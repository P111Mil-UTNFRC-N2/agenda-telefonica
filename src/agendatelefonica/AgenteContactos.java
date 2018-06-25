/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendatelefonica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import model.Contacto;
import model.Telefono;

/**
 *
 * @author Ruben
 */
public class AgenteContactos {

    private static Contacto[] listaContactos = new Contacto[10];

    public static void agregarContacto(Contacto contacto) {
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] == null) {
                listaContactos[i] = contacto;
                break;
            }
        }
    }

    public static Contacto[] getListaContactosOrdenados() {
        Contacto[] contactos = listaContactos.clone();
        for (int i = 0; i < contactos.length; i++) {
            if (contactos[i] != null) {
                for (int j = 0; j < contactos.length; j++) {
                    if (contactos[j] != null) {
                        if (contactos[i].getApellido().compareTo(contactos[j].getApellido()) < 0) {
                            //Ordenamos por Apellido
                            Contacto aux = contactos[i];
                            contactos[i] = contactos[j];
                            contactos[j] = aux;
                        } else if (contactos[i].getApellido().compareTo(contactos[j].getApellido()) == 0) {
                            //Ordenamos por Nombre
                            if (contactos[i].getNombre().compareTo(contactos[j].getNombre()) < 0) {
                                Contacto aux = contactos[i];
                                contactos[i] = contactos[j];
                                contactos[j] = aux;
                            }
                        }
                    }
                }
            }
        }
        return contactos;
    }

    static void eliminarTelefono(Contacto contactoSeleccionado, Telefono seleccionado) {
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i].equals(contactoSeleccionado)) {
                for (int j = 0; j < listaContactos[i].getTelefonos().length; j++) {
                    if (listaContactos[i].getTelefonos()[j].equals(seleccionado)) {
                        listaContactos[i].getTelefonos()[j] = null;
                        break;
                    }
                }
                break;
            }
        }
    }

    static void eliminarContacto(Contacto contactoSeleccionado) {
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                if (listaContactos[i].equals(contactoSeleccionado)) {
                    listaContactos[i] = null;
                    break;
                }
            }
        }
    }

    static Contacto buscarContactoPorDni(String nextLine) {
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                if (listaContactos[i].getDni().equals(nextLine)) {
                    return listaContactos[i];
                }
            }
        }
        //Si no encuentra nada devuelve null.
        return null;
    }

    static Contacto buscarContactoPorNumeroTelefono(String nroTelefono) {
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                for (int j = 0; j < listaContactos[i].getTelefonos().length; j++) {
                    if (listaContactos[i].getTelefonos()[j] != null) {
                        if (listaContactos[i].getTelefonos()[j].getNumero().equals(nroTelefono)) {
                            return listaContactos[i];
                        }
                    }
                }
            }
        }
        //Si no encuentra nada devuelve null.
        return null;
    }

    static Contacto buscarContactoPorNombre(String nombre) {
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                if (listaContactos[i].getNombre().equals(nombre)) {
                    return listaContactos[i];
                }
            }
        }
        //Si no encuentra nada devuelve null.
        return null;
    }

    static Contacto[] getListaContactosProxCumple() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Contacto[] resultado = new Contacto[10];
        int indiceResultado = 0;

        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                long cantDias = listaContactos[i].getDiasParaCumple();

                if (cantDias > 0 && cantDias <= 7) {
                    resultado[indiceResultado] = listaContactos[i];
                    indiceResultado++;
                }
            }
        }

        return resultado;
    }

}

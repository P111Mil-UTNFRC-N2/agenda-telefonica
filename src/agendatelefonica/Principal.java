/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendatelefonica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.Contacto;
import model.Telefono;

/**
 *
 * @author Ruben
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        inicializarDatos();

        Scanner principal = new Scanner(System.in);
        int opcionIngresada = 0;

        System.out.println("############################################################################");
        System.out.println("############################################################################");
        System.out.println("Caso Testigo: Agenda Telefónica");
        System.out.println("############################################################################");
        System.out.println("############################################################################");

        do {

            System.out.println("Seleccione una opción: ");
            System.out.println("1 - Agregar Contacto.");
            System.out.println("2 - Actualizar Contacto.");
            System.out.println("3 - Eliminar Contacto.");
            System.out.println("4 - Buscar Contacto por dni");
            System.out.println("5 - Buscar Contacto por número de teléfono");
            System.out.println("6 - Buscar Contacto por nombre");
            System.out.println("7 - Listar Contactos ordenados alfabéticamente");
            System.out.println("8 - Listar Contactos de próximo cumpleaños");
            System.out.println("9 - Salir");

            System.out.print("Opción Ingresada: ");
            opcionIngresada = principal.nextInt();

            switch (opcionIngresada) {
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    actualizarContacto();
                    break;
                case 3:
                    eliminarContacto();
                    break;
                case 4:
                    buscarContactoPorDni();
                    break;
                case 5:
                    buscarContactoPorNumeroTelefono();
                    break;
                case 6:
                    buscarContactoPorNombre();
                    break;
                case 7:
                    listarContactosOrdenados();
                    break;
                case 8:
                    listarContactosProximoCumple();
                    break;
            }

        } while (opcionIngresada != 9);

    }

    private static void agregarContacto() throws ParseException {
        Scanner datosContacto = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Contacto nuevoContacto = new Contacto();

        System.out.println("########################### ");
        System.out.println("#### Agregar Contacto ##### ");
        System.out.println("########################### ");
        System.out.println("Ingrese a continuación los datos del contacto:");
        System.out.print("Nombre: ");
        nuevoContacto.setNombre(datosContacto.nextLine());

        System.out.print("Apellido: ");
        nuevoContacto.setApellido(datosContacto.nextLine());

        System.out.print("DNI: ");
        nuevoContacto.setDni(datosContacto.nextLine());

        System.out.print("Dirección: ");
        nuevoContacto.setDireccion(datosContacto.nextLine());

        System.out.print("Fecha Nacimiento: ");
        String fechaStr = datosContacto.nextLine();
        nuevoContacto.setFechaNacimiento(format.parse(fechaStr));

        String opcionTelefono = "s";
        do {
            System.out.print("Desea Agregar un telefono (s/n): ");
            opcionTelefono = datosContacto.nextLine();

            if (opcionTelefono.equals("s")) {
                Telefono nuevoTelefono = new Telefono();
                System.out.print("Tipo (1-CASA|2-TRABAJO|3-MOVIL|4-Principal|5-OTRO): ");
                nuevoTelefono.setTipo(datosContacto.nextInt());
                datosContacto.nextLine();
                System.out.print("Numero: ");
                nuevoTelefono.setNumero(datosContacto.nextLine());
                nuevoContacto.agregarTelefono(nuevoTelefono);
            }
        } while (opcionTelefono.equals("s"));

        AgenteContactos.agregarContacto(nuevoContacto);
    }

    private static void actualizarContacto() throws ParseException {
        Scanner principal = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("################################################");
        System.out.println("#### Seleccione un contacto para Actualizar#####");
        System.out.println("################################################");
        Contacto contactoSeleccionado = seleccionarContacto();

        // En caso de que no se selecciones un contacto retorn al menu anterior
        if (contactoSeleccionado == null) {
            return;
        }

        int opcionIngresada = 0;
        do {
            System.out.println("Seleccione lo que desea modificar: ");
            System.out.println("1 - Nombre.");
            System.out.println("2 - Apellido.");
            System.out.println("3 - DNI.");
            System.out.println("4 - Direccion");
            System.out.println("5 - Fecha Nacimiento:");
            System.out.println("6 - Agregar Telefono");
            System.out.println("7 - Eliminar Telefono");
            System.out.println("8 - Volver");

            System.out.print("Opción Ingresada: ");
            opcionIngresada = principal.nextInt();
            principal.nextLine();

            switch (opcionIngresada) {
                case 1:
                    System.out.print("Nombre (" + contactoSeleccionado.getNombre() + "): ");
                    contactoSeleccionado.setNombre(principal.nextLine());
                    break;
                case 2:
                    System.out.print("Apellido (" + contactoSeleccionado.getApellido() + "): ");
                    contactoSeleccionado.setApellido(principal.nextLine());
                    break;
                case 3:
                    System.out.print("DNI (" + contactoSeleccionado.getDni() + "): ");
                    contactoSeleccionado.setDni(principal.nextLine());
                    break;
                case 4:
                    System.out.print("Direccion (" + contactoSeleccionado.getDireccion() + "): ");
                    contactoSeleccionado.setDireccion(principal.nextLine());
                    break;
                case 5:
                    System.out.print("Fecha Nacimiento (" + contactoSeleccionado.getFechaNacimiento() + "): ");
                    String fechaStr = principal.nextLine();
                    contactoSeleccionado.setFechaNacimiento(format.parse(fechaStr));
                    break;
                case 6:
                    Telefono nuevoTelefono = new Telefono();
                    System.out.print("Tipo (1-CASA|2-TRABAJO|3-MOVIL|4-Principal|5-OTRO): ");
                    nuevoTelefono.setTipo(principal.nextInt());
                    principal.nextLine();
                    System.out.print("Numero: ");
                    nuevoTelefono.setNumero(principal.nextLine());
                    contactoSeleccionado.agregarTelefono(nuevoTelefono);

                    break;
                case 7:
                    Telefono seleccionado = seleccionarTelefono(contactoSeleccionado);
                    //Elimino un telefono solo si se seleccionó uno
                    if (seleccionado != null) {
                        AgenteContactos.eliminarTelefono(contactoSeleccionado, seleccionado);
                    }
                    break;
            }
        } while (opcionIngresada != 8);
    }

    private static void eliminarContacto() {
        Contacto seleccionado = seleccionarContacto();
        if (seleccionado != null) {
            AgenteContactos.eliminarContacto(seleccionado);
        }
    }

    private static void buscarContactoPorDni() {
        Scanner principal = new Scanner(System.in);
        System.out.print("Ingrese un DNI: ");
        Contacto contactoEncontrado = AgenteContactos.buscarContactoPorDni(principal.nextLine());
        System.out.print("------------------------------------- ");
        if (contactoEncontrado != null) {
            System.out.println(contactoEncontrado.toString());
        } else {
            System.out.println("\nNo se encuentró un contacto. ");
        }
        System.out.println("------------------------------------- ");
        System.out.println("Presione cualquier tecla para continuar...");
        principal.nextLine();
    }

    private static void buscarContactoPorNumeroTelefono() {
        Scanner principal = new Scanner(System.in);
        System.out.print("Ingrese un numero de teléfono: ");
        Contacto contactoEncontrado = AgenteContactos.buscarContactoPorNumeroTelefono(principal.nextLine());
        System.out.print("------------------------------------- ");
        if (contactoEncontrado != null) {
            System.out.println(contactoEncontrado.toString());
        } else {
            System.out.println("\nNo se encuentró un contacto. ");
        }
        System.out.println("------------------------------------- ");
        System.out.println("Presione cualquier tecla para continuar...");
        principal.nextLine();

    }

    private static void buscarContactoPorNombre() {
        Scanner principal = new Scanner(System.in);
        System.out.print("Ingrese un Nombre: ");
        Contacto contactoEncontrado = AgenteContactos.buscarContactoPorNombre(principal.nextLine());
        System.out.print("------------------------------------- ");
        if (contactoEncontrado != null) {
            System.out.println(contactoEncontrado.toString());
        } else {
            System.out.println("\nNo se encuentró un contacto. ");
        }
        System.out.println("------------------------------------- ");
        System.out.println("Presione cualquier tecla para continuar...");
        principal.nextLine();
    }

    private static void listarContactosOrdenados() {
        Scanner principal = new Scanner(System.in);

        System.out.println("###############################");
        System.out.println("#### Listado de Contactos #####");
        System.out.println("############################### ");
        System.out.print("------------------------------------- ");

        Contacto[] listaContactos = AgenteContactos.getListaContactosOrdenados();
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                System.out.println(listaContactos[i].toString());
                System.out.print("------------------------------------- ");
            }
        }
        System.out.println("");

        System.out.println("Presione cualquier tecla para continuar...");
        principal.nextLine();

    }

    private static Contacto seleccionarContacto() {
        System.out.println("0 - Volver ");
        Contacto[] listaContactos = AgenteContactos.getListaContactosOrdenados();
        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] != null) {
                System.out.println((i + 1) + " - " + listaContactos[i].getApellido() + ", " + listaContactos[i].getNombre());
            }
        }

        Scanner principal = new Scanner(System.in);
        System.out.print("Contacto Seleccionado: ");
        int indice = principal.nextInt();
        if (indice == 0) {
            return null;
        }
        return listaContactos[indice - 1];
    }

    private static Telefono seleccionarTelefono(Contacto contacto) {
        System.out.println("0 - Volver ");
        Telefono[] listaTelefono = contacto.getTelefonos();
        for (int i = 0; i < listaTelefono.length; i++) {
            if (listaTelefono[i] != null) {
                System.out.println((i + 1) + " - " + listaTelefono[i].toString());
            }
        }

        Scanner principal = new Scanner(System.in);
        System.out.print("Telefono Seleccionado: ");
        int indice = principal.nextInt();
        if (indice == 0) {
            return null;
        }
        return contacto.getTelefonos()[indice - 1];
    }

    private static void listarContactosProximoCumple() {
        Scanner principal = new Scanner(System.in);
        boolean encontroContactos = false;
        System.out.println("###############################");
        System.out.println("#### Proximos Cumpleaños #####");
        System.out.println("############################### ");
        System.out.println("------------------------------------- ");

        Contacto[] listaContactos = AgenteContactos.getListaContactosProxCumple();
        if (listaContactos.length > 0) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] != null) {
                    System.out.println(listaContactos[i].getApellido() + ", " + listaContactos[i].getNombre());
                    System.out.println("Días para Cumpleaños: " + listaContactos[i].getDiasParaCumple());
                    for (int j = 0; j < listaContactos[i].getTelefonos().length; j++) {
                        if (listaContactos[i].getTelefonos()[j] != null) {
                            System.out.println(listaContactos[i].getTelefonos()[j].toString());
                        }
                    }
                    encontroContactos = true;
                    System.out.println("------------------------------------- ");
                }
            }
        }

        if (!encontroContactos) {
            System.out.println("\nNo se encuentraron contactos. ");
            System.out.println("------------------------------------- ");
        }

        System.out.println("Presione cualquier tecla para continuar...");
        principal.nextLine();
    }

    private static void inicializarDatos() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Contacto contacto;

        contacto = new Contacto();
        contacto.setNombre("Ruben");
        contacto.setApellido("Romero");
        contacto.setDni("1111111111");

        contacto.setFechaNacimiento(format.parse("10/01/1979"));
        contacto.getTelefonos()[0] = new Telefono();
        contacto.getTelefonos()[0].setTipo(1);
        contacto.getTelefonos()[0].setNumero("111111111");

        contacto.getTelefonos()[1] = new Telefono();
        contacto.getTelefonos()[1].setTipo(2);
        contacto.getTelefonos()[1].setNumero("222222222");

        contacto.getTelefonos()[2] = new Telefono();
        contacto.getTelefonos()[2].setTipo(3);
        contacto.getTelefonos()[2].setNumero("3333333");

        AgenteContactos.agregarContacto(contacto);

        contacto = new Contacto();
        contacto.setNombre("Elda");
        contacto.setApellido("Flores");
        contacto.setDni("222222222");
        contacto.setFechaNacimiento(format.parse("10/10/1989"));
        contacto.getTelefonos()[0] = new Telefono();
        contacto.getTelefonos()[0].setTipo(1);
        contacto.getTelefonos()[0].setNumero("44444444");

        contacto.getTelefonos()[1] = new Telefono();
        contacto.getTelefonos()[1].setTipo(2);
        contacto.getTelefonos()[1].setNumero("345345345");

        contacto.getTelefonos()[2] = new Telefono();
        contacto.getTelefonos()[2].setTipo(3);
        contacto.getTelefonos()[2].setNumero("777777777");

        AgenteContactos.agregarContacto(contacto);

        contacto = new Contacto();
        contacto.setNombre("Pablo");
        contacto.setApellido("Romero");
        contacto.setDni("333333333");

        contacto.setFechaNacimiento(format.parse("25/06/1983"));
        contacto.getTelefonos()[0] = new Telefono();
        contacto.getTelefonos()[0].setTipo(1);
        contacto.getTelefonos()[0].setNumero("888888888");

        contacto.getTelefonos()[1] = new Telefono();
        contacto.getTelefonos()[1].setTipo(2);
        contacto.getTelefonos()[1].setNumero("999999999");

        contacto.getTelefonos()[2] = new Telefono();
        contacto.getTelefonos()[2].setTipo(3);
        contacto.getTelefonos()[2].setNumero("999999999");

        AgenteContactos.agregarContacto(contacto);
    }

}

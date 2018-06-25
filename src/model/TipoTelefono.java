/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ruben
 */
public enum TipoTelefono {

    CASA(1), TRABAJO(2), MOVIL(3), PRINCIPAL(4), OTRO(5);

    static TipoTelefono findByValue(int tipo) {
        switch (tipo) {
            case 1:
                return CASA;
            case 2:
                return TRABAJO;
            case 3:
                return MOVIL;
            case 4:
                return PRINCIPAL;
            default:
                return OTRO;
        }
    }

    private int value;

    TipoTelefono(int tipo) {
        this.value = tipo;
    }

}

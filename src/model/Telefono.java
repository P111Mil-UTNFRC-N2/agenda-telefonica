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
public class Telefono {
    private TipoTelefono tipo;
    private String numero;
    
    public Telefono ()
    {
    }

    public TipoTelefono getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    
    
    public void setTipo(int tipo) {
        this.tipo = TipoTelefono.findByValue(tipo);
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefono " + tipo + ": " + numero;
    }
}

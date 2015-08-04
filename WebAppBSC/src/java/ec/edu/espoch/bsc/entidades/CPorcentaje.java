/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.bsc.entidades;

/**
 *
 * @author ©foqc
 */
public class CPorcentaje {
    private int codigo;
    private int porcentajeMayor;
    private int porcentajeMenor;

    public CPorcentaje() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPorcentajeMayor() {
        return porcentajeMayor;
    }

    public void setPorcentajeMayor(int porcentajeMayor) {
        this.porcentajeMayor = porcentajeMayor;
    }

    public int getPorcentajeMenor() {
        return porcentajeMenor;
    }

    public void setPorcentajeMenor(int porcentajeMenor) {
        this.porcentajeMenor = porcentajeMenor;
    }
    
    
}

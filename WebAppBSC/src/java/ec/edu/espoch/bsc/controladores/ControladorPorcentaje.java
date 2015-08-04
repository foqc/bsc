/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.bsc.controladores;

import ec.edu.espoch.bsc.entidades.CPorcentaje;
import ec.edu.espoch.bsc.modelo.MPorcentaje;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

/**
 *
 * @author @foqc
 */
@ManagedBean
@ViewScoped
public class ControladorPorcentaje implements Serializable{

    private CPorcentaje objPorcentaje;
    private CPorcentaje selObjPorcentaje;
    private ArrayList<CPorcentaje> lstPorcentaje;

    /**
     * Creates a new instance of ControladorTipoUsuario
     */
    public ControladorPorcentaje() {
        this.objPorcentaje = new CPorcentaje();
        this.selObjPorcentaje = new CPorcentaje();
        this.lstPorcentaje = new ArrayList<>();
    }

    public CPorcentaje getObjPorcentaje() {
        return objPorcentaje;
    }

    public void setObjPorcentaje(CPorcentaje objPorcentaje) {
        this.objPorcentaje = objPorcentaje;
    }

    public CPorcentaje getSelObjPorcentaje() {
        return selObjPorcentaje;
    }

    public void setSelObjPorcentaje(CPorcentaje selObjPorcentaje) {
        this.selObjPorcentaje = selObjPorcentaje;
    }

    public ArrayList<CPorcentaje> getLstPorcentaje() {
        return lstPorcentaje;
    }

    public void setLstPorcentaje(ArrayList<CPorcentaje> lstPorcentaje) {
        this.lstPorcentaje = lstPorcentaje;
    }

    /*
     postonstructor se ejecuta luego del constructor
     */
    @PostConstruct
    public void reinit() {
        cargarPorcentajes();

    }

    public void cargarPorcentajes() {
        try {
            this.lstPorcentaje = (ArrayList<CPorcentaje>) MPorcentaje.cargarPorcentajes();
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Insertar Porcentaje">
    public void insertarTipoUsuario() {
        try {
            if (MPorcentaje.insertarPorcentaje(objPorcentaje)) {
                Util.addSuccessMessage("Datos Insertados!");
                cargarPorcentajes();

            } else {
                Util.mostrarMensaje("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="Actualizar Porcentaje">
    public void actualizarTipoUsuario() {
        try {
            if (MPorcentaje.actualizarPorcentaje(selObjPorcentaje)) {
                cargarPorcentajes();
                DefaultRequestContext.getCurrentInstance().execute("PF('TporcentajeEditDialog').hide()");
                Util.addSuccessMessage("Datos actualizados!");                
            } else {
                Util.mostrarMensaje("Datos no actualizados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
     //</editor-fold>

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Eliminar Porcentaje">
    public void eliminarTipoUsuario() {
        try {
            if (MPorcentaje.eliminarPorcentaje(selObjPorcentaje.getCodigo())) {
                DefaultRequestContext.getCurrentInstance().execute("PF('TporcentajeDeleteDialog').hide()");
                Util.addSuccessMessage("Datos eliminados!");
                cargarPorcentajes();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
}

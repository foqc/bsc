/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.bsc.controladores;

import ec.edu.espoch.bsc.entidades.CTipoUsuario;
import ec.edu.espoch.bsc.modelo.MUsuario;
import ec.edu.espoch.bsc.entidades.CUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

/**
 *
 * @author @foqc
 */
@ManagedBean
@ViewScoped
public class ControladorUsuario implements Serializable {

    private CUsuario objUsuario;
    private CUsuario selObjUsuario;
    private ArrayList<CUsuario> lstUsuarios;
    private ArrayList<CUsuario> objUsuarioSesion;

    public ControladorUsuario() {
        this.objUsuario = new CUsuario();
        this.lstUsuarios = new ArrayList<>();
        this.objUsuarioSesion = new ArrayList<>();
        this.selObjUsuario = new CUsuario();
    }

    public CUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(CUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public CUsuario getSelObjUsuario() {
        return selObjUsuario;
    }

    public void setSelObjUsuario(CUsuario selObjUsuario) {
        this.selObjUsuario = selObjUsuario;
    }

    public ArrayList<CUsuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<CUsuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public ArrayList<CUsuario> getObjUsuarioSesion() {
        return objUsuarioSesion;
    }

    public void setObjUsuarioSesion(ArrayList<CUsuario> objUsuarioSesion) {
        this.objUsuarioSesion = objUsuarioSesion;
    }

    /*
     postonstructor se ejecuta luego del constructor
     */
    @PostConstruct
    public void reinit() {
        CTipoUsuario objTipo = new CTipoUsuario();
        this.objUsuario.setObjTipoUsuario(objTipo);
        this.selObjUsuario.setObjTipoUsuario(objTipo);
        cargarUsuario();
        cargarUsuarioPorSesion();

    }
    /*
     Metodo que permite cargar todos los registros de la base de datos
     */

    public void cargarUsuario() {
        try {
            this.lstUsuarios = (ArrayList<CUsuario>) MUsuario.cargarUsuarios();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void cargarUsuarioPorSesion() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            ControladorLogin loginBean = (ControladorLogin) session.getAttribute("controladorLogin");

            this.objUsuarioSesion = (ArrayList<CUsuario>) MUsuario.cargarUsuarioPorSesion(loginBean.getObjUsuario());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Insertar Usuario">
    public void insertarUsuario() {
        try {
            if (MUsuario.insertarUsuario(objUsuario)) {
                cargarUsuario();
                Util.addSuccessMessage("Datos insertados!");
            } else {
                Util.mostrarMensaje("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    //</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Actualizar Usuario">
    public void actualizarPersona() {
        Boolean resp = false,resp1=false;
        try {
            /*pregunto si hay dato en el campo clave, para de acuerdo a eso,
              lanzar la funcion pgsql determinado, esto xq una funcion
              encripta la clave al actualizar, y si va el campo vacio lo encripta
              de igual manera generando una contrasenia distinta,  por ello uso 2 funciones pgsql
              donde el otro simplemente si no hay clave se queda con la clave original*/
            if (selObjUsuario.getClave().isEmpty()) {
                if (MUsuario.actualizarUsuarioSinClave(selObjUsuario)) {
                    resp = true;
                }
            } else {
                if (MUsuario.actualizarUsuario(selObjUsuario)) {
                    resp = true;resp1=true;
                }
            }
            if (resp) {
                cargarUsuario();
                if(resp1)
                    cargarUsuarioPorSesion();//actualiza objUsuarioSesion(ojo no es la variable de sesion)

                /**
                 * ***** actualizo sesion(esto en caso que se haya cambiado tipo
                 * usuario o contrasenia)    *******
                 */
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
                ControladorLogin loginBean = (ControladorLogin) session.getAttribute("controladorLogin");
                ArrayList<CUsuario> myUser = (ArrayList<CUsuario>) MUsuario.cargarUsuarioPorSesion(loginBean.getObjUsuario());
                for (CUsuario myUser1 : myUser) {
                    loginBean.setObjUsuario(myUser1);
                }
                /**
                 * ***** fin actualizo sesion    *******
                 */

                DefaultRequestContext.getCurrentInstance().execute("PF('TusuarioEditDialog').hide()");
                Util.addSuccessMessage("Datos actualizados!");
            } else {
                Util.mostrarMensaje("Datos no actualizados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Eliminar Usuario">
    public void eliminarPersona() {
        try {
            if (MUsuario.eliminarUsuario(selObjUsuario.getCodigo())) {
                DefaultRequestContext.getCurrentInstance().execute("PF('TusuarioDeleteDialog').hide()");
                Util.addSuccessMessage("Datos eliminados!");
                cargarUsuario();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
}

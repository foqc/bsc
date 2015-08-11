/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import ec.edu.espoch.bsc.entidades.CTipoUsuario;
import ec.edu.espoch.bsc.modelo.MTipoUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author FOQC
 */
@ManagedBean
@RequestScoped
public class RolBean {

    private List<SelectItem> selectOneItemsRol;

    public RolBean() {
    }

    public List<SelectItem> getSelectOneItemsRol() {
        try {
            this.selectOneItemsRol = new ArrayList<>();
            List<CTipoUsuario> roles = MTipoUsuario.cargarTipoUsuarios();
            for (CTipoUsuario rol : roles) {
                SelectItem selectItem = new SelectItem(rol.getCodigo(), rol.getDescripcion());
                this.selectOneItemsRol.add(selectItem);
            }
        } catch (Exception e) {
            System.err.println("%%%%%%%%%%%%%%%%%%%% " + e.getMessage() + "%%%%%%%%%%%%%%%%%%%%");
        }
        return this.selectOneItemsRol;
    }

    //Devuelve lista de roles a excepcion de usuario administrador

    public List<SelectItem> getSelectOneItemsRolSinAdmin() {
        try {
            this.selectOneItemsRol = new ArrayList<>();
            List<CTipoUsuario> roles = MTipoUsuario.cargarTipoUsuarios();
            for (CTipoUsuario rol : roles) {
                if (!rol.getDescripcion().equals("Administrador")) {
                    SelectItem selectItem = new SelectItem(rol.getCodigo(), rol.getDescripcion());
                    this.selectOneItemsRol.add(selectItem);
                }
            }
        } catch (Exception e) {
            System.err.println("%%%%%%%%%%%%%%%%%%%% " + e.getMessage() + "%%%%%%%%%%%%%%%%%%%%");
        }
        return this.selectOneItemsRol;
    }
}

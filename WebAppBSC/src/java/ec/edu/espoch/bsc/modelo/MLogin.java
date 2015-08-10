/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.bsc.modelo;

import ec.edu.espoch.bsc.accesodatos.AccesoDatos;
import ec.edu.espoch.bsc.accesodatos.ConjuntoResultado;
import ec.edu.espoch.bsc.accesodatos.Parametro;
import ec.edu.espoch.bsc.entidades.CTipoUsuario;
import ec.edu.espoch.bsc.entidades.CUsuario;
import java.util.ArrayList;

/**
 *
 * @author Tupac
 */
public class MLogin {

    public static CUsuario loginUsuario(CUsuario objUsuario) throws Exception {
         
        try {
            String sql = "SELECT * FROM bsc.fn_selectxsesion_tusuario(?,?) ";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, objUsuario.getAlias()));
            lstParam.add(new Parametro(2, objUsuario.getClave()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                CTipoUsuario objTipoUsuario = MTipoUsuario.cargarRolPorCodigo(rs.getInt(10));
                objUsuario.setObjTipoUsuario(objTipoUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return objUsuario;
    }

}

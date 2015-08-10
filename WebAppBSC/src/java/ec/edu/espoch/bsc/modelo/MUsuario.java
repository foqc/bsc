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
import java.util.List;

/**
 *
 * @author @foqc
 */
public class MUsuario {

    //Insertar cPersona
    public static boolean insertarUsuario(CUsuario usuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            String sql = "SELECT bsc.fn_insert_tusuario(?,?,?,?,?,?,?,?,?,?)";
            lstParamUsusario.add(new Parametro(1, usuario.getCedula()));
            lstParamUsusario.add(new Parametro(2, usuario.getNombres()));
            lstParamUsusario.add(new Parametro(3, usuario.getApellidos()));
            lstParamUsusario.add(new Parametro(4, usuario.getAlias()));
            lstParamUsusario.add(new Parametro(5, usuario.getClave()));
            lstParamUsusario.add(new Parametro(6, usuario.getTelefono()));
            lstParamUsusario.add(new Parametro(7, usuario.getCelular()));
            lstParamUsusario.add(new Parametro(8, usuario.getCorreo()));
            lstParamUsusario.add(new Parametro(9, usuario.getDireccion()));
            lstParamUsusario.add(new Parametro(10, usuario.getObjTipoUsuario().getCodigo()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
            while (rs.next()) {
                if (rs.getBoolean(0)) {
                    respuesta = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

    public static List<CUsuario> cargarUsuarios() throws Exception {
        List<CUsuario> lstUsuarios = new ArrayList<>();
        try {
            String sql = "select * from bsc.fn_select_tusuario()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                CUsuario usuario = new CUsuario();
                usuario.setCodigo(rs.getInt(0));
                usuario.setCedula(rs.getString(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setAlias(rs.getString(4));
                //usuario.setClave(rs.getString(5));
                usuario.setTelefono(rs.getString(6));
                usuario.setCelular(rs.getString(7));
                usuario.setCorreo(rs.getString(8));
                usuario.setDireccion(rs.getString(9));

                CTipoUsuario objTipoUsuario = MTipoUsuario.cargarRolPorCodigo(rs.getInt(10));

                usuario.setObjTipoUsuario(objTipoUsuario);

                lstUsuarios.add(usuario);
            }
            rs = null;
        } catch (Exception e) {
            lstUsuarios.clear();
            throw e;
        }
        return lstUsuarios;
    }
    
    //metodo que devuelve datos de user si coincide usuario y contrase;a
    public static List<CUsuario> cargarUsuarioPorSesion(CUsuario usuario) throws Exception {
        List<CUsuario> lstUsuario = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bsc.fn_selectxsesionxalias_tusuario(?) ";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, usuario.getAlias()));//alias
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                CUsuario objUsuario = new CUsuario();
                objUsuario.setCodigo(rs.getInt(0));
                objUsuario.setCedula(rs.getString(1));
                objUsuario.setNombres(rs.getString(2));
                objUsuario.setApellidos(rs.getString(3));
                objUsuario.setAlias(rs.getString(4));
                //objUsuario.setClave(rs.getString(5));//
                objUsuario.setTelefono(rs.getString(6));
                objUsuario.setCelular(rs.getString(7));
                objUsuario.setCorreo(rs.getString(8));
                objUsuario.setDireccion(rs.getString(9));
                CTipoUsuario objTipoUsuario = MTipoUsuario.cargarRolPorCodigo(rs.getInt(10));
                objUsuario.setObjTipoUsuario(objTipoUsuario);
                lstUsuario.add(objUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lstUsuario;
    }

    public static boolean actualizarUsuario(CUsuario usuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            String sql = "SELECT bsc.fn_update_tusuario(?,?,?,?,?,?,?,?,?,?,?)";
            lstParamUsusario.add(new Parametro(1, usuario.getCodigo()));
            lstParamUsusario.add(new Parametro(2, usuario.getCedula()));
            lstParamUsusario.add(new Parametro(3, usuario.getNombres()));
            lstParamUsusario.add(new Parametro(4, usuario.getApellidos()));
            lstParamUsusario.add(new Parametro(5, usuario.getAlias()));
            lstParamUsusario.add(new Parametro(6, usuario.getClave()));
            lstParamUsusario.add(new Parametro(7, usuario.getTelefono()));
            lstParamUsusario.add(new Parametro(8, usuario.getCelular()));
            lstParamUsusario.add(new Parametro(9, usuario.getCorreo()));
            lstParamUsusario.add(new Parametro(10, usuario.getDireccion()));
            lstParamUsusario.add(new Parametro(11, usuario.getObjTipoUsuario().getCodigo()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
            while (rs.next()) {
                if (rs.getBoolean(0)) {
                    respuesta = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
    public static boolean actualizarUsuarioSinClave(CUsuario usuario) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamUsusario = new ArrayList<>();
            String sql = "SELECT bsc.fn_selectxalias_sinclave_tusuario(?,?,?,?,?,?,?,?,?)";
            lstParamUsusario.add(new Parametro(1, usuario.getAlias()));
            lstParamUsusario.add(new Parametro(2, usuario.getCedula()));
            lstParamUsusario.add(new Parametro(3, usuario.getNombres()));
            lstParamUsusario.add(new Parametro(4, usuario.getApellidos()));
            lstParamUsusario.add(new Parametro(5, usuario.getTelefono()));
            lstParamUsusario.add(new Parametro(6, usuario.getCelular()));
            lstParamUsusario.add(new Parametro(7, usuario.getCorreo()));
            lstParamUsusario.add(new Parametro(8, usuario.getDireccion()));
            lstParamUsusario.add(new Parametro(9, usuario.getObjTipoUsuario().getCodigo()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamUsusario);
            while (rs.next()) {
                if (rs.getBoolean(0)) {
                    respuesta = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

    public static boolean eliminarUsuario(int codigoPersona) throws Exception {
        boolean respuesta = false;
        try {
            String sql = "select bsc.fn_delete_tusuario(?)";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, codigoPersona));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParam);
            while (rs.next()) {
                respuesta = rs.getBoolean(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }

}

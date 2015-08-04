/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.bsc.modelo;

import ec.edu.espoch.bsc.accesodatos.AccesoDatos;
import ec.edu.espoch.bsc.accesodatos.ConjuntoResultado;
import ec.edu.espoch.bsc.accesodatos.Parametro;
import ec.edu.espoch.bsc.entidades.CPorcentaje;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author @foqc
 */
public class MPorcentaje {

    public static boolean insertarPorcentaje(CPorcentaje objPorcentaje) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamPorcentaje = new ArrayList<>();
            String sql = "SELECT bsc.fn_insert_tporcentaje(?,?)";
            lstParamPorcentaje.add(new Parametro(1, objPorcentaje.getPorcentajeMenor()));
            lstParamPorcentaje.add(new Parametro(2, objPorcentaje.getPorcentajeMayor()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamPorcentaje);
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

    public static List<CPorcentaje> cargarPorcentajes() throws Exception {
        List<CPorcentaje> lstPorcentajes = new ArrayList<>();
        try {
            String sql = "select * from bsc.fn_select_tporcentaje()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                CPorcentaje objPorcentaje = new CPorcentaje();
                objPorcentaje.setCodigo(rs.getInt(0));
                objPorcentaje.setPorcentajeMenor(rs.getInt(1));
                objPorcentaje.setPorcentajeMayor(rs.getInt(2));

                lstPorcentajes.add(objPorcentaje);
            }
            rs = null;
        } catch (Exception e) {
            lstPorcentajes.clear();
            throw e;
        }
        return lstPorcentajes;
    }

    public static boolean actualizarPorcentaje(CPorcentaje objPorcentaje) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamPorcentaje = new ArrayList<>();
            String sql = "SELECT bsc.fn_update_tporcentaje(?,?,?)";
            lstParamPorcentaje.add(new Parametro(1, objPorcentaje.getCodigo()));
            lstParamPorcentaje.add(new Parametro(2, objPorcentaje.getPorcentajeMenor()));
            lstParamPorcentaje.add(new Parametro(3, objPorcentaje.getPorcentajeMayor()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamPorcentaje);
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

    public static boolean eliminarPorcentaje(int codigo) throws Exception {
        boolean respuesta = false;
        try {
            String sql = "select bsc.fn_delete_tporcentaje(?)";
            ArrayList<Parametro> lstParam = new ArrayList<>();
            lstParam.add(new Parametro(1, codigo));

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

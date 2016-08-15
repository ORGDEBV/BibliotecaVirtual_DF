/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Autor;
import vb.entidad.LogTabla;
import vb.util.sql;

/**
 *
 * @author virtual
 */
public class logTablaDao {

    public static boolean registrarLogTabla(LogTabla lt) {
        sql conector = new sql();
        boolean flag = false;

        String[] parametros = new String[5];
        parametros[0] = lt.getNameEsquema();
        parametros[1] = lt.getNameTabla();
        parametros[2] = lt.getID_REGISTRO();
        parametros[3] = lt.getID_ACCION();
        parametros[4] = lt.getID_USUARIO();

        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_TABLA_REGISTRAR]", parametros);

        for (Object[] dato : datos) {
            if (dato[0].toString().equals("1")) {
                flag = true;

            } else {
                flag = false;
            }
        }

        return flag;
    }

    public static List<Object[]> listarLog() {
        sql conector = new sql();
        List<Object[]> lst = new ArrayList<>();

        String[] parametros = new String[1];
        parametros[0] = "LISTAR";

        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_CONSULTA]", parametros);
        for (Object[] d : datos) {
            lst.add(d);

        }
        return lst;
    }
     public static List<Object[]> listarLogDocumental() {
        sql conector = new sql();
        List<Object[]> lst = new ArrayList<>();

        String[] parametros = new String[1];
        parametros[0] = "LISTAR_HISTORIAL_DOCUMENTAL";

        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_CONSULTA]", parametros);
        for (Object[] d : datos) {
            lst.add(d);

        }
        return lst;
    }

}

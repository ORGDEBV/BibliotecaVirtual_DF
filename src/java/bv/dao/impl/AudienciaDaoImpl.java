/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao.impl;

import bv.dao.AudienciaDao;
import java.util.List;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class AudienciaDaoImpl implements AudienciaDao{
    
    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboAudiencia(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "AUDIENCIA_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_AUDIENCIA", parametros);
        return list;
    }
}

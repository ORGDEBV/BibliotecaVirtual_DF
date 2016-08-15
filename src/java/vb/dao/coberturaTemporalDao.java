/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.List;
import vb.entidad.CoberturaTemporal;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class coberturaTemporalDao implements entidadService<CoberturaTemporal>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(CoberturaTemporal t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(CoberturaTemporal t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CoberturaTemporal> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CoberturaTemporal buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Object[]> llenaComboCoberturaTemporal(){
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "COBERTURA_TEMPORAL_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_COBERTURA_TEMPORAL", parametros);
        return list;
    }
    
}

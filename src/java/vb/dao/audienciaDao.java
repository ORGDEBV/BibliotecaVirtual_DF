/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.List;
import vb.entidad.Audiencia;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class audienciaDao implements entidadService<Audiencia>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(Audiencia t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(Audiencia t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Audiencia> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Audiencia buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Object[]> llenaComboAudiencia(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "AUDIENCIA_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_AUDIENCIA", parametros);
        return list;
    }
}

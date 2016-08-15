package vb.dao;

import java.util.List;
import vb.entidad.TipoOtro;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class tipoOtroDao implements  entidadService<TipoOtro>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(TipoOtro t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(TipoOtro t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoOtro> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoOtro buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Object[]> llenaComboTipoOtro(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LISTAR_TIPO_OTRO";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_TIPO_OTRO", parametros);
        return list;
    }
    
}

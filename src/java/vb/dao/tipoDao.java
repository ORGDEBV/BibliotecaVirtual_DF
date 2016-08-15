package vb.dao;

import java.util.List;
import vb.entidad.Tipo;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class tipoDao implements entidadService<Tipo>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(Tipo tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(Tipo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipo> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tipo buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Object[]> llenaComboTipo1(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "TIPO_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_TIPO", parametros);
        return list;
    }
    public List<Object[]> llenaComboTipoXperfil(String perfil){
        String[] parametros = new String[1];
        parametros[0] = perfil;
        List<Object[]> list = conector.execProcedure("BV.SP_LISTAR_TIPO_XPERFIL", parametros);
        return list;
    }
    
}

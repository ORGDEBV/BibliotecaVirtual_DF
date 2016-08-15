package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Lenguaje;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class lenguajeDao implements entidadService<Lenguaje>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(Lenguaje t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(Lenguaje t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lenguaje> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lenguaje buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Object[]> llenaComboLenguaje(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LENGUAJE_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_LENGUAJE", parametros);
        return list;
    }
    
    public ArrayList<String> lisLenguajeDocumentalXidDocumental(String idDocumental){
    ArrayList<String> lst=new ArrayList<>();
     String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
         ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_LENGUAJE_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String len=d[0].toString();
            
             lst.add(len);
                       
        }
        return lst;
    
    }
    
    public List<Lenguaje> listarSerieIdDocumental(String idDocumental) {
        Lenguaje lenguaje = null;
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_LENGUAJE_ID_DOCUMENTAL", parametros);
        ArrayList<Lenguaje> lista = new ArrayList<Lenguaje>();
        for (Object[] dato : listaIn) {
            lenguaje = new Lenguaje();
            lenguaje.setID_LENGUAJE(dato[0].toString());
            lenguaje.setLENGUAJE(dato[1].toString());
            lista.add(lenguaje);
        }
        return lista;
    }
    
}

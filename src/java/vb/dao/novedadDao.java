
package vb.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vb.entidad.Novedad;
import vb.servicio.entidadService;
import vb.util.dateConverter;
import vb.util.sql;

public class novedadDao implements entidadService<Novedad>{

    sql conector = new sql();     
    dateConverter d = new dateConverter();
  
    public int obetenerId(){
        int n=0;        
        ArrayList<Object[]> data = null;
        String[] parametros = new String[10];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "OBTENER_ID_NOVEDAD";
        data = conector.execProcedure("[web].[SP_NOVEDADES_MANTENIMIENTO]", parametros);
        for (Object[] dat : data) {
            n = Integer.parseInt(dat[0].toString());
        }        
        return n;
    }

    @Override
    public int crearEntidad(Novedad t) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(Novedad t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Novedad> obtenerEntidades() {
        List<Novedad> lista = new ArrayList<>();
        Novedad novedad;
        String[] parametros = new String[10];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "LISTAR_NOVEDADES_INTERNO";
        List<Object[]> listain = conector.execProcedure("[web].[SP_NOVEDADES_MANTENIMIENTO]", parametros);
        try {
            for (Object[] dato : listain) {
            novedad = new Novedad();
            novedad.setID_NOVEDADES(Integer.parseInt(dato[0].toString()));
            novedad.setTITULO_CORTO(dato[1].toString());
            novedad.setTITULO_COMPLETO(dato[2].toString());
            novedad.setDESCRIPCION(dato[3].toString());
            novedad.setS_FECHA_NOVEDAD(dato[4].toString());
            novedad.setMOSTAR_INICIO(dato[5].toString());
            novedad.setACTIVO(dato[6].toString());
            novedad.setURL_IMAGEN(dato[7].toString());
            lista.add(novedad);
        }
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
        }
        return lista;
    }

    @Override
    public Novedad buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


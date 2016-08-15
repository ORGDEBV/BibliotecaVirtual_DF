package vb.dao;

import java.util.ArrayList;
import vb.entidad.FormatoMedio;
import java.util.List;
import vb.servicio.entidadService;
import vb.util.sql;

public class formatoMedioDao implements entidadService<FormatoMedio> {
    
    sql conector = new sql();
    
    public List<Object[]> llenaComboFormatoMedio(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LIST_CBOFORMATOMEDIO";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        return list;
    }

    @Override
    public int crearEntidad(FormatoMedio fm) {
        int n = 0;
        ArrayList<Object[]> datos = null;
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = fm.getDESCRIPCION();
        parametros[2] = "ADD_FORMATOMEDIO";
        datos = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        for (Object[] d : datos) {
            if(d[0].toString().equals("1")){
                n=1;
            }
        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(FormatoMedio fm) {
        int n = 0;
        ArrayList<Object[]> datos = null;
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(fm.getID_FORMATO_MEDIO());
        parametros[1] = fm.getDESCRIPCION();
        parametros[2] = "UPD_FORMATOMEDIO";
        datos = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        for (Object[] d : datos) {
            if(d[0].toString().equals("1")){
                n=1;
            }
        }
        return n;
    }

    @Override
    public List<FormatoMedio> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FormatoMedio buscarEntidad(int codigo) {
        FormatoMedio fm = null;
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(codigo);
        parametros[1] = "";
        parametros[2] = "FINDX_FORMATOMEDIO";
        List<Object[]> listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        for (Object[] dato : listaIn) {
            fm = new FormatoMedio();
            fm.setID_FORMATO_MEDIO(Integer.parseInt(dato[0].toString()));
            fm.setDESCRIPCION(dato[1].toString());            
        }
        return fm;
    }
    
    
}

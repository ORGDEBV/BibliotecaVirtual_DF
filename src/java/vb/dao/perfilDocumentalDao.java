/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.PerfilDocumental;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class perfilDocumentalDao implements  entidadService<PerfilDocumental>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(PerfilDocumental t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(PerfilDocumental t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PerfilDocumental> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PerfilDocumental buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<PerfilDocumental> listaPerfil(){
        List<PerfilDocumental> lista = new ArrayList<>();
        PerfilDocumental pd = null;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "PERFIL_DOCUMENTAL_LIST"; 

        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERFIL_DOCUMENTAL", parametros);
        for (Object[] datas : data) {
            pd = new PerfilDocumental();
            pd.setID_PERFIL_DOCUMENTAL(Integer.parseInt(datas[0].toString()));
            pd. setPERFIL_DOCUMENTAL(datas[1].toString());
            pd.setDESCRIPCION(datas[2].toString());
            pd.setESTADO(Integer.parseInt(datas[3].toString()));
            pd.setICONO(datas[4].toString());
            lista.add(pd);
        }
        return lista;
    }
    public List<PerfilDocumental> listaPerfilDocumental(String idBiblioteca){
        List<PerfilDocumental> lista = new ArrayList<>();
        PerfilDocumental pd = null;
        String[] parametros = new String[2];
     
        parametros[0] = idBiblioteca;
        parametros[1] = "PERFIL_DOCUMENTAL_LIST"; 

        ArrayList<Object[]> data = conector.execProcedure(" BV.SP_LISTAR_PERFIL_DOCUMENTAL_XBIBLIOTECA", parametros);
        for (Object[] datas : data) {
            pd = new PerfilDocumental();
            pd.setID_PERFIL_DOCUMENTAL(Integer.parseInt(datas[0].toString()));
            pd. setPERFIL_DOCUMENTAL(datas[1].toString());
            pd.setDESCRIPCION(datas[2].toString());
            pd.setESTADO(Integer.parseInt(datas[3].toString()));
            pd.setICONO(datas[4].toString());
            lista.add(pd);
        }
        return lista;
    }
   
}

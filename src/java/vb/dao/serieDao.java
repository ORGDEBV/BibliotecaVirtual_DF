/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Serie;
import vb.servicio.entidadService;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class serieDao implements entidadService<Serie>{
    
    sql conector = new sql();

    @Override
    public int crearEntidad(Serie serie) {
        int n = 0;
//        ArrayList<Object[]> data = null;
//        String[] parametros = new String[4];
//        parametros[0] = "";
//        parametros[1] = serie.getSERIE();
//        parametros[2] = "ADD_SERIE";
//        parametros[3] = "";
//        data = conector.execProcedure("BV.SP_MANTENIMIENTO_SERIE",parametros);
//        for (Object[] dat : data) {
//            if(dat[0].toString().equals("1")){
//                n = 1;
//            }
//        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizarEntidad(Serie serie) {
        int update = 0;
        String[] parametros = new String[4];
        parametros[0] = serie.getID_SERIE().toString();
        parametros[1] = serie.getSERIE();
        parametros[2] = "UPD_SERIE";
        parametros[3] = "";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_SERIE", parametros);
        for (Object[] lista : list) {
            update = Integer.parseInt(lista[0].toString());
        }
        return update;
    }
     public ArrayList<String> listSerieDocumentallXidDocumental(String idDocumental){
    ArrayList<String> lst=new ArrayList<>();
    
     String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
         ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_SERIE_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
          String  obj=d[0].toString();       
            lst.add(obj);
                       
        }
        return lst;
    
    }
    
    
    

    @Override
    public List<Serie> obtenerEntidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Serie buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Serie> obtenerSeleccion(String series){
        List<Serie> listSerie = new ArrayList<Serie>();
        Serie serie = null;
        String[] parametros = new String[1];
        parametros[0] = series;
        List<Object[]> list = conector.execProcedure("BV.SP_LISTAR_SELECCION_SERIE", parametros);
        for (Object[] lista : list) {
            serie = new Serie();
            serie.setID_SERIE(Integer.parseInt(lista[0].toString()));
            serie.setSERIE(lista[1].toString());
            listSerie.add(serie);
        }
        return listSerie;
    }
    
    public List<Object[]> llenaComboSerie(String idBiblioteca){
        String[] parametros = new String[4];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LISTAR_SERIE";
        parametros[3] = idBiblioteca;
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_SERIE", parametros);
        return list;
    }
    
    public List<Serie> listarSerieIdDocumental(String idDocumental) {
        Serie serie = null;
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_SERIE_ID_DOCUMENTAL", parametros);
        ArrayList<Serie> lista = new ArrayList<Serie>();
        for (Object[] dato : listaIn) {
            serie = new Serie();
            serie.setID_SERIE(Integer.parseInt(dato[0].toString()));
            serie.setSERIE(dato[1].toString());
            lista.add(serie);
        }
        return lista;
    }
    
    public int crearEntidad(Serie serie,String idBiblioteca) {
        int n = 0;
        ArrayList<Object[]> data = null;
        String[] parametros = new String[4];
        parametros[0] = "";
        parametros[1] = serie.getSERIE();
        parametros[2] = "ADD_SERIE";
        parametros[3] = idBiblioteca;
        data = conector.execProcedure("BV.SP_MANTENIMIENTO_SERIE",parametros);
        for (Object[] dat : data) {
            if(dat[0].toString().equals("1")){
                n = 1;
            }
        }
        return n;
    }
    
}

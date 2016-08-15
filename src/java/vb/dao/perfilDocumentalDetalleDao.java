/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.LogTabla;
import vb.entidad.MetadataTabla;
import vb.entidad.PerfilDocumentalDetalle;
import vb.util.sql;

/**
 *
 * @author virtual
 */
public class perfilDocumentalDetalleDao {

    sql conector = new sql();

    public List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalle(String perfil) {
        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle = new ArrayList<>();
        PerfilDocumentalDetalle perfilDocumentalDetalle;

        String[] array = new String[1];

        array[0] = perfil;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_PERFIL_DOCUMENTAL_DETALLE", array);
        for (Object[] d : data) {

            perfilDocumentalDetalle = new PerfilDocumentalDetalle();

            perfilDocumentalDetalle.setID_PERFIL_DOCUMENTAL(d[0].toString());
            perfilDocumentalDetalle.setID_PERFIL_DOCUMENTAL_DETALLE(Integer.parseInt(d[1].toString()));
            perfilDocumentalDetalle.setTABLA(d[2].toString());
            perfilDocumentalDetalle.setCAMPO(d[3].toString());
            perfilDocumentalDetalle.setStrVista(d[4].toString());
            perfilDocumentalDetalle.setStrRequerido(d[5].toString());
            if (d[4].toString().equals("1")) {
                perfilDocumentalDetalle.setVISTA(true);
            } else {
                perfilDocumentalDetalle.setVISTA(false);
            }
            if (d[5].toString().equals("1")) {
                perfilDocumentalDetalle.setREQUERIDO(true);
            } else {
                perfilDocumentalDetalle.setREQUERIDO(false);
            }

            lstPerfilDocumentalDetalle.add(perfilDocumentalDetalle);

        }

        return lstPerfilDocumentalDetalle;
    }

    public List<Object[]> obtenerPerfiles() {
        String[] parametros = new String[1];
        parametros[0] = "LISTAR_PERFILES";
        List<Object[]> lstPerfiles = conector.execProcedure("BV.SP_LISTAR_PERFIL", parametros);
        return lstPerfiles;

    }

    public int editarListPerfildocumentaldetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle ,int idUsuario) {
        int n = 0;
        int cont=0;
        String[] parametros=null;
        for (PerfilDocumentalDetalle p : lstPerfilDocumentalDetalle) {
            parametros = new String[5];
            parametros[0] = String.valueOf(p.getID_PERFIL_DOCUMENTAL());
            parametros[1] = String.valueOf(p.getID_PERFIL_DOCUMENTAL_DETALLE());
            if (p.isVISTA()) {
                parametros[2] = "1";
            } else {
                parametros[2] = "0";
            }
            if (p.isREQUERIDO()) {
                parametros[3] = "1";
            } else {
                parametros[3] = "0";
            }
            parametros[4] = "EDITAR";
            ArrayList<Object[]> result = conector.execProcedure("[BV].[SP_MANTENIMIENTO_PERFIL_DOCUMENTAL_DETALLE]", parametros);
            if (result != null) {
            n = 1;
            logTablaDao.registrarLogTabla(new LogTabla("BV", "PERFIL_DOCUMENTAL_DETALLE", "", "2", String.valueOf(idUsuario)));
            cont=cont+n;

        }

        }
        
        
        

        return cont;
    }

}

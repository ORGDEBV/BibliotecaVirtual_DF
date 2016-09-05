/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.PerfilDocumentalDetalle;

/**
 *
 * @author virtual
 */
public interface PerfilDocumentalDetalleDao {

    List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalle(String perfil);

    List<Object[]> obtenerPerfiles();

    int editarListPerfildocumentaldetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle, int idUsuario);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Documental;

/**
 *
 * @author virtual
 */
public interface DocumentalDao {

    int validarTitulo(Documental documental);

    String generarIdDocumental(int Tipo);

    List<Object[]> llenaComboDocumentalRelacion(String idDocumental);

    List<Documental> listarDocumental(String perfil, int tipoUsuario, int idUsuario, int idBiblioteca);

    List<Documental> listarDocumentalGn(String perfil, int idBiblioteca);

    Documental listarXIdDocumental(String idDocumental);

    Documental listarDocumentalDetalle(String idDocumental);

    Documental listarDocumentalDetalleControl(String idDocumental);

    ArrayList<String> listDocumentalRelacionXidDocumental(String idDocumental);

    ArrayList<Documental> listDocumentalPublicacion(String perfilControl, String idBiblioteca,String idEstadoControl);

    String nombreArchivo(String id);

    String controlDocumental(String idDoc, String url, int idUsuario, String publicado, String perfil);
    String controlDocumentalObservacion(String idDoc, String estadoCont, String observacion, int idUsuario);

    boolean validarFichero(String servArch, String archivo);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import vb.entidad.MetadataTabla;
import vb.dao.metadataTablaDao;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class metadataTablaBean {

    List<MetadataTabla> lstmetadatatabla;
    metadataTablaDao metadataTablaDao = new metadataTablaDao();
    MetadataTabla metadatatabla;
    boolean activarayuda;
    int longitud;

   
    String classActivarAyuda;
    String descripcion = "";

    public String getClassActivarAyuda() {
        return classActivarAyuda;
    }

    public void setClassActivarAyuda(String classActivarAyuda) {
        this.classActivarAyuda = classActivarAyuda;
    }

    public metadataTablaDao getMetadataTablaDao() {
        return metadataTablaDao;
    }

    public void setMetadataTablaDao(metadataTablaDao metadataTablaDao) {
        this.metadataTablaDao = metadataTablaDao;
    }
 public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    public MetadataTabla getMetadatatabla() {
        return metadatatabla;
    }

    public void setMetadatatabla(MetadataTabla metadatatabla) {
        this.metadatatabla = metadatatabla;
    }

    public boolean isActivarayuda() {
        return activarayuda;
    }

    public void setActivarayuda(boolean activarayuda) {

        this.activarayuda = activarayuda;
    }

    public void cambiarEstadoActivar() {
        if (activarayuda) {

            classActivarAyuda = "btnAyuda-off";
            // FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El títutlo ingresado ya existe."));
            activarayuda = false;
        } else {
            classActivarAyuda = "btnAyuda-on";
           // FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "No hay coincidencias con el título ingresado, puede ser usado."));

            activarayuda = true;
        }

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public metadataTablaBean() {
        activarayuda = true;
        lstmetadatatabla = metadataTablaDao.listarMetadataTabla();
    }

    public List<MetadataTabla> getLstmetadatatabla() {

        return lstmetadatatabla;
    }

    public void setLstmetadatatabla(List<MetadataTabla> lstmetadatatabla) {
        this.lstmetadatatabla = lstmetadatatabla;
    }

    public String obtenerAyuda(String campo) {

        for (MetadataTabla met : lstmetadatatabla) {

            if (met.getCAMPO().equals(campo)) {
                metadatatabla = met;
                String s = metadatatabla.getDESCRIPCION();

                descripcion = cadenaSL(s);
            }

        }

        return descripcion;
    }
     public int obtenerLongitud(String campo) {

        for (MetadataTabla met : lstmetadatatabla) {

            if (met.getCAMPO().equals(campo)) {
                metadatatabla = met;
                longitud=metadatatabla.getLONGITUD();
                

                
            }

        }

        return longitud;
    }

    public String cadenaSL(String texto) {
        String cadenaSL = "";
        String cadenaRes = texto;
        int ltRes = texto.length();
        int longLinea = 40;
        if (texto.length() > longLinea) {
            while (longLinea <= ltRes) {
                cadenaSL = cadenaSL + cadenaRes.substring(0, longLinea) + "<br/>";
                cadenaRes = cadenaRes.substring(longLinea);
                ltRes = cadenaRes.length();
                //System.out.println("lres "+ltRes);
                if (longLinea > ltRes) {
                    cadenaSL = cadenaSL + cadenaRes;
                }
                //System.out.println("csl "+cadenaSL);
            }

        } else {

            cadenaSL = texto;
        }

        return cadenaSL;

    }

}

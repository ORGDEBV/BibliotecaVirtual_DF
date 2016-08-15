/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import vb.dao.bibliotecaDao;
import vb.entidad.Biblioteca;
@ManagedBean
@ViewScoped
public class geoLocBibliotecasBean implements Serializable {
bibliotecaDao bibliotecaDao=new bibliotecaDao();
private MapModel simpleModel;
String idBiblioteca=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
ArrayList<Biblioteca> lstMapBibliotecas = new ArrayList<>();
    public geoLocBibliotecasBean() {
        simpleModel = new DefaultMapModel();
        lstMapBibliotecas();
           }
    
//    @PostConstruct
//    public void init() {
//      //  simpleModel = new DefaultMapModel();
//          
//        //Shared coordinates
//        LatLng coord1 = new LatLng(36.879466, 30.667648);
//        LatLng coord2 = new LatLng(36.883707, 30.689216);
//        LatLng coord3 = new LatLng(36.879703, 30.706707);
//        LatLng coord4 = new LatLng(36.885233, 30.702323);
//          
//        //Basic marker
//        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
//        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
//        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
//        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
//    }

    public void lstMapBibliotecas(){
    lstMapBibliotecas=bibliotecaDao.lstMapeoBibliotecas(idBiblioteca);
    
    for(Biblioteca bib:lstMapBibliotecas){
        double lat=Double.parseDouble(bib.getLATITUD());
        double lng=Double.parseDouble(bib.getLONGITUD());
    LatLng coord = new LatLng(lat,lng);
    simpleModel.addOverlay(new Marker(coord, bib.getTITULO_MAPA()));
    }
    
    
    
    
    }
    
    
    
    
    
    
    public ArrayList<Biblioteca> getLstMapBibliotecas() {
        return lstMapBibliotecas;
    }

    public void setLstMapBibliotecas(ArrayList<Biblioteca> lstMapBibliotecas) {
        this.lstMapBibliotecas = lstMapBibliotecas;
    }
  

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }
    
}

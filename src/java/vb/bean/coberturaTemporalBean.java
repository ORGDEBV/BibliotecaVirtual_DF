/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import vb.dao.coberturaTemporalDao;
import vb.entidad.CoberturaTemporal;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class coberturaTemporalBean {
    
    private List<SelectItem> cboCoberturaTemporal;
    private CoberturaTemporal ct;
    private coberturaTemporalDao coDao;
    public coberturaTemporalBean() {
        ct = new CoberturaTemporal();
        coDao = new coberturaTemporalDao();
    }

    public List<SelectItem> getCboCoberturaTemporal() {
        List<Object[]> lista = coDao.llenaComboCoberturaTemporal();
        cboCoberturaTemporal = new ArrayList<SelectItem>();
        if(lista!=null){
            for (Object[] fila : lista) {
                cboCoberturaTemporal.add(new SelectItem(fila[0],fila[1].toString()));
            }
        }
        return cboCoberturaTemporal;
    }

    public void setCboCoberturaTemporal(List<SelectItem> cboCoberturaTemporal) {
        this.cboCoberturaTemporal = cboCoberturaTemporal;
    }    
}

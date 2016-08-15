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
import vb.dao.tipoOtroDao;
import vb.entidad.TipoOtro;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class tipoOtroBean {

    private TipoOtro TipoOtro;
    private tipoOtroDao tipoOtroDao;
    private List<SelectItem> cboTipoOtro;
    /**
     * Creates a new instance of tipoOtroBean
     */
    public tipoOtroBean() {
        TipoOtro = new TipoOtro();
        tipoOtroDao = new tipoOtroDao();
    }

    public TipoOtro getTipoOtro() {
        return TipoOtro;
    }

    public void setTipoOtro(TipoOtro TipoOtro) {
        this.TipoOtro = TipoOtro;
    }

    public List<SelectItem> getCboTipoOtro() {
        List<Object[]> lista = tipoOtroDao.llenaComboTipoOtro();
        cboTipoOtro = new ArrayList<SelectItem>();
        if(lista!=null){
            for (Object[] fila : lista) {
                cboTipoOtro.add(new SelectItem(fila[0],fila[1].toString()));
            }
        }
        return cboTipoOtro;
    }

    public void setCboTipoOtro(List<SelectItem> cboTipoOtro) {
        this.cboTipoOtro = cboTipoOtro;
    }
}

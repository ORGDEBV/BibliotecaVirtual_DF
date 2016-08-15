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
import vb.dao.audienciaDao;
import vb.dao.tipoDao;
import vb.entidad.Audiencia;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class audienciaBean {

    private Audiencia audiencia;
    private audienciaDao audDao;
    private List<SelectItem> cboAudiencia;
    
    public audienciaBean() {
        audiencia = new Audiencia();
        audDao = new audienciaDao();
    }

    public Audiencia getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(Audiencia audiencia) {
        this.audiencia = audiencia;
    }

    public List<SelectItem> getCboAudiencia() {
        List<Object[]> lista = audDao.llenaComboAudiencia();
        cboAudiencia = new ArrayList<SelectItem>();
        if(lista!=null){
            for (Object[] fila : lista) {
                cboAudiencia.add(new SelectItem(fila[0],fila[1].toString()));
            }
        }
        return cboAudiencia;
    }

    public void setCboAudiencia(List<SelectItem> cboAudiencia) {
        this.cboAudiencia = cboAudiencia;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.bean;

import bv.dao.TipoOtroDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.TIPO_OTRO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class tipoOtroBean {

    private final TipoOtroDao tipoOtroDao;
    private List<SelectItem> cboTipoOtro;
    /**
     * Creates a new instance of tipoOtroBean
     */
    public tipoOtroBean() {
        DaoFactory factory = DaoFactory.getInstance();
        tipoOtroDao = factory.getTipoOtroDao(TIPO_OTRO);
    }

    public List<SelectItem> getCboTipoOtro() {
        List<Object[]> lista = tipoOtroDao.llenaComboTipoOtro();
        cboTipoOtro = new ArrayList<>();
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

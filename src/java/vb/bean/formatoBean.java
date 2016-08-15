/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import vb.dao.formatoDao;
import vb.entidad.Formato;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class formatoBean {

   private formatoDao objFormatoDao =new formatoDao();
   private List<Formato> cboFormato;

    public formatoDao getObjFormatoDao() {
        return objFormatoDao;
    }

    public void setObjFormatoDao(formatoDao objFormatoDao) {
        this.objFormatoDao = objFormatoDao;
    }

    public List<Formato> getCboFormato() {
        
         List<Object[]> lista = objFormatoDao.llenaComboFormato();
        cboFormato = new ArrayList<Formato>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Formato formato = new Formato();
                formato.setID_FORMATO(fila[0].toString());
                formato.setTIPO(fila[1].toString());
                cboFormato.add(formato);
            }
        }
        return cboFormato;
    }

    public void setCboFormato(List<Formato> cboFormato) {
        this.cboFormato = cboFormato;
    }
    public formatoBean() {
    }
    
}

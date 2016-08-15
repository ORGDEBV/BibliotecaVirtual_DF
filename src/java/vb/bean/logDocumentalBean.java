/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import vb.dao.logTablaDao;

@ManagedBean
@javax.faces.bean.ViewScoped
public class logDocumentalBean {

    private List<Object[]> lstLogTabla;
    private List<Object[]> lstLogTablaFilter;
    public logDocumentalBean() {
         lstLogTabla=logTablaDao.listarLogDocumental();
    }

    public List<Object[]> getLstLogTabla() {
        return lstLogTabla;
    }

    public void setLstLogTabla(List<Object[]> lstLogTabla) {
        this.lstLogTabla = lstLogTabla;
    }

    public List<Object[]> getLstLogTablaFilter() {
        return lstLogTablaFilter;
    }

    public void setLstLogTablaFilter(List<Object[]> lstLogTablaFilter) {
        this.lstLogTablaFilter = lstLogTablaFilter;
    }
    
    
    
    
}

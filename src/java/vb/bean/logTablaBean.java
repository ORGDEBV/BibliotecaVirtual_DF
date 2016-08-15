package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import vb.dao.logTablaDao;


@ManagedBean
@javax.faces.bean.ViewScoped
public class logTablaBean {
    private List<Object[]> lstLogTabla;
    private List<Object[]> lstLogTablaFilter;

    public logTablaBean() {
         lstLogTabla=logTablaDao.listarLog();
        
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

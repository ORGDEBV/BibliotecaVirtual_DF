package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.model.SelectItem;
import vb.dao.tipoDao;
import vb.entidad.Tipo;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@ViewScoped
public class tipoBean {
    private Tipo tipo;
    private tipoDao tipoDao;
    private List<SelectItem> cboTipo;
    
    //***auxiliar
    String perfil;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public tipoBean() {
        tipo = new Tipo();
        tipoDao = new tipoDao();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<SelectItem> getCboTipo() {
        List<Object[]> lista = tipoDao.llenaComboTipoXperfil(perfil);
        cboTipo = new ArrayList<SelectItem>();
        if(lista!=null){
            for (Object[] fila : lista) {
                cboTipo.add(new SelectItem(fila[0],fila[1].toString()));
            }
        }
        return cboTipo;
    }

    public void setCboTipo(List<SelectItem> cboTipo) {
        this.cboTipo = cboTipo;
    }
    
}

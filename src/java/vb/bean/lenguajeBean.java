package vb.bean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import vb.dao.lenguajeDao;
import vb.entidad.Lenguaje;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class lenguajeBean {
    private Lenguaje lenguaje;
    private lenguajeDao lenguajeDao;
    private ArrayList<Lenguaje> cboLenguaje;
    private List<SelectItem> selectedLenguaje;
    public lenguajeBean() {
        lenguaje = new Lenguaje();
        lenguajeDao = new lenguajeDao();
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public ArrayList<Lenguaje> getCboLenguaje() {
        List<Object[]> lista = lenguajeDao.llenaComboLenguaje();
        cboLenguaje = new ArrayList<Lenguaje>();
        if(lista!=null){
            for (Object[] fila : lista) {
                Lenguaje lenguaje = new Lenguaje();
                lenguaje.setID_LENGUAJE(fila[0].toString());
                lenguaje.setLENGUAJE(fila[1].toString());
                cboLenguaje.add(lenguaje);
            }
        }
        return cboLenguaje;
    }

    public void setCboLenguaje(ArrayList<Lenguaje> cboLenguaje) {
        this.cboLenguaje = cboLenguaje;
    }

    public List<SelectItem> getSelectedLenguaje() {
        return selectedLenguaje;
    }

    public void setSelectedLenguaje(List<SelectItem> selectedLenguaje) {
        this.selectedLenguaje = selectedLenguaje;
    }
}

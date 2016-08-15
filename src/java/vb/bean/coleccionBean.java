package vb.bean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.dao.coleccionDao;
import vb.entidad.Coleccion;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ViewScoped
@ManagedBean
public class coleccionBean {
    private List<Coleccion> cboColeccion;
    private coleccionDao coleccionDao;
    private Coleccion coleccion;
    String idBiblioteca;
    public coleccionBean() {
        idBiblioteca=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        coleccion = new Coleccion();
        coleccionDao = new coleccionDao();
        rfrhCboColeccion();
    }

    public List<Coleccion> getCboColeccion() {        
        return cboColeccion;
    }
    
    public void rfrhCboColeccion() {
     
        List<Object[]> lista = coleccionDao.llenaComboColeccion(idBiblioteca);
        cboColeccion = new ArrayList<Coleccion>();
        if(lista!=null){
            for (Object[] fila : lista) {
                Coleccion coleccion = new Coleccion();
                coleccion.setID_COLECCION(Integer.parseInt(fila[0].toString()));
                coleccion.setDESCRIPCION(fila[2].toString());
                cboColeccion.add(coleccion);
            }
        }
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboColeccion");
        
    }

    public void setCboColeccion(List<Coleccion> cboColeccion) {
        this.cboColeccion = cboColeccion;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }
    
    public void creaColeccion(){
        int n = coleccionDao.crearEntidad(coleccion,idBiblioteca);
        if(n == 0){
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Ocurrió un error al ejecutar la sentencia."));
        }else{
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgColeccion').hide();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO,"Inserción correcta", "Se registro la colección correctamente."));            
        }
        limpiarColeccion();
    }
    
    public void limpiarColeccion(){
        coleccion = new Coleccion();
    }
    
    
}

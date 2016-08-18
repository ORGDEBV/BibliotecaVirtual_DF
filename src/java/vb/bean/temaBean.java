package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.dao.temaDao;
import vb.entidad.Tema;

@ManagedBean
@RequestScoped
public class temaBean {
    private Tema tema;
    private ArrayList<Tema> cboTema;
    private List<SelectItem> selectedTema;
    private temaDao temaDao;
    String idBiblioteca=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
    public temaBean() {
        temaDao = new temaDao();
        tema = new Tema();
    }
    
    public void creaTema(){
        tema.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
        int insert = temaDao.crearEntidad(tema);
        if(insert == 0){
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Ocurrió un error al ejecutar la sentencia."));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
        if(insert == 2){
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Ya se agregó este tema."));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
        else{
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgTema').hide();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO,"Inserción correcta", "Se registro el tema correctamente."));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
        limparTema();
    }

    public void rfrhCboEditorial(){
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboTema");
    }
    
    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public ArrayList<Tema> getCboTema() {
        List<Object[]> lista = temaDao.llenaComboTema(idBiblioteca);
        cboTema = new ArrayList<Tema>();
        if(lista!=null){
            for (Object[] fila : lista) {
                Tema tema = new Tema();
                tema.setID_TEMA(Integer.parseInt(fila[0].toString()));
                tema.setTEMA(fila[1].toString());
                cboTema.add(tema);
            }
        }
        return cboTema;
    }

    public void setCboTema(ArrayList<Tema> cboTema) {
        this.cboTema = cboTema;
    }

    public List<SelectItem> getSelectedTema() {
        return selectedTema;
    }

    public void setSelectedTema(List<SelectItem> selectedTema) {
        this.selectedTema = selectedTema;
    }
    
    private void limparTema(){
        tema = new Tema();
    }
}

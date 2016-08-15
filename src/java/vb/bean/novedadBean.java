package vb.bean;


import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import vb.dao.transaccionDao;
import vb.entidad.ImagenNovedad;
import vb.entidad.Novedad;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import vb.dao.novedadDao;


@ManagedBean
@ViewScoped
public class novedadBean {


    
    private final transaccionDao transaccion = new transaccionDao();
    private final novedadDao novDao = new novedadDao();
    private int imagenid = novDao.obetenerId();
    private List<ImagenNovedad> listaImagenes;
    private List<ImagenNovedad> listaImagenes2;
    private ImagenNovedad imagenHome2;
    private ImagenNovedad imagenHome;
    private Novedad novedad;
    private String rutaNovedad = "img/novedades/";
    private String rutaHome = "img/novedades-index/";
    private int it = 0;
    
    private StreamedContent imgs;

    public novedadBean() {
        
        if (it == 0) {
            listaImagenes2 = new ArrayList<ImagenNovedad>();
            listaImagenes = new ArrayList<ImagenNovedad>();
            novedad = new Novedad();
            imagenHome2 = new ImagenNovedad();
            imagenHome = new ImagenNovedad();
            it++;
        }
    }

    public List<ImagenNovedad> getListaImagenes2() {
        return listaImagenes2;
    }

    public void setListaImagenes2(List<ImagenNovedad> listaImagenes2) {
        this.listaImagenes2 = listaImagenes2;
    }
    
    public ImagenNovedad getImagenHome() {
        return imagenHome;
    }

    public void setImagenHome(ImagenNovedad imagenHome) {
        this.imagenHome = imagenHome;
    }

    public ImagenNovedad getImagenHome2() {
        return imagenHome2;
    }

    public void setImagenHome2(ImagenNovedad imagenHome2) {
        this.imagenHome2 = imagenHome2;

    }

    public Novedad getNovedad() {
        return novedad;
    }

    public void setNovedad(Novedad novedad) {
        this.novedad = novedad;

    } 
    
    public List<ImagenNovedad> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(List<ImagenNovedad> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public void agegarNovedad() throws SQLException {
        int idUsuario=(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        String msg = transaccion.insertTransaccionNovedades(novedad, listaImagenes, imagenid,idUsuario);
        if (msg.equals("1")) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agrego el contenido correctamente"));
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().update("frmNovedad");
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    //****Home    
    public void abrirDlg2() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("closable", false);
        options.put("headerElement", "customheader");
        RequestContext.getCurrentInstance().openDialog("novedadHome", options, null);
    }

    public void onReturnNovedadHome(SelectEvent event) {
        ImagenNovedad img = new ImagenNovedad();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data regresada", event.getObject().toString()));
        ArrayList<UploadedFile> listaIn = (ArrayList<UploadedFile>) event.getObject();
        for (UploadedFile list : listaIn) {
            img.setID_IMAGEN_NOVEDAD(imagenid);
            img.setID_NOVEDAD(imagenid);
            img.setURL_NOVEDAD(rutaHome + list.getFileName());
            img.setORDEN(1);
        }
        imagenHome = img;
        listaImagenes.add(img);
        RequestContext.getCurrentInstance().update("frmNovedad:txtImgHome");
    }

    /*Novedad*/
    public void abrirDlg3() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("closable", false);
        options.put("headerElement", "customheader");        
        RequestContext.getCurrentInstance().openDialog("novedadHome2", options, null);
    }

    public void onReturnNovedadHome2(SelectEvent event) {
        ImagenNovedad img = new ImagenNovedad();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data regresada", event.getObject().toString()));
        ArrayList<UploadedFile> listaIn = (ArrayList<UploadedFile>) event.getObject();
        for (UploadedFile list : listaIn) {
            img.setID_IMAGEN_NOVEDAD(imagenid);
            img.setID_NOVEDAD(imagenid);
            img.setURL_NOVEDAD(rutaNovedad + list.getFileName());
            img.setORDEN(2);
        }
        imagenHome2 = img;
        listaImagenes.add(img);
        RequestContext.getCurrentInstance().update("frmNovedad:txtImgHome2");
    }

    ////Novedad detalles
    public void abrirDlg() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("closable", false);
        options.put("headerElement", "customheader");        
        RequestContext.getCurrentInstance().openDialog("novedadDetalles", options, null);
    }
    
    
    public void onReturnFromdocumentalUpd(SelectEvent event) {
        ImagenNovedad img;
        int n = 3;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data regresada", event.getObject().toString()));
        ArrayList<UploadedFile> listaIn = (ArrayList<UploadedFile>) event.getObject();
        for (UploadedFile list : listaIn) {
            img = new ImagenNovedad();
            img.setID_IMAGEN_NOVEDAD(imagenid);
            img.setID_NOVEDAD(imagenid);
            img.setURL_NOVEDAD(rutaNovedad + list.getFileName());
            img.setORDEN(n);
            listaImagenes2.add(img);
            listaImagenes.add(img);
            n++;
        }
        RequestContext.getCurrentInstance().update("frmNovedad:listContenidos");
    }
    
    public void limpiar(){
        novedad = new Novedad();
        listaImagenes2 = new ArrayList<ImagenNovedad>();
        listaImagenes = new ArrayList<ImagenNovedad>();
        imagenHome2 = new ImagenNovedad();
        imagenHome = new ImagenNovedad();
    }
    
    //listar novedades
    private List<Novedad> lstNovedad = novDao.obtenerEntidades();
    private Novedad nov = new Novedad();

    public Novedad getNov() {
        return nov;
    }

    public void setNov(Novedad nov) {
        this.nov = nov;
    }

    public List<Novedad> getLstNovedad() {
        return lstNovedad;
    }

    public void setLstNovedad(List<Novedad> lstNovedad) {
        this.lstNovedad = lstNovedad;
    }
    
    
}

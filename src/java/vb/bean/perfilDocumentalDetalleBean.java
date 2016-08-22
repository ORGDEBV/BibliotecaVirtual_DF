package vb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.CellEditEvent;
import org.primefaces.context.RequestContext;
import vb.dao.PublicacionDao;
import vb.dao.documentalDao;
import vb.dao.perfilDocumentalDetalleDao;
import vb.dto.PublicacionDto;
import vb.entidad.Documental;
import vb.entidad.PerfilDocumentalDetalle;
import vb.entidad.Publicacion;

@ManagedBean
@ViewScoped
public class perfilDocumentalDetalleBean {

    List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle;
    perfilDocumentalDetalleDao objPerfilDocumentalDetalleDao = new perfilDocumentalDetalleDao();
    String perfil;
    String perfilSelect;
    String perfilControl;
    private Documental documentalPnlControl = new Documental();
    private List<SelectItem> cboPerfiles;
    private List<SelectItem> cboVista;
    private List<SelectItem> cboRequerido;

    public List<SelectItem> getCboRequerido() {
        cboRequerido.add(new SelectItem(0, "Requerido"));
        cboRequerido.add(new SelectItem(1, "No Requerido"));
        return cboRequerido;
    }

    public void setCboRequerido(List<SelectItem> cboRequerido) {
        this.cboRequerido = cboRequerido;
    }

    public List<SelectItem> getCboVista() {
        cboVista.add(new SelectItem(0, "visible"));
        cboVista.add(new SelectItem(1, "No Visible"));
        return cboVista;
    }

    public void setCboVista(List<SelectItem> cboVista) {
        this.cboVista = cboVista;
    }

    public String getPerfilSelect() {
        return perfilSelect;
    }

    public void setPerfilSelect(String perfilSelect) {
        this.perfilSelect = perfilSelect;
    }

    public Documental getDocumentalPnlControl() {
        return documentalPnlControl;
    }

    public void setDocumentalPnlControl(Documental documentalPnlControl) {
        this.documentalPnlControl = documentalPnlControl;
    }

    public String getPerfilControl() {
        return perfilControl;
    }

    public void setPerfilControl(String perfilControl) {
        this.perfilControl = perfilControl;
    }

    public List<SelectItem> getCboPerfiles() {
        List<Object[]> lista = objPerfilDocumentalDetalleDao.obtenerPerfiles();
        cboPerfiles = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPerfiles.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }

        return cboPerfiles;
    }

    public void setCboPerfiles(List<SelectItem> cboPerfiles) {
        this.cboPerfiles = cboPerfiles;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<PerfilDocumentalDetalle> getLstPerfilDocumentalDetalle() {
        //lstPerfilDocumentalDetalle=objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle("1");
        return lstPerfilDocumentalDetalle;
    }

    public void setLstPerfilDocumentalDetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle) {
        this.lstPerfilDocumentalDetalle = lstPerfilDocumentalDetalle;
    }

    public perfilDocumentalDetalleDao getObjPerfilDocumentalDetalleDao() {
        return objPerfilDocumentalDetalleDao;
    }

    public void setObjPerfilDocumentalDetalleDao(perfilDocumentalDetalleDao objPerfilDocumentalDetalleDao) {
        this.objPerfilDocumentalDetalleDao = objPerfilDocumentalDetalleDao;
    }

    public boolean isVisible(String campo) {
        boolean visible = false;
        for (PerfilDocumentalDetalle pd : lstPerfilDocumentalDetalle) {
            if (pd.getCAMPO().equals(campo)) {
                visible = pd.isVISTA();
            }

        }

        return visible;
    }

    public void listarDetallePerdil() {

        lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle(perfil);

    }

    public void listarDetallePerdil(String perfil) {

        lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle(perfil);

    }

    public void listarDetallePerdiles() {

        lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle(perfilSelect);

    }

    public void editarPerfilDocumentalDetalle() {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        int upd = objPerfilDocumentalDetalleDao.editarListPerfildocumentaldetalle(lstPerfilDocumentalDetalle, idUsuario);

    }
//    private void msjCorrecto(String growl, String m) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
//    }
//        private void msjError(String growl, String m) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
//    }

    public String actualizarTabla() {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        int n = objPerfilDocumentalDetalleDao.editarListPerfildocumentaldetalle(lstPerfilDocumentalDetalle, idUsuario);

        if (n > 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "ACTUALIZADO EXITOSAMENTE"));
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "ERROR DE ACTUALIZACIO"));
            RequestContext.getCurrentInstance().update("gMensaje");
        }

        return "perfilDocDetalle";

    }

    public void onCellEdit(CellEditEvent event) {

        //PerfilDocumentalDetalle pdd=(PerfilDocumentalDetalle)(event.getComponent()).getRowData();
        boolean estadoantiguo = (boolean) event.getOldValue();
        Object oldValue = event.getOldValue().toString();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
        }
    }

    //ludwig
    private String idDocumentalControl = "";
    private documentalDao ddao = new documentalDao();
    private PublicacionDao pubDao = new PublicacionDao();
    private ArrayList<Documental> listaDoc = new ArrayList<Documental>();
    private List<PublicacionDto> ldocumentalpublicado = new ArrayList<>();
    private Publicacion pub = new Publicacion();
    private List<Documental> filterDocumental;
    private List<PublicacionDto> ldocumentalpublicadofiltrado = new ArrayList<>();

    public List<Documental> getFilterDocumental() {
        return filterDocumental;
    }

    public void setFilterDocumental(List<Documental> filterDocumental) {
        this.filterDocumental = filterDocumental;
    }

    public Publicacion getPub() {
        return pub;
    }

    public void setPub(Publicacion pub) {
        this.pub = pub;
    }

    public ArrayList<Documental> getListaDoc() {
        return listaDoc;
    }

    public void setListaDoc(ArrayList<Documental> listaDoc) {
        this.listaDoc = listaDoc;
    }

    public String getIdDocumentalControl() {
        return idDocumentalControl;
    }

    public void setIdDocumentalControl(String idDocumentalControl) {
        this.idDocumentalControl = idDocumentalControl;
    }

    public void listarTablaxPerfil() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        listaDoc = ddao.listDocumentalPublicacion(perfilControl, idBiblioteca);
        RequestContext.getCurrentInstance().update("frmControl:tblControlDocumental");
    }

    private List<String> publicar;
    public boolean mostrarTipoArchivo = true;
    public String tipoArchivo = "";
    public String archivo = "";
    public String archivofinal = "";

    public List<String> getPublicar() {
        return publicar;
    }

    public void setPublicar(List<String> publicar) {
        this.publicar = publicar;
    }

    public boolean isMostrarTipoArchivo() {
        return mostrarTipoArchivo;
    }

    public void setMostrarTipoArchivo(boolean mostrarTipoArchivo) {
        this.mostrarTipoArchivo = mostrarTipoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getArchivo() {

        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivofinal() {
        return archivofinal;
    }

    public void setArchivofinal(String archivofinal) {
        this.archivofinal = archivofinal;
    }

    public void mostrarTipo(String id) {
        if (perfilControl.equals("6")) {
            archivo = ddao.nombreArchivo(id);
            mostrarTipoArchivo = false;
            RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
        } else {
            mostrarTipoArchivo = true;
            archivo = ddao.nombreArchivo(id);
            RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgControl').show()");
    }

    public void handleKeyEvent() {

        archivo = archivo.toLowerCase();
    }
    public String valor0;

    public void mostrarMsgcheck() {
        //List<String> listaOut = new ArrayList<String>();
        String msg = "";
        int l = publicar.size();
        String valorI = "";
        switch (l) {
            case 1:
                valorI = publicar.get(l - 1);
                //listaOut = publicar;
                if (valorI.equals("1")) {
                    msg = "Se agregará este documental al catálogo.";
                } else {
                    msg = "No agregará este documental al catálogo.";
                }
                valor0 = valorI;
                break;
            case 2:
                if (publicar.contains(valor0)) {
                    int position = publicar.indexOf(valor0);
                    publicar.remove(position);
                }
                int s = publicar.size();
                String valorII = publicar.get(s - 1);
                if (valorII.equals("1")) {
                    msg = "Se agregará este documental al catálogo.";
                } else {
                    msg = "No agregará este documental al catálogo.";
                }
                valor0 = valorII;
                break;
            default:

                break;
        }
        FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(msg));
        RequestContext.getCurrentInstance().update("gMensaje");
        RequestContext.getCurrentInstance().update("frmDlgControl:chkPublicar");

    }

    public void cambiarLabel() {
        String concat = "";
        String r = "resources/";
        switch (tipoArchivo) {
            case "FlippingBook":
                concat = r + tipoArchivo.toLowerCase() + "/" + archivo + "/index.html";
                break;
            case "PDF":
                concat = r + tipoArchivo.toLowerCase() + "/" + archivo + ".pdf";
                break;
            case "Imagen":
                concat = r + tipoArchivo.toLowerCase() + "/" + archivo + ".jpg";
                break;
        }
        archivofinal = concat;
        RequestContext.getCurrentInstance().update("frmDlgControl:txtMuestra");
    }

    public void limpiar() {
        archivo = "";
        archivofinal = "";
        tipoArchivo = "";
        publicar = new ArrayList<>();

        RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
    }

    public void registrarControlado() {

        if (perfilControl.equals("6")) {

        } else {

            String idDoc = documentalPnlControl.getID_DOCUMENTAL();
            String url = archivofinal;
            int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
            String publicado;
            if (publicar.get(0).equals("1")) {
                publicado = "SI";
            } else {
                publicado = "NO";
            }
            String mensage = ddao.controlDocumental(idDoc, url, idUsuario, publicado);
            limpiar();
            publicar = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(mensage));
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgControl').hide()");
            listarTablaxPerfil();

        }

        //RequestContext.getCurrentInstance().update("frmDlgControl");  
    }

    public void listarDocumentalPublicado() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        ldocumentalpublicado = pubDao.listPublicacion(perfilControl, idBiblioteca);
        RequestContext.getCurrentInstance().update("frmPublicacion:tblPublicacion");
    }

    public List<PublicacionDto> getLdocumentalpublicado() {
        return ldocumentalpublicado;
    }

    public void setLdocumentalpublicado(List<PublicacionDto> ldocumentalpublicado) {
        this.ldocumentalpublicado = ldocumentalpublicado;
    }

    public List<PublicacionDto> getLdocumentalpublicadofiltrado() {
        return ldocumentalpublicadofiltrado;
    }

    public void setLdocumentalpublicadofiltrado(List<PublicacionDto> ldocumentalpublicadofiltrado) {
        this.ldocumentalpublicadofiltrado = ldocumentalpublicadofiltrado;
    }

}

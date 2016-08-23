package vb.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
    private String urlOld = "";
    private String rutaServidorArchivos;
    private boolean linkProbado = false;
    private boolean mostrarLink = false;
    private String pagDurac="PAGINAS";

    public List<SelectItem> getCboRequerido() {
        cboRequerido.add(new SelectItem(0, "Requerido"));
        cboRequerido.add(new SelectItem(1, "No Requerido"));
        return cboRequerido;
    }

    public boolean isMostrarLink() {
        return mostrarLink;
    }

    public void setMostrarLink(boolean mostrarLink) {
        this.mostrarLink = mostrarLink;
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

    public String getPagDurac() {
        if(perfilControl!=null){
         if (perfilControl.equals("6")) {
            pagDurac = "DURACION";
        } else {
            pagDurac = "PAGINAS";

        }
        
        }
       
        return pagDurac;
    }

    public void setPagDurac(String pagDurac) {
        this.pagDurac = pagDurac;
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

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

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
    private ArrayList<PublicacionDto> ldocumentalpublicado = new ArrayList<PublicacionDto>();
    private Publicacion pub = new Publicacion();
    private List<Documental> filterDocumental;
    private ArrayList<PublicacionDto> ldocumentalpublicadofiltrado = new ArrayList<PublicacionDto>();

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
        String idBiblioteca = "2";
        String r = "recursos";
        switch (tipoArchivo) {
            case "FlippingBook":
                concat = r + "/" + idBiblioteca + "/" + tipoArchivo.toLowerCase() + "/" + archivo.trim() + "/index.html";

                break;
            case "PDF":
                concat = r + "/" + idBiblioteca + "/" + tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".pdf";
                break;
            case "Imagen":
                concat = r + "/" + idBiblioteca + "/" + tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".jpg";
                break;
        }
        mostrarLink = true;
        // urlOld = documentalPnlControl.getURL();
        archivofinal = concat;
        //documentalPnlControl.setURL(concat);
        RequestContext.getCurrentInstance().update("frmDlgControl:txtMuestra");
        RequestContext.getCurrentInstance().update("frmDlgControl:grdControl:link");
    }

    public void limpiar() {
        archivo = "";
        archivofinal = "";
        tipoArchivo = "";
        publicar = new ArrayList<>();

//        if (perfilControl.equals("6")) {
//            if (urlOld.length() > 0) {
//                documentalPnlControl.setURL(urlOld);
//            } else {
//                documentalPnlControl.setURL("");
//            }
//
//        }
        tipoArchivo = "";
        archivofinal = "";
        publicar = new ArrayList<>();
        linkProbado = false;
        mostrarLink = false;

        RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
    }

    public void registrarControlado() {
        String idDoc = documentalPnlControl.getID_DOCUMENTAL();
        //archivofinal = documentalPnlControl.getURL();
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        String publicado;
        if (publicar.size() > 0) {
            publicado = publicar.get(0).toString();
        } else {
            publicado = "0";
        }
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");

        //****validaciones
        ArrayList<String> lstErrores = new ArrayList<>();

        switch (perfilControl) {
            case "6":

                break;
            default:
                if (archivofinal.trim().length() == 0) {
                    lstErrores.add("Campo Ruta Final esta Vacio");
                }

                boolean existe = ddao.validarFichero(rutaServidorArchivos, archivofinal);
                if (!existe) {
                    lstErrores.add("El Fichero no existe en el Servidor de Archivos");
                }
                if (!linkProbado) {
                    lstErrores.add("Debe validar el Link antes de Aceptar");
                }

                break;

        }

        //---------------
        if (lstErrores.size() == 0) {
            String mensage = ddao.controlDocumental(idDoc, archivofinal, idUsuario, publicado, perfilControl);
            limpiar();
            publicar = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(mensage));
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgControl').hide()");
            listarTablaxPerfil();

        } else {
            String mensaje = "No se pudo insertar el documento.\nPor los motivos:<br/>";
            for (int i = 0; i < lstErrores.size(); i++) {
                String motivo = "-" + lstErrores.get(i) + "<br/>";
                mensaje = mensaje + motivo;
            }
            msjError("gMensaje", mensaje);
            RequestContext.getCurrentInstance().update("gMensaje");

        }

    }

    public void listarDocumentalPublicado() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        ldocumentalpublicado = pubDao.listPublicacion(perfilControl, idBiblioteca);

        RequestContext.getCurrentInstance().update("frmPublicacion:tblPublicacion");

    }

    public ArrayList<PublicacionDto> getLdocumentalpublicado() {
        ldocumentalpublicado = pubDao.listPublicacion("1", "2");
        return ldocumentalpublicado;
    }

    public void setLdocumentalpublicado(ArrayList<PublicacionDto> ldocumentalpublicado) {
        this.ldocumentalpublicado = ldocumentalpublicado;
    }

    public ArrayList<PublicacionDto> getLdocumentalpublicadofiltrado() {
        return ldocumentalpublicadofiltrado;
    }

    public void setLdocumentalpublicadofiltrado(ArrayList<PublicacionDto> ldocumentalpublicadofiltrado) {
        this.ldocumentalpublicadofiltrado = ldocumentalpublicadofiltrado;
    }

    public void redirectUrl() throws IOException {
        if (archivofinal.trim().length() > 0) {
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");
            String url = "http://localhost:8080/draco/" + archivofinal;

            boolean existe = ddao.validarFichero(rutaServidorArchivos, archivofinal);
            if (!existe) {

                String mensaje = "El Fichero no existe en el Servidor de Archivos";
                msjError("gMensaje", mensaje);
                RequestContext.getCurrentInstance().update("gMensaje");
            } else {
                RequestContext.getCurrentInstance().execute(" $('.cambiarColorControlLink').css({'color':'blue !important'}); ");
                RequestContext.getCurrentInstance().update("frmDlgControl:grdControl:link");
                RequestContext.getCurrentInstance().execute("window.open('" + url + "','_blank');");
                linkProbado = true;
            }

        }
    }

}

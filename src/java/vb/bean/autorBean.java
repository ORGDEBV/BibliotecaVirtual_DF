package vb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.dao.autorDao;
import vb.dao.paisDao;
import vb.entidad.Autor;

@ManagedBean(name = "autor")
@ViewScoped
public class autorBean implements Serializable {

    private final autorDao daoa = new autorDao();
    private final paisDao daop = new paisDao();
    private List<Autor> ListAut;
    private Autor autAux = new Autor();
    private Autor autAdd = new Autor();
    private List<SelectItem> cboPais;
    private List<Autor> filterListAutor;
    private List<Autor> cboAutores;
    String idBiblioteca=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
    int idUsuario=(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
    public autorBean() {
        ListAut = daoa.obtenerEntidades(idBiblioteca);
        rfrhCboAutor();
    }

    //Listar autores
    public List<Autor> getListAut() {
        return ListAut;
    }

    //combo de autores
    public List<Autor> getCboAutores() {
        return cboAutores;
    }

    public void rfrhCboAutor() {
        List<Object[]> lista = daoa.cboAutores(idBiblioteca);
        cboAutores = new ArrayList<Autor>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Autor autor = new Autor();
                autor.setID_AUTOR(Integer.parseInt(fila[0].toString()));
                autor.setNOMBRE(fila[2] + " " + fila[3] + ", " + fila[1]);
                cboAutores.add(autor);
            }
        }
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboAutor");
        
    }

    public void setCboAutores(List<Autor> cboAutores) {
        this.cboAutores = cboAutores;
    }

    //filtrado
    public List<Autor> getFilterListAutor() {
        return filterListAutor;
    }

    public void setFilterListAutor(List<Autor> filterListAutor) {
        this.filterListAutor = filterListAutor;
    }

    //cargar actualizar
    public Autor getAutAux() {
        return autAux;
    }

    public void setAutAux(Autor autAux) {
        this.autAux = autAux;
    }
    //carga combo pais

    public List<SelectItem> getCboPais() {
        List<Object[]> lista = daop.llenaComboTipoInstitucion();
        cboPais = new ArrayList<SelectItem>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPais.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboPais;
    }

    //insertar autor    
    public Autor getAutAdd() {
        return autAdd;
    }

    public void setAutAdd(Autor autAdd) {
        this.autAdd = autAdd;
    }

    public void crearAutor() {
        String nombre = fragmentarNombre(autAdd.getNOMBRE());
        String pat = letraCapital(autAdd.getAPELLIDO_PATERNO());
        String mat = letraCapital(autAdd.getAPELLIDO_MATERNO());
        autAdd.setNOMBRE(nombre);
        autAdd.setAPELLIDO_PATERNO(pat);
        autAdd.setAPELLIDO_MATERNO(mat);
        autAdd.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
        int flag1 = daoa.buscarEntidad(autAdd);
        if (flag1 == 0) {
            int flag2 = daoa.crearEntidad(autAdd,idUsuario);
            if (flag2 == 0) {
                msjError("No se ha podido registrar.");
            } else {
                msjCorrecto("Su registro se ha agregado con éxito.");
            }
        } else {
            msjError("El registro que esta tratando de ingresar ya existe.");
        }
        limpiarEntidadAutor(autAdd);

    }

    public void crearAutorDocumental() {
        String nombre = fragmentarNombre(autAdd.getNOMBRE());
        String pat = letraCapital(autAdd.getAPELLIDO_PATERNO());
        String mat = letraCapital(autAdd.getAPELLIDO_MATERNO());
        autAdd.setNOMBRE(nombre);
        autAdd.setAPELLIDO_PATERNO(pat);
        autAdd.setAPELLIDO_MATERNO(mat);
        autAdd.setID_BIBLIOTECA_REGISTRO(idBiblioteca);

        int flag1 = daoa.buscarEntidad(autAdd);
        if (flag1 == 0) {
            int flag2 = daoa.crearEntidad(autAdd,idUsuario);
            if (flag2 == 0) {
                msjError("No se ha podido registrar.");
            } else {
                msjCorrecto("Su registro se ha agregado con éxito.");
            }
        } else {
            msjError("El registro que esta tratando de ingresar ya existe.");
        }
        limpiarEntidadAutor(autAdd);
        rfrhCboAutor();

    }

    private String fragmentarNombre(String s) {
        String delimitadores = " ";
        String nombre = s;
        String[] NombreSeparado = nombre.split(delimitadores);
        String nombrep = "";
        String nombres = "";
        for (int i = 0; i < NombreSeparado.length; i++) {
            nombrep = "";
            int p = NombreSeparado[i].length();
            for (int j = 0; j < p; j++) {
                String word = "";
                if (j == 0) {
                    String letrainicio = (NombreSeparado[i].charAt(j) + "").toUpperCase();
                    word += letrainicio;
                } else {
                    String letrarestante = (NombreSeparado[i].charAt(j) + "").toLowerCase();
                    word += letrarestante;
                }
                nombrep += word + "";
            }
            nombres = nombres + nombrep + " ";
        }
        nombres = nombres.substring(0, nombres.length() - 1);
        return nombres;
    }

    private String letraCapital(String s) {
        String[] palabra = new String[s.length()];
        String letra = s;
        String letras = "";
        for (int i = 0; i < letra.length(); i++) {
            String word = "";
            if (i == 0) {
                String letrainicio = (letra.charAt(i) + "").toUpperCase();
                palabra[i] = letrainicio;
                word += palabra[i];
            } else {
                String letrarestante = (letra.charAt(i) + "").toLowerCase();
                palabra[i] = letrarestante;
                word += palabra[i];
            }
            letras += word;
        }
        return letras;
    }

    //Borrar autor
    public void borrarAutor(Autor aut) {
        int flag = daoa.eliminarEntidad(aut.getID_AUTOR(),idUsuario);
        if (flag == 0) {
            msjError("gMensaje", "Este autor se encuentra asignado a otros documentos");
        } else {
            ListAut = daoa.obtenerEntidades(idBiblioteca);
            msjCorrecto("gMensaje", "Se borro correctamente");
        }
    }

    //Modificar autor
    public void actualizarAutor() {
        
        int flag = daoa.actualizarEntidad(autAux,idUsuario);
        if (flag == 0) {
            msjError("gMensaje", "No se pudo actualizar correctamante");
        } else {
            msjCorrecto("gMensaje", "Se modifico");
        }
    }

    //accesorios
    private void msjError(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjCorrecto(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void msjCorrecto(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void limpiarEntidadAutor(Autor t) {
        t.setNOMBRE("");
        t.setAPELLIDO_PATERNO("");
        t.setAPELLIDO_MATERNO("");
        t.setID_PAIS("");
    }

}

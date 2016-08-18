package vb.bean;

import java.io.File;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;


@ManagedBean
@ViewScoped
public class PruebaListadoArchivos {

    private String rutaServidorArchivos;
    private ArrayList<String> listaArchivosCarpeta = new ArrayList<>();

    public ArrayList<String> getListaArchivosCarpeta() {

        return listaArchivosCarpeta;
    }

    public void setListaArchivosCarpeta(ArrayList<String> listaArchivosCarpeta) {
        this.listaArchivosCarpeta = listaArchivosCarpeta;
    }

    public PruebaListadoArchivos() {
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");
    }

    public void mostrarLista() {
        
        String nombreArchivo = "ave-4.jpg";

        String imagen = "recursos\\2\\imagen";
        String flipping = "recursos\\2\\flippingbook";
        String pdf = "recursos\\2\\pdf";

        File dir = new File(rutaServidorArchivos + imagen);

        String[] ficheros = dir.list();

        if (ficheros == null) {
            System.out.println("No hay ficheros en el directorio especificado");
        } else {
            for (int x = 0; x < ficheros.length; x++) {
                listaArchivosCarpeta.add(ficheros[x]);
                
            }
        }
        RequestContext.getCurrentInstance().update("frmListadoArchivos:tablaArchivos");
    }
}

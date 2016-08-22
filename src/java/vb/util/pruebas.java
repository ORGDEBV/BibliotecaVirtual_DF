/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.util;

import java.io.File;

/**
 *
 * @author virtual
 */
public class pruebas {

    public static void main(String[] args) {

        String servidorArchivos = "C:/glassfish-4.1.1/glassfish4/glassfish/domains/domain1/docroot/draco/";
        String cacrpetaXtipo="recursos/2/flippingbook/";
        String sfichero="recursos/2/flippingbook/1000016674/index.html";
        
        
        
    String msgn= validarFichero(servidorArchivos,sfichero);
        System.out.println("msg :: "+msgn);
        
//        String url=servidorArchivos+cacrpetaXtipo+sfichero;
//        
//        File fichero = new File(url);
//
//        if (fichero.exists()) {
//            System.out.println("EXITO,El fichero || " + sfichero + " || existe");
//        } else {
//            System.out.println("PUTO CABRON, SE BAJARON EL FICHERO");
//        }
    }
    
    public static String validarFichero(String servArch,String idBiblioteca,String tipoArchivo,String archivo){
    String msg="";
        String cacrpetaXtipo="";
    
     switch (tipoArchivo) {
            case "FlippingBook":
                cacrpetaXtipo = "recursos/"+idBiblioteca+"/flippingbook/"+archivo+ "/index.html";
                break;
            case "PDF":
                cacrpetaXtipo = "recursos/"+idBiblioteca+"/pdf/"+archivo+".pdf";
                break;
            case "Imagen":
                cacrpetaXtipo = "recursos/"+idBiblioteca+"/imagen/"+archivo+".jpg";
                break;
        }
     String url=servArch+cacrpetaXtipo;
        File fichero = new File(url);

        if (fichero.exists()) {
            msg="EXITO,El fichero || " + archivo + " || existe";
            //System.out.println("EXITO,El fichero || " + archivo + " || existe");
        } else {
            msg="PUTO CABRON, SE BAJARON EL FICHERO";
            //System.out.println("PUTO CABRON, SE BAJARON EL FICHERO");
        }
     
    
    return msg;
    
    }
    
     public static String validarFichero(String servArch,String archivo){
    String msg="";
       
    
     
     String url=servArch+archivo;
        File fichero = new File(url);

        if (fichero.exists()) {
            msg="EXITO,El fichero || " + archivo + " || existe";
            //System.out.println("EXITO,El fichero || " + archivo + " || existe");
        } else {
            msg="PUTO CABRON, SE BAJARON EL FICHERO";
            //System.out.println("PUTO CABRON, SE BAJARON EL FICHERO");
        }
     
    
    return msg;
    
    }
    
    
    
}

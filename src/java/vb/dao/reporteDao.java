package vb.dao;
import vb.entidad.reporteUsuarioDocumental;
import java.util.ArrayList;
import java.util.List;
import vb.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class reporteDao{
    sql conector = new sql();
    
    public List<Object[]> listUsuarioDocumental(String date,String idBiblioteca){
        String[] parametros = new String[2];
        parametros[0] = date;
        parametros[1] = idBiblioteca;
        List<Object[]> usuarioDocumental = conector.execProcedure("BV.SP_REPORTE_USUARIO_DOCUMENTAL", parametros);
        return usuarioDocumental;
    }
    public List<reporteUsuarioDocumental> obtenerEntidades(String date,String idBiblioteca) {
        List<reporteUsuarioDocumental> listU = new ArrayList<reporteUsuarioDocumental>();
        reporteUsuarioDocumental usuarioD = null;
        String[] parametros = new String[2];
        parametros[0] = date;
        parametros[1] = idBiblioteca;
        List<Object[]> lista = conector.execProcedure("BV.SP_REPORTE_USUARIO_DOCUMENTAL", parametros);
        for (Object[] list : lista) {
            usuarioD = new reporteUsuarioDocumental();
            usuarioD.setUSUARIO(list[0].toString());
            usuarioD.setCONTEO(Integer.parseInt(list[1].toString()));
            listU.add(usuarioD);
        }
        return listU;
    }
    
    public List<Object[]> listPeriodoTrabajadores(int Ano, int mesIni, int mesFin,int idUsuario, int TipoUsuario,String idBiblioteca){
        String[] parametros = new String[6];
        parametros[0] = Ano + "";
        parametros[1] = mesIni + "";
        parametros[2] = mesFin + "";
        if(TipoUsuario == 3){
           parametros[3] = idUsuario + "";
           parametros[4] = "LIST_UNICO";
        }else{
           parametros[3] = "";
           parametros[4] = "LIST_TOTAL";
        }
        parametros[5] = idBiblioteca;
        List<Object[]> list = conector.execProcedure("BV.SP_REPORTE_RANGO_TRABAJADORES", parametros);
        return list;
    } 

    
    //LUDWIG
    
    public ArrayList<Object[]> listaConsolidadoMensual(int Anio,int idBiblioteca){
        ArrayList<Object[]> lista = new ArrayList<>();
        String[] parametros = new String[3];
        parametros[0]=String.valueOf(Anio);
        parametros[1]=String.valueOf(idBiblioteca);
        parametros[2]= "LIST_DATOS";
        lista = conector.execProcedure("[BV].[SP_REPORTE_LINEAL]", parametros);
        return lista;
    }
    
    
    public ArrayList<Object[]> listaUsusariosMensual(int Anio,int idBiblioteca){
       String[] parametros = new String[3];
        parametros[0]=String.valueOf(Anio);
        parametros[1]=String.valueOf(idBiblioteca);
        parametros[2]= "LIST_USUARIOS";       
        ArrayList<Object[]> lstUsuario = conector.execProcedure("[BV].[SP_REPORTE_LINEAL]", parametros);
        return lstUsuario;
    }
    
     public  int conteoMaxMensual(int Anio,int idBiblioteca){
         Integer i = 0;
       String[] parametros = new String[3];
        parametros[0]=String.valueOf(Anio);
        parametros[1]=String.valueOf(idBiblioteca);
        parametros[2]= "MAX_CONTEO";       
        ArrayList<Object[]> lstUsuario = conector.execProcedure("[BV].[SP_REPORTE_LINEAL]", parametros);
         for (Object[] data : lstUsuario) {
             i = Integer.parseInt(data[0].toString());
         }
        return i;
    }

    
    
    //
    
    
    public List<reporteUsuarioDocumental> obtenerPorPeriodo(int ano, int fecini, int fecfin,int idUsuario, int TipoUsuario,String idBiblioteca) {
        List<reporteUsuarioDocumental> listU = new ArrayList<reporteUsuarioDocumental>();
        reporteUsuarioDocumental usuarioD = null;
        String[] parametros = new String[6];
        parametros[0] = ano + "";
        parametros[1] = fecini + "";
        parametros[2] = fecfin + "";
        if(TipoUsuario == 3){
           parametros[3] = idUsuario + "";
           parametros[4] = "LIST_UNICO";
        }else{
           parametros[3] = "";
           parametros[4] = "LIST_TOTAL";
        }
        parametros[5] = idBiblioteca;
        List<Object[]> lista = conector.execProcedure("BV.SP_REPORTE_RANGO_TRABAJADORES", parametros);
        for (Object[] list : lista) {
            usuarioD = new reporteUsuarioDocumental();
            usuarioD.setUSUARIO(list[0].toString());
            usuarioD.setCONTEO(Integer.parseInt(list[1].toString()));
            listU.add(usuarioD);
        }
        return listU;
    }
    
    
    //----jinmi
    public List<Object[]> listaInserUpdTotales(int Ano, int mesIni, int mesFin,int idUsuario, int TipoUsuario,String idBiblioteca){
        List<Object[]> list=new ArrayList<>();
        String[] parametros = new String[6];
        parametros[0] = Ano + "";
        parametros[1] = mesIni + "";
        parametros[2] = mesFin + "";
        if(TipoUsuario == 3){
           parametros[3] = idUsuario + "";
           parametros[4] = "LIST_INSERT_UPD_XUSUARIO";
        }else{
           parametros[3] = "";
           parametros[4] = "LIST_INSERT_UPD_TOTAL";
           
        }
         parametros[5] = idBiblioteca;
      list = conector.execProcedure("BV.SP_REPORTE_RANGO_TRABAJADORES", parametros);
        return list;
    } 

}


package vb.dao;

import java.util.List;
import vb.util.sql;


public class paisDao {
 
    sql conector = new sql();
    
    public List<Object[]> llenaComboTipoInstitucion(){
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "LIST_PAIS";
        parametros[6] = "";
               
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        return list;
    }
    
}

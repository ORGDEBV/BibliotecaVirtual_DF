package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.AuxContenido;
import vb.entidad.TablaContenidos;
import vb.util.sql;

public class tablaContenidoDao {

    sql conector = new sql();

    public ArrayList<AuxContenido> lstTablaContenidosXidDocumental(String idDocumental) {
        ArrayList<AuxContenido>lst = new ArrayList<>();
        AuxContenido tc = null;
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_TABLA_CONTENIDO_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            tc = new AuxContenido();
            tc.setIndice(d[0].toString());
            tc.setTema(d[1].toString());
            lst.add(tc);

        }
        return lst;

    }

}

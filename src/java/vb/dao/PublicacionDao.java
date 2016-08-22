/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vb.dto.PublicacionDto;
import vb.util.sql;

/**
 *
 * @author virtual
 */
public class PublicacionDao {

    sql conector = new sql();

    public List<PublicacionDto> listPublicacion(String ID_PERFIL, String ID_BIBLIOTECA) {
        String[] parametros = new String[2];
        List<PublicacionDto> lPublicacion = new ArrayList<>();
        PublicacionDto dto = null;
        parametros[0] = ID_PERFIL;
        parametros[1] = ID_BIBLIOTECA;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_LISTAR_DOCUMENTAL_PUBLICADO]", parametros);
        for (Object[] datos : data) {
            dto = new PublicacionDto();
            dto.setID_DOCUMENTAL(datos[0].toString());
            dto.setOTRO(datos[1].toString());
            dto.setTITULO(datos[2].toString());
            dto.setURL(datos[3].toString());
            dto.setNRO_VISITAS(Integer.parseInt(datos[4].toString()));
            dto.setID_USUARIO(Integer.parseInt(datos[5].toString()));
            dto.setNOMBRE(datos[6].toString());
            dto.setAPELLIDO_PATERNO(datos[7].toString());
            dto.setAPELLIDO_MATERNO(datos[8].toString());
            dto.setFECHA_PUBLICACION((Date) datos[9]);
            dto.setVISIBLE(Integer.parseInt(datos[10].toString()));
            if (dto.getVISIBLE() == 1) {
                dto.setCLASS_VISIBLE("GreenBack");
            } else if (dto.getVISIBLE() == 0) {
                dto.setCLASS_VISIBLE("RedBack");
            }
            lPublicacion.add(dto);
        }
        return lPublicacion;
    }

}

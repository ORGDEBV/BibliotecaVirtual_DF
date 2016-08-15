/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.MetadataTabla;
import vb.util.sql;

/**
 *
 * @author virtual
 */
public class metadataTablaDao {
     sql conector = new sql();
     
     public List<MetadataTabla> listarMetadataTabla(){
     List<MetadataTabla> lstMetadata=new ArrayList<>();
     MetadataTabla metadata;
     
      String[] array = new String[1];

        array[0] = "DOCUMENTAL";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_METADATA_TABLAS", array);
        for (Object[] d : data) {
            metadata=new MetadataTabla();
            metadata.setID_METADATA(Integer.parseInt(d[0].toString()));
            metadata.setCAMPO(d[1].toString());
            metadata.setETIQUETA_DC(d[2].toString());
            metadata.setDESCRIPCION(d[3].toString());
            metadata.setLONGITUD(Integer.parseInt(d[4].toString()));
            lstMetadata.add(metadata);
            
        }
        
     
     return lstMetadata;
     }
    
}

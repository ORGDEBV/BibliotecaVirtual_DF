/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.util.sql;

/**
 *
 * @author virtual
 */
public class formatoDao {
     sql conector = new sql();
     
      public List<Object[]> llenaComboFormato() {
       
        String[] array = new String[1];
        array[0] = "";
      
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_FORMATO", array);

        return data;
    }
    
    
    
}

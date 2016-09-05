/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao.impl;

import bv.dao.FormatoDao;
import java.util.ArrayList;
import java.util.List;
import bv.util.sql;

/**
 *
 * @author virtual
 */
public class FormatoDaoImpl implements FormatoDao {

    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboFormato() {
        String[] array = new String[1];
        array[0] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_FORMATO", array);
        return data;
    }

}

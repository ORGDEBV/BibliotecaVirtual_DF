/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.dao;
import vb.entidad.LogTabla;
public class pruebas {
    
    public static void main(String[] args) {
        //LogTabla lt =new 
     boolean f=  logTablaDao.registrarLogTabla(new LogTabla("BV","TESTING", "", "1", "35"));
     if(f){
         System.out.println("registro log");
    }else{

            System.out.println("no registro log");
     }
    
}}

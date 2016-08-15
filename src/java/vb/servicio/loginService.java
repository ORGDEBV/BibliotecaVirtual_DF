/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vb.servicio;

import java.io.IOException;
import vb.entidad.Usuario;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public interface loginService {
    public Usuario validar(String user, String password);
    public int recuperar(String email);
}

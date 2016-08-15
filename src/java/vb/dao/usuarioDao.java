package vb.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.LogTabla;
import vb.entidad.TipoUsuario;
import vb.servicio.entidadService;
import vb.entidad.Usuario;
import vb.util.sql;

/**
 *
 * @author virtual
 */
public class usuarioDao implements entidadService<Usuario> {

    sql conector = new sql();

    @Override
    public int crearEntidad(Usuario u) {
        int n = 0;

        String[] parametros = new String[7];
        parametros[0] = String.valueOf(u.getID_USUARIO());
        parametros[1] = u.getUSUARIO();
        parametros[2] = u.getCONTRASENA();
        if (u.isBoolActivo()) {
            parametros[3] = "1";

        } else {
            parametros[3] = "0";
        }
        parametros[4] = String.valueOf(u.getID_PERSONAL_BIBLIOTECA());
        parametros[5] = String.valueOf(u.getID_TIPO_USUARIO());

        parametros[5] = "LIST_USUARIO";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        if (result != null) {
            n = 1;

        }

        return n;
    }

    @Override
    public int eliminarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int actualizarEntidad(Usuario u, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(u.getID_USUARIO());
        parametros[1] = u.getUSUARIO();
        parametros[2] = u.getCONTRASENA();
        if (u.isBoolActivo()) {
            parametros[3] = "1";
        } else {
            parametros[3] = "0";
        }

        parametros[4] = String.valueOf(u.getID_PERSONAL_BIBLIOTECA());
        parametros[5] = String.valueOf(u.getID_TIPO_USUARIO());
        parametros[6] = "";

        parametros[7] = "UPD_USUARIO";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        if (result != null) {
            n = 1;
            logTablaDao.registrarLogTabla(new LogTabla("BV", "USUARIO", "", "2", String.valueOf(idUsuario)));
        }

        return n;

    }

    public int resetContrasena(Usuario u, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(u.getID_USUARIO());
        parametros[1] = u.getUSUARIO();
        parametros[2] = u.getCONTRASENA();
        if (u.isBoolActivo()) {
            parametros[3] = "1";
        } else {
            parametros[3] = "0";
        }

        parametros[4] = String.valueOf(u.getID_PERSONAL_BIBLIOTECA());
        parametros[5] = String.valueOf(u.getID_TIPO_USUARIO());
        parametros[6] = "0";

        parametros[7] = "RESET_USUARIO";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        if (result != null) {
            n = 1;
            logTablaDao.registrarLogTabla(new LogTabla("BV", "USUARIO", "", "2", String.valueOf(idUsuario)));

        }

        return n;

    }

    @Override
    public List<Usuario> obtenerEntidades() {
        List<Usuario> lstUsuario = new ArrayList<>();
        Usuario usuario = null;
        String[] parametros = new String[11];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "LIST_USUARIO";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "";

        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        for (Object[] datos : data) {
            usuario = new Usuario();

            usuario.setID_USUARIO(Integer.parseInt(datos[0].toString()));
            usuario.setUSUARIO(datos[1].toString());
            usuario.setCONTRASENA(datos[2].toString());
            if (datos[3].equals("1")) {
                usuario.setBoolActivo(true);
            } else {
                usuario.setBoolActivo(false);
            }

            usuario.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[4].toString()));
            usuario.setNombrePersonalBiblioteca(datos[5].toString());
            usuario.setID_TIPO_USUARIO(Integer.parseInt(datos[6].toString()));
            usuario.setTipoUsuario(datos[7].toString());
            usuario.setNombreBiblioteca(datos[8].toString());
            usuario.setCAMBIO_PASSW_SISTEMA(Integer.parseInt(datos[9].toString()));

            lstUsuario.add(usuario);
        }
        return lstUsuario;

    }

    public List<Usuario> obtenerEntidadesParametros(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca) {
        List<Usuario> lstUsuario = new ArrayList<>();
        Usuario usuario = null;
        String[] parametros = new String[11];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "LIST_USUARIO";
        parametros[8] = idTipoUsuario;
        parametros[9] = idBibliotecaMediador;
        parametros[10] = idPersonalBiblioteca;

        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        for (Object[] datos : data) {
            usuario = new Usuario();

            usuario.setID_USUARIO(Integer.parseInt(datos[0].toString()));
            usuario.setUSUARIO(datos[1].toString());
            usuario.setCONTRASENA(datos[2].toString());
            if (datos[3].equals("1")) {
                usuario.setBoolActivo(true);
            } else {
                usuario.setBoolActivo(false);
            }

            usuario.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[4].toString()));
            usuario.setNombrePersonalBiblioteca(datos[5].toString());
            usuario.setID_TIPO_USUARIO(Integer.parseInt(datos[6].toString()));
            usuario.setTipoUsuario(datos[7].toString());
            usuario.setNombreBiblioteca(datos[8].toString());

            usuario.setCAMBIO_PASSW_SISTEMA(Integer.parseInt(datos[9].toString()));

            lstUsuario.add(usuario);
        }
        return lstUsuario;

    }

    public List<Object[]> obtenerTipousuario(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca) {

        String[] parametros = new String[11];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "LIST_TIPO_USUARIO";
        parametros[8] = idTipoUsuario;
        parametros[9] = idBibliotecaMediador;
        parametros[10] = idPersonalBiblioteca;
        TipoUsuario tipoUsuario = null;
        ArrayList<Object[]> lstTipoUsuario = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);

        return lstTipoUsuario;

    }

    @Override
    public Usuario buscarEntidad(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int cambiarContrasena(Usuario usuario,int idUsuario) {
        int retorno = 0;
        String[] parametros = new String[2];
        parametros[0] = usuario.getID_USUARIO() + "";
        parametros[1] = usuario.getCONTRASENA_NUEVA1();
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_CAMBIAR_CONTRASENA", parametros);
        for (Object[] datas : data) {
            retorno = Integer.parseInt(datas[0].toString());
             logTablaDao.registrarLogTabla(new LogTabla("BV", "USUARIO", "", "2", String.valueOf(idUsuario)));
        }
        return retorno;
    }

    @Override
    public int actualizarEntidad(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

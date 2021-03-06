package conexion.usuarios;

import conexion.ConexionBDD;
import conexion.rol.RolDAO;
import conexion.rol.RolVO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUser{
    private Connection con = null;

    public UserDAO() {
        con= ConexionBDD.getInstance();
    }

    @Override
    public List<UserVO> getAll() throws SQLException {
        List<UserVO> listaDatos = new ArrayList<>();

        //Consulta a la tabla rol
        try (Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("select * from usuario");

            while (res.next()) {
                UserVO user = new UserVO();

                user.setEmail(res.getString("email"));
                user.setContraseña("contraseña");
                user.setNombre("nombre");
                user.setFechaCreacion(res.getTimestamp("fecha_creacion").toLocalDateTime());

                LocalDateTime ultMod = res.getTimestamp("ult_mod_passwd") !=null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltModPassword(ultMod);

                LocalDateTime ultConexion= res.getTimestamp("ult_mod_passwd") !=null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltConexion(ultConexion);
                user.setKey(res.getString("key"));

                RolDAO rol = new RolDAO();
                user.setRol(rol.findById(res.getInt("rol_id")));

                listaDatos.add(user);
            }
        }

        return listaDatos;
    }

    @Override
    public UserVO findByEmail(String email) throws SQLException {
        //Consulta a la tabla usuario con email especifico
        ResultSet res = null;


        String query = "select * from usuario where email LIKE ?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1,email);

            res= ps.executeQuery();

            if(res.next()){
                UserVO user = new UserVO();

                user.setEmail(res.getString("email"));
                user.setContraseña("contraseña");
                user.setNombre("nombre");
                user.setFechaCreacion(res.getTimestamp("fecha_creacion").toLocalDateTime());

                LocalDateTime ultMod = res.getTimestamp("ult_mod_passwd") !=null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltModPassword(ultMod);

                LocalDateTime ultConexion= res.getTimestamp("ult_mod_passwd") !=null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltConexion(ultConexion);
                user.setKey(res.getString("key"));

                RolDAO rol = new RolDAO();
                user.setRol(rol.findById(res.getInt("rol_id")));

                return user;
            }

        }

        return null;
    }

    @Override
    public UserVO findByName(String nombre) throws SQLException {
        //Consulta a la tabla usuario con email especifico
        ResultSet res = null;


        String query = "select * from usuario where nombre LIKE ?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1,nombre);

            res= ps.executeQuery();

            if(res.next()){
                UserVO user = new UserVO();

                user.setEmail(res.getString("email"));
                user.setContraseña("contraseña");
                user.setNombre("nombre");
                user.setFechaCreacion(res.getTimestamp("fecha_creacion").toLocalDateTime());

                LocalDateTime ultMod = res.getTimestamp("ult_mod_passwd") !=null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltModPassword(ultMod);

                LocalDateTime ultConexion= res.getTimestamp("ult_mod_passwd") !=null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltConexion(ultConexion);
                user.setKey(res.getString("key"));

                RolDAO rol = new RolDAO();
                user.setRol(rol.findById(res.getInt("rol_id")));

                return user;
            }

        }

        return null;
    }

    @Override
    public List<UserVO> getAllUserByRolId(int rolID) throws SQLException {
        List<UserVO> listaDatos = new ArrayList<>();

        //Consulta a la tabla usuario con email especifico
        ResultSet res = null;

        String query = "select * from usuario where rol_id=?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, rolID);

            res = ps.executeQuery();

            while (res.next()) {
                UserVO user = new UserVO();

                user.setEmail(res.getString("email"));
                user.setContraseña("contraseña");
                user.setNombre("nombre");
                user.setFechaCreacion(res.getTimestamp("fecha_creacion").toLocalDateTime());

                LocalDateTime ultMod = res.getTimestamp("ult_mod_passwd") != null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltModPassword(ultMod);

                LocalDateTime ultConexion = res.getTimestamp("ult_mod_passwd") != null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltConexion(ultConexion);
                user.setKey(res.getString("key"));

                RolDAO rol = new RolDAO();
                user.setRol(rol.findById(res.getInt("rol_id")));

                listaDatos.add(user);
            }

            return listaDatos;
        }
    }

    @Override
    public List<UserVO> getAllUserByRolName(String rolName) throws SQLException {
        List<UserVO> listaDatos = new ArrayList<>();

        //Consulta a la tabla usuario con email especifico
        ResultSet res = null;

        String query = "select * from usuario where rol_id = ( select rol_id from rol where rol_name like ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, rolName);

            res = ps.executeQuery();

            while (res.next()) {
                UserVO user = new UserVO();

                user.setEmail(res.getString("email"));
                user.setContraseña("contraseña");
                user.setNombre("nombre");
                user.setFechaCreacion(res.getTimestamp("fecha_creacion").toLocalDateTime());

                LocalDateTime ultMod = res.getTimestamp("ult_mod_passwd") != null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltModPassword(ultMod);

                LocalDateTime ultConexion = res.getTimestamp("ult_mod_passwd") != null ? res.getTimestamp("ult_mod_passwd").toLocalDateTime() : null;
                user.setUltConexion(ultConexion);
                user.setKey(res.getString("key"));

                RolDAO rol = new RolDAO();
                user.setRol(rol.findById(res.getInt("rol_id")));

                listaDatos.add(user);
            }

            return listaDatos;
        }
    }

    @Override
    public String countUsers() throws SQLException {
        try( Statement st = con.createStatement();){
            ResultSet res = st.executeQuery("SELECT COUNT(*) AS contadorUsuario FROM usuario");
            res.next();

            return ""+res.getInt("contadorUsuario");
        }
    }

    @Override
    public Integer insertUser(UserVO usuario) throws SQLException {
        int numFilas =0;
        String sql = "insert into usuario values (?,?,?,?,?,?,?,?)";

        if (findByEmail(usuario.getEmail()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1,usuario.getEmail());
                prest.setString(2, usuario.getContraseña());
                prest.setString(3,usuario.getNombre());
                prest.setTimestamp(4, Timestamp.valueOf(usuario.getFechaCreacion()));
                prest.setTimestamp(5,usuario.getUltModPassword()==null ? null : Timestamp.valueOf(usuario.getUltModPassword()));
                prest.setTimestamp(6,usuario.getUltConexion()==null ? null : Timestamp.valueOf(usuario.getUltConexion()));
                prest.setString(7,usuario.getKey());
                prest.setInt(8,usuario.getRol().getRolID());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public Integer insertListUser(List<UserVO> usuarios) throws SQLException {
        int numeroFilas=0;

        for(UserVO user : usuarios){
            numeroFilas+= insertUser(user);
        }

        return numeroFilas;
    }

    @Override
    public Integer updateUser(String email, UserVO usuarioActualizado) throws SQLException {
        int numFilas = 0;
        String sql = "update usuario set email=?, contraseña=?,nombre=?,fecha_creacion=?, ult_mod_passwd=?, ultima_conexion=?, key=?, rol_id=?" +
                " where email=?";
        if (findByEmail(email) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1,usuarioActualizado.getEmail());
                prest.setString(2, usuarioActualizado.getContraseña());
                prest.setString(3,usuarioActualizado.getNombre());
                prest.setTimestamp(4, Timestamp.valueOf(usuarioActualizado.getFechaCreacion()));
                prest.setTimestamp(5,usuarioActualizado.getUltModPassword()==null ? null : Timestamp.valueOf(usuarioActualizado.getUltModPassword()));
                prest.setTimestamp(6,usuarioActualizado.getUltConexion()==null ? null : Timestamp.valueOf(usuarioActualizado.getUltConexion()));
                prest.setString(7,usuarioActualizado.getKey());
                prest.setInt(8,usuarioActualizado.getRol().getRolID());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public Integer deleteUser(UserVO usuario) throws SQLException {
        int numFilas = 0;

        String sql = "delete from usuario where email = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, usuario.getEmail());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }
}

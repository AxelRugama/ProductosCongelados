
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import controlador.beanDirecciones;
import controlador.beanUsuarios;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author axelj
 */
public class UsuarioDB {
    
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    
    String nombre;
    String apellido;
    String correo;
    String telefono;
    String id;
    int rol;
    
    

    public UsuarioDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
   
    public boolean ValidarUsuario(String pass, String ident) 
            throws SNMPExceptions, SQLException{
        boolean existe = false;
        String select="";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="select * from usuario where identificacion="+"'"+ident+"'"+" and DECRYPTBYPASSPHRASE('password', contrasenna)="+"'"+pass+"'"+ "and estado= 1";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if(rsPA.next()){
                existe=true;
                nombre = rsPA.getString("nombre");
                apellido = rsPA.getString("apellido");
                correo = rsPA.getString("correo");
                telefono = rsPA.getString("telefono");
                id= rsPA.getString("identificacion");
                rol = rsPA.getInt("rol");
            }
            
            rsPA.close();
      
            return existe;
            
             } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public boolean ValidarContrasenna(Usuario u, String pass) 
            throws SNMPExceptions, SQLException{
        boolean existe = false;
        String select="";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="select * from usuario where identificacion="+"'"+u.ident+"'"+" and DECRYPTBYPASSPHRASE('password', contrasenna)="+"'"+pass+"'";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if(rsPA.next()){
                existe=true;
                nombre = rsPA.getString("nombre");
                apellido = rsPA.getString("apellido");
                correo = rsPA.getString("correo");
                telefono = rsPA.getString("telefono");
                id= rsPA.getString("identificacion");
                rol = rsPA.getInt("rol");
            }
            
            rsPA.close();
      
            return existe;
            
             } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public void ingresarUsuario(Usuario u) 
            throws SNMPExceptions, SQLException {
        
    String insert = "";
         try{
       Usuario usuario = new Usuario();
       usuario = u;
       
       insert = "INSERT INTO usuario VALUES "
         + "(" + "'" + usuario.ident + "'" + "," 
               + "'"+ usuario.nombre +"'"+ ","
               + "'"+ usuario.apellido +"'" + ","
               + "'"+ usuario.correo +"'" + ","
               + "'"+ usuario.telefono +"'" + ","
               + "ENCRYPTBYPASSPHRASE('password',"+ "'" + usuario.pass +"')"+ ","
               +  usuario.rol + ","
               +  usuario.estado  + ")";
       
       accesoDatos.ejecutaSQL(insert);
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public LinkedList<Usuario> ListarUsuarios() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Usuario> listaUsuarios= new LinkedList<>();
        
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT identificacion, nombre, apellido, correo, telefono, rol, estado from usuario where rol= 1 or rol=2";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String ident = rsPA.getString("identificacion");
                
                String nombre = rsPA.getString("nombre");
                
                String apellido = rsPA.getString("apellido");
                
                String correo = rsPA.getString("correo");
                
                String telefono = rsPA.getString("telefono");
                int rol= rsPA.getInt("rol");
                String rol_s;
                if(rol == 1){
                    rol_s = "Administrador";
                }else{
                    rol_s = "Empleado";
                }
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                Usuario nuevoUsuario= new Usuario(ident, nombre, apellido, correo, telefono, rol_s, estado_s);
                
                listaUsuarios.add(nuevoUsuario);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaUsuarios;
    }
    
    public LinkedList<Usuario> ListarClientes() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Usuario> listaUsuarios= new LinkedList<>();
        
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT identificacion, nombre, apellido, correo, telefono, rol, estado from usuario where rol= 3";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String ident = rsPA.getString("identificacion");
                
                String nombre = rsPA.getString("nombre");
                
                String correo = rsPA.getString("correo");
                
                String telefono = rsPA.getString("telefono");
                
                int rol= rsPA.getInt("rol");
                String rol_s = "Cliente";
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                Usuario nuevoUsuario= new Usuario(ident, nombre, apellido, correo, telefono, rol_s, estado_s);
                
                listaUsuarios.add(nuevoUsuario);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaUsuarios;
    }
    
    public Usuario clienteModi(String identificacion) throws SNMPExceptions, SQLException{
        String select= "";
        Usuario usuario = null;
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select= "SELECT identificacion, nombre, apellido, correo, telefono, estado from usuario where identificacion=" + "'" + identificacion + "'";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String ident = rsPA.getString("identificacion");
                
                String nombreC = rsPA.getString("nombre");
                
                String apellidoC = rsPA.getString("apellido");
                
                String correoC = rsPA.getString("correo");
                
                String telefonoC = rsPA.getString("telefono");
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                usuario = new Usuario(ident, nombreC, apellidoC, correoC, telefonoC, estado_s);
                
                
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(SNMPExceptions | ClassNotFoundException | NamingException e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return usuario;
    }

    public void ModificarCliente(Usuario u) 
            throws SNMPExceptions, SQLException {
        
    String update = "";
         try{
             Usuario usuario = new Usuario();
             usuario = u;
             int estado = 0;
             
             if(usuario.estado_s.equals("Activo")){
                 estado = 1;
             }else{
                 estado = 2;
             }
       
       
       update = "update usuario set nombre=" + "'" + u.nombre + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set apellido=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set correo=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set telefono=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set estado=" + estado + "where identificacion=" + "'" + u.ident + "'";
       
       accesoDatos.ejecutaSQL(update);
       
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public void eliminarCliente(String identificacion) 
            throws SNMPExceptions, SQLException {
        
            String update = "";
         try{
             Usuario usuario = new Usuario();
             usuario = clienteModi(identificacion);
             int estado = 0;
             
             if(usuario.estado_s.equals("Activo")){
                 estado = 2;
             }else{
                 estado = 2;
             }
             
         update ="update usuario set estado=" + estado + "where identificacion=" + "'" + usuario.ident + "'";
       
       accesoDatos.ejecutaSQL(update);
       
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public Usuario usuarioModi(String identificacion) throws SNMPExceptions, SQLException{
        String select= "";
        Usuario usuario = null;
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select= "SELECT identificacion, nombre, apellido, correo, telefono, estado from usuario where identificacion=" + "'" + identificacion + "'";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String ident = rsPA.getString("identificacion");
                
                String nombreC = rsPA.getString("nombre");
                
                String apellidoC = rsPA.getString("apellido");
                
                String correoC = rsPA.getString("correo");
                
                String telefonoC = rsPA.getString("telefono");
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                usuario = new Usuario(ident, nombreC, apellidoC, correoC, telefonoC, estado_s);
                
                
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(SNMPExceptions | ClassNotFoundException | NamingException e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return usuario;
    }
    
    public void ModificarUsuario(Usuario u) 
            throws SNMPExceptions, SQLException {
        
    String update = "";
         try{
             Usuario usuario = new Usuario();
             usuario = u;
             int estado = 0;
             
             if(usuario.estado_s.equals("Activo")){
                 estado = 1;
             }else{
                 estado = 2;
             }
       
       
       update = "update usuario set nombre=" + "'" + u.nombre + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set apellido=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set correo=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set telefono=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set estado=" + estado + "where identificacion=" + "'" + u.ident + "'";
       
       accesoDatos.ejecutaSQL(update);
       
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
     public void ModificarConfi(Usuario u) 
            throws SNMPExceptions, SQLException {
        
    String update = "";
         try{
             Usuario usuario = new Usuario();
             usuario = u;
             int estado = 0;
             
             if(usuario.estado_s.equals("Activo")){
                 estado = 1;
             }else{
                 estado = 2;
             }
       
       
       update = "update usuario set nombre=" + "'" + u.nombre + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set apellido=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set correo=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set telefono=" + "'" + u.apellido + "'" + "where identificacion=" + "'" + u.ident + "'" +
                "update usuario set contrasenna= ENCRYPTBYPASSPHRASE('password','"+ u.pass + "') where identificacion=" + "'" + u.ident + "'";
       
       accesoDatos.ejecutaSQL(update);
       
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
     public void eliminarUsuario(String identificacion) 
            throws SNMPExceptions, SQLException {
        
            String update = "";
         try{
             Usuario usuario = new Usuario();
             usuario = usuarioModi(identificacion);
             int estado = 0;
             
             if(usuario.estado_s.equals("Activo")){
                 estado = 2;
             }else{
                 estado = 2;
             }
             
         update ="update usuario set estado=" + estado + "where identificacion=" + "'" + usuario.ident + "'";
       
       accesoDatos.ejecutaSQL(update);
       
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
     
  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }  

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   
    
    
}

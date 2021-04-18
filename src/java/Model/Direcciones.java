
package Model;

/**
 *
 * @author axelj
 */
public class Direcciones {
    String idUsuario;
    String direccion;
    int idDireccion;
    int estado;

    public Direcciones(String idUsuario, String direccion, int idDireccion, int estado) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.idDireccion = idDireccion;
        this.estado = estado;
    }

    public Direcciones(String idUsuario, String direccion, int estado) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Direcciones() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
    
}

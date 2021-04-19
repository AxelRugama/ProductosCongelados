
package Model;

/**
 *
 * @author gbd
 */
public class Direcciones {
    String idUsuario;
    String direccion;
    String canton;
    String distrito;
    String barrio;
    int idDireccion;
    
    int estado;

    public Direcciones(String idUsuario, String direccion, String canton, String distrito, String barrio, int idDireccion, int estado) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.canton = canton;
        this.distrito = distrito;
        this.barrio = barrio;
        this.idDireccion = idDireccion;
        this.estado = estado;
    }

    public Direcciones(String idUsuario, String direccion,  String canton, String distrito, String barrio, int estado) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.canton = canton;
        this.distrito = distrito;
        this.barrio = barrio;
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

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    
    
    
    
}

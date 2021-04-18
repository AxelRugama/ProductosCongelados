
package Model;

/**
 *
 * @author Villegas716
 */
public class Despacho {
    int idDespacho;
    int idFactura;
    String fecha;
    String idUsuario;

    public Despacho(int idDespacho, int idFactura, String fecha, String idUsuario) {
        this.idDespacho = idDespacho;
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public Despacho(int idFactura, String fecha, String idUsuario) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public int getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(int idDespacho) {
        this.idDespacho = idDespacho;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}

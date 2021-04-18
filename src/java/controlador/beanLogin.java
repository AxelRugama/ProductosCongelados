/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.SNMPExceptions;
import Model.UsuarioDB;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author GBD
 */
@Named(value = "beanLogin")
@SessionScoped
public class beanLogin implements Serializable {

    /**
     * Creates a new instance of beanLogin
     */
    String ident;
    String pass;
    String nombre;
    String id;
    String error;
    String inicioSesion = "Iniciar Sesion";
    UsuarioDB udb = new UsuarioDB();
    
    public beanLogin() {
    }
    
    public void getLogin()throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException{
       boolean result = udb.ValidarUsuario(pass, ident);
       if(result){
           nombre =  udb.getNombre() + " " + udb.getApellido();
           id = udb.getId();
           this.setInicioSesion("Cerrar Sesion");
           this.setIdent("");
           this.setPass("");
           if(udb.getRol() == 1){
            FacesContext.getCurrentInstance().getExternalContext().redirect("indexAdmin.xhtml");
           }else{
               if(udb.getRol() == 2){
                   FacesContext.getCurrentInstance().getExternalContext().redirect("indexAdmin.xhtml");
               }else{
                   FacesContext.getCurrentInstance().getExternalContext().redirect("indexCliente.xhtml");
               }
           }   
           
       }else{
       this.setError("Datos Incorrectos");
       this.setPass("");
       }
    }
    
    public void getIniciarSesion()throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException{
        if(inicioSesion.equals("Iniciar Sesion")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("Ingreso.xhtml");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            this.setNombre("");
            this.setInicioSesion("Iniciar Sesion");
            udb.setRol(0);
        }
    }
    
     public void getInicio()throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException{
         if(udb.getRol() == 1 || udb.getRol() == 2){
          FacesContext.getCurrentInstance().getExternalContext().redirect("indexAdmin.xhtml");   
         }else{
             if(udb.getRol() == 3){
              FacesContext.getCurrentInstance().getExternalContext().redirect("indexCliente.xhtml"); 
             }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");  
             }
         }
     }
     
     public void getConfi()throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException{
         if(udb.getRol() == 1 || udb.getRol() == 2){
          FacesContext.getCurrentInstance().getExternalContext().redirect("indexAdmin.xhtml");   
         }else{
             if(udb.getRol() == 3){
              FacesContext.getCurrentInstance().getExternalContext().redirect("indexCliente.xhtml"); 
             }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");  
             }
         }
     }
     
     public void validarPedido() throws IOException{
         if(this.nombre == null || this.nombre.equals("")){
             FacesContext.getCurrentInstance().getExternalContext().redirect("Ingreso.xhtml"); 
         }else{
             FacesContext.getCurrentInstance().getExternalContext().redirect("Pedidos.xhtml"); 
         }
     }

    public String getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(String inicioSesion) {
        this.inicioSesion = inicioSesion;
    }
    

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
  
    
}

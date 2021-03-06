package com.example.clienteandroid.Modelo;

public class Empresa {

    public int nitEmpresa;
    public String nombre;
    public String ubicacion;
    public String telefono;
    public String correo;
    public String descripcion;
    public String usuario;
    public String contrasenia;

    public Empresa(int nitEmpresa, String nombre, String ubicacion, String telefono, String correo, String descripcion, String usuario, String contrasenia) {
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.correo = correo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Empresa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(int nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Empresa{" + "nitEmpresa=" + nitEmpresa + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono + ", correo=" + correo + ", descripcion=" + descripcion + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }

}
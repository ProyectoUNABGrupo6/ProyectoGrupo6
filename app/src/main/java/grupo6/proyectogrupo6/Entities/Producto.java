package grupo6.proyectogrupo6.Entities;

import java.util.Date;
import java.util.UUID;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private int precio;
    private String imagen;
    private boolean eliminado;
    private Date actualizacion;
    private Date creado;



    public Producto(String id, String nombre, String descripcion, int precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.creado = new Date();
        this.actualizacion = new Date();
        this.eliminado = false;
    }

    public Producto(String nombre, String descripcion, int precio, String imagen) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.creado = new Date();
        this.actualizacion = new Date();
        this.eliminado = false;
    }

    public Producto(String id, String nombre, String descripcion, int precio, String imagen, Boolean eliminado, Date creado, Date actualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.creado = creado;
        this.actualizacion = actualizacion;
        this.eliminado = eliminado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
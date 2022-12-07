package grupo6.proyectogrupo6.Entities;

public class Producto {
    private byte[] imagen;
    private String nombre;
    private String descripcion;
    private int precio;

    public Producto(byte[] imagen, String nombre, String descripcion, int precio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public byte[] getImagen() {

        return imagen;
    }

    public void setImagen(byte[] imagen) {

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
package grupo6.proyectogrupo6.Entities;

public class Producto {
    private int imagen;
    private String name;
    private String descripcion;
    private int precio;

    public Producto(int imagen, String name, String descripcion, int precio) {
        this.imagen = imagen;
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

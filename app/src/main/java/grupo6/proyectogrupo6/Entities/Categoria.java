package grupo6.proyectogrupo6.Entities;

import java.util.UUID;

public class Categoria {

    private String idCat;
    private String categoria;
    private String imagen;

    public Categoria(String idCat, String categoria, String imagen) {
        this.idCat = idCat;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Categoria(String categoria, String imagen) {
        this.idCat = UUID.randomUUID().toString();
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Categoria() {

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdCat() {
        return idCat;
    }

    public void setIdCat(String idCat) {
        this.idCat = idCat;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

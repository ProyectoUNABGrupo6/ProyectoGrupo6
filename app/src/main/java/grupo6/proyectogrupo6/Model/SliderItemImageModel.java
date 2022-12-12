package grupo6.proyectogrupo6.Model;

public class SliderItemImageModel {

    private int imagen;
    private String titulo;

    public SliderItemImageModel() {
    }
    public SliderItemImageModel(int imagen, String titulo) {
        this.imagen = imagen;
        this.titulo = titulo;
    }
    public int getImagen() {
        return imagen;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

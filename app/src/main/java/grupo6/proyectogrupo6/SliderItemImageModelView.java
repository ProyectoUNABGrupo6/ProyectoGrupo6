package grupo6.proyectogrupo6;

public class SliderItemImageModelView {

    private int imagen;
    private String titulo;

    public SliderItemImageModelView() {
    }
    public SliderItemImageModelView(int imagen, String titulo) {
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

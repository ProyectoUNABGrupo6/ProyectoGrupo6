package grupo6.proyectogrupo6.model;

public class CardItemRecommendedCategoryModel {

    private int imagen;
    private String titulo;

    public CardItemRecommendedCategoryModel() {
    }
    public CardItemRecommendedCategoryModel(int imagen, String titulo) {
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

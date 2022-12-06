package grupo6.proyectogrupo6;

public class CardItemRecommendedProductModel {

    private int image;
    private String name;
    private String details;

    public CardItemRecommendedProductModel() {
    }

    public CardItemRecommendedProductModel(int image, String titulo, String detalles) {
        this.image = image;
        this.name = titulo;
        this.details = detalles;
    }

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}

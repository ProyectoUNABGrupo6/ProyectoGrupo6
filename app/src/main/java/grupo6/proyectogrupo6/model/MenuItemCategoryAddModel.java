package grupo6.proyectogrupo6.model;

public class MenuItemCategoryAddModel {
    String urlPhoto;
    String name;
    String shortDescription;

    public MenuItemCategoryAddModel() {
    }
    public MenuItemCategoryAddModel(String urlPhoto, String name, String shortDescription) {
        this.urlPhoto = urlPhoto;
        this.name = name;
        this.shortDescription = shortDescription;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        return "MenuItemCategoryAddModel{" +
                "urlPhoto='" + urlPhoto + '\'' +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}

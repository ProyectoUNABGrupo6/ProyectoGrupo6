package grupo6.proyectogrupo6.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "product")
public class Product implements Serializable {

    @PrimaryKey
    private Long uid;
    @ColumnInfo(name = "url_image")
    private String urlImage;
    @ColumnInfo(name = "sku")
    private String sku;
    @ColumnInfo(name = "manufacturer")
    private String manufacturer;
    @ColumnInfo(name = "reference")
    private String reference;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "qualifier")
    private Long qualifier;
    @ColumnInfo(name = "update_date")
    private Date updateDate;
    @ColumnInfo(name = "delete_date")
    private Date deleteDate;



    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public Long getQualifier() {
        return qualifier;
    }
    public void setQualifier(Long qualifier) {
        this.qualifier = qualifier;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Date getDeleteDate() {
        return deleteDate;
    }
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}

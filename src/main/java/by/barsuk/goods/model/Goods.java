package by.barsuk.goods.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fk_manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "vendor_code")
    @NotEmpty(message = "Код украшения обязателен")
    private String vendor;

    @Column(name = "name_goods")
    @NotEmpty(message = "Наименование украшения обязательно")
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_type_metal_id")
    private TypeMetal metal;

    @Column(name = "price")
    @NotNull(message = "Цена украшения обязательна")
    private double price;

    @Column(name = "description")
    @Size(max = 100, message = "Слишком длинное описание")
    private String description;

    @Column(name = "image")
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeMetal getMetal() {
        return metal;
    }

    public void setMetal(TypeMetal metal) {
        this.metal = metal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

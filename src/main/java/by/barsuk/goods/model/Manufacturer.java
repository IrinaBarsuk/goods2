package by.barsuk.goods.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_manufacturer")
    @NotEmpty(message = "Название производителя обязательно")
    private String name;

    @Column(name = "country")
    @NotEmpty(message = "Страна производителя обязательна")
    private String country;

    @Column(name = "city")
    @NotEmpty(message = "Город производителя обязателен")
    private String city;

    @Column(name = "address")
    @NotEmpty(message = "Адрес производителя обязателен")
    private String address;

    @Column(name = "phone_number")
    @NotEmpty(message = "Номер телефона производителя обязателен")
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty(message = "Email производителя обязателен")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package ugr.cc.vendors_products.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

@Entity
public class Vendor {

    private Integer id;
    private String name;
    private String lastName;
    private Date birthDate;

    private Collection<Product> productsById;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(name, vendor.name) &&
                Objects.equals(lastName, vendor.lastName) &&
                Objects.equals(birthDate, vendor.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, birthDate);
    }

    @OneToMany(mappedBy = "vendorByVendorId")
    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }

    @Transient
    public boolean isAdult() {
        String birthDateString = birthDate.toString();
        LocalDate currentLocalDate = LocalDate.now();

        //Parsing the date
        LocalDate birthDate = LocalDate.parse(birthDateString);

        //calculating number of days in between
        int noOfYearsBetween = (int) ChronoUnit.YEARS.between(birthDate, currentLocalDate);
        return noOfYearsBetween >= 18;
    }

    public Vendor(String name, String lastName, Date birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.productsById = new LinkedList<>();
    }

    public Vendor(Integer id, String name, String lastName, Date birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.productsById = new LinkedList<>();
    }

    public Vendor() {
        this.productsById = new LinkedList<>();
    }
}

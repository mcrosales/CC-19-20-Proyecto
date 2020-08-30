package ugr.cc.vendors_products.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

public class Vendor {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String lastName;
    private Date birthDate;

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
}

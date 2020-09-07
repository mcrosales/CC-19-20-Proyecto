package ugr.cc.vendors_products.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Product {


    private Integer id;
    private Double price;
    private Double discountedPrice;
    private String name;
    private Integer amountAvailable;
    private Boolean hasDiscount;
    private Date expirationDate;
    private Boolean active;

    private Vendor vendorByVendorId;
    private Integer vendorId;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    @JsonIgnore
    public Vendor getVendorByVendorId() {
        return vendorByVendorId;
    }

    public void setVendorByVendorId(Vendor vendorByVendorId) {
        this.vendorByVendorId = vendorByVendorId;
    }

    @Column(name = "vendor_id")
    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(price, product.price) &&
                Objects.equals(discountedPrice, product.discountedPrice) &&
                Objects.equals(name, product.name) &&
                Objects.equals(amountAvailable, product.amountAvailable) &&
                Objects.equals(hasDiscount, product.hasDiscount) &&
                Objects.equals(expirationDate, product.expirationDate) &&
                Objects.equals(active, product.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, discountedPrice, name, amountAvailable, hasDiscount, expirationDate, active);
    }

    public Product(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Product() {
    }

    @Transient
    Boolean isExpired() {
        LocalDate localDate = LocalDate.now();
        Date today = Date.valueOf(localDate);
        return (expirationDate != null && expirationDate.getTime() <= today.getTime());
    }

    @Transient
    Boolean requiresInventoryAnalysis() {
        return amountAvailable <= 2;
    }
}

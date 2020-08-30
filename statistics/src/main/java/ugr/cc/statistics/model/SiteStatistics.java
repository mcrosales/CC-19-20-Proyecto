package ugr.cc.statistics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class SiteStatistics {

    @Id
    @GeneratedValue
    private Integer id;
    private Double totalProducts;
    private Double netInventoryValue;
    private Double totalVendors;
    private Date measurementDay;
    private Time measurementTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Double totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Double getNetInventoryValue() {
        return netInventoryValue;
    }

    public void setNetInventoryValue(Double netInventoryValue) {
        this.netInventoryValue = netInventoryValue;
    }

    public Double getTotalVendors() {
        return totalVendors;
    }

    public void setTotalVendors(Double totalVendors) {
        this.totalVendors = totalVendors;
    }

    public Date getMeasurementDay() {
        return measurementDay;
    }

    public void setMeasurementDay(Date measurementDay) {
        this.measurementDay = measurementDay;
    }

    public Time getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Time measurementTime) {
        this.measurementTime = measurementTime;
    }
}

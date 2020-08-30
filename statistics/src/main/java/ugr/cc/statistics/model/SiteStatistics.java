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
    private Integer totalProducts;
    private Double netInventoryValue;
    private Integer totalVendors;
    private Date measurementDay;
    private Time measurementTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNetInventoryValue() {
        return netInventoryValue;
    }

    public void setNetInventoryValue(Double netInventoryValue) {
        this.netInventoryValue = netInventoryValue;
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

    public SiteStatistics(Integer totalProducts, Double netInventoryValue, Integer totalVendors, Date measurementDay,
                          Time measurementTime) {
        this.totalProducts = totalProducts;
        this.netInventoryValue = netInventoryValue;
        this.totalVendors = totalVendors;
        this.measurementDay = measurementDay;
        this.measurementTime = measurementTime;
    }
}

package ugr.cc.statistics.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ugr.cc.statistics.model.SiteStatistics;

import java.sql.Date;
import java.util.List;

public interface StatsRepository extends PagingAndSortingRepository<SiteStatistics, Integer> {

    List<SiteStatistics> findAllOrderById();

    List<SiteStatistics> findAllByMeasurementDayGreaterThanEqualOrderById(Date date);

    List<SiteStatistics> findAllByMeasurementDayLessThanEqualOrderById(Date date);

    List<SiteStatistics> findAllByMeasurementDayGreaterThanEqualOrMeasurementDayLessThanEqualOrderById(Date firstDate, Date secondDate);
}

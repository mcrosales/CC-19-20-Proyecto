package ugr.cc.statistics.service;


import org.springframework.stereotype.Service;
import ugr.cc.statistics.model.SiteStatistics;
import ugr.cc.statistics.repository.StatsRepository;

import java.sql.Date;
import java.util.List;

@Service
public class SiteStatisticsService {

    private final StatsRepository statsRepository;

    public SiteStatisticsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<SiteStatistics> retrieveHistoricalData(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            return statsRepository.findAllByMeasurementDayGreaterThanEqualOrMeasurementDayLessThanEqualOrderById(
                    firstDate, secondDate);
        }
        if (firstDate != null) {
            return statsRepository.findAllByMeasurementDayGreaterThanEqualOrderById(firstDate);
        }
        if (secondDate != null) {
            return statsRepository.findAllByMeasurementDayLessThanEqualOrderById(secondDate);
        }
        return statsRepository.findAllOrderById();
    }
}

package ugr.cc.statistics.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ugr.cc.statistics.model.SiteStatistics;
import ugr.cc.statistics.service.SiteStatisticsService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class StatisticsController {

    private final SiteStatisticsService statisticsService;

    public StatisticsController(SiteStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * GET /historical -> retrieves site statistics using date filters
     *
     * @return List<Product>
     */
    @GetMapping("/historical")
    ResponseEntity<List<SiteStatistics>> retrieveHistoricalStatistics(
            @RequestParam(value = "firstDate", required = false) Date firstDate,
            @RequestParam(value = "secondDate", required = false) Date secondDate) {

        List<SiteStatistics> siteStatistics = statisticsService.retrieveHistoricalData(firstDate, secondDate);

        return new ResponseEntity<>(siteStatistics, HttpStatus.OK);
    }
}

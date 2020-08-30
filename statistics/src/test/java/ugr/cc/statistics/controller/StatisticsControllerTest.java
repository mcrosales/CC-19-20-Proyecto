package ugr.cc.statistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ugr.cc.statistics.model.SiteStatistics;
import ugr.cc.statistics.service.SiteStatisticsService;
import ugr.cc.statistics.util.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class StatisticsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SiteStatisticsService statisticsService;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Test getting historical data through GET method
     */
    @Test
    void retrieveHistoricalStatistics() throws Exception {
        List<SiteStatistics> statistics = new ArrayList<SiteStatistics>(2);
        statistics.add(new SiteStatistics(32,1200.00,21,
                GeneralUtils.sqlToday(),GeneralUtils.sqlNow()));
        statistics.add(new SiteStatistics(45,1500.70,
                32, GeneralUtils.sqlToday(),GeneralUtils.sqlNow()));
        Mockito.when(statisticsService.retrieveHistoricalData(null,null)).thenReturn(statistics);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/historical")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());

    }
}
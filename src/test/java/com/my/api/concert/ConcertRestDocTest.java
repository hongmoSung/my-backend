package com.my.api.concert;

import com.my.api.RestDocTestSupport;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConcertRestDocTest extends RestDocTestSupport {

    @Test
    void getAvailableDates() throws Exception {
        mockMvc.perform(
                        get("/concerts/{concertId}/available-dates", "concertId")
                )
                .andExpect(status().isOk())
                .andDo(document("available-dates", pathParameters(
                        parameterWithName("concertId").description("concert ID")
                )));
    }

    @Test
    void getRemainSeats() throws Exception {
        mockMvc.perform(
                        get("/concerts/{concertId}/{dateId}/remain-seats",
                                "concertId", "dateId")
                )
                .andExpect(status().isOk())
                .andDo(document("get-remain-seats", pathParameters(
                        parameterWithName("concertId").description("concert ID"),
                        parameterWithName("dateId").description("date ID")
                )));
    }

    @Test
    void doReserve() throws Exception {
        mockMvc.perform(
                        post("/concerts/{concertId}/{dateId}/reserve",
                                "concertId", "dateId")
                )
                .andExpect(status().isOk())
                .andDo(document("do reserve", pathParameters(
                        parameterWithName("concertId").description("concert ID"),
                        parameterWithName("dateId").description("date ID")
                )));
    }

    @Override
    protected Object initController() {
        return new ConcertController();
    }
}

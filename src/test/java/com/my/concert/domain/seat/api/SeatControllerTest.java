package com.my.concert.domain.seat.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.my.RestDocTestSupport;
import com.my.concert.domain.seat.api.dto.ResEnableSeat;
import com.my.concert.domain.seat.api.dto.ResRemainSeats;
import com.my.concert.domain.seat.domain.SeatService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

class SeatControllerTest extends RestDocTestSupport {

    private final SeatService seatService = mock(SeatService.class);

    @Override
    protected Object initController() {
        return new SeatController(seatService);
    }

    @Test
    void getAvailableDates() throws Exception {
        List<ResRemainSeats> response = List.of(
            ResRemainSeats.builder()
                .date(LocalDate.of(2021, 1, 1))
                .count(1)
                .build(),
            ResRemainSeats.builder()
                .date(LocalDate.of(2021, 1, 2))
                .count(1)
                .build()
        );

        given(seatService.getRemainSeats(1L)).willReturn(response);

        mockMvc.perform(
                get("/api/v1/concerts/{concertId}/remain-seats", 1L)
            )
            .andExpect(status().isOk())
            .andDo(document("available-dates",
                pathParameters(
                    parameterWithName("concertId").description("콘서트 ID")),
                responseFields(
                    fieldWithPath(".[].date").type(JsonFieldType.STRING).description("날짜"),
                    fieldWithPath(".[].count").type(JsonFieldType.NUMBER).description("잔여 좌석 수")
                )
            ));
    }

    @Test
    void getRemainSeats() throws Exception {
        List<ResEnableSeat> res = List.of(
            new ResEnableSeat(1L, 1),
            new ResEnableSeat(2L, 2)
        );
        given(seatService.getRemainSeatsByDate(1L, "2020-01-03")).willReturn(res);

        mockMvc.perform(
                get("/api/v1/concerts/{concertId}/remain-seats/{date}", 1L, "2020-01-03")
            )
            .andExpect(status().isOk())
            .andDo(document("get-remain-seats", pathParameters(
                    parameterWithName("concertId").description("콘서트 아이디"),
                    parameterWithName("date").description("날짜")),
                responseFields(
                    fieldWithPath(".[].id").type(JsonFieldType.NUMBER).description("좌석 id"),
                    fieldWithPath(".[].no").type(JsonFieldType.NUMBER).description("좌석 번호")
                )
            ));
    }
}
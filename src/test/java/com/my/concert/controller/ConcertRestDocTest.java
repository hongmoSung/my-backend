package com.my.concert.controller;

import com.my.RestDocTestSupport;
import com.my.concert.controller.dto.CreateConcertDto;
import com.my.concert.controller.dto.ResConcertDto;
import com.my.concert.controller.dto.ResRemainSeats;
import com.my.concert.controller.dto.ReserveDto;
import com.my.concert.service.ConcertService;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseBody;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConcertRestDocTest extends RestDocTestSupport {

    private final ConcertService concertService = mock(ConcertService.class);

    @Override
    protected Object initController() {
        return new ConcertController(concertService);
    }

    @Test
    public void createConcert() throws Exception {
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = start.plusDays(10);
        CreateConcertDto request = new CreateConcertDto();
        request.setName("test");
        request.setStartDate(start);
        request.setEndDate(end);

        mockMvc.perform(
                        post("/api/v1/concerts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(
                        document("create-concert",
                                requestFields(
                                        fieldWithPath("name").description("콘서트 이름"),
                                        fieldWithPath("startDate").description("콘서트 시작일"),
                                        fieldWithPath("endDate").description("콘서트 종료일")
                                )
                        )
                );
    }

    @Test
    void getAvailableDates() throws Exception {

        ResConcertDto resConcertDto = ResConcertDto.builder()
            .name("test")
            .availableDates(Arrays.asList(
                LocalDate.of(2021, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.of(2021, 1, 2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            ))
            .build();

        given(concertService.getAvailableDates(1L)).willReturn(resConcertDto);

        mockMvc.perform(
                        get("/api/v1/concerts/{concertId}/available-dates", 1L)
                )
                .andExpect(status().isOk())
                .andDo(document("available-dates",
                    pathParameters(
                        parameterWithName("concertId").description("concert ID")),
                    responseFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("콘서트 이름"),
                        fieldWithPath("availableDates").type(JsonFieldType.ARRAY).description("콘서트 가능 날짜")
                    )
                ));
    }

    @Test
    void getRemainSeats() throws Exception {

        given(concertService.getRemainSeats(any(), any())).willReturn(
            ResRemainSeats.builder()
                .date("2020-01-03")
                .seatNumbers(Arrays.asList(1, 2, 3))
                .build()
        );

        mockMvc.perform(
                get("/api/v1/concerts/{concertId}/{date}/remain-seats", 1L, "2020-01-03")
            )
            .andExpect(status().isOk())
            .andDo(document("get-remain-seats", pathParameters(
                parameterWithName("concertId").description("콘서트 아이디"),
                parameterWithName("date").description("날짜")),
                responseFields(
                    fieldWithPath("date").type(JsonFieldType.STRING).description("콘서트 날짜"),
                    fieldWithPath("seatNumbers").type(JsonFieldType.ARRAY).description("좌석 번호")
                )
            ));
    }

    @Test
    void doReserve() throws Exception {
        ReserveDto request = new ReserveDto();
        request.setUserId(UUID.randomUUID());
        request.setConcertId(1L);
        request.setDate("2020-01-03");
        request.setSeatNumber(1);

        mockMvc.perform(
                post("/api/v1/concerts/reserve")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isCreated())
            .andDo(document("reserve",
                requestFields(
                    fieldWithPath("userId").description("유저 ID"),
                    fieldWithPath("concertId").description("콘서트 ID"),
                    fieldWithPath("date").description("선택한 날짜"),
                    fieldWithPath("seatNumber").description("좌석 번호")
                ))
            );
    }
}

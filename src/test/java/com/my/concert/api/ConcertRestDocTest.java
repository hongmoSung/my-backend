package com.my.concert.api;

import com.my.RestDocTestSupport;
import com.my.concert.api.dto.ReqCreateConcertDto;
import com.my.concert.app.ConcertFacade;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConcertRestDocTest extends RestDocTestSupport {

	private final ConcertFacade concertFacade = mock(ConcertFacade.class);

	@Override
	protected Object initController() {
		return new ConcertController(concertFacade);
	}

	@Test
	public void createConcert() throws Exception {
		LocalDate start = LocalDate.of(2023, 1, 1);
		LocalDate end = start.plusDays(10);
		ReqCreateConcertDto request = new ReqCreateConcertDto();
		request.setName("test");
		request.setStartDate(start);
		request.setEndDate(end);

		mockMvc
			.perform(post("/api/v1/concerts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(document("create-concert",
					requestFields(fieldWithPath("name").type(JsonFieldType.STRING).description("콘서트 이름"),
							fieldWithPath("startDate").type(JsonFieldType.STRING).description("콘서트 시작일"),
							fieldWithPath("endDate").type(JsonFieldType.STRING).description("콘서트 종료일"))));
	}

}

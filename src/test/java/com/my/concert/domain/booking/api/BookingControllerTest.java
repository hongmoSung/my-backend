package com.my.concert.domain.booking.api;

import com.my.RestDocTestSupport;
import com.my.concert.domain.booking.api.dto.RequestBookingDto;
import com.my.concert.domain.booking.app.BookingFacade;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingControllerTest extends RestDocTestSupport {

	private final BookingFacade bookingFacade = mock(BookingFacade.class);

	@Override
	protected Object initController() {
		return new BookingController(bookingFacade);
	}

	@Test
	void booking() throws Exception {

		String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVXVpZCI6Ij";

		RequestBookingDto requestBody = new RequestBookingDto();

		mockMvc
			.perform(post("/api/v1/booking").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestBody))
				.header("Authorization", authorization))
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(document("booking",
					requestFields(fieldWithPath("seatId").type(JsonFieldType.NUMBER).description("좌석 id"))));

	}

}
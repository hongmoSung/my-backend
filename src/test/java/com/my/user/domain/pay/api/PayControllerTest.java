package com.my.user.domain.pay.api;

import com.my.RestDocTestSupport;
import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import com.my.user.domain.pay.app.PayFacade;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.math.BigInteger;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PayControllerTest extends RestDocTestSupport {

    private final PayFacade payFacade = mock(PayFacade.class);

    @Override
    protected Object initController() {
        return new PayController(payFacade);
    }

    @Test
    void getBalance() throws Exception {
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVXVpZCI6IjUxNTk1ZDgyLT";
        ResPayDto res = ResPayDto.builder()
                .email("a@a.com")
                .money(BigInteger.ZERO)
                .build();
        given(payFacade.getBalance(authorization)).willReturn(res);

        mockMvc.perform(
                        get("/api/v1/pays/balance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", authorization)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("get-balance",
                                responseFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
                                        fieldWithPath("money").type(JsonFieldType.NUMBER).description("잔액")
                                )
                        )
                );
    }

    @Test
    void reCharge() throws Exception {
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVXVpZCI6IjUxNTk1ZDgyLTU5ZjctNDk2";
        ReqChargeDto requestBody = new ReqChargeDto();

        mockMvc.perform(
                        put("/api/v1/pays/balance-recharge")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", authorization)
                                .content(objectMapper.writeValueAsString(requestBody))
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document("balance-recharge",
                                requestFields(
                                        fieldWithPath("chargeAmount").type(JsonFieldType.NUMBER).description("충전할 금액")
                                )
                        )
                );
    }

    @Test
    void pay() throws Exception {

        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVXVpZCI6IjUxNTk1ZDgyLTU5ZjctNDk2";
        ReqChargeDto requestBody = new ReqChargeDto();

        mockMvc.perform(
                        put("/api/v1/pays/{bookingId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", authorization)
                                .content(objectMapper.writeValueAsString(requestBody))
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "payment",
                                pathParameters(
                                        parameterWithName("bookingId").description("예약 id")
                                )
                        )
                );
    }

}
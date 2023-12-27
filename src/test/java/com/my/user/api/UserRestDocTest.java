package com.my.user.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.my.RestDocTestSupport;
import com.my.user.api.dto.ReqTokenDto;
import com.my.user.app.UserFacade;
import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

class UserRestDocTest extends RestDocTestSupport {

    private final UserFacade userFacade = mock(UserFacade.class);

    @Override
    protected Object initController() {
        return new UserController(userFacade);
    }

    @Test
    void getToken() throws Exception {

        ReqTokenDto requestBody = new ReqTokenDto();
        requestBody.setEmail("a@a.com");

        given(userFacade.getToken(requestBody.getEmail())).willReturn("adasdasdsad");

        mockMvc.perform(
                post("/api/v1/users/tokens")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(
                document("get-user-token",
                    requestFields(
                        fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
                    ),
                    responseFields(
                        fieldWithPath("token").type(JsonFieldType.STRING).description("token 값"),
                        fieldWithPath("type").type(JsonFieldType.STRING).description("토큰 타입")
                    )
                )
            );
    }

    @Test
    void getBalance() throws Exception {
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVXVpZCI6IjUxNTk1ZDgyLTU5ZjctNDk2Zi05NzQ2LTAxNDViZjJiYWU2OCIsImVtYWlsIjoiYUBhLmNvbSIsIm51bWJlciI6MCwiZXhwaXJlQXQiOjE3MDM2MTkxMTl9.X1YQDi5RU_3j_8SeYh2IKu2P9B1IcHyjEn2ciYGQp_I";
        ResPayDto res = ResPayDto.builder()
            .email("a@a.com")
            .money(BigInteger.ZERO)
            .build();
        given(userFacade.getBalance(authorization))
            .willReturn(res);

        mockMvc.perform(
                get("/api/v1/users/balance")
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
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVXVpZCI6IjUxNTk1ZDgyLTU5ZjctNDk2Zi05NzQ2LTAxNDViZjJiYWU2OCIsImVtYWlsIjoiYUBhLmNvbSIsIm51bWJlciI6MCwiZXhwaXJlQXQiOjE3MDM2MTkxMTl9.X1YQDi5RU_3j_8SeYh2IKu2P9B1IcHyjEn2ciYGQp_I";
        ReqChargeDto requestBody = new ReqChargeDto();

        mockMvc.perform(
                put("/api/v1/users/balance-recharge")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorization)
                    .content(objectMapper.writeValueAsString(requestBody))
            )
            .andDo(print())
            .andExpect(status().isNoContent())
            .andDo(
                document("get-balance",
                    requestFields(
                        fieldWithPath("chargeAmount").type(JsonFieldType.NUMBER).description("충전할 금액")
                    )
                )
            );
    }
}

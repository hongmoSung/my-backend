package com.my.user.api;

import com.my.RestDocTestSupport;
import com.my.user.api.dto.ReqTokenDto;
import com.my.user.api.dto.ResTokenDto;
import com.my.user.app.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        ResTokenDto resTokenDto = new ResTokenDto("adasdasdsad");

        given(userFacade.getTokenString(requestBody.getEmail())).willReturn(resTokenDto);

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
}

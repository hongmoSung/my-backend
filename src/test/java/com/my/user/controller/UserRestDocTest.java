package com.my.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.my.RestDocTestSupport;
import com.my.user.service.Token;
import com.my.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


class UserRestDocTest extends RestDocTestSupport {

    private final UserService userService = mock(UserService.class);

    @Override
    protected Object initController() {
        return new UserController(userService);
    }

    @Test
    void getToken() throws Exception {

        ResTokenDto resTokenDto = ResTokenDto.builder()
            .token("adsnaskdnakjnvfa;efjnwv;kaejnw;kaefjnwck;aejnwc;aejnw")
            .build();

        given(userService.getToken(any(Token.class))).willReturn(resTokenDto);

        TokenReqDto requestBody = new TokenReqDto();
        requestBody.setEmail("a@a.com");

        mockMvc.perform(
                        post("/api/v1/users/tokens")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBody))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("get-user-token",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").description("email")
                                ),
                                responseFields(
                                        fieldWithPath("type").description("token type"),
                                        fieldWithPath("token").description("token")
                                )
                        )
                );
    }
}

package com.my.api.user;

import com.my.api.RestDocTestSupport;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserRestDocTest extends RestDocTestSupport {

    @Test
    void getToken() throws Exception {
        mockMvc.perform(
                        get("/users/{userId}/token", "test")
                )
                .andExpect(status().isOk())
                .andDo(document("get-user-token", pathParameters(
                        parameterWithName("userId").description("user ID")
                )));
    }

    @Override
    protected Object initController() {
        return new UserController();
    }
}

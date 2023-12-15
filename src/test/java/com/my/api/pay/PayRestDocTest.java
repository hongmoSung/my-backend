package com.my.api.pay;

import com.my.api.RestDocTestSupport;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PayRestDocTest extends RestDocTestSupport {

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(
                        get("/pays/balance")
                )
                .andExpect(status().isOk())
                .andDo(document("get-pays-balance"));
    }

    @Test
    public void doCharge() throws Exception {
        mockMvc.perform(
                        post("/pays/charge")
                )
                .andExpect(status().isOk())
                .andDo(document("post-pays-charge"));
    }

    @Test
    public void doPayment() throws Exception {
        mockMvc.perform(
                        post("/pays/payment")
                )
                .andExpect(status().isOk())
                .andDo(document("post-pays-payment"));

    }

    @Override
    protected Object initController() {
        return new PayController();
    }
}

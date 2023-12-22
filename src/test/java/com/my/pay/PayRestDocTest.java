package com.my.pay;

import com.my.RestDocTestSupport;
import com.my.pay.controller.PayController;
import com.my.pay.controller.PaymentDto;
import com.my.pay.controller.ReqChargeDto;
import com.my.pay.controller.ResWalletDto;
import com.my.pay.service.PayService;
import com.my.user.service.UserService;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PayRestDocTest extends RestDocTestSupport {

    private final PayService payService = mock(PayService.class);

    @Override
    protected Object initController() {
        return new PayController(payService);
    }

    @Test
    void getBalance() throws Exception {

        ResWalletDto response = ResWalletDto.builder()
            .email("a@a.com")
            .money(new BigInteger("0"))
            .build();

        given(payService.getBalance(any()))
            .willReturn(response);

        mockMvc.perform(
                get("/api/v1/pays/balance")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(document("get-pays-balance", responseFields(
                fieldWithPath("email").description("email"),
                fieldWithPath("money").description("money")
            )));
    }

    @Test
    public void doCharge() throws Exception {

        ReqChargeDto reqChargeDto = new ReqChargeDto();

        mockMvc.perform(
                put("/api/v1/pays/balance-recharge")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(reqChargeDto))
            )
            .andExpect(status().isNoContent())
            .andDo(document("post-pays-charge", requestFields(
                fieldWithPath("chargeAmount").description("chargeAmount")
            )));
    }

    @Test
    public void doPayment() throws Exception {

        PaymentDto request = new PaymentDto();

        mockMvc.perform(
                put("/api/v1/pays/payment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isNoContent())
            .andDo(document("post-pays-payment"));

    }
}

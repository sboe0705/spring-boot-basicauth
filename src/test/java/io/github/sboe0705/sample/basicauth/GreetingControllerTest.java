package io.github.sboe0705.sample.basicauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGreetWithoutAuthentication() throws Exception {
        // when
        mockMvc.perform(get("/api/greet"))

                // then
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void testGreetWithUser() throws Exception {
        // when
        mockMvc.perform(get("/api/greet")
                        .with(httpBasic("user", "password"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, user!"));
    }

}

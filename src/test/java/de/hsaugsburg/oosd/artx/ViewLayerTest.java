package de.hsaugsburg.oosd.artx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ViewLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")));
    }

    @Test
    void shouldReturnRegisterPage() throws Exception {
        mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Mitglied werden")));
    }

    @Test
    @WithMockUser
    void shouldReturnFAQPage() throws Exception {
        mockMvc.perform(get("/faq")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Fragen")));
    }

    @Test
    @WithMockUser
    void shouldReturnAboutPage() throws Exception {
        mockMvc.perform(get("/about")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Wir sind ArtX")));
    }
}



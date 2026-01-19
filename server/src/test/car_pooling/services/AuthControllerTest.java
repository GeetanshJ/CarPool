package car_pooling.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import car_pooling.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void register_ok() throws Exception {
        mockMvc.perform(post("/auth/register")
                .contentType("application/json")
                .content("{\"name\":\"A\",\"email\":\"a@gmail.com\",\"password\":\"123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void login_ok() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content("{\"email\":\"a@gmail.com\",\"password\":\"123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void register_bad_request() throws Exception {
        mockMvc.perform(post("/auth/register"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void wrong_url() throws Exception {
        mockMvc.perform(post("/auth/loginn"))
                .andExpect(status().isNotFound());
    }

    @Test
    void login_missing_body() throws Exception {
        mockMvc.perform(post("/auth/login"))
                .andExpect(status().isBadRequest());
    }
}

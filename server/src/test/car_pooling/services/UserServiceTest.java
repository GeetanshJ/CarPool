package car_pooling.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import car_pooling.models.User;
import car_pooling.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @InjectMocks
    UserService userService;

    @Test
    void register_success() {
        User u = new User();
        u.setPassword("123");
        when(encoder.encode("123")).thenReturn("hashed");
        when(userRepository.save(any())).thenReturn(u);
        assertNotNull(userService.register(u));
    }

    @Test
    void login_success() {
        User u = new User();
        u.setPassword("hashed");
        when(userRepository.findByEmail("a@gmail.com")).thenReturn(u);
        when(encoder.matches("123", "hashed")).thenReturn(true);
        assertNotNull(userService.login("a@gmail.com", "123"));
    }

    @Test
    void login_wrong_password() {
        User u = new User();
        u.setPassword("hashed");
        when(userRepository.findByEmail("a@gmail.com")).thenReturn(u);
        when(encoder.matches("111", "hashed")).thenReturn(false);
        assertNull(userService.login("a@gmail.com", "111"));
    }

    @Test
    void login_user_not_found() {
        when(userRepository.findByEmail("x@gmail.com")).thenReturn(null);
        assertNull(userService.login("x@gmail.com", "123"));
    }

    @Test
    void password_encoded_on_register() {
        User u = new User();
        u.setPassword("123");
        when(encoder.encode("123")).thenReturn("hashed");
        userService.register(u);
        verify(encoder).encode("123");
    }
}

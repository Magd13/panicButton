package alarm.community.panicbutton;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import alarm.community.panicbutton.utils.PasswordEncoderUtil;

@SpringBootTest
class PanicButtonAppApplicationTests {

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Test
    void contextLoads() {
        assertNotNull(passwordEncoderUtil, "PasswordEncoderUtil should be autowired.");
    }
}

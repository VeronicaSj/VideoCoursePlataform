package com.example.apirest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.example.apirest.Model.User;

@JsonTest
class UserJsonTest {

    @Autowired
    private JacksonTester<User> json;

    @Test
    void userSerializationTest() throws IOException {
        User user = new User("",null,null,null,null,null);
        assertThat(json.write(user)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(user)).hasJsonPathStringValue("@.name");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.name")
            .isEqualTo("");
    }

    @Test
    void userDeserializationTest() throws IOException {
    String expected = """
            {
                "name": "",
                "mail": null,
                "date": null,
                "img": null,
                "type": null,
                "lastLog": null
            }
            """;
    assertThat(json.parse(expected))
            .isEqualTo(new User("",null,null,null,null,null));
    assertThat(json.parseObject(expected).getName()).isEqualTo("");
    }
}
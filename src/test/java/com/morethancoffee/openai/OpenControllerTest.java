package com.morethancoffee.openai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class OpenControllerTest {
    private OpenController openController;
    private final OpenAiService openAiService = mock(OpenAiService.class);

    @BeforeEach
    public void setUp(){
        openController = new OpenController(openAiService);
    }

    @Test
    void testHealthWorks(){
        ResponseEntity<String> result = openController.getHealth();
        assertThat (result.toString()).isEqualTo("I am available");
    }
}

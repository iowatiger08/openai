package com.morethancoffee.openai;

import com.azure.ai.openai.OpenAIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class OpenAiServiceTests {

    private OpenAiService openAiService;
    private final OpenAIClient openAIClient = mock(OpenAIClient.class);

    @BeforeEach
    void setUp(){
        openAiService = new OpenAiService(openAIClient);
    }

    @Test
    void postValidFileAndgetValidStringTest() throws IOException {
        Path file = Files.createFile(Paths.get("any"));

        doReturn("This is a test").when(openAiService).getTextFromSoundFile(file.toFile());
       //  when ( openAiService)..getTextFromSoundFile(any(File.class))).thenReturn("This is a test");
        File testFile = mock(File.class);

        String result = openAiService.getTextFromSoundFile(testFile);
        assertThat (result).startsWith("This is a test");
    }

    @Test
    void postValidStringAndGetValidFileTest() throws IOException {
        String file = "This is a test and only a test";
        File testFile = mock(File.class);
        doReturn(testFile).when(openAiService).getSpokenFromText(file);
        //  when ( openAiService)..getTextFromSoundFile(any(File.class))).thenReturn("This is a test");
        File result = openAiService.getSpokenFromText(file);
        assertThat (result).isFile();
    }
}

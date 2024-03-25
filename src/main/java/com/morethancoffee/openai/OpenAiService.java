package com.morethancoffee.openai;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.SpeechGenerationOptions;
import com.azure.ai.openai.models.SpeechVoice;
import com.azure.core.util.BinaryData;
import com.morethancoffee.openai.model.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OpenAiService {

    private final OpenAIClient openAIClient;

    @Autowired
    public OpenAiService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public Result getBasicResult() {
        // open AI service
        //get result
        //verify result is good

        return null;
    }

    private void openAIService(){
        // look up AI service

    }

    public String getTextFromSoundFile(File aSoundFile){
        // post file to AI
        openAIService();
        String text = postFileToService(aSoundFile);
        return text;
    }

    private String postFileToService(File aSoundFile) {
        return "";
    }

    public File getSpokenFromText(@Valid String someText) throws IOException {
        openAIService();
        File receivedFile = submitText(someText);
        return verifyFile(receivedFile);
    }

    private File submitText(String someText) throws IOException {
        BinaryData result = openAIClient.generateSpeechFromText("modelName",
                new SpeechGenerationOptions(someText, SpeechVoice.ALLOY));
        if (result.isReplayable() && result.getLength() > 0){
            return Files.createFile(Path.of("soemFile.mp3")).toFile();
        }
       return null;
    }

    private File verifyFile(File receivedFile) throws IOException {
        if (Files.isReadable(receivedFile.toPath())){
            return receivedFile;
        }
        throw new IOException("File is not readable or is corrupt");
    }
}

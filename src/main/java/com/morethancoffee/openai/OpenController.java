package com.morethancoffee.openai;

import com.morethancoffee.openai.model.Result;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@RestController
public class OpenController {

    private final OpenAiService openAiService;
    @Autowired
    public OpenController (OpenAiService openAiService){
        this.openAiService = openAiService;

    }

    @RequestMapping("/health")
    public ResponseEntity <String> getHealth (){

        return ResponseEntity.ok("I am available "
                +       checkAiServiceIsUp());
    }

    private String checkAiServiceIsUp() {
        return "with AI ready";
    }

    @RequestMapping("/hello")
    public ResponseEntity <Result> getResult (){
        Result result = openAiService.getBasicResult();
        if (result.hasBady()){
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/readwords")
    public ResponseEntity readWords( String textToRead) throws IOException {
        File result = openAiService.getSpokenFromText(textToRead);
        if (Files.isReadable(result.toPath())){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/readfile")
    public ResponseEntity<String> readFile(  @Valid File textToRead) {
        String result = openAiService.getTextFromSoundFile(textToRead);
        if (StringUtils.isNotBlank(result)){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
}

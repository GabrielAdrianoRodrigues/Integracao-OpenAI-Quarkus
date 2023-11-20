package br.com.gabrieladriano.domain.models.forms;


import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;

@Getter
public class SpeechToTextOpenAIForm {
    @NotNull
    @RestForm("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private FileUpload file;

    @NotBlank
    @RestForm("model")
    private String model;

    @RestForm("language")
    private String language;

    @RestForm("prompt")
    private String prompt;

    @RestForm("responseFormat")
    private String responseFormat;

    @RestForm("temperature")
    private Float temperature;
}

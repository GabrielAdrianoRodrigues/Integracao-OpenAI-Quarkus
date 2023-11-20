package br.com.gabrieladriano.controllers;

import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import br.com.gabrieladriano.domain.models.forms.SpeechToTextOpenAIForm;
import br.com.gabrieladriano.services.SpeechToTextService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("speech-to-text")
public class SpeechToTextController {

    @Inject
    private SpeechToTextService speechToTextService;
   
    @POST
    @Path("openai")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RestResponse<?> speechToTextOpenAi(@BeanParam @Valid SpeechToTextOpenAIForm form, @RestHeader(value = "Authorization") String token) throws Exception {
        return ResponseBuilder.ok(speechToTextService.speechToTextOpenAi(form, token)).build();
    }
}

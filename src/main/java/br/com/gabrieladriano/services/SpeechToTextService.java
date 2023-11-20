package br.com.gabrieladriano.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gabrieladriano.configs.clients.OpenAIClient;
import br.com.gabrieladriano.domain.models.dtos.SpeechToTextOpenAIDTO;
import br.com.gabrieladriano.domain.models.forms.SpeechToTextOpenAIForm;
import br.com.gabrieladriano.domain.responses.GenericResponseSingleRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SpeechToTextService {

    @Inject
    private OpenAIClient openAIClient;
    
    public GenericResponseSingleRecord<SpeechToTextOpenAIDTO> speechToTextOpenAi(SpeechToTextOpenAIForm form, String token) throws Exception {
        return new GenericResponseSingleRecord<SpeechToTextOpenAIDTO>("Tradução concluida com sucesso", "success", new ObjectMapper().readValue(openAIClient.speechToText(form, token), SpeechToTextOpenAIDTO.class));
    } 
}

package br.com.gabrieladriano.configs.clients;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;

import br.com.gabrieladriano.domain.exceptions.ClientException;
import br.com.gabrieladriano.domain.exceptions.InvalidTokenException;

//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.gabrieladriano.domain.models.forms.SpeechToTextOpenAIForm;
import br.com.gabrieladriano.utils.ClientUtlis;
import io.smallrye.config.ConfigMapping;

//@RegisterRestClient(configKey = "openai-client")
@ConfigMapping(prefix = "quarkus.rest-client.openai-client")
public interface OpenAIClient {
    String url();

    default String speechToText(SpeechToTextOpenAIForm form, String token) throws ClientException, InvalidTokenException {
        final String boundary = UUID.randomUUID().toString();
        try {
            return HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create(String.format("%s/%s", url(), "v1/audio/transcriptions")))
                .POST(ClientUtlis.createFormData(form, boundary))
                .header("Content-Type", String.format("multipart/form-data; boundary=%s", boundary))
                .header("Authorization", token)
            .build(), BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new ClientException("Erro ao enviar a requisição para o cliente speechToText", e.getCause());
        } catch (NullPointerException e) {
            throw new InvalidTokenException("Token nulo", e);
        }
    }
}



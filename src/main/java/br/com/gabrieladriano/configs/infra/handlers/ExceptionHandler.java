package br.com.gabrieladriano.configs.infra.handlers;

import br.com.gabrieladriano.domain.exceptions.ClientException;
import br.com.gabrieladriano.domain.exceptions.InvalidTokenException;
import br.com.gabrieladriano.domain.responses.GenericResponse;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        return switch (exception) {
            case NotAllowedException e -> Response.status(Status.METHOD_NOT_ALLOWED).entity(new GenericResponse("Este metodo não é suportado", "error")).build();
            case NotSupportedException e -> Response.status(Status.UNSUPPORTED_MEDIA_TYPE).entity(new GenericResponse("Registro não encontrado", "error")).build();
            case ClientException e -> Response.status(Status.NOT_FOUND).entity(new GenericResponse("API parceira com problemas", "error")).build();
            case InvalidTokenException e -> Response.status(Status.NOT_FOUND).entity(new GenericResponse("TOKEN inválido", "error")).build();
            case Exception e -> Response.status(Status.INTERNAL_SERVER_ERROR).entity(new GenericResponse("Ocorreu um erro interno", "error")).build();
            default -> Response.status(418).entity(new GenericResponse("Erro não catálogado", "error")).build();
        };
    }
}

package br.com.gabrieladriano.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class GenericResponse {
    private String message;
    private String status;
}

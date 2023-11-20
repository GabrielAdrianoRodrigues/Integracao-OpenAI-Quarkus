package br.com.gabrieladriano.domain.responses;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GenericResponseSingleRecord<T> extends GenericResponse implements Serializable {
    private T record;

    public GenericResponseSingleRecord(String message, String status, T record) {
        super(message, status);
        this.record = record;
    }
}

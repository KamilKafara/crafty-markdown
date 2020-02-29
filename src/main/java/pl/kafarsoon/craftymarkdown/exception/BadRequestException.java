package pl.kafarsoon.craftymarkdown.exception;

import lombok.Getter;
import pl.kafarsoon.craftymarkdown.exception.handler.FieldInfo;

import java.util.Collection;
import java.util.Collections;

@Getter
public class BadRequestException extends RuntimeException {
    private Collection<FieldInfo> fields;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, FieldInfo fieldInfo) {
        super(message);
        this.fields = Collections.singletonList(fieldInfo);
    }
}

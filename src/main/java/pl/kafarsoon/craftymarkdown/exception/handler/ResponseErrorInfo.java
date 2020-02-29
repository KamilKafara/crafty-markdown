package pl.kafarsoon.craftymarkdown.exception.handler;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ResponseErrorInfo {
    private String message;
    private Collection<FieldInfo> fields;
}

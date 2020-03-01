package pl.kafarsoon.craftymarkdown.exception.handler;

import lombok.NoArgsConstructor;
import pl.kafarsoon.craftymarkdown.exception.BadRequestException;
import pl.kafarsoon.craftymarkdown.exception.NotFoundException;

@NoArgsConstructor
public class ErrorsUtils {
    public static void notFoundException(String message) {
        throw new NotFoundException(message);
    }

    public static void notFoundException(String message, FieldInfo fieldInfo) {
        throw new NotFoundException(message, fieldInfo);
    }

    public static void badRequestException(String message) {
        throw new BadRequestException(message);
    }

    public static void badRequestException(String message, FieldInfo fieldInfo) {
        throw new BadRequestException(message, fieldInfo);
    }
}

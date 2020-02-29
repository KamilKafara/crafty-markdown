package pl.kafarsoon.craftymarkdown.exception.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kafarsoon.craftymarkdown.exception.BadRequestException;
import pl.kafarsoon.craftymarkdown.exception.NotFoundException;

@Log4j2
@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseErrorInfo> handleNotFoundException(NotFoundException ex) {
        ResponseErrorInfo errorInfo = ResponseErrorInfo.builder()
                .message(ex.getMessage())
                .fields(ex.getFields())
                .build();
        log.error(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseErrorInfo> handleBadRequestException(BadRequestException ex) {
        ResponseErrorInfo errorInfo = ResponseErrorInfo.builder()
                .message(ex.getMessage())
                .fields(ex.getFields())
                .build();
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }
}

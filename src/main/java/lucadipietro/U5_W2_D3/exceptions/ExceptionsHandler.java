package lucadipietro.U5_W2_D3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayLoad handleBadRequest(BadRequestException ex){
        return new ErrorsPayLoad(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayLoad handleNotFound(NotFoundException ex){
        return new ErrorsPayLoad(ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayLoad handleGenericError(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayLoad("Errore lato server, verificheremo subito!",LocalDateTime.now());
    }
}

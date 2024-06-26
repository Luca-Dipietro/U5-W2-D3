package lucadipietro.U5_W2_D3.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorsPayLoad {
    private String message;
    private LocalDateTime timeStamp;
}

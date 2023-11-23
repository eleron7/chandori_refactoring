package chandori.server.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class NormalResponse {

    private final int status;
    private final String statusName;
    private final Object message;

    public static ResponseEntity<?> toResponseEntity(HttpStatus httpStatus, Object o) {
        ResponseEntity<NormalResponse> responseEntity = ResponseEntity
                .status(httpStatus)
                .body(NormalResponse.builder()
                        .status(httpStatus.value())
                        .statusName(httpStatus.name())
                        .message(o)
                        .build()
                );

        return responseEntity;
    }
}

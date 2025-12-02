package dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessageDto {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}

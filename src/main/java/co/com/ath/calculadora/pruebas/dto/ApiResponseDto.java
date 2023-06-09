package co.com.ath.calculadora.pruebas.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {

    private Object data;
    private String message;
    private HttpStatus status;
	private Integer totalPag;	
	private Integer actualPag;
    
}
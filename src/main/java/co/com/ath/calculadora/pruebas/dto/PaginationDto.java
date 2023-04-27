package co.com.ath.calculadora.pruebas.dto;

import lombok.Data;

@Data
public class PaginationDto {
    private int page;
    private int totalPages;
    private int totalElements;
    private int pageSize;
}

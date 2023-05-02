package co.com.ath.calculadora.pruebas.util;



import co.com.ath.calculadora.pruebas.dto.PaginationDto;
import co.com.ath.calculadora.pruebas.dto.ParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ParametroUtil {

    private ParametroUtil(){}

    public static ParametersEntity dtoToEntity(ParametersDto dto, ParametersEntity entity) {
        entity.setCapa(dto.getCapa());
        entity.setEstado(dto.getEstado());
        entity.setValor(dto.getValor());
        return entity;
    }


    public static List<ParametersDto> parametersDto(List<ParametersEntity> entityParameters) {
        List<ParametersDto> dto = new ArrayList<>();
        entityParameters.stream().forEach(toDto -> {

            ParametersDto dtoNew = new ParametersDto();

        });
        return dto;
    }

    public static ParametersDto mapParametersDto(ParametersEntity parametersEntity) {
        ParametersDto parametersDto = new ParametersDto();
        if (parametersEntity!=null) {
            parametersDto.setDni(parametersEntity.getDni());
            parametersDto.setCapa(parametersEntity.getCapa());
            parametersDto.setEstado(parametersEntity.getEstado());
            parametersDto.setValor(parametersEntity.getValor());
        }
        return parametersDto;
    }
    public static List<ParametersDto> mapListParametersDto(Page<ParametersEntity> parametersEntity) {
        List<ParametersDto> listParametersDto = new ArrayList<>();
        if (!parametersEntity.isEmpty()) {
            for (ParametersEntity parameters : parametersEntity.getContent()) {
                ParametersDto parametersDto = new ParametersDto();
                parametersDto.setDni(parameters.getDni());
                parametersDto.setCapa(parameters.getCapa());
                parametersDto.setEstado(parameters.getEstado());
                parametersDto.setValor(parameters.getValor());
                listParametersDto.add(parametersDto);
            }
        }
        return listParametersDto;
    }

    public static PaginationDto mapPaginationDto(Page<ParametersEntity> parametersEntity, Integer pageNo, Integer pageSize) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setTotalElements((int) parametersEntity.getTotalElements());
        paginationDto.setTotalPages(parametersEntity.getTotalPages());
        paginationDto.setPage(pageNo);
        paginationDto.setPageSize(pageSize);
        return paginationDto;
    }


}

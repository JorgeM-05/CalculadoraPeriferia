package co.com.ath.calculadora.pruebas.serviceimpl;


import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.PaginationDto;
import co.com.ath.calculadora.pruebas.dto.ParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.service.IParametersConsultarService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ParametroUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ParametersConsultarServiceImpl implements IParametersConsultarService {
    @Autowired
    private ParametersRepository parametersRepository;

    /**
     * Metodo para obtener la consulta de la tabla parametros con paginacion
     *
     * @param valor
     * @param pageNo
     * @param pageSize
     * @return ApiResponseDto
     */


    @Override
    public ApiResponseDto getParametersByValue(String valor, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of((pageNo-1), pageSize);
        log.info("buscando en Base datos valor <{}>", valor);
        Page<ParametersEntity> parametersEntity;
        if (valor.isEmpty())
            parametersEntity = parametersRepository.findAll(paging);
        else
            parametersEntity = parametersRepository.findByValor(valor, paging);

        if (parametersEntity.getContent().size()>0) {
            PaginationDto paginationDto = ParametroUtil.mapPaginationDto(parametersEntity, pageNo, pageSize);
            return ApiResponseDto.builder().data(ParametroUtil.mapPageParametersDto(parametersEntity))
                                            .totalPag(parametersEntity.getTotalPages())
                                            .actualPag(pageNo)
                                            .message(Constants.SUCESSFULL).status(HttpStatus.OK).build();
        }
        return ApiResponseDto.builder().data(null).message("no existe data").status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @Override
    public ApiResponseDto getParametersByLayer(String capa) {
        log.info("buscando en Base datos valor <{}>", capa);
        List<ParametersEntity> parametersEntity;
        if (capa.isEmpty())
            parametersEntity = (List<ParametersEntity>) parametersRepository.findAll();
        else
            parametersEntity = parametersRepository.findByCapa(capa);

        if (parametersEntity.size()>0) {
            return ApiResponseDto.builder().data(ParametroUtil.mapListParametersDto(parametersEntity))
                    .message(Constants.SUCESSFULL).status(HttpStatus.OK).build();
        }
        return ApiResponseDto.builder().data(null).message("no existe data").status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @Override
    public ApiResponseDto getId(int id) {
        log.info("buscando en Base datos valor <{}>", id);
        ParametersEntity parametersEntity = parametersRepository.findByDni(id);

        if (parametersEntity != null) {
            ParametersDto parametersDto = ParametroUtil.mapParametersDto(parametersEntity);
            return ApiResponseDto.builder().data(parametersDto).message(Constants.SUCESSFULL).status(HttpStatus.OK).build();
        }
        return ApiResponseDto.builder().data(null).message("no existe data").status(HttpStatus.BAD_REQUEST)
                .build();
    }

}

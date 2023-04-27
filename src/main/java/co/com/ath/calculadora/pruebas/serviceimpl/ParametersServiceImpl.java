package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.PaginationDto;
import co.com.ath.calculadora.pruebas.dto.ParametersDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.service.IParametersService;
import co.com.ath.calculadora.pruebas.util.Constants;
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
public class ParametersServiceImpl implements IParametersService {


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
        Pageable paging = PageRequest.of(pageNo, pageSize);
        log.info("buscando en Base datos valor <{}>", valor);
        Page<ParametersEntity> parametersEntity;
        if (valor == null)
            parametersEntity = parametersRepository.findAll(paging);
        else
            parametersEntity = parametersRepository.findByValor(valor, paging);

        if (parametersEntity != null) {
            List<ParametersDto> parametersDto = mapParametersDto(parametersEntity);
            PaginationDto paginationDto = mapPaginationDto(parametersEntity, pageNo, pageSize);
            return ApiResponseDto.builder().data(parametersDto).pagination(paginationDto).message(Constants.SUCESSFULL).status(HttpStatus.OK).build();
        }
        return ApiResponseDto.builder().data(null).message("no existe data").status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @Override
    public ApiResponseDto getParametersByLayer(String capa, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        log.info("buscando en Base datos valor <{}>", capa);
        Page<ParametersEntity> parametersEntity;
        if (capa == null)
            parametersEntity = parametersRepository.findAll(paging);
        else
            parametersEntity = parametersRepository.findByCapa(capa, paging);

        if (parametersEntity != null) {
            List<ParametersDto> parametersDto = mapParametersDto(parametersEntity);
            PaginationDto paginationDto = mapPaginationDto(parametersEntity, pageNo, pageSize);
            return ApiResponseDto.builder().data(parametersDto).pagination(paginationDto).message(Constants.SUCESSFULL).status(HttpStatus.OK).build();
        }
        return ApiResponseDto.builder().data(null).message("no existe data").status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @Override
    public ApiResponseDto createParameters(RequestParametersDto requestParametersDto) {

        ParametersEntity parametersEntity = new ParametersEntity();
        if (!requestParametersDto.getCapa().isEmpty() && !requestParametersDto.getValor().isEmpty() && !requestParametersDto.getEstado().isEmpty() && !requestParametersDto.getCapa().isEmpty())
            parametersEntity = parametersRepository.save(mapParametersEntity(requestParametersDto));

        if (parametersEntity.getDni() > 0) {
            return ApiResponseDto.builder().data(parametersEntity).message(Constants.SUCESSFULL).status(HttpStatus.CREATED).build();
        }
        return ApiResponseDto.builder().data(null).message("Error insertando en Base Datos").status(HttpStatus.CONFLICT).build();
    }

    @Override
    public ApiResponseDto updateParameters(int dni, RequestParametersDto requestParametersDto) {
        ParametersEntity parametersEntity = parametersRepository.findByDni(dni);
        log.info("param update <{}>", parametersEntity);
        if (parametersEntity != null) {
            parametersEntity.setCapa(requestParametersDto.getCapa());
            parametersEntity.setEstado(requestParametersDto.getEstado());
            parametersEntity.setValor(requestParametersDto.getValor());
            parametersEntity= parametersRepository.save(parametersEntity);
            log.info("param update <{}>",parametersEntity);

            if (parametersEntity.getDni() > 0) {
                return ApiResponseDto.builder().data(parametersEntity).message(Constants.SUCESSFULL).status(HttpStatus.CREATED).build();
            }
        }
        return ApiResponseDto.builder().data(null).message("Error actualizando en Base Datos").status(HttpStatus.CONFLICT).build();
    }

    private ParametersEntity mapParametersEntity(RequestParametersDto requestParametersDto) {
        ParametersEntity parametersEntity = new ParametersEntity();

        if (requestParametersDto != null) {
            parametersEntity.setCapa(requestParametersDto.getCapa());
            parametersEntity.setEstado(requestParametersDto.getEstado());
            parametersEntity.setValor(requestParametersDto.getValor());
        }
        return parametersEntity;
    }


    private List<ParametersDto> mapParametersDto(Page<ParametersEntity> parametersEntity) {
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

    private PaginationDto mapPaginationDto(Page<ParametersEntity> parametersEntity, Integer pageNo, Integer pageSize) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setTotalElements((int) parametersEntity.getTotalElements());
        paginationDto.setTotalPages(parametersEntity.getTotalPages());
        paginationDto.setPage(pageNo);
        paginationDto.setPageSize(pageSize);
        return paginationDto;
    }
}

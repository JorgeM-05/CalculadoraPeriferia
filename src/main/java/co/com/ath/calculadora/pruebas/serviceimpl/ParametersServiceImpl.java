package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.PaginationDto;
import co.com.ath.calculadora.pruebas.dto.ParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.service.IParametersService;
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
    public ApiResponseDto getParameters(String valor, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        log.info("buscando en Base datos valor <{}>",valor);
        Page<ParametersEntity> parametersEntity;
        if(valor == null)
            parametersEntity = parametersRepository.findAll(paging);
        else
            parametersEntity = parametersRepository.findByValor(valor,paging);

        if (parametersEntity!=null) {
            List<ParametersDto> parametersDto = mapParametersDto(parametersEntity);
            PaginationDto paginationDto = mapPaginationDto(parametersEntity,pageNo,pageSize);
            return ApiResponseDto.builder().data(parametersDto).pagination(paginationDto).message("successful").status(HttpStatus.OK).build();
        }
        return ApiResponseDto.builder().data(null).message("no existe data").status(HttpStatus.BAD_REQUEST)
                .build();
    }
    private List<ParametersDto> mapParametersDto(Page<ParametersEntity> parametersEntity){
        List<ParametersDto> listParametersDto = new ArrayList<>();
        if(!parametersEntity.isEmpty()){
            for(ParametersEntity parameters:parametersEntity.getContent()){
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

    private PaginationDto  mapPaginationDto(Page<ParametersEntity> parametersEntity,Integer pageNo,Integer pageSize) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setTotalElements((int) parametersEntity.getTotalElements());
        paginationDto.setTotalPages(parametersEntity.getTotalPages());
        paginationDto.setPage(pageNo);
        paginationDto.setPageSize(pageSize);
        return paginationDto;
    }
}

package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;

import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ParametroUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParametersConsultaServiceImplTest {

    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private ParametersConsultarServiceImpl parametersConsultarServiceImpl;

    private ParametersEntity parametersEntity;
    private Pageable page;
    Integer pag = 1;
    Integer size = 2;
    String valor = "valor";
    String capa = "capa";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        page = PageRequest.of(pag - 1, size);

        parametersEntity = new ParametersEntity();
        parametersEntity.setDni(1);
        parametersEntity.setCapa("capa");
        parametersEntity.setEstado("activo");
        parametersEntity.setValor("valor");
    }

    @Test
    public void testConsultaParametersServiceImpl() {
        List<ParametersEntity> parametersEntities = new ArrayList<>();
        parametersEntities.add(parametersEntity);

        Page<ParametersEntity> parametersEntity = new PageImpl<>(parametersEntities);

        Mockito.when(parametersRepository.findAll()).thenReturn(parametersEntity);
        Mockito.when(parametersRepository.findByValor(valor, page)).thenReturn(parametersEntity);

        ApiResponseDto expected = ApiResponseDto.builder().data(ParametroUtil.mapParametersDto(parametersEntity.getContent()))
                .message(Constants.SUCESSFULL)
                .status(HttpStatus.OK)
                .actualPag(pag)
                .totalPag(parametersEntity.getTotalPages()).build();

        ApiResponseDto resul = parametersConsultarServiceImpl.getParametersByValue(valor, pag, size);
        assertEquals(expected, resul);
    }

    @Test
    public void testConsultaNullParametersServiceImpl() {

        Mockito.when(parametersRepository.findAll()).thenReturn(null);
        Mockito.when(parametersRepository.findByValor(valor, page)).thenReturn(null);

        ApiResponseDto expected2 = ApiResponseDto.builder().data(null)
                .message("no existe data")
                .status(HttpStatus.BAD_REQUEST)
                .build();

        assertNull(expected2.getData());
    }

    @Test
    public void testConsultaParametersLayerServiceImpl() {
        List<ParametersEntity> parametersEntities = new ArrayList<>();
        parametersEntities.add(parametersEntity);

        Mockito.when(parametersRepository.findAll()).thenReturn(parametersEntities);
        Mockito.when(parametersRepository.findByCapa(capa)).thenReturn(parametersEntities);

        ApiResponseDto expected = ApiResponseDto.builder().data(ParametroUtil.mapParametersDto(parametersEntities))
                .message(Constants.SUCESSFULL)
                .status(HttpStatus.OK).build();

        ApiResponseDto resul = parametersConsultarServiceImpl.getParametersByLayer(capa);

        assertEquals(expected, resul);


        Mockito.when(parametersRepository.findAll()).thenReturn(null);
        Mockito.when(parametersRepository.findByCapa(capa)).thenReturn(null);

        ApiResponseDto expected2 = ApiResponseDto.builder().data(null)
                .message("no existe data")
                .status(HttpStatus.BAD_REQUEST)
                .build();

        assertNull(expected2.getData());
    }

    @Test
    public void testConsultaParametersByIdServiceImpl() {
        ParametersEntity parametersEntities = parametersEntity;

        Mockito.when(parametersRepository.findByDni(1)).thenReturn(parametersEntities);

        ApiResponseDto expected = ApiResponseDto.builder().data(ParametroUtil.mapParametersDto(parametersEntity))
                .message(Constants.SUCESSFULL)
                .status(HttpStatus.OK).build();

        ApiResponseDto resul = parametersConsultarServiceImpl.getId(1);

        assertEquals(expected, resul);


        Mockito.when(parametersRepository.findByDni(1)).thenReturn(null);

        ApiResponseDto expected2 = ApiResponseDto.builder().data(null)
                .message("no existe data")
                .status(HttpStatus.BAD_REQUEST)
                .build();

        assertNull(expected2.getData());
    }

}

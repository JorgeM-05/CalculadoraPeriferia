package co.com.ath.calculadora.pruebas.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;

class FabricaConsultaServiceImplTest {

	@Mock
	private IFabricaRepository fabricaRepository;

	@InjectMocks
	private FabricaConsultaServiceImpl fabricaConsultaServiceImpl;

	private FabricaEntity fabricaEntity;
	private Pageable page;
		Integer pag = 1;
		Integer size = 2;
		String nombreFabrica = "ath";
		String nombreContacto = "Veronica";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		page = PageRequest.of(pag - 1, size);
		
		fabricaEntity = new FabricaEntity();
		fabricaEntity.setDni(1);
		fabricaEntity.setNombreFabrica("prueba");
		fabricaEntity.setNombreContacto("contacto");
		fabricaEntity.setTelefono("telefono");
		fabricaEntity.setEstado("activo");
		
	}

	@Test
	void testFabricaConsultaServiceImpl() {
		List<FabricaEntity> fabricas = new ArrayList<>();
		fabricas.add(fabricaEntity);
		
		Page<FabricaEntity> fabricasPage = new PageImpl<>(fabricas);
		Mockito.when(fabricaRepository.consultarFabricasPorSimillitudes(nombreFabrica, nombreContacto, page)).thenReturn(fabricasPage);

		
		Pageable page = PageRequest.of(pag - 1, size);
		Page<FabricaEntity> existeFabrica = fabricasPage;
				
		ApiResponseDto expected = ApiResponseDto.builder().data(FabricaUtil.listDto(existeFabrica.getContent())).message(Constants.OK)
				.status(HttpStatus.OK).actualPag(pag).totalPag(existeFabrica.getTotalPages()).build();

		ApiResponseDto resul = fabricaConsultaServiceImpl.consultarFabrica(nombreFabrica, nombreContacto, pag, size);
		
		assertEquals(expected, resul);
	}
}

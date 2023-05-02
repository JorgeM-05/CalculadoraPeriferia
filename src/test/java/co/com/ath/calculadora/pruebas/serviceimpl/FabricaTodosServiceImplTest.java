package co.com.ath.calculadora.pruebas.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;

class FabricaTodosServiceImplTest {

	@Mock
	private IFabricaRepository fabricaRepository;
	
	@InjectMocks
	private FabricaTodosServiceImpl fabricaTodos;
	
	private FabricaEntity fabricaEntity;
	
	@BeforeEach
	void setUp () {
		
		MockitoAnnotations.openMocks(this);
		
		fabricaEntity = new FabricaEntity();
		fabricaEntity.setDni(1);
		fabricaEntity.setNombreFabrica("prueba");
		fabricaEntity.setNombreContacto("contacto");
		fabricaEntity.setTelefono("telefono");
		fabricaEntity.setEstado("activo");
	}
	
	@Test
	void testTodosFabrica() {
		when(fabricaRepository.findAll()).thenReturn(List.of(fabricaEntity));
		
		ApiResponseDto expected = ApiResponseDto.builder().data(FabricaUtil.listDto(List.of(fabricaEntity))).message(Constants.OK).status(HttpStatus.OK)
				.build();
		
		ApiResponseDto resul = fabricaTodos.todosFabrica();
		
		assertEquals(expected, resul);
	}
	
}

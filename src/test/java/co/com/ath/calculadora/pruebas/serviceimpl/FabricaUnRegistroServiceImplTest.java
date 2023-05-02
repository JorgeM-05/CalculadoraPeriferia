package co.com.ath.calculadora.pruebas.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ContactoDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ContactoUtil;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;

class FabricaUnRegistroServiceImplTest {

	@Mock
	private IContactoRepository contactoRepository;

	@Mock
	private IFabricaRepository fabricaRepository;

	@InjectMocks
	private FabricaUnRegistoServiceImpl unRegistroServiceImpl;

	private FabricaEntity fabricaEntity;
	private FabricaDto fabricaDto;

	private ContactoEntity contactoEntity;
	private ContactoDto contactoDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		fabricaEntity = new FabricaEntity();

		contactoEntity = new ContactoEntity();
		contactoEntity.setDni(1);
		contactoEntity.setNombre("veronica");
		contactoEntity.setCorreo("vero@gmail");
		contactoEntity.setCargo("aprendiz");
		contactoEntity.setDniFabrica(1);

		fabricaDto = FabricaUtil.entityToDto(new FabricaDto(), fabricaEntity);
	}

	@Test
	void testFabricaUnRegistro() {
		
		 when(fabricaRepository.consultarUnaFabrica(fabricaEntity.getDni())).thenReturn(fabricaEntity);
		 when(contactoRepository.consultarContactosPorFabrica(fabricaEntity.getDni())).thenReturn(List.of(contactoEntity));
		
			List<ContactoDto> listaContactos = new ArrayList<>();
			List.of(contactoEntity).stream().forEach(contactoEntity -> {
				contactoDto = ContactoUtil.entityToDto(new ContactoDto(), contactoEntity);
			    listaContactos.add(contactoDto);
			});
			fabricaDto.setContactos(listaContactos);
				ApiResponseDto expected = ApiResponseDto.builder().data(fabricaDto).message(Constants.OK).status(HttpStatus.OK).build();
		
				ApiResponseDto resul = unRegistroServiceImpl.unaFabrica(fabricaEntity.getDni());
		
		assertEquals(expected, resul);
	}
}

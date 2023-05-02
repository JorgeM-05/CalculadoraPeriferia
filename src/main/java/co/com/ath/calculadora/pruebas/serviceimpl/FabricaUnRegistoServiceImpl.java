package co.com.ath.calculadora.pruebas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ContactoDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.service.IFabricaUnRegistroService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ContactoUtil;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Veronica Jimenez Clase que realiza la consuta de una fabrica en la base de datos
 */
 
@Service
@Slf4j
public class FabricaUnRegistoServiceImpl implements IFabricaUnRegistroService{


	@Autowired
	private IContactoRepository contactoRepository;
	
	@Autowired
	private IFabricaRepository fabricaRepository;
	
	/**
	 * Metodo que  la consuta una fabrica
	 * @param dni -> para buscar la fabrica
	 * @return ApiResponseDto
	 */
	@Override
	public ApiResponseDto unaFabrica(Integer dni) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		
		FabricaEntity entityFabrica =fabricaRepository.consultarUnaFabrica(dni);

		if (entityFabrica == null)
			return ApiResponseDto.builder().data(null).message(Constants.ERROR_NO_ENCONTRADO)
					.status(HttpStatus.BAD_REQUEST).build();
		
		FabricaDto dtoFabrica = FabricaUtil.entityToDto(new FabricaDto(), entityFabrica);
		List<ContactoEntity> existentes = contactoRepository.consultarContactosPorFabrica(dni);
		List<ContactoDto> listaContactos = new ArrayList<>();
		existentes.stream().forEach(contactoEntity -> {
		    ContactoDto contactoDto = ContactoUtil.entityToDto(new ContactoDto(), contactoEntity);
		    listaContactos.add(contactoDto);
		});
		dtoFabrica.setContactos(listaContactos);

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return ApiResponseDto.builder().data(dtoFabrica).message(Constants.OK).status(HttpStatus.OK)
				.build();
	}

}

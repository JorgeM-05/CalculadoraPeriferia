package co.com.ath.calculadora.pruebas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.service.IFabricaTodosService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Veronica Jimenez Clase que realiza la consuta de todas las fabricas en la base de datos 
 */

@Service
@Slf4j
public class FabricaTodosServiceImpl implements IFabricaTodosService {

	@Autowired
	private IFabricaRepository fabricaRepository;

	/**
	 * Metodo que  la consuta de todas las fabricas
	 * @return ApiResponseDto
	 */
	@Override
	public ApiResponseDto todosFabrica() {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		List<FabricaEntity> entityFabrica = fabricaRepository.findAll();

		if (entityFabrica.isEmpty())
			return ApiResponseDto.builder().data(null).message(Constants.ERROR_NO_ENCONTRADO)
					.status(HttpStatus.BAD_REQUEST).build();

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return ApiResponseDto.builder().data(FabricaUtil.listDto(entityFabrica)).message(Constants.OK).status(HttpStatus.OK)
				.build();
		
		
	}
}

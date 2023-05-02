package co.com.ath.calculadora.pruebas.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.TiposPruebasDto;
import co.com.ath.calculadora.pruebas.repository.ITiposPruebasRepository;
import co.com.ath.calculadora.pruebas.service.ITiposPruebasUpdateService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.TiposPruebasUtil;
import lombok.extern.slf4j.Slf4j;

/* Actualizar en la BD los tipos de pruebas
 * ABP 2023/04/27
 */

@Service
@Slf4j
public class TiposPruebasUpdateServiceImpl implements ITiposPruebasUpdateService {

	@Autowired
	private ITiposPruebasRepository tiposPruebasRepository;
	
	@Override
	public ApiResponseDto updateTipoPrueba(TiposPruebasDto tipoPruebaDto) {
		/* Ajustes
		 * ABP 2023/04/29
		 */

		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		var entityVerify = tiposPruebasRepository.findById(tipoPruebaDto.getDni());
		var entity = TiposPruebasUtil.dtoToEntity(tipoPruebaDto);
		if (entityVerify.isEmpty()) {
			log.error(Constants.LOG_ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), 404,
					"No hay datos");
			return ApiResponseDto.builder().data(null).message(Constants.ERROR_NO_ENCONTRADO)
					.status(HttpStatus.BAD_REQUEST).build();};
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return ApiResponseDto.builder()
				.data(tiposPruebasRepository.save(entity))
				.message(Constants.OK)
				.status(HttpStatus.OK)
				.build();
		
	}

}

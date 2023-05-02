package co.com.ath.calculadora.pruebas.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.TiposPruebasDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.ITiposPruebasRepository;
import co.com.ath.calculadora.pruebas.service.ITiposPruebasCreateService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import co.com.ath.calculadora.pruebas.util.TiposPruebasUtil;
import lombok.extern.slf4j.Slf4j;

/* Crear en la BD los tipos de pruebas
 * ABP 2023/04/27
 */
@Service
@Slf4j
public class TiposPruebasCreateServiceImpl implements ITiposPruebasCreateService {

	@Autowired
	ITiposPruebasRepository tiposPruebasRepository;

	@Override
	public ApiResponseDto createTipoPrueba(TiposPruebasDto tiposPruebasDto) {
		/* Ajustes
		 * ABP 2023/04/29 y 2023/04/30
		 */

		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		TiposPruebasUtil entity = new TiposPruebasUtil();
				return ApiResponseDto.builder()
				.data(tiposPruebasRepository.save(entity.dtoToEntity(tiposPruebasDto)))
				.message(Constants.OK)
				.status(HttpStatus.OK)
				.build();
	}

}

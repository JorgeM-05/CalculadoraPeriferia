package co.com.ath.calculadora.pruebas.controller;


import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.service.IParametersActualizarService;
import co.com.ath.calculadora.pruebas.service.IParametersCrearService;
import co.com.ath.calculadora.pruebas.service.IParametersConsultarService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parametro")
@Slf4j
public class ParametersController {

    @Autowired
    private IParametersConsultarService parametersService;
    @Autowired
    private IParametersCrearService parametersCrearServiceService;
    @Autowired
    private IParametersActualizarService parametersActualizarService;


    @GetMapping("/buscarValor")
    public ResponseEntity<ApiResponseDto> searchValue(@RequestHeader(value = Constants.X_NOMBRE_PARAMETRO_VALOR, required = true) String value,
                                                      @RequestHeader(value = Constants.X_PAG, required = false, defaultValue = Constants.X_PAG_DEFAULT) Integer pageNo,
                                                      @RequestHeader(value = Constants.X_SIZE, required = false, defaultValue = Constants.X_PAG_SIZE_DEFAULT) Integer pageSize) {


        ApiResponseDto response;
        try {
            log.info("Servicio controller Init. <{}>",value);
            response = parametersService.getParametersByValue(value, pageNo, pageSize);
        } catch ( Exception e) {
            log.error("ha ocurrido un error: {} " ,e.getMessage());
            response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/buscarCapa")
    public ResponseEntity<ApiResponseDto> buscarCapa(@RequestHeader(value = Constants.X_NOMBRE_PARAMETRO_CAPA, required = true) String value,
                                                     @RequestHeader(value = Constants.X_PAG, required = false, defaultValue = Constants.X_PAG_DEFAULT) Integer pageNo,
                                                     @RequestHeader(value = Constants.X_SIZE, required = false, defaultValue = Constants.X_PAG_SIZE_DEFAULT) Integer pageSize) {
        ApiResponseDto response;
        try {
            log.info("Service create controller Init. <{}>", value);
            response = parametersService.getParametersByLayer(value, pageNo, pageSize);
        } catch (Exception e) {
            log.error("Ha ocurrido un error : {} ", e.getMessage());
            response = ResponseError.error("error ");
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/id")
    public ResponseEntity<ApiResponseDto> buscarId(@RequestHeader(value = Constants.X_ID, required = true) int id) {
        ApiResponseDto response;
        try {
            log.info("Service create controller Init. <{}>", id);
            response = parametersService.getId(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error : {} ", e.getMessage());
            response = ResponseError.error("error ");
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> createParameters(@RequestBody RequestParametersDto request) {
        ApiResponseDto response;
        try {
            log.info("Service controller Init. <{}>", request);
            response = parametersCrearServiceService.createParameters(request);
        } catch (Exception e) {
            log.error("Ha ocurrido un error : {} ", e.getMessage());
            response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{dni}")
    public ResponseEntity<ApiResponseDto> updateParameters(@PathVariable("dni") int dni,@RequestBody RequestParametersDto request) {
        ApiResponseDto response;
        try {
            log.info("Service Update controller Init. <{}>", request);
            response = parametersActualizarService.updateParameters(dni,request);
        } catch (Exception e) {
            log.error("Ha ocurrido un error en la actualizacion de los parametros {}", e.getMessage());
            response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

}

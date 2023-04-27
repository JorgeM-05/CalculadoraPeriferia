package co.com.ath.calculadora.pruebas.controller;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.service.IParametersService;
import co.com.ath.calculadora.pruebas.util.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parameters")
@Slf4j
public class ParametersController {
    Logger logger = LoggerFactory.getLogger(ParametersController.class);

    @Autowired
    private IParametersService parametersService;


    @GetMapping("/searchValue")
    public ResponseEntity<ApiResponseDto> searchValue(@RequestParam(required = false) String value,
                                                      @RequestParam(defaultValue = "0", required = false) Integer pageNo,
                                                      @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        ApiResponseDto response;
        try {
            log.info("Service controller Init. <{}>", value);
            response = parametersService.getParametersByValue(value, pageNo, pageSize);
        } catch (Exception e) {
            log.error("Ha ocurriod un error : {} ", e.getMessage());
            response = ResponseError.error("error ");
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/searchLayer")
    public ResponseEntity<ApiResponseDto> searchLayer(@RequestParam(required = false) String value,
                                                      @RequestParam(defaultValue = "0", required = false) Integer pageNo,
                                                      @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        ApiResponseDto response;
        try {
            log.info("Service controller Init. <{}>", value);
            response = parametersService.getParametersByLayer(value, pageNo, pageSize);
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
            response = parametersService.createParameters(request);
        } catch (Exception e) {
            log.error("Ha ocurrido un error : {} ", e.getMessage());
            response = ResponseError.error("error ");
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

}

package co.com.ath.calculadora.pruebas.util;


import co.com.ath.calculadora.pruebas.dto.ContactoDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;

public class ContactoUtil {
	
	private ContactoUtil() {}
		
	public static ContactoEntity contactoEntity(Integer dniFabrica, ContactoDto dto){
	        ContactoEntity contactoEntity = new ContactoEntity();
	        dtoToEntity(dto, contactoEntity);
	        contactoEntity.setDniFabrica(dniFabrica);
	        return contactoEntity;	   
	    }
	
	public static ContactoEntity dtoToEntity(ContactoDto dto, ContactoEntity entity) {
		entity.setNombre(dto.getNombre());
		entity.setCorreo(dto.getCorreo());
		entity.setCargo(dto.getCargo());
		return entity;
	}
	
	public static ContactoDto entityToDto(ContactoDto dto, ContactoEntity entity) {
		dto.setDni(entity.getDni());
		dto.setNombre(entity.getNombre());
		dto.setCorreo(entity.getCorreo());
		dto.setCargo(entity.getCargo());
		dto.setDniFabrica(entity.getDniFabrica());
		return dto;
	}
	
	
}

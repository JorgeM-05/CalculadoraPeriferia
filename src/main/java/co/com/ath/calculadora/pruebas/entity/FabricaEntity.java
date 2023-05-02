package co.com.ath.calculadora.pruebas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fabricas")
public class FabricaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dni_fabrica")
	private Integer dni;
	
	@Column(name = "nombre_fabrica")
	private String nombreFabrica;
	
	@Column(name = "nombre_contacto")
	private String nombreContacto;
	
	@Column(name = "telefono_contacto")
	private String telefono;
		
	@Column(name = "estado_fabrica")
	private String estado;
		
}

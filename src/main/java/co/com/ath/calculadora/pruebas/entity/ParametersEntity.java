package co.com.ath.calculadora.pruebas.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "parametros")
@Data
public class ParametersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dni;
    @Column(name = "capa")
    private String capa;
    @Column(name = "estado")
    private String estado;
    @Column(name = "valor")
    private String valor;
}

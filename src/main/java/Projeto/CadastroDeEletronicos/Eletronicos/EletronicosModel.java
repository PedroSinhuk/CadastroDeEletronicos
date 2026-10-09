package Projeto.CadastroDeEletronicos.Eletronicos;

import Projeto.CadastroDeEletronicos.Marca.MarcaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_eletronicos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EletronicosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Cria automaticamento o id e liga ao eletronico.

   @Column(name = "id")
    private Long id; //Id do eletronico

    @Column(name = "nome")
    private String nome; //Nome do eletronico

    @Enumerated(EnumType.STRING)//Implementa o enums
    @Column(name = "departamento_eletronicos")//Cria coluna de departamento de eletronicos
    private TipoEletronico tipoEletronico;

    @Column(name = "cor")
    private String cor; //Cor do eletronico

    @Column(name = "Valor", precision = 10, scale = 2)//Cria coluna valor, com 10 casas decimais e 2 casas depois da virgula.
    private BigDecimal valor;

    //@ManyToOne - um eletronico para somente uma unica marca
    @ManyToOne
    @JoinColumn(name = "marca_id")//Foreing Key
    @ToString.Exclude
    private MarcaModel marca;

}

package Projeto.CadastroDeEletronicos.Eletronicos;

import Projeto.CadastroDeEletronicos.Marca.MarcaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EletronicosDTO {


    private Long id; //Id do eletronico

    private String nome; //Nome do eletronico

    private TipoEletronico tipoEletronico;

    private String cor; //Cor do eletronico

    private BigDecimal valor;

    private Integer ano;

    private MarcaModel marca;


}

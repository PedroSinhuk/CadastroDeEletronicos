package Projeto.CadastroDeEletronicos.Eletronicos;

import Projeto.CadastroDeEletronicos.Marca.MarcaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "cor")
    private String cor; //Cor do eletronico

    //@ManyToOne - um eletronico para somente uma unica marca
    @ManyToOne
    @JoinColumn(name = "marca_id")//Foreing Key
    @ToString.Exclude
    private MarcaModel marcaModel;

}

package Projeto.CadastroDeEletronicos.Marca;

import Projeto.CadastroDeEletronicos.Eletronicos.EletronicosModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tb_marcas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarcaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_marca")//Cria coluna nome_marca
    private String nome;

    @OneToMany(mappedBy = "marca")
    @JsonIgnore
    private List<EletronicosModel> eletroicos;



}

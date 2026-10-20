package Projeto.CadastroDeEletronicos.Marca;

import Projeto.CadastroDeEletronicos.Eletronicos.EletronicosModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaDTO {

    private Long id;

    private String nome;

    private List<EletronicosModel> eletroicos;


}

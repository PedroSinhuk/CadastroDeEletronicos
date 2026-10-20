package Projeto.CadastroDeEletronicos.Marca;

import Projeto.CadastroDeEletronicos.Eletronicos.EletronicosModel;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MarcaMapper {



    public MarcaModel map(MarcaDTO marcaDTO){
       MarcaModel marcaModel = new MarcaModel();
       marcaModel.setId(marcaDTO.getId());
       marcaModel.setNome(marcaDTO.getNome());
       marcaModel.setEletroicos(marcaDTO.getEletroicos());

       return marcaModel;
    }
    public MarcaDTO map(MarcaModel marcaModel){
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(marcaModel.getId());
        marcaDTO.setNome(marcaModel.getNome());
        marcaDTO.setEletroicos(marcaModel.getEletroicos());

        return marcaDTO;
    }
}

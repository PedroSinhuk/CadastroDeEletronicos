package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.stereotype.Component;

@Component
public class EletronicosMapper {

    public EletronicosModel map(EletronicosDTO eletronicosDTO) {
        EletronicosModel eletronicosModel = new EletronicosModel();
        eletronicosModel.setId(eletronicosDTO.getId());
        eletronicosModel.setNome(eletronicosDTO.getNome());
        eletronicosModel.setCor(eletronicosDTO.getCor());
        eletronicosModel.setValor(eletronicosDTO.getValor());
        eletronicosModel.setAno(eletronicosDTO.getAno());
        eletronicosModel.setTipoEletronico(eletronicosDTO.getTipoEletronico());

        return eletronicosModel;
    }

    public EletronicosDTO map(EletronicosModel eletronicosModel) {
        EletronicosDTO eletronicosDTO = new EletronicosDTO();
        eletronicosDTO.setId(eletronicosModel.getId());
        eletronicosDTO.setNome(eletronicosModel.getNome());
        eletronicosDTO.setCor(eletronicosModel.getCor());
        eletronicosDTO.setValor(eletronicosModel.getValor());
        eletronicosDTO.setAno(eletronicosModel.getAno());
        eletronicosDTO.setTipoEletronico(eletronicosModel.getTipoEletronico());

        return eletronicosDTO;
    }
}

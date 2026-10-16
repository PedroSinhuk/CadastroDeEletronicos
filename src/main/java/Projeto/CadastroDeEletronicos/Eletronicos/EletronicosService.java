package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EletronicosService {

    private EletronicosRepository eletronicosRepository;
    private EletronicosMapper eletronicosMapper;

    public EletronicosService(EletronicosRepository eletronicosRepository, EletronicosMapper eletronicosMapper) {
        this.eletronicosRepository = eletronicosRepository;
        this.eletronicosMapper = eletronicosMapper;
    }

    //Listar eletronicos
    public List<EletronicosModel> listarEletronicos() {
        return eletronicosRepository.findAll();
    }

    //listar eletronicos por ID
    public EletronicosModel listarEletronicoPorId(Long id) {
        Optional<EletronicosModel> eletronicosPorID = eletronicosRepository.findById(id);
        return eletronicosPorID.orElse(null);//or.Else caso o usuario digite um id nao existente, caso aconteça retornara nulo
    }


    //Criar Eletronico
    public EletronicosDTO criarEletronico (EletronicosDTO eletronicosDTO){
        EletronicosModel eletronicos = eletronicosMapper.map(eletronicosDTO);
        eletronicos = eletronicosRepository.save(eletronicos);
        return eletronicosMapper.map(eletronicos);
    }

    //Deletar eletronicos Por Id
    public void deletarEletronicoPorId(Long id){
       eletronicosRepository.deleteById(id);
    }

    //Atualizar eletronico por Id
    public EletronicosModel atualizarPorId( Long id, EletronicosModel eletronicosAtualizado){
        if(eletronicosRepository.existsById(id)){
            eletronicosAtualizado.setId(id);
            return eletronicosRepository.save(eletronicosAtualizado);
        }
        return null;
    }
}

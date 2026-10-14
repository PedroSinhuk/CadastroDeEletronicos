package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EletronicosService {

    private EletronicosRepository eletronicosRepository;

    public EletronicosService(EletronicosRepository eletronicosRepository) {
        this.eletronicosRepository = eletronicosRepository;
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
    public EletronicosModel criarEletronico (EletronicosModel eletronicos){
        return eletronicosRepository.save(eletronicos);
    }

    //Deletar eletronicos Por Id
    public void deletarEletronicoPorId(Long id){
       eletronicosRepository.deleteById(id);
    }
}

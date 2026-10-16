package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EletronicosService {

    private EletronicosRepository eletronicosRepository;
    private EletronicosMapper eletronicosMapper;

    public EletronicosService(EletronicosRepository eletronicosRepository, EletronicosMapper eletronicosMapper) {
        this.eletronicosRepository = eletronicosRepository;
        this.eletronicosMapper = eletronicosMapper;
    }

    //Listar eletronicos
    public List<EletronicosDTO> listarEletronicos() {
        List<EletronicosModel> eletronicos = eletronicosRepository.findAll();
        return eletronicos.stream()
                .map(eletronicosMapper::map)
                .collect(Collectors.toList());
    }

    //listar eletronicos por ID
    public EletronicosDTO listarEletronicoPorId(Long id) {
        Optional<EletronicosModel> eletronicosPorID = eletronicosRepository.findById(id);
        return eletronicosPorID.map(eletronicosMapper::map).orElse(null);//or.Else caso o usuario digite um id nao existente, caso aconteça retornara nulo
    }


    //Criar Eletronico
    public EletronicosDTO criarEletronico(EletronicosDTO eletronicosDTO) {
        EletronicosModel eletronicos = eletronicosMapper.map(eletronicosDTO);
        eletronicos = eletronicosRepository.save(eletronicos);
        return eletronicosMapper.map(eletronicos);
    }

    //Deletar eletronicos Por Id
    public void deletarEletronicoPorId(Long id) {
        eletronicosRepository.deleteById(id);
    }

    //Atualizar eletronico por Id
    public EletronicosDTO atualizarPorId(Long id, EletronicosDTO eletronicosDTO) {
        Optional<EletronicosModel> eletronicoExistente = eletronicosRepository.findById(id);
        if (eletronicoExistente.isPresent()) {
            EletronicosModel eletronicoAtualizado = eletronicosMapper.map(eletronicosDTO);
            eletronicoAtualizado.setId(id);
            EletronicosModel eletronicoSalvo = eletronicosRepository.save(eletronicoAtualizado);
            return eletronicosMapper.map(eletronicoSalvo);
        }
        return null;
    }
}

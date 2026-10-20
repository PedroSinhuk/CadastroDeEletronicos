package Projeto.CadastroDeEletronicos.Marca;

import Projeto.CadastroDeEletronicos.Eletronicos.EletronicosModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public MarcaService(MarcaRepository marcaRepository, MarcaMapper marcaMapper) {
        this.marcaRepository = marcaRepository;
        this.marcaMapper = marcaMapper;
    }
    //CRUD:

    //Listar
    public List<MarcaDTO> listarMarcas() {
        List<MarcaModel> marcas = marcaRepository.findAll();
        return marcas.stream()
                .map(marcaMapper::map)
                .collect(Collectors.toList());
    }

    //Listar por ID
    public MarcaDTO listarPorId(Long id) {
        Optional<MarcaModel> marcasPorId = marcaRepository.findById(id);
        return marcasPorId.map(marcaMapper::map).orElse(null);
    }
    //Alterar por ID
    public MarcaDTO alterarPorId(MarcaDTO marcaDTO, Long id){
        Optional<MarcaModel> marcasExiste = marcaRepository.findById(id);
        if (marcasExiste.isPresent()){
            MarcaModel marcaAtualizada = marcaMapper.map(marcaDTO);
            marcaAtualizada.setId(id);
            MarcaModel marcaSalva = marcaRepository.save(marcaAtualizada);
            return marcaMapper.map(marcaSalva);
        }
        return null;
    }
    //Criar
    public MarcaDTO criarMarca(MarcaDTO marcaDTO){
        MarcaModel marca = marcaMapper.map(marcaDTO);
        marca = marcaRepository.save(marca);
        return marcaMapper.map(marca);
    }
    //Deletar
    public void deletarMarca(Long id){
        marcaRepository.deleteById(id);
    }
}

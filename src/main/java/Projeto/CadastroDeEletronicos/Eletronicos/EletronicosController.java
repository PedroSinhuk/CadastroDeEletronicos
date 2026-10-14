package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("eletronicos")

public class EletronicosController {

    EletronicosService eletronicosService;

    //adicionar eletronico-CREATE
    @PostMapping("/criar")
    public String criarEletronico() {
        return "Eletronico criado";
    }

    //Procurar eletronico por ID
    @GetMapping("/listar/{id}")
    public EletronicosModel listarPorId(@PathVariable Long id) {
        return eletronicosService.listarEletronicoPorId(id);

    }


    //Listar eletronicos-READ
    @GetMapping("/listar")
    public List<EletronicosModel> ListlistarTodos() {
        return eletronicosService.listarEletronicos();
    }

    //alterar eletronicos-UPDATE
    @PutMapping("/alterarID")
    public String alterarDadosId() {
        return "Dados alterado";
    }


    //Deletar eletronico-DELETE
    @DeleteMapping("/deletarID")
    public String deletarEletronicoId() {
        return "Eletronico deletado";
    }


}

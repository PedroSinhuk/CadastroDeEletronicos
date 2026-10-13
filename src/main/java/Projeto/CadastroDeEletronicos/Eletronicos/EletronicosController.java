package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping

public class EletronicosController {

    //adicionar eletronico-CREATE
    @PostMapping("/criar")
    public String criarEletronico() {
        return "Eletronico criado";
    }

    //Procurar eletronico por ID
    @GetMapping("listarPorID")
    public String listarPorId() {
        return "Listar eletronico por ID ";
    }


    //Listar eletronicos-READ
    @GetMapping("/listar")
    public String listarTodos() {
        return "Listar todos";
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

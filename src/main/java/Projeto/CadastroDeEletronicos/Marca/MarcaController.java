package Projeto.CadastroDeEletronicos.Marca;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("marca")
public class MarcaController {


    //adicionar marca - CREATE
    @PostMapping("/criarMarca")
    public String criarMarca() {
        return "Marca criada";
    }

    //Procurar marca por ID
    @GetMapping("listarPorID")
    public String listarMarcaPorId() {
        return "Listar marca por ID ";
    }


    //Listar marca - READ
    @GetMapping("/listarTodos")
    public String listarTodas() {
        return "Listar todos";
    }

    //alterar marca - UPDATE
    @PutMapping("/alterarID")
    public String alterarDadosId() {
        return "Dados da marca alterado";
    }


    //Deletar eletronico-DELETE
    @DeleteMapping("/deletarID")
    public String deletarMarcaId() {
        return "Marca deletada";
    }

}

package Projeto.CadastroDeEletronicos.Eletronicos;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("eletronicos")

public class EletronicosController {

    private EletronicosService eletronicosService;

    public EletronicosController(EletronicosService eletronicosService) {
        this.eletronicosService = eletronicosService;
    }

    //adicionar eletronico-CREATE
    @PostMapping("/criar")
    public EletronicosModel criarEletronico(@RequestBody EletronicosModel eletronicos) {//@RequestBody para serializar para o banco de dados
        return eletronicosService.criarEletronico(eletronicos);
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
    @DeleteMapping("/deletar/{id}")
    public void  deletarEletronicoId(@PathVariable Long id) {
       eletronicosService.deletarEletronicoPorId(id);
    }


}

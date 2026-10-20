package Projeto.CadastroDeEletronicos.Eletronicos;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/eletronicos")

public class EletronicosController {

    private final EletronicosService eletronicosService;

    public EletronicosController(EletronicosService eletronicosService) {
        this.eletronicosService = eletronicosService;
    }

    //adicionar eletronico-CREATE
    @PostMapping("/criar")
    public ResponseEntity<String> criarEletronico(@RequestBody EletronicosDTO eletronicos) {//@RequestBody para serializar para o banco de dados
        EletronicosDTO eletronicoCriado = eletronicosService.criarEletronico(eletronicos);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Eletronico criado com sucesso" + eletronicoCriado.getNome() + "ID: " + eletronicoCriado.getId());
    }

    //Procurar eletronico por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        EletronicosDTO eletronicos = eletronicosService.listarEletronicoPorId(id);
        if (eletronicos != null) {
            return ResponseEntity.ok(eletronicos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Eletronico de id: " + id + "não foi encontrado");
        }

    }


    //Listar eletronicos-READ
    @GetMapping("/listar")
    public ResponseEntity<List<EletronicosDTO>> ListlistarTodos() {
        List<EletronicosDTO> eletronicos = eletronicosService.listarEletronicos();
        return ResponseEntity.ok(eletronicos);
    }

    //alterar eletronicos-UPDATE
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarDadosId(@PathVariable Long id, @RequestBody EletronicosDTO eletronicosAtualizado) {
        EletronicosDTO eletronico = eletronicosService.atualizarPorId(id, eletronicosAtualizado);
        if (eletronico != null) {
            return ResponseEntity.ok(eletronico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + "não encontrado");
        }
    }


    //Deletar eletronico-DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEletronicoId(@PathVariable Long id) {
        if (eletronicosService.listarEletronicoPorId(id) != null) {
            eletronicosService.deletarEletronicoPorId(id);
            return ResponseEntity.ok("Eletronico deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Eletronico de id: " + id + "não encontrado");
        }
    }


}

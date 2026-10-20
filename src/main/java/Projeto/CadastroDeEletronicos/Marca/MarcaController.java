package Projeto.CadastroDeEletronicos.Marca;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    //adicionar marca - CREATE
    @PostMapping("/criarMarca")
    public ResponseEntity<String> criarMarca(@RequestBody MarcaDTO marcas) {
        MarcaDTO marcaCriada = marcaService.criarMarca(marcas);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Marca criado com sucesso" + marcaCriada.getNome() + "ID: " + marcaCriada.getId());
    }

    //Procurar marca por ID
    @GetMapping("/listarPorID/{id}")
    public ResponseEntity<?> listarMarcaPorId(@PathVariable Long id) {
        MarcaDTO marcas = marcaService.listarPorId(id);
        if (marcas != null) {
            return ResponseEntity.ok(marcas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca de id: " + id + "não foi encontrado");
        }
    }


    //Listar marca - READ
    @GetMapping("/listarMarcas")
    public ResponseEntity<List<MarcaDTO>> listarTodas() {
        List<MarcaDTO> marcas = marcaService.listarMarcas();
        return ResponseEntity.ok(marcas);
    }

    //alterar marca - UPDATE
    @PutMapping("/alterarID/{id}")
    public ResponseEntity<?> alterarDadosId(@PathVariable Long id, @RequestBody MarcaDTO marcasAtualizada) {
        MarcaDTO marca = marcaService.alterarPorId(marcasAtualizada, id);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + "não encontrado");
        }
    }


    //Deletar eletronico-DELETE
    @DeleteMapping("/deletarID/{id}")
    public ResponseEntity<String> deletarMarcaId(@PathVariable Long id) {
        if (marcaService.listarPorId(id) != null) {

            marcaService.deletarMarca(id);
            return ResponseEntity.ok("Marca deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca de id: " + id + "não encontrado");
        }

    }

}

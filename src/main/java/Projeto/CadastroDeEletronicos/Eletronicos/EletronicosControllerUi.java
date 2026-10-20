package Projeto.CadastroDeEletronicos.Eletronicos;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/eletronicos/ui")
public class EletronicosControllerUi {

    private final EletronicosService eletronicosService;

    public EletronicosControllerUi(EletronicosService eletronicosService) {
        this.eletronicosService = eletronicosService;
    }

    @GetMapping("/listar")
    public String ListlistarTodos(Model model) {
        List<EletronicosDTO> eletronicos = eletronicosService.listarEletronicos();
        model.addAttribute("eletronicos", eletronicos);
        return "listar eletronicos";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarEletronicoId(@PathVariable Long id) {
        eletronicosService.deletarEletronicoPorId(id);
        return "redirect:/eletronicos/ui/listar";
    }
    @GetMapping("/listar/{id}")
    public String listarPorId(@PathVariable Long id, Model model) {
        EletronicosDTO eletronicos = eletronicosService.listarEletronicoPorId(id);
        if (eletronicos != null){
            model.addAttribute("eletronicos", eletronicos);
            return "detalhes";
        }else{
            model.addAttribute("mensagem", "Eletronico não encontrado");
            return "listarEletronicos";
        }


    }
}

package Projeto.CadastroDeEletronicos.Marca;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/marca/ui")
public class MarcasControllerUi {

    private final MarcaService marcaService;

    public MarcasControllerUi(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @ModelAttribute("secao")
    public String secao() {
        return "marcas";
    }

    @GetMapping("/listar")
    public String listarTodas(Model model) {
        List<MarcaDTO> marcas = marcaService.listarMarcas();
        model.addAttribute("marcas", marcas);
        return "listarMarcas";
    }

    @GetMapping("/listar/{id}")
    public String listarPorId(@PathVariable Long id, Model model) {
        MarcaDTO marca = marcaService.listarPorId(id);
        if (marca == null) {
            return "redirect:/marca/ui/listar?naoEncontrado";
        }
        model.addAttribute("marca", marca);
        return "detalhesMarca";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("marca", new MarcaDTO());
        return "formularioMarca";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        MarcaDTO marca = marcaService.listarPorId(id);
        if (marca == null) {
            return "redirect:/marca/ui/listar?naoEncontrado";
        }
        model.addAttribute("marca", marca);
        return "formularioMarca";
    }
}

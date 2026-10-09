package Projeto.CadastroDeEletronicos.Eletronicos;

import Projeto.CadastroDeEletronicos.Marca.MarcaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EletronicosRepository extends JpaRepository<EletronicosModel, Long> {
}

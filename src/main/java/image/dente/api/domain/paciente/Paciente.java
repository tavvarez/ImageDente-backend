package image.dente.api.domain.paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String genero;
    private String seguroSaude;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dadosPaciente) {
        this.ativo = true;
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.telefone = dadosPaciente.telefone();
        this.genero = dadosPaciente.genero();
        this.seguroSaude = dadosPaciente.seguroSaude();
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dadosPaciente) {
        if (dadosPaciente.nome() != null) {
            this.nome = dadosPaciente.nome();
        }
        if (dadosPaciente.telefone() != null) {
            this.telefone = dadosPaciente.telefone();
        }
    }

    public void excluirLogico() {
        this.ativo = false;
    }
}
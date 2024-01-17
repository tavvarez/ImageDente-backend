package image.dente.api.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
    @NotNull
    Long id,
    
    String nome,

    String telefone
) {

}

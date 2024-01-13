package image.dente.api.domain.dentista;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDentista(
    @NotNull
    Long id,
    
    String nome,

    String telefone
) {

}

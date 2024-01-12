package image.dente.api.dentista;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDentista(
    @NotNull
    Long id,
    
    String nome,

    String telefone
) {

}

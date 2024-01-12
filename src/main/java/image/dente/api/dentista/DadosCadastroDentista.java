package image.dente.api.dentista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroDentista(
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String cro,

    @NotBlank
    Especialidade especialidade
) {}

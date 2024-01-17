package image.dente.api.domain.paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String genero, String seguroSaude) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getGenero(), paciente.getSeguroSaude());
    }

}
package image.dente.api.domain.paciente;

public record DadosDetalhamentoPaciente(Long id, String name, String email, String genero, String telefone, String seguroSaude) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getGenero(), paciente.getTelefone(), paciente.getSeguroSaude());
    }
}

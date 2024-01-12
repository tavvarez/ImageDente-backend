package image.dente.api.dentista;

public record DadosDetalhamentoDentista(Long id, String name, String email, String cro, String telefone, Especialidade especialidade) {

    public DadosDetalhamentoDentista(Dentista dentista) {
        this(dentista.getId(), dentista.getNome(), dentista.getEmail(), dentista.getCro(), dentista.getTelefone(), dentista.getEspecialidade());
    }

}

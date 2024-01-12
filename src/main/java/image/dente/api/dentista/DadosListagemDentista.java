package image.dente.api.dentista;

public record DadosListagemDentista(Long id, String nome, String email, String cro, Especialidade especialidade) {

    public DadosListagemDentista(Dentista dentista) {
        this(dentista.getId(), dentista.getNome(), dentista.getEmail(), dentista.getCro(), dentista.getEspecialidade());
    }

}
package image.dente.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import image.dente.api.dentista.DadosAtualizacaoDentista;
import image.dente.api.dentista.DadosCadastroDentista;
import image.dente.api.dentista.DadosDetalhamentoDentista;
import image.dente.api.dentista.DadosListagemDentista;
import image.dente.api.dentista.Dentista;
import image.dente.api.dentista.DentistaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("dentistas")
public class DentistaController {

    @Autowired
    private DentistaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDentista(@RequestBody @Valid DadosCadastroDentista dados, UriComponentsBuilder uriBuilder) {
        var dentista = new Dentista(dados);
        repository.save(dentista);
        var uri = uriBuilder.path("/dentistas/{id}").buildAndExpand(dentista.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDentista(dentista));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemDentista>> listarDentistas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemDentista::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDentistas(@RequestBody @Valid DadosAtualizacaoDentista dados) {
        var dentista = repository.getReferenceById(dados.id());
        dentista.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoDentista(dentista));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id) {
        var dentista = repository.getReferenceById(id);
        dentista.excluirLogico();        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity visualizarMedico(@PathVariable Long id) {
        var dentista = repository.getReferenceById(id);              
        return ResponseEntity.ok(new DadosDetalhamentoDentista(dentista));
    }

}

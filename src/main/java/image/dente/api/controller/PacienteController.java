package image.dente.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import image.dente.api.domain.dentista.DadosListagemDentista;
import image.dente.api.domain.paciente.DadosCadastroPaciente;
import image.dente.api.domain.paciente.DadosDetalhamentoPaciente;
import image.dente.api.domain.paciente.DadosListagemPaciente;
import image.dente.api.domain.paciente.Paciente;
import image.dente.api.domain.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosPaciente, UriComponentsBuilder uriBuilder ) {
        var paciente = new Paciente(dadosPaciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemPaciente>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }
}

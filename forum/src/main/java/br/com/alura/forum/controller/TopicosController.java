package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController //essa anotação faz com o spring assuma que tosdo metodo tenha um '@ResponseBody' logo não precisa retornar uma view html
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		if(nomeCurso == null){
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso); //o nome do método é findBy + nome do atributo que queremos filtrar, usando padrão java para nome de variável
			//realizando o filtro em um relacionamento, basta colocar a classe do relacionamento seguida do atributo do relacionamento
			return TopicoDto.converter(topicos);
		}
	}

	//nomenclatura: TopicoDto = os dados saem da API para o cliente ; TopicoForm = dados que chegam do cliente para a API
	//não é uma boa pratica trablahar com a entidade, e sim uma classe DTO
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
	//a anotação @RequestBody sinaliza pro spring para ele pegar o parametro 'form' do corpo da requisao e não da url, como no metodo anterior
	//@Valid fala pro Spring que quando ele receber os dados vindos do corpo da requisao ele precisa validar de acordo com as anotações da classe TopicoForm
	//caso as validações sejam satisfeitas ele executa o codigo abaixo, caso alguma não satisfaça, ele devolve o código '400' para o usuário

		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);

		//retornando uma mensagem mais apropriada que tudo ocorreu bem
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //constroi a uri quer será retornada pelo método, informando o id do objeto persistido no banco
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
		//esse return devolve uma resposta '201', em vez do genérico '202', dizendo que foi persistindo no banco um novo valor
		//o '200' é apenas um 'ok', e não representa de fato o que ocorreu no método
		//A classe ResponseEntity fornece uma maneira de devolver o '201'

	}

	//Para métodos void nas classes controller, será devolvida uma resposta sem conteúdo, juntamente com o código HTTP 200 (OK),
	// caso a requisição seja processada com sucesso.

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){

		//o getOne faz a busca do topico pelo id, é uma implementacao do JpaRepository. Caso nao encontre o topico, ele gera um exception
		Optional<Topico> topico = topicoRepository.findById(id); //o findById caso nao tenha o registro no banco ele retorna null, e o optional permite ser nulo

		//formatando o retorno quando não existe o id
		if(topico.isPresent()){
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}") //no PUT, eu tambem preciso receber um arquivo json, que vem do corpo da requisao, para poder atualizar pelo id
	@Transactional //indica para o spring para commitar a transação depois que o método for finalizado
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm form){

		Optional<Topico> optional = topicoRepository.findById(id);

		if(optional.isPresent()){
			Topico topico = form.atualizar(id,topicoRepository);// não precisa chamar um metodo para atulizar no banco, a JPA detecta que houve alterações e ao final do método, quando for commitar ela já atualiza os dados
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()){
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

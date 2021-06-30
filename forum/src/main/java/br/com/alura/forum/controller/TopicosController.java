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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

	//interessante quando utilizarmos os parametros passados na url da requisição colocar a anotação @RequestParam para indicar ao Spring que está vindo pela url
	//porem quando fazemos isso, o parametro passa a ser obrigatorio, passando required = false, ele permite que não seja obrigatório

	//realizamos a consulta da api da seguinte maneira: localhost:8080/topicos?page=0&size=10&sort=id,desc
	//o nome dos parametros são em ingles, desta forma podemos enviar filtros com mais de um campo, por exemplo data e id
	//basta fazer localhost:8080/topicos?page=0&size=10&sort=dataCriacao,asc&sort=id,asc PODEMOS ter quantos sorty quisermos
	//caso não seja enviado um campo de ordenação podemos definir um padrão de ordenação usando @PageableDefault, se enviarmos o parametro de ordenção o spring ignora o default
	@GetMapping
	@Cacheable(value="listaDeTopicos") //guardar o retorno desse método em cache
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id",direction = Sort.Direction.DESC,page = 0, size = 10) Pageable paginacao){ //para usar Spring pegar as informação de paginacao da web precisa habilitar um método na classe main: @EnableSpringDataWebSupport
//								 @RequestParam int pagina,
//								 @RequestParam int qtd,
//								 @RequestParam String ordenacao) {

		//Pageable paginacao = PageRequest.of(pagina,qtd, Sort.Direction.DESC,ordenacao);//realização da paginação e ordenação

		if(nomeCurso == null){
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao); //o nome do método é findBy + nome do atributo que queremos filtrar, usando padrão java para nome de variável
			//realizando o filtro em um relacionamento, basta colocar a classe do relacionamento seguida do atributo do relacionamento
			return TopicoDto.converter(topicos);
		}
	}

	//nomenclatura: TopicoDto = os dados saem da API para o cliente ; TopicoForm = dados que chegam do cliente para a API
	//não é uma boa pratica trablahar com a entidade, e sim uma classe DTO
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeTopicos", allEntries = true)	//fala pro Spring limpar o cache antes de cadatrar para quando chamar o metoto listar ele adicionar o valor que foi cadastrado no cache
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
	@CacheEvict(value="listaDeTopicos", allEntries = true)
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
	@CacheEvict(value="listaDeTopicos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()){
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

package br.com.alura.forum.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice //essa classe vai ser chamada quando der alguma exception em um método, e indicaremos como tratá-la
public class ErroDeValidacaoHandler { //o handle é um interceptador

    @Autowired
    private MessageSource messageSource; //ajuda a pegar as mensagens de erro, no idioma que o cliente requisitar

    @ResponseStatus(code = HttpStatus.BAD_REQUEST) // caso não tenha essa anotação, o spring devolve codigo '200' de ok, porque a exception foi tratada
    @ExceptionHandler(MethodArgumentNotValidException.class) // indica para o spring, que quando acontecer uma exception do tipo MethodArgumentNotValidException, ele deve acionar o método handle
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormularioDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); //retorna todos os erros de formulario que aconteceram

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale()); //o getLocale, serve para o Spring detectar o idiomar e devolver a mensagem de erro no idioma correto
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(),mensagem);
            dto.add(erro);
        });

        return dto;
    }

}


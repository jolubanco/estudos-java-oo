package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

//Actions são métodos de classes controller
@Controller
public class HelloController {

    //o método hello é uma Action
    @GetMapping("/hello") //defindo para o spring qual o path que quando solicitado no navegador retornara o método abaixo
    public String hello(Model model){ //Model está asbtraindo uma requisao HTTP
        model.addAttribute("nome","Mundo");
        return "hello"; //o retorno é o nome da pagina html que queremos exibir, a pagina encontra-se em resources.templates
    }
}

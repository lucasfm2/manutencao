package br.ucsal.manutencao.controller;

import br.ucsal.manutencao.models.Manutencao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManutencaoController {

    List<Manutencao> solicitacoes =  new ArrayList<>();

    @RequestMapping(value="/solicitarManutencao", method = RequestMethod.GET)
    public ModelAndView form(){
        return new ModelAndView("manutencao/formManutencao");
    }

    @RequestMapping(value="/solicitarManutencao", method = RequestMethod.POST)
    public ModelAndView form(Manutencao manutencao){

        solicitacoes.add(manutencao);

        return new ModelAndView("redirect:/solicitarManutencao");
    }
    @RequestMapping("/manutencoes")
    public ModelAndView listaManutencao(){
        ModelAndView mv = new ModelAndView("manutencao/detalheManutencao");
        Iterable<Manutencao> manutencoes = solicitacoes;
        mv.addObject("manutencoes", manutencoes);
        return mv;
    }
    @RequestMapping("/deletar/{codigo}")
    public ModelAndView deletarManutencao(@PathVariable long codigo){
        for (Manutencao manutencaoRemove : solicitacoes) {
            if (manutencaoRemove.getId() == codigo){
                solicitacoes.remove(manutencaoRemove);
            }
        }
        return new ModelAndView("redirect:/manutencoes");
    }
}

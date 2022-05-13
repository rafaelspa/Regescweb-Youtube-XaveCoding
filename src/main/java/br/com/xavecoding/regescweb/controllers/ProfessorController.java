package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.dto.RequisicaoNovoProfessor;
import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping("/professores")
    public ModelAndView index() {
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

//    @GetMapping("/professor/new")
//    public String nnew() {
//        return "professores/new";
//    }

    @GetMapping("/professor/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    // web parameter tampering
    @PostMapping("/professores")
    public String create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("\n*************  TEM ERROS *************\n");
            return "redirect:/professor/new";
        } else {
            Professor professor = requisicao.toProfessor();
    //        System.out.println();
    //        System.out.println(requisicao);
    //        System.out.println();
    //        System.out.println();
    //        System.out.println(professor);
    //        System.out.println();
            this.professorRepository.save(professor);

            return "redirect:/professores";

        }
    }
}

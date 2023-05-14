package com.br.projetoconexao.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.projetoconexao.Model.Aluno;
import com.br.projetoconexao.Repository.AlunoRepository;

@Controller
public class AlunoController {

    List<Aluno> alunos = new ArrayList<>();

    @Autowired
    AlunoRepository repository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/home")
    public String salvar(Aluno aluno) {
        repository.save(aluno);
        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("lista");
        ArrayList<Aluno> alunos = new ArrayList<>();
        alunos = (ArrayList<Aluno>) repository.findAll();
        mv.addObject("alunos", alunos);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/lista";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("home");
        ArrayList<Aluno> alunos = new ArrayList<>();
        alunos = (ArrayList<Aluno>) repository.findAll();
        Aluno alunofind = new Aluno();
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                alunofind = aluno;
            }
        }

        mv.addObject("aluno", alunofind);
        return mv;
    }

}
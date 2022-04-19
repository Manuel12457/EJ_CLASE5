package com.example.LAB4_FP_GRUPO1.controller;


import com.example.LAB4_FP_GRUPO1.entity.Employees;
import com.example.LAB4_FP_GRUPO1.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/Search")
public class SearchController {

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping(value = {"","/"})
    public String indice(){
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio (@ModelAttribute("salario") Employees salario, Model model){

        model.addAttribute("listaEmpleados", employeesRepository.listaEmpleadosPorSalario());
        return "Search/lista2";
    }

    @PostMapping("/busqueda")
    public String buscar (@ModelAttribute("salario") @Valid Employees salario, BindingResult bindingResult, Model model){

        if(!bindingResult.hasErrors()) {
            model.addAttribute("listaEmpleados", employeesRepository.findBySalary(salario.getSalary()));
        }
        return "Search/lista2";
    }

    @GetMapping(value = "/Filtro2")
    public String cantidadEmpleadosPorPais (){

        //COMPLETAR
        return "/Search/salario";
    }

    @GetMapping("/listar")
    public String listarEmpleadoDep() {
        //COMPLETAR
        return "/Search/lista3";

    }


}

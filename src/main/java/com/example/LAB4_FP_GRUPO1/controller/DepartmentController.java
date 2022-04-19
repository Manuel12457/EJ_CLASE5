package com.example.LAB4_FP_GRUPO1.controller;

import com.example.LAB4_FP_GRUPO1.entity.Departments;
import com.example.LAB4_FP_GRUPO1.repository.DepartmentsRepository;
import com.example.LAB4_FP_GRUPO1.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentsRepository dr;

    @Autowired
    EmployeesRepository er;

    @GetMapping(value = {"","/lista"})
    public String salarioPorDpto(Model model){

        model.addAttribute("listado",dr.obtenerSalarioPorDpto());
        return "department/listaDptos";
    }

    @GetMapping("/employees")
    public String empleadosPorDpto(Model model, @RequestParam("id") int idDpto) {

        Optional<Departments> opDpto = dr.findById(idDpto);

        if (opDpto.isPresent()) {

            model.addAttribute("listaEmployee", er.findByDepartmentOrderBySalaryDesc(opDpto.get()));
            return "department/listaEmpleados";
        }
        return "department/listaDptos";
    }
}

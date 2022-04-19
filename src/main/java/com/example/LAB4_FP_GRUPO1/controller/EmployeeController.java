package com.example.LAB4_FP_GRUPO1.controller;
import com.example.LAB4_FP_GRUPO1.entity.Employees;
import com.example.LAB4_FP_GRUPO1.repository.DepartmentsRepository;
import com.example.LAB4_FP_GRUPO1.repository.EmployeesRepository;
import com.example.LAB4_FP_GRUPO1.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {"","/"})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employees") Employees employees,Model model) {
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaJefes", employeesRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/Frm";
    }

    @PostMapping("/save")
    public String guardarEmployee(@ModelAttribute("employees") @Valid Employees employees, BindingResult bindingResult,
                                  RedirectAttributes attr, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", getListaJefes());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        }else {
            if (employees.getId() == null) {
                attr.addFlashAttribute("msg", "Empleado creado exitosamente");
                employees.setHireDate(new Date());
                employeesRepository.save(employees);
                return "redirect:/employee";
            }else {
                Employees employeeDB = employeesRepository.getById(employees.getId());
                employees.setHireDate(employeeDB.getHireDate());
                employeesRepository.save(employees);
                attr.addFlashAttribute("msg1", "Empleado actualizado exitosamente");
                return "redirect:/employee";
            }
        }
    }

    @GetMapping("/edit")
    public String editarEmployee(@ModelAttribute("employees") Employees employee,
                                 Model model,@RequestParam("id") int id) {
        Optional<Employees> optEmp = employeesRepository.findById(id);
        if (optEmp.isPresent()) {
            Employees employees = optEmp.get();
            model.addAttribute("employees", employees);
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", getListaJefes());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        } else {
            return "redirect:/employee/lista";
        }
    }

    public List<Employees> getListaJefes() {
        List<Employees> listaJefes = employeesRepository.findAll();
        Employees e = new Employees();
        e.setId(0);
        e.setFirstName("--No tiene Jefe--");
        listaJefes.add(0, e);
        return listaJefes;
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg2","Empleado borrado exitosamente");
        }
        return "redirect:/employee";

    }

    @PostMapping("/search")
    public String buscar (@RequestParam("campo") String campo, @RequestParam("filtro") String filtro, Model model){

        List<Employees> listaEmpleados;

        if (filtro.equals("nombre")) {
            listaEmpleados = employeesRepository.buscarEmpleadosPorNombre(campo);
        } else if (filtro.equals("apellido")) {
            listaEmpleados = employeesRepository.buscarEmpleadosPorApellido(campo);
        } else if (filtro.equals("cargo")) {
            listaEmpleados = employeesRepository.buscarEmpleadosPorCargo(campo);
        } else if (filtro.equals("departamento")) {
            listaEmpleados = employeesRepository.buscarEmpleadosPorDepartamento(campo);
        } else if (filtro.equals("ciudad")) {
            listaEmpleados = employeesRepository.buscarEmpleadosPorCiudad(campo);
        } else {
            listaEmpleados = employeesRepository.findAll();
        }
        model.addAttribute("listaEmpleados", listaEmpleados);
        return "employee/lista";
    }

}

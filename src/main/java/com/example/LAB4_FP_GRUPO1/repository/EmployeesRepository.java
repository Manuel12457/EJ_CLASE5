package com.example.LAB4_FP_GRUPO1.repository;

import com.example.LAB4_FP_GRUPO1.entity.Departments;
import com.example.LAB4_FP_GRUPO1.entity.Employees;
import com.example.LAB4_FP_GRUPO1.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    List<Employees> findByDepartmentOrderBySalaryDesc(Departments dpto);

    @Query(nativeQuery = true, value = "select * from employees where first_name like %1%")
    List<Employees> buscarEmpleadosPorNombre(String nombre);

    @Query(nativeQuery = true, value = "select * from employees where last_name like %1%")
    List<Employees> buscarEmpleadosPorApellido(String apellido);

    @Query(nativeQuery = true, value = "select e.* from employees e\n" +
            "inner join jobs j on (e.job_id = j.job_id)\n" +
            "where j.job_title like %1%")
    List<Employees> buscarEmpleadosPorCargo(String cargo);

    @Query(nativeQuery = true, value = "select e.* from employees e\n" +
            "inner join departments d on (e.department_id = d.department_id)\n" +
            "where d.department_name like %1%")
    List<Employees> buscarEmpleadosPorDepartamento(String departamento);


    @Query(nativeQuery = true, value = "select e.* from employees e\n" +
            "inner join departments d on (e.department_id = d.department_id)\n" +
            "inner join locations l on (d.location_id = l.location_id)\n" +
            "where l.city like %1%")
    List<Employees> buscarEmpleadosPorCiudad(String ciudad);

    @Query(nativeQuery = true, value = "select * from employees order by salary DESC")
    List<Employees> listaEmpleadosPorSalario();

    @Query(nativeQuery = true, value = "select * from employees where salary = ?1;")
    List<Employees> buscarEmpleadosPorSalario(BigDecimal salario);

    List<Employees> findBySalary(BigDecimal salario);


}

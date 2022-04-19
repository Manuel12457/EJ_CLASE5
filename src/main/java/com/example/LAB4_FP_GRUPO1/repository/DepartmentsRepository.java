package com.example.LAB4_FP_GRUPO1.repository;

import com.example.LAB4_FP_GRUPO1.dto.SalarioDepartamentoDto;
import com.example.LAB4_FP_GRUPO1.entity.Departments;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {

    @Query(value= """
            select d.department_id as 'departmentid', d.department_name as 'departmentname', truncate(avg(e.salary),0) as 'departmentsalary' from employees e\s
            inner join departments d on (d.department_id = e.department_id)\s
            group by d.department_id\s
            order by 'promedio'""",nativeQuery = true)
    List<SalarioDepartamentoDto> obtenerSalarioPorDpto();
}

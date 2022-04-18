package com.example.LAB4_FP_GRUPO1.repository;

import com.example.LAB4_FP_GRUPO1.entity.Departments;
import com.example.LAB4_FP_GRUPO1.entity.Employees;
import com.example.LAB4_FP_GRUPO1.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    public List<Employees> findByFirstname(String firstName);
    public List<Employees> findByLastname(String lastName);
    public List<Employees> findByJob(Jobs jobs);
    public List<Employees> findByDepartment(Departments departments);


}

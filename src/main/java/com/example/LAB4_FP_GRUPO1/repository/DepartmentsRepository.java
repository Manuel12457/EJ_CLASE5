package com.example.LAB4_FP_GRUPO1.repository;

import com.example.LAB4_FP_GRUPO1.entity.Departments;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {


}

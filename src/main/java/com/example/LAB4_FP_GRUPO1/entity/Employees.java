package com.example.LAB4_FP_GRUPO1.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 20)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    @NotBlank
    private String lastName;

    @Column(name = "email", nullable = false, length = 25)
    @NotBlank
    private String email;

    @Column(name = "password", length = 65)
    @NotBlank
    @Size(min = 8, message = "La contraseña debe tener como mínimo 8 caracteres.")
    @Size(max = 65,message = "La contraseña puede tener como máximo 65 caracteres.")
    private String password;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Jobs job=new Jobs();

    @Column(name = "salary") //precision = 8, scale = 2
    @Digits(integer=10,fraction = 2)
    @Min(value=1,message = "Debe ser mayor que cero")
    private BigDecimal salary;

    @Column(name = "commission_pct", precision = 2, scale = 2)
    private BigDecimal commissionPct;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employees manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department = new Departments();

    @Column(name = "enabled")
    private int enabled;

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Employees getManager() {
        return manager;
    }

    public void setManager(Employees manager) {
        this.manager = manager;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

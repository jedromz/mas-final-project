package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}

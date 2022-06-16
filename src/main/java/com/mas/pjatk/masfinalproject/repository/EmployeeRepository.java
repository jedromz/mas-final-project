package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.AdminEmployee;
import com.mas.pjatk.masfinalproject.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByAdminEmployeeIsNotNull(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from Visit v left join fetch v.vacations left join fetch v.shifts where d.id = ?1")
    Optional<Employee> findDoctorWithVisitsAndAvailabilitiesAndVacation(long id);
}

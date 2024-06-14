package com.projucti.dspring.repository;

import com.projucti.dspring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}

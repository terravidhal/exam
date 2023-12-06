package com.dojo.fullspring.starter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.fullspring.starter.models.Tables;
import com.dojo.fullspring.starter.models.User;



@Repository
public interface TableRepo extends CrudRepository<Tables, Long> {
	List<Tables> findAll();
}
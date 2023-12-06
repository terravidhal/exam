package com.dojo.fullspring.starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.fullspring.starter.models.Tables;
import com.dojo.fullspring.starter.models.User;
import com.dojo.fullspring.starter.repositories.TableRepo;


@Service
public class TableService {
    //injection
    private final TableRepo tableRepo;
	
	public TableService(TableRepo tableRepo) {
		this.tableRepo = tableRepo;
	}
	
	public List<Tables> allTables(){
		return tableRepo.findAll();
	}

	public List<Tables> allTablesServers(){
		List<Tables> Tablesss = tableRepo.findAll();

		List<Tables> matchingTabless = new ArrayList<>(); 

		for (Tables elt: Tablesss) {
			if (elt.getServer() != null) {
				matchingTabless.add(elt);
			  }
		}

		return matchingTabless; 
	}
	
	public Tables updateTable(Tables table) {
		return tableRepo.save(table);
	}
	
	public List<Tables> getAssignedTables(User server){

       	List<Tables> Tablesss = tableRepo.findAll();

		List<Tables> matchingTabless = new ArrayList<>(); 

		for (Tables elt: Tablesss) {
			if (elt.getServer() == server) {
				matchingTabless.add(elt);
			  }
		}
		return matchingTabless; 

	}

	public List<Tables> getNotServersTables(){

       	List<Tables> Tablesss = tableRepo.findAll();

		List<Tables> matchingTabless = new ArrayList<>(); 

		for (Tables elt: Tablesss) {
			if (elt.getServer() == null) {
				matchingTabless.add(elt);
			  }
		}
		return matchingTabless; 

	}
	
/* 	public List<Tables> getUnassignedTables(User server){

        List<Tables> Tablesss = tableRepo.findAll();

		List<Tables> matchingTabless = new ArrayList<>(); 

		for (Tables elt: Tablesss) {
			if (elt.getServer() != server) {
				matchingTabless.add(elt);
			  }
		}
		return matchingTabless; 

		//return tableRepo.findByUserNotContains(user);// tous les tables qui n st pas assignès a cet utilisateur specifique
	} */
	
	public Tables addTable(Tables table) {
		return tableRepo.save(table);
	}
	
	public void deleteTable(Tables table) {
		tableRepo.delete(table);
	}
	
	public Tables findById(Long id) {
		Optional<Tables> optionalTable = tableRepo.findById(id);
		if(optionalTable.isPresent()) {
			return optionalTable.get();
		}else {
			return null;
		}
	}
}

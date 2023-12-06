package com.dojo.fullspring.starter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.dojo.fullspring.starter.models.Tables;
import com.dojo.fullspring.starter.models.User;
import com.dojo.fullspring.starter.service.TableService;
import com.dojo.fullspring.starter.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class TableController {

    // injection 
    @Autowired
	private UserService userService;

    @Autowired
	private TableService tableService;


    // home page // all tables current user
    @RequestMapping("/dashboard")
	public String dashboards(HttpSession session, 
                            Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId); 
		
		
	 	model.addAttribute("user", user);
		model.addAttribute("assignedTables", tableService.getAssignedTables(user));  // toutes les tables qui  st  assignès a cet utilisateur specifique
		 
		return "dashboard.jsp";
	} 

    // allTable page // all tables all users and Open Table page // all tables all not  users servers
    @RequestMapping("/allTables")
	public String allTabless(HttpSession session, 
                            Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		model.addAttribute("AllTables", tableService.allTablesServers());  

        model.addAttribute("AllTablesNotServers", tableService.getNotServersTables()); 

		 
		return "allTables.jsp";
	} 

    // process remove server for all tables of currentUser
    @RequestMapping("/dashboard/remove/{id}")
	public String openTabless_process_remove(@PathVariable("id") Long id,
                              HttpSession session, 
                              Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

        Tables table = tableService.findById(id);
		model.addAttribute("table", table);

        table.setServer(null);
        tableService.updateTable(table);

		 
		return "redirect:/allTables";
	} 

    // Open Table page // all tables all not  users servers
 /*    @RequestMapping("/opentables")
	public String openTabless(HttpSession session, 
                              Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		} 

		 
        model.addAttribute("AllTablesNotServers", tableService.getNotServersTables()); 
		return "openTables.jsp";
	} */


     // process add server for all tables of currentUser
    @RequestMapping("/dashboard/add/{id}")
	public String openTabless_process_add(@PathVariable("id") Long id,
                              HttpSession session, 
                              Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

        Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);

        Tables table = tableService.findById(id);
		model.addAttribute("table", table);

        table.setServer(user);
        tableService.updateTable(table);
		 
		return "redirect:/allTables";
	} 


    // page create new Tables
    @GetMapping("/tables/new")
	public String newTable(@ModelAttribute("table") Tables table, 
                             HttpSession session, 
                             Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Long userId = (Long) session.getAttribute("userId"); 
		
		model.addAttribute("userId", userId);
		return "new_table.jsp";
	} 


     // processing create new table
    @PostMapping("/tables/new")
	public String addNewTable(@Valid @ModelAttribute("table") Tables table, 
                                BindingResult result, 
                                HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		if(result.hasErrors()) {
			return "new_table.jsp";
		}else {

			tableService.addTable(table); 
            
			return "redirect:/dashboard";
		}
	}


    // edit page table
    @GetMapping("/tables/edit/{id}")
	public String openEditTable(@PathVariable("id") Long id, 
                                   HttpSession session,
                                   Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}


        Long userId = (Long) session.getAttribute("userId"); 
		model.addAttribute("userId", userId);

		
		Tables table = tableService.findById(id);
		model.addAttribute("tableObj", table);

     

		return "edit_table.jsp";
	}


    // processing edit table
    @PostMapping("/tables/edit/{id}")  
	public String editTable(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("tableObj") Tables table, 
			BindingResult result, 
			HttpSession session,
            Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		if(result.hasErrors()) {
           model.addAttribute("tableObj", table);
		   return "edit_table.jsp";
		}else {
			
			tableService.updateTable(table);
			return "redirect:/dashboard";
		}
	}


   

    // delete table
    @RequestMapping("/tables/delete/{id}")
	public String deleteTables(@PathVariable("id") Long id, 
                                HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Tables table = tableService.findById(id); 

		tableService.deleteTable(table); 
		
		return "redirect:/dashboard";
	}


}

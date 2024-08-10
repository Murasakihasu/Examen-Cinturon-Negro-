package com.martinvictoriano.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.martinvictoriano.models.Tabla;
import com.martinvictoriano.models.User;
import com.martinvictoriano.services.TablaService;
import com.martinvictoriano.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TablaController {
	@Autowired
	private final UserService userService;
	@Autowired
	private final TablaService tablaService;
	
	//Constructor
	public TablaController(UserService userService, TablaService tablaService){
		this.userService = userService;
		this.tablaService = tablaService;
		}
	
	
	
	//Formulario tabla
	@GetMapping("/newtabla")
	public String showFormNewTabla(HttpSession sesion, @ModelAttribute("tabla") Tabla newTabla, Model model) {
			if(sesion.getAttribute("user_id") == null){
				return "redirect:";
			}
			model.addAttribute("user", (long)sesion.getAttribute("user_id"));
			return "newtable.jsp";
		}
		
	
	
	// data nueva Tabla
	@PostMapping("/newtabla")
	public String createTabla(@Valid @ModelAttribute("tabla") Tabla newTabla,
								BindingResult result, HttpSession sesion) {
		if (result.hasErrors()) {
			return "newtable.jsp";
			} 
		else {
			User user = userService.getUserById((long)sesion.getAttribute("user_id"));
			newTabla.setUser(user);
			tablaService.createTabla(newTabla);
			return "redirect:/home/"+user.getId();
			}
		}

	
	
		
	// edit Tabla
	@GetMapping("/edittabla/{id}")
	public String formEditTabla(@PathVariable("id") long id, Model model,HttpSession sesion){
		if(sesion.getAttribute("user_id") == null){
			return "redirect:";
		}
		model.addAttribute("user", (long)sesion.getAttribute("user_id"));
		model.addAttribute("tabla", tablaService.findTabla(id));
		return "edittabla.jsp";
	}
	
	
	
	
	// data edit Tabla
	@PostMapping("/edittabla/{id}")
	public String editProgram(@Valid @ModelAttribute("tabla") Tabla newTabla,
							  BindingResult result,
							  @PathVariable("id") Long id,
							  HttpSession sesion) {
		if(result.hasErrors()) {
			return "edittabla.jsp";
		}
		tablaService.updateTabla(newTabla);
		return "redirect:/home/"+(long)sesion.getAttribute("user_id");
		}
	
	
	
	//show other tables
	@GetMapping("/tablas")
	public String showTables(Model model, HttpSession sesion) {
		if(sesion.getAttribute("user_id") == null){
			return "redirect:";
		}
		model.addAttribute("user", (long)sesion.getAttribute("user_id"));
		model.addAttribute("tablas", tablaService.findTablaNull());
		return "tablas.jsp";
	}
	
	
	
	//eliminar relacion
	@GetMapping("/delete/{id}/user")
	public String deleteRelation(@PathVariable("id") Long id, HttpSession sesion) {
		if(sesion.getAttribute("user_id") == null){
			return "redirect:";
		}
		User user = userService.getUserById((long)sesion.getAttribute("user_id"));
		List<Tabla> tablas= user.getTablas();
		Tabla tabla = tablaService.findTabla(id);
		tablas.remove(tabla);
		tabla.setUser(null);
		user.setTablas(tablas);
		tablaService.updateTablaUser(tabla);
		userService.updateUser(user);
		return "redirect:/home/"+(long)sesion.getAttribute("user_id");
	}
	
	
	//agregar relacion
	@GetMapping("/pickup/{id}")
	public String createRelation(@PathVariable("id") Long id, HttpSession sesion) {
		if(sesion.getAttribute("user_id") == null){
			return "redirect:";
		}
		User user = userService.getUserById((long)sesion.getAttribute("user_id"));
		List<Tabla> tablas= user.getTablas();
		Tabla tabla = tablaService.findTabla(id);
		tablas.add(tabla);
		tabla.setUser(user);
		user.setTablas(tablas);
		tablaService.updateTablaUser(tabla);
		userService.updateUser(user);
		return "redirect:/home/"+(long)sesion.getAttribute("user_id");
	}
	
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteProgram(@PathVariable("id") Long id, HttpSession sesion) {
		tablaService.deleteTabla(id);
		return "redirect:/home/"+(long)sesion.getAttribute("user_id");
		}
	}

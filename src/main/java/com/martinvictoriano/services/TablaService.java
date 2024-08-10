package com.martinvictoriano.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martinvictoriano.models.Tabla;
import com.martinvictoriano.repositories.TablaRepository;

@Service
public class TablaService {
	@Autowired
	private final TablaRepository tablaRepository;
	
	//Constructor
	public TablaService(TablaRepository tablaRepository) {
		this.tablaRepository = tablaRepository;
		}
	
	
	//Pick all the tablas
	public List<Tabla> allTablas() {
		return tablaRepository.findAll();
		}
			
	
	//create a Tabla
	public Tabla createTabla(Tabla newTabla) {
		return tablaRepository.save(newTabla);
		}
			
	
	//find a tabla by id
	public Tabla findTabla(Long id) {
		Optional<Tabla> optionalTabla = tablaRepository.findById(id);
		if(optionalTabla.isPresent()) {
			return optionalTabla.get();
		} 
		else {
			return null;
    	}
	}
		
	//update a Tabla
	 public Tabla updateTabla(Tabla updatedTabla) {
		Tabla existingTabla = findTabla(updatedTabla.getId());
		existingTabla.setGuestName(updatedTabla.getGuestName());
	    existingTabla.setGuests(updatedTabla.getGuests());
	    existingTabla.setNote(updatedTabla.getNote());
     
		return tablaRepository.save(existingTabla);
		}	
		
	public Tabla updateTablaUser(Tabla updatedTabla) {
			Tabla existingTabla = findTabla(updatedTabla.getId());
			existingTabla.setGuestName(updatedTabla.getGuestName());
		    existingTabla.setGuests(updatedTabla.getGuests());
		    existingTabla.setNote(updatedTabla.getNote());
		    existingTabla.setUser(updatedTabla.getUser());
	     
			return tablaRepository.save(existingTabla);
			}	
			 
	 
	 //find all tablas without a Guest
	 public List<Tabla> findTablaNull() {
		 List<Tabla> lista = tablaRepository.findAll();
		 List<Tabla> sinPadre = new ArrayList<>(); 
		 for(Tabla tabla : lista){
			 if(tabla.getUser() == null) {
				 sinPadre.add(tabla);
			 }
		 }
		 return sinPadre;
	 }
			
	 
	 //delete a tabla
	public void deleteTabla(Long id) {
		tablaRepository.deleteById(id);
		}
}

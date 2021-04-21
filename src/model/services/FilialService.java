package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Filial;

public class FilialService {
	
	public List<Filial> findAll(){
		List<Filial> list = new ArrayList<>();
		list.add(new Filial(1,"Palhoça"));
		list.add(new Filial(2,"São José"));
		
		return list;
	}
	
}

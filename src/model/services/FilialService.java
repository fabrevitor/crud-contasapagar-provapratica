package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Filial;

public class FilialService {
	
	public List<Filial> findAll(){
		List<Filial> list = new ArrayList<>();
		list.add(new Filial(1,"Palho�a"));
		list.add(new Filial(2,"S�o Jos�"));
		list.add(new Filial(3,"Floripa"));
		list.add(new Filial(4,"Bigua�u"));
		
		return list;
	}
	
}

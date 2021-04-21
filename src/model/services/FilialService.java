package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.FilialDao;
import model.entities.Filial;

public class FilialService {
	
	private FilialDao dao = DaoFactory.createFilialDao();
	
	
	public List<Filial> findAll(){
		return dao.findAll();
	}
	
}

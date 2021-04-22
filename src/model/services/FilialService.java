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
	
	public void saveOrUpdate(Filial obj) {
		if(obj.getCodigo()==null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
}

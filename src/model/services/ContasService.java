package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ContasDao;
import model.entities.Contas;

public class ContasService {
	
	private ContasDao dao = DaoFactory.createContasDao();
	
	
	public List<Contas> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Contas obj) {
		if(obj.getCodigo()==null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
	public void remove(Contas obj) {
		dao.deleteById(obj.getCodigo());
	}
	
}

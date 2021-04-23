package model.services;

import model.dao.AttributeLocalDao;
import model.dao.DaoFactory;
import model.entities.AttributeLocal;

public class AttributeLocalService {
	
	private AttributeLocalDao dao = DaoFactory.createAttributeLocalDao();
	
	
	public AttributeLocal find(){
		return dao.find();
	}
	
	
}

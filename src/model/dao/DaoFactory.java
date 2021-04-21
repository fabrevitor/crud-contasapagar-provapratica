package model.dao;

import db.DB;
import model.dao.impl.FilialDaoJDBC;
import model.dao.impl.ContasDaoJDBC;

public class DaoFactory {

	public static ContasDao createContasDao() {
		return new ContasDaoJDBC(DB.getConnection());
	}
	
	public static FilialDao createFilialDao() {
		return new FilialDaoJDBC(DB.getConnection());
	}
}

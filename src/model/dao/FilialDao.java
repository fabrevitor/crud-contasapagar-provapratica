package model.dao;

import java.util.List;

import model.entities.Filial;

public interface FilialDao {

	void insert(Filial obj);
	void update(Filial obj);
	void deleteById(Integer id);
	Filial findById(Integer id);
	List<Filial> findAll();
}

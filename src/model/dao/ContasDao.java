package model.dao;

import java.util.List;

import model.entities.Filial;
import model.entities.Contas;

public interface ContasDao {

	void insert(Contas obj);
	void update(Contas obj);
	void deleteById(Integer id);
	Contas findById(Integer id);
	List<Contas> findAll();
	List<Contas> findByFilial(Filial filial);
	void pagar(Integer codigo, Double saldo);
}

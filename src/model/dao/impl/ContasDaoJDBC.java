package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.ContasDao;
import model.entities.Contas;
import model.entities.Filial;

public class ContasDaoJDBC implements ContasDao {

	private Connection conn;
	
	public ContasDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Contas obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO contas "
					+ "(descricao, dataRegistro, foiPago, valor, filialCodigo) "
					+ "VALUES "
					+ "(?, CURRENT_TIMESTAMP(), false, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getDescricao());
			//Fixo CURRENT_TIME_STAMP
			//st.setDate(2, new java.sql.Date(obj.getDataRegistro().getTime()));
			//Fixo False
			//st.setBoolean(2, obj.getFoiPago());
			//st.setBoolean(2, false);
			st.setDouble(2, obj.getValor());
			st.setInt(3, obj.getFilial().getCodigo());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCodigo(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Contas obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE contas "
					+ "SET descricao = ?, valor = ?, filialCodigo = ? "
					+ "WHERE codigo = ?");
			
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getValor());
			st.setInt(3, obj.getFilial().getCodigo());
			st.setInt(4, obj.getCodigo());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM contas WHERE codigo = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Contas findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT contas.*,filial.descricao as FilNome "
					+ "FROM contas INNER JOIN filial "
					+ "ON contas.filialCodigo = filial.codigo "
					+ "WHERE contas.codigo = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Filial fil = instantiateFilial(rs);
				Contas obj = instantiateContas(rs, fil);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Contas instantiateContas(ResultSet rs, Filial fil) throws SQLException {
		Contas obj = new Contas();
		obj.setCodigo(rs.getInt("codigo"));
		obj.setDescricao(rs.getString("descricao"));
		obj.setFoiPago(rs.getBoolean("foiPago"));
		obj.setValor(rs.getDouble("valor"));
		obj.setDataRegistro(new java.util.Date(rs.getTimestamp("DataRegistro").getTime()));
		obj.setFilial(fil);
		obj.setFilNome(rs.getString("filNome"));
		return obj;
	}

	private Filial instantiateFilial(ResultSet rs) throws SQLException {
		Filial fil = new Filial();
		fil.setCodigo(rs.getInt("filialCodigo"));
		fil.setNome(rs.getString("filNome"));
		return fil;
	}

	@Override
	public List<Contas> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT contas.*,filial.nome as filNome "
					+ "FROM contas INNER JOIN filial "
					+ "ON contas.filialCodigo = filial.codigo "
					+ "ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Contas> list = new ArrayList<>();
			Map<Integer, Filial> map = new HashMap<>();
			
			while (rs.next()) {
				
				Filial fil = map.get(rs.getInt("filialCodigo"));
				
				if (fil == null) {
					fil = instantiateFilial(rs);
					map.put(rs.getInt("filialCodigo"), fil);
				}
				
				Contas obj = instantiateContas(rs, fil);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Contas> findByFilial(Filial filial) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT contas.*,filial.nome as filNome "
					+ "FROM contas INNER JOIN filial "
					+ "ON contas.filialCodigo = filial.codigo "
					+ "WHERE filialCodigo = ? "
					+ "ORDER BY nome");
			
			st.setInt(1, filial.getCodigo());
			
			rs = st.executeQuery();
			
			List<Contas> list = new ArrayList<>();
			Map<Integer, Filial> map = new HashMap<>();
			
			while (rs.next()) {
				
				Filial fil = map.get(rs.getInt("filialCodigo"));
				
				if (fil == null) {
					fil = instantiateFilial(rs);
					map.put(rs.getInt("filialCodigo"), fil);
				}
				
				Contas obj = instantiateContas(rs, fil);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}

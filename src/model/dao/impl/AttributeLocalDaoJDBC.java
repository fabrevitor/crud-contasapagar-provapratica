package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.AttributeLocalDao;
import model.entities.AttributeLocal;

public class AttributeLocalDaoJDBC implements AttributeLocalDao {

	private Connection conn;

	public AttributeLocalDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public AttributeLocal find() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM attribute_local where codigo = 1");
			rs = st.executeQuery();
			AttributeLocal obj = new AttributeLocal();

			if (rs != null && rs.next()) {
				obj.setSaldoTotal(rs.getDouble("saldoTotal"));
			}

			return obj;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}

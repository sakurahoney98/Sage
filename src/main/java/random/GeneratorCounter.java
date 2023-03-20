package random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneratorCounter {
	
	
	public int contarUsuarios(Connection conexao) throws SQLException {
		
		Statement st;
		st = conexao.createStatement();
		
		String sql = "SELECT count(*) FROM \"USUARIO\"";

		ResultSet rs = st.executeQuery(sql);
		
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	public int contarUsuariosEscolas(Connection conexao) throws SQLException {
		
		Statement st;
		st = conexao.createStatement();
		
		String sql = "SELECT count(*) FROM \"USUARIO\" where TIPO_USUARIO = 1";

		ResultSet rs = st.executeQuery(sql);
		
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	public int contarUsuariosColetoras(Connection conexao) throws SQLException {
		
		Statement st;
		st = conexao.createStatement();
		
		String sql = "SELECT count(*) FROM \"USUARIO\" where TIPO_USUARIO = 2";

		ResultSet rs = st.executeQuery(sql);
		
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	
	public int contarChamados(Connection conexao) throws SQLException {
		
		Statement st;
		st = conexao.createStatement();
		
		String sql = "SELECT count(*) FROM \"CHAMADO\" ";

		ResultSet rs = st.executeQuery(sql);
		
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	

	public int contarChamadosAbertos(Connection conexao) throws SQLException {
		
		Statement st;
		st = conexao.createStatement();
		
		String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 1";

		ResultSet rs = st.executeQuery(sql);
		
		
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	public int contarChamadosEmColeta(Connection conexao) throws SQLException {
		
		Statement st;
		st = conexao.createStatement();
		
		String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 2";

		ResultSet rs = st.executeQuery(sql);
		
		rs.next();
		
		return rs.getInt(1);
		
	}

public int contarChamadosColetados(Connection conexao) throws SQLException {
	
	Statement st;
	st = conexao.createStatement();
	
	String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 3";

	ResultSet rs = st.executeQuery(sql);
	
	rs.next();
	
	return rs.getInt(1);
	
}

public int contarChamadosCancelados(Connection conexao) throws SQLException {
	
	Statement st;
	st = conexao.createStatement();
	
	String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 5";

	ResultSet rs = st.executeQuery(sql);
	
	rs.next();
	
	return rs.getInt(1);
	
}



public int contarChamadosAbertosUser(Connection conexao, String id_user) throws SQLException {
	
	Statement st;
	st = conexao.createStatement();
	
	String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 1 and id_escola = '" + id_user + "'";

	ResultSet rs = st.executeQuery(sql);
	
	rs.next();
	
	return rs.getInt(1);
	
}

public int contarChamadosEmColetaUser(Connection conexao, String id_user) throws SQLException {
	
	Statement st;
	st = conexao.createStatement();
	
	String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 2 and (id_escola = '" + id_user + "' or id_coletor = '" + id_user + "'";

	ResultSet rs = st.executeQuery(sql);
	
	rs.next();
	
	return rs.getInt(1);
	
}

public int contarChamadosColetadosUser(Connection conexao, String id_user) throws SQLException {

Statement st;
st = conexao.createStatement();

String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 3 and (id_escola = '" + id_user + "' or id_coletor = '" + id_user + "'";


ResultSet rs = st.executeQuery(sql);

rs.next();

return rs.getInt(1);

}

public int contarChamadosCanceladosUser(Connection conexao, String id_user) throws SQLException {

Statement st;
st = conexao.createStatement();

String sql = "SELECT count(*) FROM \"CHAMADO\" where status_chamado = 5 and (id_escola = '" + id_user + "' or id_coletor = '" + id_user + "'";


ResultSet rs = st.executeQuery(sql);

rs.next();

return rs.getInt(1);

}
	
	
	

}

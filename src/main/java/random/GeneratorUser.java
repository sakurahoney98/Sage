package random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import br.ucsal.sage.user.controller.UserController;
import br.ucsal.sage.user.model.Adress;

public class GeneratorUser {

	RewriteMethods user = new RewriteMethods();
	UserController uc = new UserController();

	public void gerarUsuario(Connection conexao, int quantidade) throws SQLException {

		Random random = new Random();

		for (int i = 0; i < quantidade;) {

			int tipo_usuario = random.nextInt(2) + 1;

			String nome = "";

			if (tipo_usuario == 1)

				nome = gerarNomeEscola();
			else
				nome = gerarNomeColetora();

			String apelido = nome;

			String email = apelido.replaceAll(" ", "").toLowerCase() + "@gmail.com";

			String senha = apelido.substring(0, nome.indexOf(" ")).toLowerCase() + "123";

			int cod = random.nextInt(24592) + 1;

			Adress endereco_aux = uc.getAdressByZipCode(pegarCepPeloCodigo(cod));

			endereco_aux.setComplemento((random.nextInt(491) + 10) + "");

			String endereco = endereco_aux.getLogradouro() + ", " + endereco_aux.getComplemento() + " - "
					+ endereco_aux.getBairro().replaceAll("'", "''") + ", CEP: " + endereco_aux.getCep();

			String cidade = "Salvador";

			String estado = "BA";

			String cnpj = (random.nextInt(90) + 10) + "." + (random.nextInt(900) + 100) + "."
					+ (random.nextInt(900) + 100) + "/00" + random.nextInt(10) + random.nextInt(10) + "-"
					+ (random.nextInt(90) + 10);

			String telefone = "(71) " + (random.nextInt(20) + 20) + (random.nextInt(90) + 10)
					+ (random.nextInt(90) + 10) + (random.nextInt(90) + 10);

			String celular = "(71) 9" + (random.nextInt(20) + 80) + (random.nextInt(90) + 10)
					+ (random.nextInt(90) + 10) + (random.nextInt(90) + 10);

			String sobre = "Descrição do usuário " + apelido;

			String dia = random.nextInt(28) + 1 + "";
			if (dia.length() < 2)
				dia = "0" + dia;
			String mes = random.nextInt(12) + 1 + "";
			if (mes.length() < 2)
				mes = "0" + mes;
			String ano = random.nextInt(23) + 2000 + "";
			String data = dia + "/" + mes + "/" + ano;

			if (user.createUser(conexao, email, senha, nome, apelido, tipo_usuario, sobre, cnpj, endereco, cidade,
					estado, telefone, celular, data))
				i++;

		}
		System.out.println("Usuários gerados");

	}

	public String gerarNomeEscola() {

		ArrayList<String> listaNomes = new ArrayList<String>();
		ArrayList<String> listaStatus = new ArrayList<String>();

		Random random = new Random();

		listaStatus.add("Academy");
		listaStatus.add("Institute");
		listaStatus.add("College");
		listaStatus.add("High");
		listaStatus.add("Conservatory");
		listaStatus.add("Technical School");
		listaStatus.add("School for Boys");
		listaStatus.add("School for Girls");
		listaStatus.add("University");
		listaStatus.add("School");
		listaStatus.add("Secondary School");

		listaNomes.add("Clearwater");
		listaNomes.add("Mountainridge");
		listaNomes.add("Angelwood");
		listaNomes.add("Storm");
		listaNomes.add("Coast");
		listaNomes.add("Riverbank");
		listaNomes.add("Lakewood");
		listaNomes.add("Sunny");
		listaNomes.add("Faith");
		listaNomes.add("Mountainview");
		listaNomes.add("River");
		listaNomes.add("Fork");
		listaNomes.add("Silver");
		listaNomes.add("Oak");
		listaNomes.add("Timbe");
		listaNomes.add("Creek ");
		listaNomes.add("Blue");
		listaNomes.add("Angelwood");
		listaNomes.add("Diamond");
		listaNomes.add("Pinewood");
		listaNomes.add("Saint Helena");
		listaNomes.add("Kindergarten");
		listaNomes.add("Edgewood");
		listaNomes.add("Laguna");
		listaNomes.add("Elementary");
		listaNomes.add("Skyline");
		listaNomes.add("Hawking");
		listaNomes.add("Westview");
		listaNomes.add("Seal");
		listaNomes.add("Bay");
		listaNomes.add("Pacific");
		listaNomes.add("Grove");
		listaNomes.add("Silverleaf");
		listaNomes.add("Eastview");

		String nomeFinal = "";

		int quantidadeNomes = random.nextInt(3) + 1;

		for (int i = 0; i < quantidadeNomes; i++) {
			int pos = random.nextInt(listaNomes.size());
			if (i != 0)
				nomeFinal = nomeFinal + " " + listaNomes.get(pos);
			else
				nomeFinal = listaNomes.get(pos);
			listaNomes.remove(pos);
		}

		return nomeFinal + " " + listaStatus.get(random.nextInt(listaStatus.size()));

	}

	public String gerarNomeColetora() {

		ArrayList<String> listaNomes = new ArrayList<String>();

		Random random = new Random();

		listaNomes.add("Flora");
		listaNomes.add("Tropical");
		listaNomes.add("Routine");
		listaNomes.add("Soul");
		listaNomes.add("Bella");
		listaNomes.add("Garden");
		listaNomes.add("Energy");
		listaNomes.add("Sunshine");
		listaNomes.add("Jasmine");
		listaNomes.add("Sky");
		listaNomes.add("Eco");
		listaNomes.add("Nature");
		listaNomes.add("Sparkle");
		listaNomes.add("Flower");
		listaNomes.add("Rainbow");
		listaNomes.add("Sweet");
		listaNomes.add("Green");
		listaNomes.add("Lady");
		listaNomes.add("Life");
		listaNomes.add("Motion");
		listaNomes.add("Active");
		listaNomes.add("Beautiful");
		listaNomes.add("Planet");
		listaNomes.add("Star");
		listaNomes.add("Wild");
		listaNomes.add("Ame");
		listaNomes.add("Love");
		listaNomes.add("Ocean");
		listaNomes.add("Canguru");
		listaNomes.add("Element");
		listaNomes.add("Tour");
		listaNomes.add("CenterPoint");
		listaNomes.add("More");
		listaNomes.add("Peaches");

		String nomeFinal = "";

		int quantidadeNomes = random.nextInt(2) + 1;

		for (int i = 0; i < quantidadeNomes; i++) {
			int pos = random.nextInt(listaNomes.size());
			if (i != 0)
				nomeFinal = nomeFinal + " " + listaNomes.get(pos);
			else
				nomeFinal = listaNomes.get(pos);
			listaNomes.remove(pos);
		}

		return nomeFinal + " Coleta";

	}

	public String pegarCepPeloCodigo(int codigo) {

		String url = "jdbc:postgresql://localhost:5432/LOCALIDADES";
		String usuario = "postgres";
		String senha = "postgres";
		Connection conexao;

		// Tentando fazer conexão com o banco de dados
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			Statement st;
			st = conexao.createStatement();
			String sql = "SELECT cep\r\n" + "	FROM public.endereco \r\n" + "	where id_endereco = " + codigo + "\r\n"
					+ "	;";

			ResultSet rs = st.executeQuery(sql);

			rs.next();

			return rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}

package random;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import br.ucsal.sage.connection.Conexao;

public class Principal {

	public static void main(String[] args) throws SQLException {
		GeneratorUser g = new GeneratorUser();
		GeneratorRequest gr = new GeneratorRequest();
		GeneratorAssumeRequest ar = new GeneratorAssumeRequest();
		GeneratorCancelRequest gcr = new GeneratorCancelRequest();
		GeneratorCancelCollection gcc = new GeneratorCancelCollection();
		GeneratorCompletedRequest gcr2 = new GeneratorCompletedRequest();
		Conexao conect = new Conexao();
		
		int op = 0;
		
		do {
			System.out.println(":::::GERADORES::::::");
			System.out.println("1 Gerar usuário");
			System.out.println("2 Gerar chamado");
			System.out.println("3 Assumir coleta");
			System.out.println("4 Cancelar coleta");
			System.out.println("5 Cancelar chamado");
			System.out.println("6 Concluir chamado");
			System.out.println("7 Relatórios");
			System.out.println("0 Sair");
			System.out.print("\nOpção: ");
			Scanner sc = new Scanner(System.in);
			op = sc.nextInt();
			
			if(op != 0) {
				int quantidade = 0;
				if(op != 7) {
				System.out.println("Informe a quantidade: ");
				quantidade = sc.nextInt();
				}
				
				
				switch(op) {
				case 1:
					g.gerarUsuario(conect.getConexao(), quantidade);
					break;
					
				case 2:
					gr.gerarChamado(conect.getConexao(), quantidade);
					break;
					
				case 3:
					ar.assumirColeta(conect.getConexao(), quantidade);
					break;
					
				case 4:
					gcc.cancelarColeta(conect.getConexao(), quantidade);
					break;
					
				case 5:
					gcr.cancelarChamado(conect.getConexao(), quantidade);
					break;
					
				case 6:
					gcr2.coletaRealizada(conect.getConexao(), quantidade);
					break;
					
				case 7:
					relatorios(conect.getConexao());
					break;
					
				default:
					break;
				}
			}
			
			System.out.println("\n\n");
		}while (op != 0);
		
	
	}
	
	
	public static void relatorios(Connection conexao) throws SQLException {
		GeneratorTop10 top10 = new GeneratorTop10();
		GeneratorPositionRanking gpr = new GeneratorPositionRanking();
		GeneratorAllRequestOpen gar = new GeneratorAllRequestOpen();
		GeneratorReportsByYear grba = new GeneratorReportsByYear();
		GeneratorReportsByMonth grbm = new GeneratorReportsByMonth ();
		GeneratorAllUserRequest gaur = new GeneratorAllUserRequest();
		
		int op;
		System.out.println("\n\n");
		System.out.println(":::::RELATÓRIOS::::::");
		System.out.println("1 Top 10 das Escolas");
		System.out.println("2 Posição no Ranking");
		System.out.println("3 Todos os chamados abertos");
		System.out.println("4 Quantidade reciclada por ano");
		System.out.println("5 Quantidade reciclada por mês");
		System.out.println("6 Todas as coletas do usuário");
		System.out.println("7 Contador");
		System.out.print("\nOpção: ");
		Scanner sc = new Scanner(System.in);
		op = sc.nextInt();
		
		
		switch(op) {
		case 1:
			top10.retornarTop10Escolas(conexao);
			break;
			
		case 2:
			System.out.println("Informe a quantidade:");
			int quantidade = sc.nextInt();
			gpr.pegarPosicaoRaking(conexao, quantidade);
			break;
		
		case 3:
			gar.retornarTodosChamadosAbertos(conexao);
			break;
		case 4:
			System.out.println("Informe a quantidade:");
			quantidade = sc.nextInt();
			grba.gerarRelatórioPorAno(conexao, quantidade);
			break;
			
			
		case 5:
			System.out.println("Informe a quantidade:");
			quantidade = sc.nextInt();
			grbm.gerarRelatórioPorMes(conexao, quantidade);
			break;
			
		case 6:
			System.out.println("Informe a quantidade:");
			quantidade = sc.nextInt();
			gaur.retornarTodosChamados(conexao, quantidade);
			break;
			
		case 7:
			contador(conexao);
			break;
			
		default:
			break;
		
		}
		
		System.out.println("\n\n");
		
		
		
	}
	
	public static void contador (Connection conexao) throws SQLException {
		GeneratorCounter gc = new GeneratorCounter();
		
		
		System.out.println("\n\n");
		System.out.println(":::::CONTADORES::::::");
		System.out.println("1 Contar usuários");
		System.out.println("2 Contar Escolas");
		System.out.println("3 Contar Coletoras");
		System.out.println("4 Contar chamados");
		System.out.println("5 Contar chamados abertos");
		System.out.println("6 Contar chamados em coleta");
		System.out.println("7 Contar chamados coletados");
		System.out.println("8 Contar chamados cancelados");
		System.out.println("9 Contar chamados abertos por usuario");
		System.out.println("10 Contar chamados em coleta por usuario");
		System.out.println("11 Contar chamados coletados por usuario");
		System.out.println("12 Contar chamados cancelados por usuario");
		System.out.print("\nOpção: ");
		Scanner sc = new Scanner(System.in);
		int op = sc.nextInt();
		String id = "";
		
		switch(op) {
		case 1:
			System.out.println("Retorno: " + gc.contarUsuarios(conexao)); 
			break;
			
		case 2:
			System.out.println("Retorno: " + gc.contarUsuariosEscolas(conexao)); 
			break;
		
		case 3:
			System.out.println("Retorno: " + gc.contarUsuariosColetoras(conexao)); 
			break;
		case 4:
			System.out.println("Retorno: " + gc.contarChamados(conexao)); 
			break;
			
			
		case 5:
			System.out.println("Retorno: " + gc.contarChamadosAbertos(conexao)); 
			break;
			
		case 6:
			System.out.println("Retorno: " + gc.contarChamadosEmColeta(conexao)); 
			break;
			
		case 7:
			System.out.println("Retorno: " + gc.contarChamadosColetados(conexao)); 
			break;
			
		case 8:
			System.out.println("Retorno: " + gc.contarChamadosCancelados(conexao)); 
			break;
			
		case 9:
			System.out.print("Informe o ID: ");
			id = sc.next();
			System.out.println("\nRetorno: " + gc.contarChamadosAbertosUser(conexao, id.toUpperCase()));
			break;
			
		case 10:
			System.out.print("Informe o ID: ");
			id = sc.next();
			System.out.println("\nRetorno: " + gc.contarChamadosEmColetaUser(conexao, id.toUpperCase()));
			break;
			
		case 11:
			System.out.print("Informe o ID: ");
			id = sc.next();
			System.out.println("\nRetorno: " + gc.contarChamadosColetadosUser(conexao, id.toUpperCase()));
			break;
			
		case 12:
			System.out.print("Informe o ID: ");
			id = sc.next();
			System.out.println("\nRetorno: " + gc.contarChamadosCanceladosUser(conexao, id.toUpperCase()));
			break;
			
			
		default:
			break;
		
		}
		
		System.out.println("\n\n");
		
		
		
	}

}

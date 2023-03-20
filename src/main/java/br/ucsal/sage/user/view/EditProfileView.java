/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.ucsal.sage.user.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.ucsal.sage.collector.view.PageCollectorView;
import br.ucsal.sage.requester.view.PageRequesterView;
import br.ucsal.sage.user.controller.UserController;
import br.ucsal.sage.user.model.Adress;
import br.ucsal.sage.user.model.User;

/**
 *
 * @author Suporte TI
 */
public class EditProfileView extends javax.swing.JFrame {

	/**
	 * Creates new form Principal
	 */

	private CanvasBackground tela;
	private static User usuario;
	private MaskFormatter CNPJMask;
	private MaskFormatter TelefoneMask;
	private MaskFormatter CelularMask;
	private MaskFormatter CEPMask;
	private UserController controller;
	private static Connection conect;

	public static void setConect(Connection c) {
		conect = c;
	}

	public static void setUsuario(User u) {
		usuario = u;
	}

	public EditProfileView() {
		initComponents();
		setExtendedState(MAXIMIZED_BOTH);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setLayout(new GridLayout());
		tela = new CanvasBackground("src/Imagens/editar_perfil.png");

		jLabel1 = new javax.swing.JLabel();
		jTextFieldCNPJ = new javax.swing.JFormattedTextField();
		jLabel9 = new javax.swing.JLabel();
		jTextFieldNome = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		jTextFieldLogradouro = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jTextFieldEmail = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		jTextFieldCep = new javax.swing.JFormattedTextField();
		jLabel13 = new javax.swing.JLabel();
		jTextFieldTelefone = new javax.swing.JFormattedTextField();
		jButtonSalvar = new javax.swing.JButton();
		jButtonCancelar = new javax.swing.JButton();
		jLabel14 = new javax.swing.JLabel();
		jTextFieldComplemento = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		jTextFieldBairro = new javax.swing.JTextField();
		jLabel16 = new javax.swing.JLabel();
		jTextFieldApelido = new javax.swing.JTextField();
		jLabel17 = new javax.swing.JLabel();
		jTextFieldSenha = new javax.swing.JTextField();
		jButtonBuscar = new javax.swing.JButton();
		jLabel18 = new javax.swing.JLabel();
		jTextFieldCidade = new javax.swing.JTextField();
		jLabel19 = new javax.swing.JLabel();
		jTextFieldUF = new javax.swing.JTextField();
		jLabel20 = new javax.swing.JLabel();
		jTextFieldCelular = new javax.swing.JFormattedTextField();
		jLabelAvisoErro = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaSobre = new javax.swing.JTextArea();

		try {
			CNPJMask = new MaskFormatter("##.###.###/####-##");
			TelefoneMask = new MaskFormatter("(##) ####-####");
			CelularMask = new MaskFormatter("(##) #####-####");
			CEPMask = new MaskFormatter("########");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel1.setText("CNPJ:");

		jTextFieldCNPJ.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldCNPJ.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldCNPJ.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

		jTextFieldCNPJ.setValue(null);
		jTextFieldCNPJ.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
		jTextFieldCNPJ.setText(usuario.getCnpj());

		jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel9.setText("Nome:");

		jTextFieldNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldNome.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldNome.setText(usuario.getNome());

		jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel10.setText("E-mail:");

		jTextFieldLogradouro.setBackground(new java.awt.Color(102, 102, 102));
		jTextFieldLogradouro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		jTextFieldLogradouro.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldLogradouro.setText(usuario.getEndereco().substring(0, usuario.getEndereco().indexOf(",")));

		jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel11.setText("CEP:");

		jTextFieldEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldEmail.setText(usuario.getEmail());

		jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel12.setText("Logradouro:");

		jTextFieldCep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldCep.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldCep.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldCep.setValue(null);
		jTextFieldCep.setFormatterFactory(new DefaultFormatterFactory(CEPMask));

		jTextFieldCep.setText(usuario.getEndereco().substring(usuario.getEndereco().indexOf("CEP") + 5,
				usuario.getEndereco().length()));

		jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel13.setText("Telefone:");

		jTextFieldTelefone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldTelefone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldTelefone.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldTelefone.setValue(null);
		jTextFieldTelefone.setFormatterFactory(new DefaultFormatterFactory(TelefoneMask));

		jTextFieldTelefone.setText(usuario.getTelefone());

		jButtonSalvar.setBackground(new java.awt.Color(51, 204, 0));
		jButtonSalvar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jButtonSalvar.setForeground(new java.awt.Color(255, 255, 255));
		jButtonSalvar.setText("Salvar");
		jButtonSalvar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
		jButtonSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jButtonSalvarMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButtonCancelar.setBackground(new java.awt.Color(51, 204, 0));
		jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jButtonCancelar.setForeground(new java.awt.Color(255, 255, 255));
		jButtonCancelar.setText("Cancelar");
		jButtonCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
		jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCancelarActionPerformed(evt);
			}
		});

		jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel14.setText("Complemento:");

		jTextFieldComplemento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldComplemento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldComplemento.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldComplemento.setText(usuario.getEndereco().substring(usuario.getEndereco().indexOf(",") + 2,
				usuario.getEndereco().lastIndexOf("-")));

		jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel15.setText("Bairro:");

		jTextFieldBairro.setBackground(new java.awt.Color(102, 102, 102));
		jTextFieldBairro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		jTextFieldBairro.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldBairro.setText(usuario.getEndereco().substring(usuario.getEndereco().lastIndexOf("-") + 2,
				usuario.getEndereco().lastIndexOf(",")));

		jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel16.setText("Apelido:");

		jTextFieldApelido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldApelido.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldApelido.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldApelido.setText(usuario.getApelido());

		jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel17.setText("Senha:");

		jTextFieldSenha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldSenha.setText(usuario.getSenha());

		jButtonBuscar.setBackground(new java.awt.Color(51, 204, 0));
		jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jButtonBuscar.setForeground(new java.awt.Color(255, 255, 255));
		jButtonBuscar.setText("Buscar");
		jButtonBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
		jButtonBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jButtonBuscarMouseClicked(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel18.setText("Cidade:");

		jTextFieldCidade.setBackground(new java.awt.Color(102, 102, 102));
		jTextFieldCidade.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		jTextFieldCidade.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldCidade.setText(usuario.getCidade());

		jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel19.setText("UF:");

		jTextFieldUF.setBackground(new java.awt.Color(102, 102, 102));
		jTextFieldUF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		jTextFieldUF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldUF.setText(usuario.getEstado());

		jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel20.setText("Celular:");

		jTextFieldCelular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextFieldCelular.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
		jTextFieldCelular.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		jTextFieldCelular.setValue(null);
		jTextFieldCelular.setFormatterFactory(new DefaultFormatterFactory(CelularMask));

		jTextFieldCelular.setText(usuario.getCelular());

		jLabelAvisoErro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabelAvisoErro.setForeground(new java.awt.Color(204, 0, 0));
		jLabelAvisoErro.setText("");

		jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
		jLabel2.setText("Editar Perfil");

		jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel21.setText("Sobre:");

		jTextAreaSobre.setColumns(20);
		jTextAreaSobre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTextAreaSobre.setRows(5);
		jTextAreaSobre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));
		jTextAreaSobre.setText(usuario.getSobre_usuario());
		jScrollPane1.setViewportView(jTextAreaSobre);

		jTextFieldBairro.setEnabled(false);
		jTextFieldCidade.setEnabled(false);
		jTextFieldLogradouro.setEnabled(false);
		jTextFieldUF.setEnabled(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(tela);
		tela.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(32, 32, 32).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 276,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(262, 262, 262).addComponent(jLabelAvisoErro,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
										.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(256, 256, 256))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(jTextFieldCep,
																		javax.swing.GroupLayout.DEFAULT_SIZE, 176,
																		Short.MAX_VALUE)
																.addGap(37, 37, 37).addComponent(jButtonBuscar,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 88,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jTextFieldTelefone,
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldEmail,
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldCNPJ,
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldLogradouro,
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldNome,
																javax.swing.GroupLayout.Alignment.LEADING))
														.addGap(36, 36, 36)))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE,
																91, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldComplemento))
												.addGap(33, 33, 33)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldBairro).addComponent(jLabel15,
																javax.swing.GroupLayout.PREFERRED_SIZE, 91,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldCidade,
																javax.swing.GroupLayout.DEFAULT_SIZE, 313,
																Short.MAX_VALUE))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jTextFieldUF,
																		javax.swing.GroupLayout.DEFAULT_SIZE, 142,
																		Short.MAX_VALUE)
																.addComponent(jLabel19,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 80,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGap(162, 162, 162).addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE,
																80, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldApelido,
																javax.swing.GroupLayout.DEFAULT_SIZE, 311,
																Short.MAX_VALUE)
														.addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE,
																80, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldSenha)
														.addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE,
																80, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextFieldCelular)))))
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addGap(40, 40, 40)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup().addGap(14, 14, 14)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabelAvisoErro, javax.swing.GroupLayout.PREFERRED_SIZE,
														23, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jTextFieldCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9).addComponent(jLabel16))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextFieldApelido, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel10).addComponent(jLabel17))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel11).addComponent(jLabel18).addComponent(jLabel19))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextFieldUF, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup().addGap(1, 1, 1).addComponent(
														jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel12).addComponent(jLabel14).addComponent(jLabel15))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextFieldLogradouro,
														javax.swing.GroupLayout.PREFERRED_SIZE, 35,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextFieldComplemento,
														javax.swing.GroupLayout.PREFERRED_SIZE, 35,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel13).addComponent(jLabel20))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextFieldTelefone,
														javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, Short.MAX_VALUE)
										.addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 15,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE,
														30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
		getContentPane().add(tela);
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelarActionPerformed
		new MyProfileView().main(null);
		this.dispose();
	}// GEN-LAST:event_jButtonCancelarActionPerformed

	private void jButtonSalvarMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {// GEN-FIRST:event_jButtonSalvarMouseClicked

		controller = new UserController();
		String endereco = jTextFieldLogradouro.getText() + ", " + jTextFieldComplemento.getText() + " - "
				+ jTextFieldBairro.getText().replaceAll("'", "''") + ", CEP: " + jTextFieldCep.getText();

		if (controller.alterUserData(conect, usuario.getId_usuario(), jTextFieldEmail.getText(),
				jTextFieldSenha.getText(), jTextFieldNome.getText(), jTextFieldApelido.getText(),
				jTextAreaSobre.getText(), jTextFieldCNPJ.getText(), endereco, jTextFieldCidade.getText(),
				jTextFieldUF.getText(), jTextFieldTelefone.getText(), jTextFieldCelular.getText())) {
			usuario = controller.getAllUserData(conect, jTextFieldEmail.getText());
			MyProfileView.setUsuario(usuario);
			PageCollectorView.setUsuario(usuario);
			PageRequesterView.setUsuario(usuario);
			new MyProfileView().main(null);
			this.dispose();
		} else
			jLabelAvisoErro.setText("ERRO! E-mail existente/inválido ou campo obrigatório foi deixado em branco.");

	}// GEN-LAST:event_jButtonSalvarMouseClicked

	private void jButtonBuscarMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
		controller = new UserController();

		Adress endereco = controller.getAdressByZipCode(jTextFieldCep.getText());

		if (endereco.getLogradouro() == null) {
			jTextFieldCidade.setText("");
			jTextFieldUF.setText("");
			jTextFieldBairro.setText("");
			jTextFieldLogradouro.setText("");
			jTextFieldBairro.setEnabled(true);
			jTextFieldCidade.setEnabled(true);
			jTextFieldLogradouro.setEnabled(true);
			jTextFieldUF.setEnabled(true);
			jTextFieldBairro.setBackground(new java.awt.Color(0, 0, 0, 0));
			jTextFieldCidade.setBackground(new java.awt.Color(0, 0, 0, 0));
			jTextFieldLogradouro.setBackground(new java.awt.Color(0, 0, 0, 0));
			jTextFieldUF.setBackground(new java.awt.Color(0, 0, 0, 0));
			jLabelAvisoErro.setText("CEP não encontrado. Informe um novo CEP ou preencha o endereço manualmente.");

		} else {
			jLabelAvisoErro.setText("");
			jTextFieldBairro.setBackground(new java.awt.Color(102, 102, 102));
			jTextFieldCidade.setBackground(new java.awt.Color(102, 102, 102));
			jTextFieldLogradouro.setBackground(new java.awt.Color(102, 102, 102));
			jTextFieldUF.setBackground(new java.awt.Color(102, 102, 102));
			jTextFieldBairro.setEnabled(false);
			jTextFieldCidade.setEnabled(false);
			jTextFieldLogradouro.setEnabled(false);
			jTextFieldUF.setEnabled(false);
			jTextFieldBairro.setText(endereco.getBairro());
			jTextFieldCidade.setText("Salvador");
			jTextFieldLogradouro.setText(endereco.getLogradouro());
			jTextFieldUF.setText("Bahia");

		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(EditProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(EditProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(EditProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(EditProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EditProfileView().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonBuscar;
	private javax.swing.JButton jButtonCancelar;
	private javax.swing.JButton jButtonSalvar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel jLabelAvisoErro;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaSobre;
	private javax.swing.JTextField jTextFieldApelido;
	private javax.swing.JTextField jTextFieldBairro;
	private javax.swing.JFormattedTextField jTextFieldCNPJ;
	private javax.swing.JFormattedTextField jTextFieldCep;
	private javax.swing.JTextField jTextFieldCidade;
	private javax.swing.JTextField jTextFieldComplemento;
	private javax.swing.JTextField jTextFieldEmail;
	private javax.swing.JTextField jTextFieldLogradouro;
	private javax.swing.JTextField jTextFieldNome;
	private javax.swing.JTextField jTextFieldSenha;
	private javax.swing.JFormattedTextField jTextFieldTelefone;
	private javax.swing.JFormattedTextField jTextFieldCelular;
	private javax.swing.JTextField jTextFieldUF;
	// End of variables declaration//GEN-END:variables
}

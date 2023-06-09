/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.ucsal.sage.collector.view;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;

import br.ucsal.sage.calendario.util.TextDataChooser;
import br.ucsal.sage.request.controller.RequestController;
import br.ucsal.sage.request.model.Request;
import br.ucsal.sage.request.view.RequestDetailsView;
import br.ucsal.sage.user.model.User;
import br.ucsal.sage.user.view.CanvasBackground;

/**
 *
 * @author Suporte TI
 */
public class TakeRequestView1 extends javax.swing.JFrame {

	/**
	 * Creates new form Solicitacao_Coleta_1
	 */

	private CanvasBackground tela;
	private RequestController controller;
	private static Request chamado;
	private static User usuario;
	private static Connection conect;

	public static void setChamado(Request r) {
		chamado = r;
	}

	public static void setUsuario(User u) {
		usuario = u;
	}

	public static void setConect(Connection c) {
		conect = c;
	}

	public TakeRequestView1() {

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
		tela = new CanvasBackground("src/Imagens/cadastro_1.png");

		jButtonProximo = new javax.swing.JButton();
		jButtonAnterior = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabelAvisoErro = new javax.swing.JLabel();
		txtData = new TextDataChooser();

		txtData.setColumns(10);
		txtData.setFont(new java.awt.Font("Arial", 0, 20));
		txtData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButtonProximo.setBackground(new java.awt.Color(51, 204, 0));
		jButtonProximo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jButtonProximo.setForeground(new java.awt.Color(255, 255, 255));
		jButtonProximo.setText("Próximo");
		jButtonProximo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
		jButtonProximo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jButtonProximoMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButtonAnterior.setBackground(new java.awt.Color(51, 204, 0));
		jButtonAnterior.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jButtonAnterior.setForeground(new java.awt.Color(255, 255, 255));
		jButtonAnterior.setText("Anterior");
		jButtonAnterior.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
		jButtonAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jButtonAnteriorMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jScrollPane1.setBorder(null);

		jTextArea1.setEditable(false);
		jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
		jTextArea1.setColumns(10);
		jTextArea1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
		jTextArea1.setLineWrap(true);
		jTextArea1.setRows(2);
		jTextArea1.setTabSize(5);
		jTextArea1.setText("Informe o dia de previsão de coleta:");
		jTextArea1.setBorder(null);
		jTextArea1.setOpaque(false);
		jTextArea1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
		jTextArea1.setSelectionColor(new java.awt.Color(255, 255, 255));
		jScrollPane1.setViewportView(jTextArea1);

		jLabelAvisoErro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabelAvisoErro.setForeground(new java.awt.Color(204, 0, 0));
		jLabelAvisoErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(tela);
		tela.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(67, Short.MAX_VALUE).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(200, 200, 200))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(100, 100, 100)))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabelAvisoErro, javax.swing.GroupLayout.PREFERRED_SIZE, 513,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup().addGap(100, 100, 100).addComponent(
												txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 306,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(200, 200, 200)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(250, 250, 250)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(67, 67, 67)
						.addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(77, 77, 77).addComponent(jLabelAvisoErro)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(50, 50, 50)));

		pack();
		getContentPane().add(tela);
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonAnteriorMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {// GEN-FIRST:event_jButtonAnteriorMouseClicked
		new RequestDetailsView().main(null);
		this.dispose();
	}// GEN-LAST:event_jButtonAnteriorMouseClicked

	private void jButtonProximoMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {// GEN-FIRST:event_jButtonProximoMouseClicked
		controller = new RequestController();
		if (controller.validateDate(txtData.getText())) {
			String mensagem;
			chamado.setPrevisao_coleta(txtData.getText());
			if (controller.getStatusRequest(conect, chamado.getId_chamado()) == 1) {
				controller.takeRequest(conect, chamado.getId_chamado(), usuario.getId_usuario(), txtData.getText());
				mensagem = "Solicitação concluída com sucesso";
			} else
				mensagem = "Opa, parece que outra coletora já assumiu esse chamado :(";

			TakeRequestView2.setMensagem(mensagem);
			TakeRequestView2.setConect(conect);
			TakeRequestView2.setUsuario(usuario);
			new TakeRequestView2().main(null);
			this.dispose();
		} else
			jLabelAvisoErro.setText("A hora informada é anterior ao dia de hoje ou inválida.");
	}// GEN-LAST:event_jButtonProximoMouseClicked

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
			java.util.logging.Logger.getLogger(TakeRequestView1.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TakeRequestView1.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TakeRequestView1.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TakeRequestView1.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
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
				new TakeRequestView1().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonAnterior;
	private javax.swing.JButton jButtonProximo;
	private javax.swing.JLabel jLabelAvisoErro;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private TextDataChooser txtData;
	// End of variables declaration//GEN-END:variables
}

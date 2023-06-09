/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.ucsal.sage.collector.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;

import br.ucsal.sage.request.view.MyRequests;
import br.ucsal.sage.user.model.User;
import br.ucsal.sage.user.view.CanvasBackground;

/**
 *
 * @author Suporte TI
 */
public class CompletedRequestView4 extends javax.swing.JFrame {

	/**
	 * Creates new form Solicitacao_Coleta_1
	 */

	private static Connection conect;
	private static User usuario;

	CanvasBackground tela;

	public static void setUsuario(User u) {
		usuario = u;
	}

	public static void setConect(Connection c) {
		conect = c;
	}

	public CompletedRequestView4() throws SQLException {

		initComponents();
		setExtendedState(MAXIMIZED_BOTH);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() throws SQLException {

		setLayout(new GridLayout());
		tela = new CanvasBackground("src/Imagens/coletas.png");
		jButtonPaginaInicial = new javax.swing.JButton();
		jButtonMinhasSolicitacoes = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaMensagem = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButtonPaginaInicial.setBackground(new java.awt.Color(51, 204, 0));
		jButtonPaginaInicial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jButtonPaginaInicial.setForeground(new java.awt.Color(255, 255, 255));
		jButtonPaginaInicial.setText("Página Inicial");
		jButtonPaginaInicial.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
		jButtonPaginaInicial.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jButtonPaginaInicialMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButtonMinhasSolicitacoes.setBackground(new java.awt.Color(51, 204, 0));
		jButtonMinhasSolicitacoes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jButtonMinhasSolicitacoes.setForeground(new java.awt.Color(255, 255, 255));
		jButtonMinhasSolicitacoes.setText("Minhas Solicitações");
		jButtonMinhasSolicitacoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
		jButtonMinhasSolicitacoes.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jButtonMinhasSolicitacoesMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jScrollPane1.setBackground(new Color(0, 0, 0, 0));
		jScrollPane1.setBorder(null);

		jTextAreaMensagem.setEditable(false);
		jTextAreaMensagem.setBackground(new java.awt.Color(255, 255, 255));
		jTextAreaMensagem.setColumns(10);
		jTextAreaMensagem.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
		jTextAreaMensagem.setForeground(new java.awt.Color(51, 204, 0));
		jTextAreaMensagem.setLineWrap(true);
		jTextAreaMensagem.setRows(2);
		jTextAreaMensagem.setTabSize(5);
		jTextAreaMensagem.setText("Coleta confirmada!");
		jTextAreaMensagem.setWrapStyleWord(true);
		jTextAreaMensagem.setBorder(null);
		jTextAreaMensagem.setOpaque(false);
		jTextAreaMensagem.setSelectedTextColor(new java.awt.Color(0, 0, 0));
		jTextAreaMensagem.setSelectionColor(new java.awt.Color(255, 255, 255));
		jScrollPane1.setViewportView(jTextAreaMensagem);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(tela);
		tela.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap(355, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGap(47, 47, 47)
										.addComponent(jButtonMinhasSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE,
												171, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(30, 30, 30).addComponent(jButtonPaginaInicial,
												javax.swing.GroupLayout.PREFERRED_SIZE, 171,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(100, 100, 100)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(250, 250, 250)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(127, 127, 127)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButtonMinhasSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonPaginaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(45, Short.MAX_VALUE)));

		pack();
		getContentPane().add(tela);

	}// </editor-fold>//GEN-END:initComponents

	private void jButtonMinhasSolicitacoesMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {// GEN-FIRST:event_jButtonMinhasSolicitacoesMouseClicked
		MyRequests.setUsuario(usuario);
		MyRequests.setConect(conect);
		MyRequests.setContexto(2);
		new MyRequests().main(null);
		this.dispose();
	}// GEN-LAST:event_jButtonMinhasSolicitacoesMouseClicked

	private void jButtonPaginaInicialMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {// GEN-FIRST:event_jButtonPaginaInicialMouseClicked
		new PageCollectorView().main(null);
		this.dispose();
	}// GEN-LAST:event_jButtonPaginaInicialMouseClicked

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
			java.util.logging.Logger.getLogger(CompletedRequestView4.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CompletedRequestView4.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CompletedRequestView4.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CompletedRequestView4.class.getName()).log(java.util.logging.Level.SEVERE,
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
				try {
					new CompletedRequestView4().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonMinhasSolicitacoes;
	private javax.swing.JButton jButtonPaginaInicial;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaMensagem;
	// End of variables declaration//GEN-END:variables
}

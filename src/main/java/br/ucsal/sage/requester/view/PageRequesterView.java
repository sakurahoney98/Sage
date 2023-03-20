/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.ucsal.sage.requester.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucsal.sage.requester.controller.RequesterController;
import br.ucsal.sage.user.model.User;
import br.ucsal.sage.user.view.CanvasBackground;
import br.ucsal.sage.user.view.MyProfileView;

/**
 *
 * @author Suporte TI
 */
public class PageRequesterView extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
	/**
     * Creates new form Principal
     */
	private static User usuario;
	private ArrayList<User> ranking;
	private int posicao;
	private static Connection conect;
	private RequesterController controller;
	 private CanvasBackground tela;
    
    public static void setUsuario(User u) {
    	usuario = u;
    }
    
    public static void setConect(Connection c) {
    	conect = c;
    }
    
  
    
    
    public PageRequesterView() throws SQLException {
        
        
        
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
       
     
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws SQLException 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

    	 setLayout(new GridLayout());
         tela = new CanvasBackground("src/Imagens/tela_inicial_coletor.png");
         controller = new RequesterController();
         ranking = controller.getRequesterRanking(conect);
         posicao = controller.getRankinPosition(conect, usuario.getId_usuario());
         
         jLabelBemVindo = new javax.swing.JTextArea();
         jButtonMeuPerfil = new javax.swing.JButton();
         jScrollPane1 = new javax.swing.JScrollPane();
         jTableRankingEscolas = new javax.swing.JTable();
         jButtonSolicitarColeta = new javax.swing.JButton();
         jLabelPosicao2 = new javax.swing.JLabel();
         jLabelPosicao3 = new javax.swing.JLabel();
         jLabelPosicao4 = new javax.swing.JLabel();
         jLabelNomePosicao2 = new javax.swing.JLabel();
         jLabelNomePosicao3 = new javax.swing.JLabel();
         jLabelNomePosicao1 = new javax.swing.JLabel();
         jLabelSuaPosicao = new javax.swing.JLabel();
         jLabel1 = new javax.swing.JLabel();
         jScrollPane2 = new javax.swing.JScrollPane();

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

         jLabelBemVindo.setEditable(false);
         jLabelBemVindo.setBackground(new Color(0,0,0,0));
         jLabelBemVindo.setColumns(20);
         jLabelBemVindo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
         jLabelBemVindo.setRows(5);
         jLabelBemVindo.setWrapStyleWord(true);
         jLabelBemVindo.setLineWrap(true);
         jLabelBemVindo.setBorder(null);
         jLabelBemVindo.setText("Bem vindo, " + usuario.getApelido() + "!");
         jScrollPane2.setViewportView(jLabelBemVindo);
         
         jScrollPane2.getViewport().setBackground(new Color(0,0,0,0));
         jScrollPane2.setBorder(null);
         
         

         jButtonMeuPerfil.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
         jButtonMeuPerfil.setForeground(new java.awt.Color(51, 153, 0));
         jButtonMeuPerfil.setBackground(Color.WHITE);
         jButtonMeuPerfil.setText("Meu perfil");
         jButtonMeuPerfil.setBorder(null);
         jButtonMeuPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jButtonMeuPerfilMouseClicked(evt);
             }
         });
         jButtonMeuPerfil.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 try {
					jButtonMeuPerfilActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
         });

         jScrollPane1.setBackground(new java.awt.Color(0, 153, 51));
         
        Object [][] ob = controller.generateObjectArray(ranking);
           

         jTableRankingEscolas.setBackground(new java.awt.Color(253, 191, 0));
         jTableRankingEscolas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
         jTableRankingEscolas.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
         jTableRankingEscolas.setForeground(new java.awt.Color(255, 255, 255));
         jTableRankingEscolas.setModel(new javax.swing.table.DefaultTableModel(
             ob,
             new String [] {
                 "Nome", "Reciclado (Kg)"
             }
         ) {
             Class[] types = new Class [] {
                 java.lang.String.class, java.lang.String.class
             };
             boolean[] canEdit = new boolean [] {
                 false, false
             };

             public Class getColumnClass(int columnIndex) {
                 return types [columnIndex];
             }

             public boolean isCellEditable(int rowIndex, int columnIndex) {
                 return canEdit [columnIndex];
             }
         });
         jTableRankingEscolas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
         jTableRankingEscolas.setGridColor(new java.awt.Color(255, 255, 255));
         jTableRankingEscolas.setSelectionForeground(new java.awt.Color(102, 102, 102));
         jTableRankingEscolas.setRowSelectionAllowed(false);
      
         jTableRankingEscolas.setShowGrid(true);
         jTableRankingEscolas.setRowHeight(40); 
         jTableRankingEscolas.getColumnModel().getColumn(0).setPreferredWidth(1000);
         jTableRankingEscolas.getColumnModel().getColumn(1).setPreferredWidth(50);
         jTableRankingEscolas.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jTableRankingEscolasMouseClicked(evt);
             }
         });
         jScrollPane1.setViewportView(jTableRankingEscolas);

         jButtonSolicitarColeta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
         jButtonSolicitarColeta.setForeground(new java.awt.Color(51, 153, 0));
         jButtonSolicitarColeta.setBackground(Color.WHITE);
         jButtonSolicitarColeta.setText("Solicitar Coleta");
         jButtonSolicitarColeta.setBorder(null);
         jButtonSolicitarColeta.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jButtonSolicitarColetaMouseClicked(evt);
             }
         });
         jButtonSolicitarColeta.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButtonSolicitarColetaActionPerformed(evt);
             }
         });

         jLabelPosicao2.setBackground(new java.awt.Color(242, 114, 129));
         jLabelPosicao2.setOpaque(true);
         

         jLabelPosicao3.setBackground(new java.awt.Color(115, 126, 230));
         jLabelPosicao3.setOpaque(true);

         jLabelPosicao4.setBackground(new java.awt.Color(253, 191, 0));
         jLabelPosicao4.setOpaque(true);

         jLabelNomePosicao2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
         jLabelNomePosicao2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         if(ranking.size() >= 2)
         jLabelNomePosicao2.setText(ranking.get(1).getApelido() + " #2");

         jLabelNomePosicao3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
         jLabelNomePosicao3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         if(ranking.size() >= 3)
         jLabelNomePosicao3.setText(ranking.get(2).getApelido() + " #3");

         jLabelNomePosicao1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
         jLabelNomePosicao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         jLabelNomePosicao1.setText(ranking.get(0).getApelido() + " #1");

         jLabelSuaPosicao.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
         jLabelSuaPosicao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabelSuaPosicao.setText("Sua posição: " + posicao + "º");

         jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
         jLabel1.setText("Top 10 Colégios:");

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(tela);
         tela.setLayout(layout);
         layout.setHorizontalGroup(
                 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(layout.createSequentialGroup()
                             .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                             .addComponent(jLabelSuaPosicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addGroup(layout.createSequentialGroup()
                             .addGap(50, 50, 50)
                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                 .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                                 .addGroup(layout.createSequentialGroup()
                                     .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                     .addGap(0, 0, Short.MAX_VALUE))
                                 .addGroup(layout.createSequentialGroup()
                                     .addGap(150, 150, 150)
                                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                         .addGroup(layout.createSequentialGroup()
                                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                 .addComponent(jLabelPosicao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                 .addComponent(jLabelNomePosicao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                 .addComponent(jLabelNomePosicao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                 .addComponent(jLabelPosicao4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                 .addComponent(jLabelPosicao3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                 .addComponent(jLabelNomePosicao3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGap(97, 97, 97))
                                         .addGroup(layout.createSequentialGroup()
                                             .addComponent(jScrollPane2)
                                             .addGap(18, 18, 18)
                                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                 .addComponent(jButtonMeuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                 .addComponent(jButtonSolicitarColeta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                     .addGap(50, 50, 50))
             );
             layout.setVerticalGroup(
                 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addGroup(layout.createSequentialGroup()
                     .addGap(100, 100, 100)
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                             .addComponent(jButtonMeuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addGap(18, 18, 18)
                             .addComponent(jButtonSolicitarColeta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGap(18, 18, 18)
                     .addComponent(jLabelNomePosicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(layout.createSequentialGroup()
                             .addComponent(jLabelNomePosicao2)
                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                             .addComponent(jLabelPosicao2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(jLabelPosicao4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(layout.createSequentialGroup()
                             .addComponent(jLabelNomePosicao3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                             .addComponent(jLabelPosicao3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                     .addGap(30, 30, 30)
                     .addComponent(jLabelSuaPosicao)
                     .addGap(20, 20, 20)
                     .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addGap(18, 18, 18)
                     .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                     .addContainerGap())
             );

             pack();
         getContentPane().add(tela);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMeuPerfilActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButtonMeuPerfilActionPerformed
    	MyProfileView.setUsuario(usuario);
    	MyProfileView.setConect(conect);
        new MyProfileView();
		MyProfileView.main(null);
        this.dispose();
    }//GEN-LAST:event_jButtonMeuPerfilActionPerformed

    private void jButtonSolicitarColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSolicitarColetaActionPerformed
    	CreateRequestView1.setIdUsuario(usuario.getId_usuario());
        CreateRequestView1.setConect(conect);
        new CreateRequestView1();
		CreateRequestView1.main(null);
        this.dispose();
    }//GEN-LAST:event_jButtonSolicitarColetaActionPerformed

    private void jButtonSolicitarColetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSolicitarColetaMouseClicked
        
    }//GEN-LAST:event_jButtonSolicitarColetaMouseClicked

    private void jTableRankingEscolasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRankingEscolasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableRankingEscolasMouseClicked

    private void jButtonMeuPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonMeuPerfilMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMeuPerfilMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PageRequesterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PageRequesterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PageRequesterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PageRequesterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new PageRequesterView().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMeuPerfil;
    private javax.swing.JButton jButtonSolicitarColeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea jLabelBemVindo;
    private javax.swing.JLabel jLabelNomePosicao1;
    private javax.swing.JLabel jLabelNomePosicao2;
    private javax.swing.JLabel jLabelNomePosicao3;
    private javax.swing.JLabel jLabelPosicao2;
    private javax.swing.JLabel jLabelPosicao3;
    private javax.swing.JLabel jLabelPosicao4;
    private javax.swing.JLabel jLabelSuaPosicao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRankingEscolas;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

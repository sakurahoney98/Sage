/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.ucsal.sage.requester.view;

import java.awt.GridLayout;
import java.sql.Connection;

import br.ucsal.sage.request.model.Request;
import br.ucsal.sage.user.view.CanvasBackground;

/**
 *
 * @author Suporte TI
 */
public class CreateRequestView4 extends javax.swing.JFrame {

    /**
     * Creates new form Solicitacao_Coleta_1
     */
    
    private CanvasBackground tela;
    
    private static Request chamado;
    
 private static Connection conect;
    
    public static void setConect(Connection c) {
    	conect = c;
    }
    
    public static void setChamado(Request r) {
    	chamado = r;
    }
    public CreateRequestView4() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
   
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	
         setLayout(new GridLayout());
     tela = new CanvasBackground("src/Imagens/cadastro_1.png");
     
   jButtonProximo = new javax.swing.JButton();
         jButtonAnterior = new javax.swing.JButton();
         jScrollPane1 = new javax.swing.JScrollPane();
         jTextArea1 = new javax.swing.JTextArea();
         jScrollPane2 = new javax.swing.JScrollPane();
         jTextAreaAdicional = new javax.swing.JTextArea();

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

         jButtonProximo.setBackground(new java.awt.Color(51, 204, 0));
         jButtonProximo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
         jButtonProximo.setForeground(new java.awt.Color(255, 255, 255));
         jButtonProximo.setText("Próximo");
         jButtonProximo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
         jButtonProximo.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jButtonProximoMouseClicked(evt);
             }
         });
         jButtonProximo.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButtonProximoActionPerformed(evt);
             }
         });

         jButtonAnterior.setBackground(new java.awt.Color(51, 204, 0));
         jButtonAnterior.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
         jButtonAnterior.setForeground(new java.awt.Color(255, 255, 255));
         jButtonAnterior.setText("Anterior");
         jButtonAnterior.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
         jButtonAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jButtonAnteriorMouseClicked(evt);
             }
         });
         jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButtonAnteriorActionPerformed(evt);
             }
         });

         jScrollPane1.setBorder(null);

         jTextArea1.setEditable(false);
         jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
         jTextArea1.setColumns(5);
         jTextArea1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
         jTextArea1.setWrapStyleWord (true);
         jTextArea1.setLineWrap(true);
         jTextArea1.setRows(2);
         jTextArea1.setTabSize(5);
         jTextArea1.setText("Informações adicionais (opicional):");
         jTextArea1.setBorder(null);
         jTextArea1.setOpaque(false);
         jTextArea1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
         jTextArea1.setSelectionColor(new java.awt.Color(255, 255, 255));
         jScrollPane1.setViewportView(jTextArea1);

         jTextAreaAdicional.setColumns(20);
         jTextAreaAdicional.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
         jTextAreaAdicional.setLineWrap(true);
         jTextAreaAdicional.setWrapStyleWord(true);
         jTextAreaAdicional.setRows(5);
         jTextAreaAdicional.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
         jScrollPane2.setViewportView(jTextAreaAdicional);

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(tela);
         tela.setLayout(layout);
          layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                 .addContainerGap(67, Short.MAX_VALUE)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                             .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addGap(200, 200, 200))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                             .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addGap(18, 18, 18)
                             .addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addGap(100, 100, 100)))
                     .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(250, 250, 250)
                 .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(35, 35, 35)
                 .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(50, 50, 50))
         );

         pack();
         getContentPane().add(tela);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnteriorMouseClicked
        new CreateRequestView3().main(null);
        this.dispose();
    }//GEN-LAST:event_jButtonAnteriorMouseClicked

    private void jButtonProximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonProximoMouseClicked
        chamado.setSobre_chamado(jTextAreaAdicional.getText());
        CreateRequestView5.setChamado(chamado);
        CreateRequestView5.setConect(conect);
        new CreateRequestView5().main(null);
    	this.dispose();
    }//GEN-LAST:event_jButtonProximoMouseClicked

    private void jButtonProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonProximoActionPerformed

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
            java.util.logging.Logger.getLogger(CreateRequestView4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateRequestView4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateRequestView4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateRequestView4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CreateRequestView4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonProximo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextAreaAdicional;
    // End of variables declaration//GEN-END:variables
}

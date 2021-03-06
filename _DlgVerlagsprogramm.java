/*
 *
 * Das JAVA-Programm Miles-Verlag Verlagsverwaltung stellt alle notwendigen
 * Funktionen f�r die Verwaltung des Carola Hartman Miles-Verlags bereit.
 *
 * Copyright (C) 2017 EDV-Beratung und Betrieb, Entwicklung von SOftware
 *                    Dipl.Inform Thomas Zimmermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package milesVerlagMain;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import net.miginfocom.swing.*;

/**
 *
 * @author Thomas Zimmermann
 */
public class _DlgVerlagsprogramm extends javax.swing.JDialog {

    /**
     * Creates new form _DlgVerlagsprogramm
     *
     * @param parent
     * @param modal
     */
    public _DlgVerlagsprogramm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buttonGroupAusgabeformat.add(jRadioButtonPDF);
        buttonGroupAusgabeformat.add(jRadioButtonDOC);
        buttonGroupAusgabeformat.add(jRadioButtonXLS);

        buttonGroupSortierung.add(jRadioButtonISBN);
        buttonGroupSortierung.add(jRadioButtonAutor);
        buttonGroupSortierung.add(jRadioButtonTitel);

        buttonGroupUmfang.add(rbAlle);
        buttonGroupUmfang.add(rbAktiv);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jRadioButtonPDF = new JRadioButton();
        jRadioButtonISBN = new JRadioButton();
        rbAlle = new JRadioButton();
        jRadioButtonDOC = new JRadioButton();
        jRadioButtonAutor = new JRadioButton();
        rbAktiv = new JRadioButton();
        jRadioButtonXLS = new JRadioButton();
        jRadioButtonTitel = new JRadioButton();
        jButtonDrucken = new JButton();
        jButtonSchliessen = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carola Hartmann Miles Verlag");
        setResizable(false);
        setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        setMinimumSize(new Dimension(305, 260));
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- jLabel1 ----
            jLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
            jLabel1.setText("Verlagsprogramm drucken");
            panel1.add(jLabel1);
            jLabel1.setBounds(0, 0, 166, jLabel1.getPreferredSize().height);

            //---- jLabel2 ----
            jLabel2.setText("Ausgabeformat");
            jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | Font.BOLD));
            panel1.add(jLabel2);
            jLabel2.setBounds(new Rectangle(new Point(0, 45), jLabel2.getPreferredSize()));

            //---- jLabel3 ----
            jLabel3.setText("sortiert nach");
            jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | Font.BOLD));
            panel1.add(jLabel3);
            jLabel3.setBounds(new Rectangle(new Point(93, 45), jLabel3.getPreferredSize()));

            //---- jLabel4 ----
            jLabel4.setText("Umfang");
            jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | Font.BOLD));
            panel1.add(jLabel4);
            jLabel4.setBounds(171, 45, 99, jLabel4.getPreferredSize().height);

            //---- jRadioButtonPDF ----
            jRadioButtonPDF.setSelected(true);
            jRadioButtonPDF.setText("PDF");
            panel1.add(jRadioButtonPDF);
            jRadioButtonPDF.setBounds(0, 64, 88, jRadioButtonPDF.getPreferredSize().height);

            //---- jRadioButtonISBN ----
            jRadioButtonISBN.setSelected(true);
            jRadioButtonISBN.setText("ISBN");
            panel1.add(jRadioButtonISBN);
            jRadioButtonISBN.setBounds(93, 64, 73, jRadioButtonISBN.getPreferredSize().height);

            //---- rbAlle ----
            rbAlle.setText("alle B\u00fccher");
            panel1.add(rbAlle);
            rbAlle.setBounds(171, 64, 99, rbAlle.getPreferredSize().height);

            //---- jRadioButtonDOC ----
            jRadioButtonDOC.setText("DOC");
            jRadioButtonDOC.setEnabled(false);
            panel1.add(jRadioButtonDOC);
            jRadioButtonDOC.setBounds(0, 92, 88, jRadioButtonDOC.getPreferredSize().height);

            //---- jRadioButtonAutor ----
            jRadioButtonAutor.setText("Autor");
            panel1.add(jRadioButtonAutor);
            jRadioButtonAutor.setBounds(93, 92, 73, jRadioButtonAutor.getPreferredSize().height);

            //---- rbAktiv ----
            rbAktiv.setSelected(true);
            rbAktiv.setText("aktive Vertr\u00e4ge");
            panel1.add(rbAktiv);
            rbAktiv.setBounds(new Rectangle(new Point(171, 92), rbAktiv.getPreferredSize()));

            //---- jRadioButtonXLS ----
            jRadioButtonXLS.setText("XLS");
            jRadioButtonXLS.setEnabled(false);
            panel1.add(jRadioButtonXLS);
            jRadioButtonXLS.setBounds(0, 120, 88, jRadioButtonXLS.getPreferredSize().height);

            //---- jRadioButtonTitel ----
            jRadioButtonTitel.setText("Titel");
            panel1.add(jRadioButtonTitel);
            jRadioButtonTitel.setBounds(93, 120, 73, jRadioButtonTitel.getPreferredSize().height);

            //---- jButtonDrucken ----
            jButtonDrucken.setText("Drucken");
            jButtonDrucken.setToolTipText("Druckt das Verlagsporgramm im gew\u00e4hlten Format und schlie\u00dft den Dialog");
            jButtonDrucken.addActionListener(e -> jButtonDruckenActionPerformed(e));
            panel1.add(jButtonDrucken);
            jButtonDrucken.setBounds(25, 170, 121, jButtonDrucken.getPreferredSize().height);

            //---- jButtonSchliessen ----
            jButtonSchliessen.setText("Schlie\u00dfen");
            jButtonSchliessen.setToolTipText("Schlie\u00dft den Dialog");
            jButtonSchliessen.addActionListener(e -> jButtonSchliessenActionPerformed(e));
            panel1.add(jButtonSchliessen);
            jButtonSchliessen.setBounds(155, 170, 121, jButtonSchliessen.getPreferredSize().height);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setSize(295, 250);
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDruckenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDruckenActionPerformed
        // TODO add your handling code here:
        Integer Sortierung = 0;

        if (jRadioButtonISBN.isSelected()) {
            Sortierung = 0;     // ISBN
        } else {
            if (jRadioButtonAutor.isSelected()) {
                Sortierung = 1; // Autor
            } else {
                Sortierung = 2; // Titel
            }
        }

        if (jRadioButtonPDF.isSelected()) {
            berVerlagsprogramm.PDF(Sortierung, rbAktiv.isSelected());
        } else {
            if (jRadioButtonDOC.isSelected()) {
                berVerlagsprogramm.DOC(Sortierung, rbAktiv.isSelected());
            } else {
                berVerlagsprogramm.XLS(Sortierung, rbAktiv.isSelected());
            }
        }
        // schlie�en des Dialoges
        this.dispose();
    }//GEN-LAST:event_jButtonDruckenActionPerformed

    private void jButtonSchliessenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSchliessenActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonSchliessenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /**
         * try { for (javax.swing.UIManager.LookAndFeelInfo info :
         * javax.swing.UIManager.getInstalledLookAndFeels()) { if
         * ("Nimbus".equals(info.getName())) {
         * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } }
         * } catch (ClassNotFoundException | InstantiationException |
         * IllegalAccessException | javax.swing.UnsupportedLookAndFeelException
         * ex) {
         * java.util.logging.Logger.getLogger(CarolaHartmannMilesVerlag.class.getName()).log(java.util.logging.Level.SEVERE,
         * null, ex); }
         */
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            _DlgVerlagsprogramm dialog = new _DlgVerlagsprogramm(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    dialog.setVisible(false);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JRadioButton jRadioButtonPDF;
    private JRadioButton jRadioButtonISBN;
    private JRadioButton rbAlle;
    private JRadioButton jRadioButtonDOC;
    private JRadioButton jRadioButtonAutor;
    private JRadioButton rbAktiv;
    private JRadioButton jRadioButtonXLS;
    private JRadioButton jRadioButtonTitel;
    private JButton jButtonDrucken;
    private JButton jButtonSchliessen;
    // End of variables declaration//GEN-END:variables

    private ButtonGroup buttonGroupAusgabeformat = new ButtonGroup();
    private ButtonGroup buttonGroupSortierung = new ButtonGroup();
    private ButtonGroup buttonGroupUmfang = new ButtonGroup();
}

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
import com.toedter.calendar.*;

/**
 *
 * @author Thomas Zimmermann
 */
public class _DlgUebersichtMahnungen extends javax.swing.JDialog {

    /**
     * Creates new form _DlgUebersichtMahnungen
     *
     * @param parent
     * @param modal
     */
    public _DlgUebersichtMahnungen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buttonGroupAusgabeformat.add(jRadioButtonPDF);
        buttonGroupAusgabeformat.add(jRadioButtonXLS);
        buttonGroupAusgabeformat.add(jRadioButtonDoc);
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
        jButtonDrucken = new JButton();
        jButtonSchlie�en = new JButton();
        jLabelZeitraum = new JLabel();
        jLabelAusgabeformat = new JLabel();
        jLabelVon = new JLabel();
        jLabelBis = new JLabel();
        jRadioButtonPDF = new JRadioButton();
        jRadioButtonXLS = new JRadioButton();
        jRadioButtonDoc = new JRadioButton();
        jDateChooserVon = new JDateChooser();
        jDateChooserBis = new JDateChooser();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u00dcbersicht der offenen Einnahmen");
        setResizable(false);
        setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        setMinimumSize(new Dimension(410, 265));
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- jButtonDrucken ----
            jButtonDrucken.setText("Drucken");
            jButtonDrucken.addActionListener(e -> jButtonDruckenActionPerformed(e));

            //---- jButtonSchlie�en ----
            jButtonSchlie�en.setText("Schlie\u00dfen");
            jButtonSchlie�en.addActionListener(e -> jButtonSchlie�enActionPerformed(e));

            //---- jLabelZeitraum ----
            jLabelZeitraum.setFont(new Font("Tahoma", Font.BOLD, 12));
            jLabelZeitraum.setText("Zeitraum");

            //---- jLabelAusgabeformat ----
            jLabelAusgabeformat.setFont(new Font("Tahoma", Font.BOLD, 12));
            jLabelAusgabeformat.setText("Ausgabeformat");

            //---- jLabelVon ----
            jLabelVon.setText("von ...");

            //---- jLabelBis ----
            jLabelBis.setText("... bis");

            //---- jRadioButtonPDF ----
            jRadioButtonPDF.setText("PDF");
            jRadioButtonPDF.setEnabled(false);

            //---- jRadioButtonXLS ----
            jRadioButtonXLS.setText("XLS");
            jRadioButtonXLS.setSelected(true);

            //---- jRadioButtonDoc ----
            jRadioButtonDoc.setText("DOC");
            jRadioButtonDoc.setEnabled(false);

            //---- label1 ----
            label1.setText("Offene Rechnungen identifizieren");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 1f));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabelZeitraum)
                                .addGap(198, 198, 198)
                                .addComponent(jLabelAusgabeformat))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(jButtonDrucken)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonSchlie�en, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(jLabelVon)
                                        .addComponent(jDateChooserVon, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(jLabelBis)
                                        .addComponent(jDateChooserBis, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                    .addGap(36, 36, 36)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(jRadioButtonPDF)
                                        .addComponent(jRadioButtonXLS)
                                        .addComponent(jRadioButtonDoc)))))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE))
            );
            panel1Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jDateChooserBis, jDateChooserVon});
            panel1Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonDrucken, jButtonSchlie�en});
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(jLabelZeitraum)
                            .addComponent(jLabelAusgabeformat))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabelVon)
                                .addGap(6, 6, 6)
                                .addComponent(jDateChooserVon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabelBis)
                                .addGap(6, 6, 6)
                                .addComponent(jDateChooserBis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonPDF)
                                .addGap(0, 0, 0)
                                .addComponent(jRadioButtonXLS)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonDoc)
                        .addGap(11, 11, 11)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(jButtonDrucken)
                            .addComponent(jButtonSchlie�en)))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        setSize(400, 255);
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(jRadioButtonPDF);
        buttonGroup1.add(jRadioButtonXLS);
        buttonGroup1.add(jRadioButtonDoc);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSchlie�enActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSchlie�enActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonSchlie�enActionPerformed

    private void jButtonDruckenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDruckenActionPerformed
        // TODO add your handling code here:
        java.util.Date vonDate = jDateChooserVon.getDate();
        java.util.Date bisDate = jDateChooserBis.getDate();

        if (jRadioButtonPDF.isSelected()) { // Format PDF
            berMahnungen.berichtPDF(Modulhelferlein.printDateFormat("yyyy-MM-dd", vonDate), Modulhelferlein.printDateFormat("yyyy-MM-dd", bisDate));
        } else if (jRadioButtonXLS.isSelected()) { // Format XLSX
            berMahnungen.berichtXLS(Modulhelferlein.printDateFormat("yyyy-MM-dd", vonDate), Modulhelferlein.printDateFormat("yyyy-MM-dd", bisDate));
        } else { // Format DOCX
            berMahnungen.berichtDOC(Modulhelferlein.printDateFormat("yyyy-MM-dd", vonDate), Modulhelferlein.printDateFormat("yyyy-MM-dd", bisDate));
        }
        this.dispose();
    }//GEN-LAST:event_jButtonDruckenActionPerformed

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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarolaHartmannMilesVerlag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
*/        
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            _DlgUebersichtMahnungen dialog = new _DlgUebersichtMahnungen(new javax.swing.JFrame(), true);

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
    private JButton jButtonDrucken;
    private JButton jButtonSchlie�en;
    private JLabel jLabelZeitraum;
    private JLabel jLabelAusgabeformat;
    private JLabel jLabelVon;
    private JLabel jLabelBis;
    private JRadioButton jRadioButtonPDF;
    private JRadioButton jRadioButtonXLS;
    private JRadioButton jRadioButtonDoc;
    private JDateChooser jDateChooserVon;
    private JDateChooser jDateChooserBis;
    private JLabel label1;
    // End of variables declaration//GEN-END:variables

    private ButtonGroup buttonGroupAusgabeformat = new ButtonGroup();
}

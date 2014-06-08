/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import cc.MyParser;
import cc.ParseException;
import cc.syntaxtree.Scope;
import cc.visitor.PrinterVisitor;
import cc.visitor.StepSequenceVisitor;
import cc.visitor.SemanticsVisitor;
import cc.visitor.TreeVisitor;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mwimola
 */
public class ParserFrame extends javax.swing.JFrame {

    /**
     * Creates new form ProgramFrame
     */
    public ParserFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filetxtchooser = new javax.swing.JFileChooser();
        jPanelUserMsg = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPaneArea = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInput = new javax.swing.JTextArea();
        jPanelTree = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabelTree = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNuovo = new javax.swing.JMenuItem();
        jMenuItemApri = new javax.swing.JMenuItem();
        jMenuItemSalva = new javax.swing.JMenuItem();
        jMenuItemChiudi = new javax.swing.JMenuItem();
        jMenuCheck = new javax.swing.JMenu();
        jMenuItemSyntax = new javax.swing.JMenuItem();
        jMenuItemSemantics = new javax.swing.JMenuItem();
        jMenuGenerate = new javax.swing.JMenu();
        jMenuItemTree = new javax.swing.JMenuItem();
        jMenuItemTextFile = new javax.swing.JMenuItem();
        jMenuDance = new javax.swing.JMenuItem();

        filetxtchooser.setFileFilter(new FileNameExtensionFilter("file di testo", "txt", "text"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dance Language Tool");
        setResizable(false);

        jTextAreaOutput.setEditable(false);
        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setRows(5);
        jScrollPane3.setViewportView(jTextAreaOutput);

        jLabel2.setText("Messages");

        javax.swing.GroupLayout jPanelUserMsgLayout = new javax.swing.GroupLayout(jPanelUserMsg);
        jPanelUserMsg.setLayout(jPanelUserMsgLayout);
        jPanelUserMsgLayout.setHorizontalGroup(
            jPanelUserMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserMsgLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );
        jPanelUserMsgLayout.setVerticalGroup(
            jPanelUserMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserMsgLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
        );

        jTextAreaInput.setColumns(20);
        jTextAreaInput.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInput);

        javax.swing.GroupLayout jPaneAreaLayout = new javax.swing.GroupLayout(jPaneArea);
        jPaneArea.setLayout(jPaneAreaLayout);
        jPaneAreaLayout.setHorizontalGroup(
            jPaneAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );
        jPaneAreaLayout.setVerticalGroup(
            jPaneAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabelTree.setText("Syntax Tree");

        javax.swing.GroupLayout jPanelTreeLayout = new javax.swing.GroupLayout(jPanelTree);
        jPanelTree.setLayout(jPanelTreeLayout);
        jPanelTreeLayout.setHorizontalGroup(
            jPanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTreeLayout.createSequentialGroup()
                .addComponent(jLabelTree, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanelTreeLayout.setVerticalGroup(
            jPanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTreeLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelTree, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        );

        jMenuFile.setText("File");

        jMenuItemNuovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNuovo.setText("New");
        jMenuItemNuovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuovoActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemNuovo);

        jMenuItemApri.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemApri.setText("Open");
        jMenuItemApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemApriActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemApri);

        jMenuItemSalva.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalva.setText("Save");
        jMenuItemSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalvaActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSalva);

        jMenuItemChiudi.setText("Close");
        jMenuItemChiudi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChiudiActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemChiudi);

        menuBar.add(jMenuFile);

        jMenuCheck.setText("Check");

        jMenuItemSyntax.setText("Syntax");
        jMenuItemSyntax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSyntaxActionPerformed(evt);
            }
        });
        jMenuCheck.add(jMenuItemSyntax);

        jMenuItemSemantics.setText("Semantics");
        jMenuItemSemantics.setEnabled(false);
        jMenuItemSemantics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSemanticsActionPerformed(evt);
            }
        });
        jMenuCheck.add(jMenuItemSemantics);

        menuBar.add(jMenuCheck);

        jMenuGenerate.setText("Generate");
        jMenuGenerate.setEnabled(false);

        jMenuItemTree.setText("Tree");
        jMenuItemTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTreeActionPerformed(evt);
            }
        });
        jMenuGenerate.add(jMenuItemTree);

        jMenuItemTextFile.setText("Text File");
        jMenuItemTextFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTextFileActionPerformed(evt);
            }
        });
        jMenuGenerate.add(jMenuItemTextFile);

        jMenuDance.setText("Dance!");
        jMenuDance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDanceActionPerformed(evt);
            }
        });
        jMenuGenerate.add(jMenuDance);

        menuBar.add(jMenuGenerate);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPaneArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUserMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelUserMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemChiudiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChiudiActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItemChiudiActionPerformed

    private void jMenuItemNuovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuovoActionPerformed
        // TODO add your handling code here:
        resetViews();
    }//GEN-LAST:event_jMenuItemNuovoActionPerformed

    private void jMenuItemApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemApriActionPerformed
        // TODO add your handling code here:
        int returnVal = filetxtchooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = filetxtchooser.getSelectedFile();
            try {
                // What to do with the file, e.g. display it in a TextArea
                jTextAreaInput.read(new FileReader(file.getAbsolutePath()), null);
            } catch (IOException ex) {
                System.out.println("problem accessing file" + file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jMenuItemApriActionPerformed

    private void jMenuItemSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalvaActionPerformed
        // TODO add your handling code here:
        try {
            int actionDialog = filetxtchooser.showSaveDialog(this);
            if (actionDialog == JFileChooser.APPROVE_OPTION) {
                File fileName = new File(filetxtchooser.getSelectedFile() + "");
                if (fileName != null) {
                    if (fileName.exists()) {
                        actionDialog = JOptionPane.showConfirmDialog(this, "Sovrascrivere file esistente?");

                        while (actionDialog == JOptionPane.NO_OPTION) {
                            actionDialog = filetxtchooser.showSaveDialog(this);
                            if (actionDialog == JFileChooser.APPROVE_OPTION) {
                                fileName = new File(filetxtchooser.getSelectedFile() + "");
                                if (fileName.exists()) {
                                    actionDialog = JOptionPane.showConfirmDialog(this, "Sovrascrivere file esistente?");

                                }
                            }

                        }
                        if (actionDialog == JOptionPane.YES_OPTION) {
                            BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
                            jTextAreaInput.write(outFile);
                            //outFile.write(jTextAreaInput.getText()); //put in textfile
                            outFile.close();
                        }
                        return;
                    }

                    BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
                    jTextAreaInput.write(outFile);
                    //outFile.write(jTextAreaInput.getText()); //put in textfile
                    outFile.close();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jMenuItemSalvaActionPerformed

    private void jMenuItemSyntaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSyntaxActionPerformed
        try {
            // TODO add your handling code here:
            //tolower??
            InputStream is = new ByteArrayInputStream(jTextAreaInput.getText().toLowerCase().getBytes("UTF-8"));
            jTextAreaOutput.append("Syntax Check...\n");
            if( parser==null) 
                parser = new MyParser(is); 
            else 
                MyParser.ReInit(is) ;

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ParserFrame.class.getName()).log(Level.SEVERE, null, ex);
            jTextAreaOutput.append(ex.getMessage() + "\n");
        }
        try {
            scope = parser.Scope();
            jTextAreaOutput.append("Ok! Now Semantcs Check is enabled\n");
            jMenuItemSemantics.setEnabled(true);
            jTextAreaInput.getDocument().addDocumentListener(txtlistener);
        } catch (ParseException ex) {
            jTextAreaOutput.append(ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_jMenuItemSyntaxActionPerformed

    private void jMenuItemSemanticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSemanticsActionPerformed
        // TODO add your handling code here:
        jTextAreaOutput.append("Semantics Check...\n");
        SemanticsVisitor sv = new SemanticsVisitor();
        scope.accept(sv);
        
        PrinterVisitor pv = new PrinterVisitor();
        scope.accept(pv);
        
        List<String> errors = sv.getErrors();
        List<String> warnings = sv.getWarnings();
        if (!warnings.isEmpty())
        {
            jTextAreaOutput.append("WARNINGS:" + "\n");
            for (String string : warnings) {
                jTextAreaOutput.append(string + "\n");
            }
        }
        if (!errors.isEmpty() || pv.isTimeOutOfThreshold() || pv.isCoreographyEmpty())
        {
            jTextAreaOutput.append("ERRORS DETECTED:" + "\n");
            for (String string : errors) {
                jTextAreaOutput.append(string + "\n");
            }
            if(pv.isTimeOutOfThreshold() )
                jTextAreaOutput.append(pv.THRESHOLD_EXCEEDED + "\n");
            
            if(pv.isCoreographyEmpty())
                jTextAreaOutput.append(pv.COREOGRAPHY_EMPTY + "\n");
            jTextAreaOutput.append("Can't proceed to generate anything." + "\n");
        }
        else
        {
            jMenuGenerate.setEnabled(true);
            jTextAreaOutput.append("Ok! Now Generate Menu is now enabled." + "\n");
        }
    }//GEN-LAST:event_jMenuItemSemanticsActionPerformed

    private void jMenuItemTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTreeActionPerformed
        // TODO add your handling code here:
        TreeVisitor tv = new TreeVisitor();
        scope.accept(tv, null);
        jScrollPane2.setViewportView(tv.getTree());
    }//GEN-LAST:event_jMenuItemTreeActionPerformed

    private void jMenuItemTextFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTextFileActionPerformed
        try {
            // TODO add your handling code here:
            PrinterVisitor pv = new PrinterVisitor();
            String result = scope.accept(pv);
            if(result.equals(PrinterVisitor.SUCCESS))
            {
                pv.printfile();
                jTextAreaOutput.append(PrinterVisitor.SUCCESS + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(ParserFrame.class.getName()).log(Level.SEVERE, null, ex);
            jTextAreaOutput.append(ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_jMenuItemTextFileActionPerformed

    private void jMenuDanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDanceActionPerformed
        // TODO add your handling code here:
        StepSequenceVisitor ssv = new StepSequenceVisitor();
        scope.accept(ssv);
        if(! new File (ssv.getMp3_filename()).isFile() )
        {
        	jTextAreaOutput.append("Mp3 path: no file found. Can't proceed to dance\n");
        	return;
        }
        
        if(! new File (ssv.getVideo_filename()).isFile() )
        {
        	jTextAreaOutput.append("Video path: no file found. Can't proceed to dance\n");
        	return;
        }
        
        DanceFrame danceframe = new DanceFrame();
        danceframe.setVisible(true);
        
        //Passare sequenza mosse, audio, video
        danceframe.setSequence(ssv.getSequence());
        danceframe.setMedia(ssv.getMp3_filename(), ssv.getVideo_filename());
        this.dispose();
    }//GEN-LAST:event_jMenuDanceActionPerformed

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
            java.util.logging.Logger.getLogger(ParserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParserFrame().setVisible(true);
            }
        });
    }

    private void resetViews() {
        jTextAreaInput.setText(null);
        jTextAreaInput.getDocument().removeDocumentListener(txtlistener);
        jTextAreaOutput.setText(null);
        jMenuItemSemantics.setEnabled(false);
        jMenuGenerate.setEnabled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser filetxtchooser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTree;
    private javax.swing.JMenu jMenuCheck;
    private javax.swing.JMenuItem jMenuDance;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuGenerate;
    private javax.swing.JMenuItem jMenuItemApri;
    private javax.swing.JMenuItem jMenuItemChiudi;
    private javax.swing.JMenuItem jMenuItemNuovo;
    private javax.swing.JMenuItem jMenuItemSalva;
    private javax.swing.JMenuItem jMenuItemSemantics;
    private javax.swing.JMenuItem jMenuItemSyntax;
    private javax.swing.JMenuItem jMenuItemTextFile;
    private javax.swing.JMenuItem jMenuItemTree;
    private javax.swing.JPanel jPaneArea;
    private javax.swing.JPanel jPanelTree;
    private javax.swing.JPanel jPanelUserMsg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextAreaInput;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
    private Scope scope = null;
    private MyParser parser = null;
    private TextAreaDocumentListener txtlistener = new TextAreaDocumentListener();
    
    private class TextAreaDocumentListener implements DocumentListener{
                @Override
                public void insertUpdate(DocumentEvent e) {
                    jTextAreaOutput.append("Text modified! Redo Syntax Check\n");
                    jMenuItemSemantics.setEnabled(false);
                    jMenuGenerate.setEnabled(false);
                    jTextAreaInput.getDocument().removeDocumentListener(txtlistener);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    jTextAreaOutput.append("Text modified! Redo Syntax Check\n");
                    jMenuItemSemantics.setEnabled(false);
                    jMenuGenerate.setEnabled(false);
                    jTextAreaInput.getDocument().removeDocumentListener(txtlistener);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                 }
    }
}

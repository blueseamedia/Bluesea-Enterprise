/*
 * Copyright (c) 2011 Topmedia Company
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.topmedia.monitor.form;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import vn.topmedia.monitor.commons.Constants;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class Login extends javax.swing.JDialog {

    private String CMD_LOGIN = "cmd.login"/*
             * NOI18N
             */;
    private String CMD_CANCEL = "cmd.cancel"/*
             * NOI18N
             */;

    /**
     * Creates new form Login
     */
    public Login(MDIMain parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mdi = parent;
        Dimension d = parent.getSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 3);
        /*
         * pnlLogin.registerKeyboardAction(new ActionListener() { public void
         * actionPerformed(java.awt.event.ActionEvent actionEvent) {
         * btnOKActionPerformed(actionEvent); } },
         * KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0, false),
         * pnlLogin.WHEN_IN_FOCUSED_WINDOW);
         */
        this.getRootPane().setDefaultButton(btnOK);
        pnlLogin.registerKeyboardAction(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
                btnCancel.doClick();
            }
        }, KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false),
                pnlLogin.WHEN_IN_FOCUSED_WINDOW);

        txtPassword.setActionCommand(CMD_LOGIN);
        txtPassword.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                windowAction(event);
            }
        });

    }

    private void windowAction(Object actionCommand) {
        String cmd = null;
        if (actionCommand != null) {
            if (actionCommand instanceof ActionEvent) {
                cmd = ((ActionEvent) actionCommand).getActionCommand();
            } else {
                cmd = actionCommand.toString();
            }
        }
        if (cmd == null) {
            // do nothing
        } else if (cmd.equals(CMD_CANCEL)) {
            btnCancelActionPerformed((ActionEvent) actionCommand);
        } else if (cmd.equals(CMD_LOGIN)) {
            btnOK.doClick(1);
        }
    } // windowAction()

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        lblUserName.setText("User name");

        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        lblPassword.setText("Password");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.setPreferredSize(new java.awt.Dimension(70, 23));
        btnOK.setSelected(true);
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setMaximumSize(new java.awt.Dimension(70, 23));
        btnCancel.setPreferredSize(new java.awt.Dimension(70, 23));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlLoginLayout = new org.jdesktop.layout.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlLoginLayout.createSequentialGroup()
                        .add(pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lblUserName)
                            .add(lblPassword))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(txtUserName)
                            .add(txtPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .add(btnOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnCancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(19, 19, 19)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblUserName)
                    .add(txtUserName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblPassword)
                    .add(txtPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnCancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        String userName = txtUserName.getText();
        String password = new String(txtPassword.getPassword());
//        int ret = 0;
//        String error = "";
        if (userName.equals("") || password.equals("")) {
            JOptionPane.showOptionDialog(this, "Please input username/password!!!",
                    "Login", JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, Constants.optionOK, Constants.optionOK[0]);
//        } else {
//            String request = Constants.CMD_LOGIN + userName + "#" + password + "#" + mdi.getMonPort();
//            MonitorClientCheck client = new MonitorClientCheck();
//            try {
//                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                ret = client.sendRequestEx(Constants.WTS_SERVER, Constants.WTS_PORT, request);
//            } catch (IOException ioe) {
//                System.out.println("Error checking login: " + ioe);
//                error = "\n" + ioe;
//                ret = -3;
//            } finally {
//                this.setCursor(Cursor.getDefaultCursor());
//                client = null;
//            }
//            if (ret == -1) {//login invalid
//                JOptionPane.showOptionDialog(this, "Could not login. Please check username/password or network state",
//                        "Login", JOptionPane.OK_OPTION,
//                        JOptionPane.WARNING_MESSAGE, null, Constants.optionOK, Constants.optionOK[0]);
//            } else if (ret == -2) {
//                JOptionPane.showOptionDialog(this, "Error checking user",
//                        "Login", JOptionPane.OK_OPTION,
//                        JOptionPane.ERROR_MESSAGE, null, Constants.optionOK, Constants.optionOK[0]);
//            } else if (ret == -3) {
//                JOptionPane.showOptionDialog(this, "Error connecting to server " + error,
//                        "Login", JOptionPane.OK_OPTION,
//                        JOptionPane.ERROR_MESSAGE, null, Constants.optionOK, Constants.optionOK[0]);
//            } else {
//                this.dispose();
//                mdi.setUserName(userName);
//                mdi.setPassword(password);
//                mdi.setLogin(true);

//                if ((ret & Constants.MON_ACTIVE) == Constants.MON_ACTIVE) {
//                    mdi.setMonActive(true);
//                }
//                if ((ret & Constants.MON_REPORT) == Constants.MON_REPORT) {
//                    mdi.setMonReport(true);
//                }
                /*
             * if (mdi.getMonActive() == false){ ret =
             * JOptionPane.showOptionDialog(this, "ExMonitor is inactive. Do you
             * want to active it?", "Alert", JOptionPane.YES_NO_OPTION,
             * JOptionPane.WARNING_MESSAGE, null, Constants.optionYESNO,
             * Constants.optionOK[0]); if (ret == 0){
             *
             * }else{
             *
             * }
             * }
             */
//                mdi.monitor();
//            }
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
    private MDIMain mdi;
}

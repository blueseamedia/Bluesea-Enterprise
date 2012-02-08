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

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import vn.topmedia.monitor.MonitorBean;
import vn.topmedia.monitor.bean.MonitorSettingBean;
import vn.topmedia.monitor.common.MonitorLevel;
import vn.topmedia.monitor.commons.Alert;
import vn.topmedia.monitor.commons.Constants;
import vn.topmedia.monitor.commons.MyItem;
import vn.topmedia.monitor.commons.MyTreeCellRenderer;
import vn.topmedia.monitor.server.Server;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class JListMonitor extends javax.swing.JInternalFrame {

    /**
     * Creates new form SMSMonitor
     */
    public JListMonitor(MDIMain mdi) {
        initComponents();
        mdiMain = mdi;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) d.getWidth() - 15, (int) d.getHeight() - 100);
        inits();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBottom = new javax.swing.JPanel();
        jButtonClear = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jBtnUpdateAddress = new javax.swing.JButton();
        jScrollPaneMain = new javax.swing.JScrollPane();
        jSplitPane = new javax.swing.JSplitPane();
        jScrollPaneRight = new javax.swing.JScrollPane();
        jListMessage = new javax.swing.JList();
        jScrollPaneLeft = new javax.swing.JScrollPane();
        jTreeMonitor = new javax.swing.JTree();

        setMaximizable(true);
        setResizable(true);
        setTitle("Monitoring");

        jButtonClear.setText("Clear");
        jButtonClear.setEnabled(false);
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jBtnUpdateAddress.setText("Monitor");
        jBtnUpdateAddress.setEnabled(false);
        jBtnUpdateAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdateAddressActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanelBottomLayout = new org.jdesktop.layout.GroupLayout(jPanelBottom);
        jPanelBottom.setLayout(jPanelBottomLayout);
        jPanelBottomLayout.setHorizontalGroup(
            jPanelBottomLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .add(jBtnUpdateAddress)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonClear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButtonExit)
                .addContainerGap())
        );
        jPanelBottomLayout.setVerticalGroup(
            jPanelBottomLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jButtonExit)
                .add(jBtnUpdateAddress)
                .add(jButtonClear))
        );

        jSplitPane.setDividerLocation(150);
        jSplitPane.setDividerSize(4);

        jListMessage.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jListMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jListMessageMouseReleased(evt);
            }
        });
        jScrollPaneRight.setViewportView(jListMessage);

        jSplitPane.setRightComponent(jScrollPaneRight);

        jScrollPaneLeft.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneLeft.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTreeMonitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTreeMonitorMouseReleased(evt);
            }
        });
        jTreeMonitor.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeMonitorValueChanged(evt);
            }
        });
        jScrollPaneLeft.setViewportView(jTreeMonitor);

        jSplitPane.setLeftComponent(jScrollPaneLeft);

        jScrollPaneMain.setViewportView(jSplitPane);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelBottom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jScrollPaneMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jScrollPaneMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelBottom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTreeMonitorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMonitorMouseReleased
        // TODO add your handling code here:
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeMonitor.getLastSelectedPathComponent();
        if (selectedNode.getLevel() >= 2) {
            MyItem itm = (MyItem) selectedNode.getUserObject();
            if (evt.getButton() == MouseEvent.BUTTON1
                    && evt.getClickCount() == Constants.DOUBLE_CLICK) {
                if (itm.isTicked()) {
                    itm.unTickNode();
                } else {
                    itm.tickNode();
                }
            } else if (evt.getButton() == MouseEvent.BUTTON3) {
                if (itm.isMarked()) {
                    itm.unMarkNode();
                } else {
                    itm.markNode();
                }
            }
            treeModel.nodeChanged(selectedNode);
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        loadDataToSelectedNode(selectedNode);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jTreeMonitorMouseReleased

    private void jListMessageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMessageMouseReleased
        // TODO add your handling code here:
        if (!listModel.isEmpty()) {
            if (evt.getClickCount() == 2) {//double click
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeMonitor.getLastSelectedPathComponent();
                if (selectedNode.getLevel() >= 2) {
                    int idx = vSelectedHash.size() - jListMessage.getSelectedIndex() - 1;
                    MonitorBean smsError = vSelectedHash.get(idx);
                    messageDlg = new Messaging(mdiMain, true, smsError);
                    messageDlg.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jListMessageMouseReleased

    private void jTreeMonitorValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeMonitorValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTreeMonitorValueChanged

    private void jBtnUpdateAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateAddressActionPerformed
        // TODO add your handling code here:
//        try {
//            refreshServer(monUser);
//        } catch (IOException ex) {
//        }
    }//GEN-LAST:event_jBtnUpdateAddressActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        // TODO add your handling code here:
        doExit();
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeMonitor.getLastSelectedPathComponent();
        if (selectedNode.isLeaf()) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            ArrayList<MonitorBean> vector = hashNode.get(new Integer(selectedNode.hashCode()));
            vector.clear();
            listModel.clear();
            setNodeLabel(selectedNode);
            setNodeLabel((DefaultMutableTreeNode) selectedNode.getParent());
            jButtonClear.setEnabled(false);
            ((MyItem) selectedNode.getUserObject()).unMarkNode();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void inits() {

        listModel = new DefaultListModel();
        jListMessage.setModel(listModel);

        setMonitorUsersBean();

        jBtnUpdateAddress.setText("Monitor[" + monUser.getUserName() + "::" + monUser.getUserIP() + ":" + monUser.getUserPort() + "]");

        root = new DefaultMutableTreeNode(new MyItem(Constants.ROOT_LABEL, new Font("Tahoma", Font.BOLD, 11)));

        for (int i = 0; i < Constants.MONITOR_CPS.length; i++) {
            child = new DefaultMutableTreeNode(new MyItem(Constants.MONITOR_CPS[i], new Font("Tahoma", Font.BOLD, 11)));
            for (int j = 0; j < Constants.MONITOR_LABELS.length; j++) {
                child.insert(new DefaultMutableTreeNode(new MyItem(Constants.MONITOR_LABELS[j])), j);
            }
            root.insert(child, i);
        }

        treeModel = new DefaultTreeModel(root);
        jTreeMonitor.setModel(treeModel);

        //Expand node child
        for (int i = 0; i < Constants.MONITOR_CPS.length; i++) {
            int rowid = (Constants.MONITOR_LABELS.length + 1) * i + 2;
            jTreeMonitor.expandRow(rowid);
        }

        treeRenderer = new MyTreeCellRenderer();
        jTreeMonitor.setCellRenderer(treeRenderer);
        jTreeMonitor.setShowsRootHandles(false);
        jTreeMonitor.setSelectionRow(0);
        hashNode = new LinkedHashMap<Integer, ArrayList<MonitorBean>>();
        loadDataToAllNodes();
    }

    /**
     * Thiet lap cac thong so ban dau khi load tu file config
     */
    private void setMonitorUsersBean() {
        monUser = new MonitorSettingBean(mdiMain);
        monUser.setUserName(mdiMain.getUserName());
        monUser.setPassword(mdiMain.getPassword());
        monUser.setUserIP(mdiMain.getMonServer());
        monUser.setUserPort(mdiMain.getMonPort());
        monUser.setAlertType(mdiMain.getAlertType());
        monUser.setVitualHost(mdiMain.getVitualHost());
    }

    public void setMonitorUsersBean(MonitorSettingBean bean) {
        monUser = bean;
    }

    public void doExit() {
        if (server != null) {
            server.closeSocket();
        }
    }

    public void alert(MonitorBean bean) {
        if (bean == null || (bean.getLevel().equals(MonitorLevel.DEBUG) && !monUser.getDebugMode())) {
            return;
        }

        DefaultTreeModel treeModelTmp = (DefaultTreeModel) jTreeMonitor.getModel();
        TreeNode rootTmp = (TreeNode) treeModelTmp.getRoot();
        DefaultMutableTreeNode node = getNode(rootTmp, bean);
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeMonitor.getLastSelectedPathComponent();

        int nodeId = new Integer(node.hashCode());
        ArrayList<MonitorBean> vector = hashNode.get(nodeId);
        if (vector == null) {
            vector = new ArrayList<MonitorBean>();
        }
        if (vector.size() > 1000) {
            List<MonitorBean> tmp = vector.subList(0, 500);
            vector.clear();
            vector.addAll(tmp);
        }
        vector.add(bean);

        if (node.equals(selectedNode)) {
            if (listModel.size() > 1000) {
                listModel.removeRange(500, listModel.size() - 1);
            }
            listModel.add(0, bean.getInfo());
            jButtonClear.setEnabled(true);
        }

        //Mark not ro Red color
        MyItem itm = (MyItem) node.getUserObject();
        itm.markNode();

        Alert.alert(monUser, bean);
        setNodeLabel(node);
        setNodeLabel((DefaultMutableTreeNode) node.getParent());
    }

    private DefaultMutableTreeNode getNode(TreeNode root, MonitorBean bean) {
        DefaultMutableTreeNode node;
        int index;
        for (index = 0; index < Constants.MONITOR_CPS.length; index++) {
            if (bean.getServiceId().toUpperCase().equals(Constants.MONITOR_CPS[index].toUpperCase())) {
                break;
            }
        }
        node = (DefaultMutableTreeNode) treeModel.getChild(root, index);
        node = (DefaultMutableTreeNode) treeModel.getChild(node, getIndexOfNode(bean));
        return node;
    }

    private int getIndexOfNode(MonitorBean bean) {
        if (bean.getServiceId().equals("Logger")) {
            for (int i = 0; i < Constants.MONITOR_LOGGER.length; i++) {
                if (bean.getType().toUpperCase().equals(Constants.MONITOR_LOGGER[i])) {
                    return i;
                }
            }
        }
        return 0;
    }

    private ArrayList<MonitorBean> loadDataToSelectedNode(DefaultMutableTreeNode node) {
        if (hashNode == null) {
            hashNode = new LinkedHashMap<Integer, ArrayList<MonitorBean>>();
        }
        ArrayList<MonitorBean> vector = hashNode.get(new Integer(node.hashCode()));
        int level = node.getLevel();
        if (vector == null) {//data not load into vector yet
            vector = new ArrayList<MonitorBean>();
//            if (level == Constants.TREE_ROOT_LEVEL) {//load data of root level
//                for (int i = 0; i < Constants.MONITOR_CPS.length; i++) {
//                    vector.add(Constants.MONITOR_CPS[i]);
//                }
//            } else if (level == Constants.TREE_PARENT_LEVEL) {//load data of parent level
//                for (int i = 0; i < Constants.MONITOR_LABELS.length; i++) {
//                    vector.add(Constants.MONITOR_LABELS[i]);
//                }
//            } else if (level == Constants.TREE_CHILD_LEVEL) {//load data of child level

//                MyItem itm = (MyItem) node.getUserObjectPath()[1];
//                String serviceNumber = itm.getText();
//                serviceNumber = serviceNumber.substring(0, 4);
//                vector = new ArrayList<MonitorBean>();//make a blank data
//            }
            //Set to hash
            hashNode.put(new Integer(node.hashCode()), vector);
        }

        //reload the list
        if (vSelectedHash != vector) {
            vSelectedHash = vector;
            listModel.clear();
            if (level == Constants.TREE_CHILD_LEVEL) {
                int size = vector.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        MonitorBean bean = (MonitorBean) vector.get(i);
                        listModel.add(0, bean.getInfo());
                    }

                    MyItem itm = (MyItem) node.getUserObjectPath()[2];
                    String serviceNumber = itm.getText();
                    int pos = serviceNumber.indexOf("(");
                    if (pos > 0) {
                        serviceNumber = serviceNumber.substring(0, pos);
                    }
                    serviceNumber = serviceNumber + "(" + size + ")";
                    itm.setText(serviceNumber);
                    if (!itm.isMarked()) {
                        itm.tickNode();
                    }
                    jButtonClear.setEnabled(true);
                    treeModel.nodeChanged(node);
                } else {
                    jButtonClear.setEnabled(false);
                }
            } else {
                jButtonClear.setEnabled(false);
            }
        }
        return vector;
    }

    private void loadDataToAllNodes() {
        DefaultMutableTreeNode node;
        DefaultMutableTreeNode root1 = (DefaultMutableTreeNode) treeModel.getRoot();
        for (int i = 0; i < Constants.MONITOR_CPS.length; i++) {
            long count = 0;
            node = (DefaultMutableTreeNode) root1.getChildAt(i);
//            if (treeModel.getIndexOfChild(root1, node) <= Constants.START_MONITOR_INDEX) {
//                continue;
//            }
            for (int j = 0; j < Constants.MONITOR_LABELS.length; j++) {
                ArrayList<MonitorBean> v = loadDataToSelectedNode((DefaultMutableTreeNode) node.getChildAt(j));
                count += v.size();
            }
            if (count > 0) {
                MyItem itm = (MyItem) node.getUserObject();
                String txt = Constants.MONITOR_CPS[i] + "(" + count + ")";
                itm.setText(txt);
                treeModel.nodeChanged(node);
            }
        }
    }

    /**
     * Set node number of issue.
     *
     * @param node The node of tree.
     */
    private void setNodeLabel(DefaultMutableTreeNode node) {
        ArrayList<MonitorBean> vector;
        int total = 0;
        MyItem itm = (MyItem) node.getUserObject();
        String txt = "";
        if (node.isLeaf()) {
            vector = hashNode.get(new Integer(node.hashCode()));
            if (vector == null) {
                vector = new ArrayList<MonitorBean>();
            }
            total = vector.size();
            if (total > 0) {
                txt = Constants.MONITOR_LABELS[getIndexOfNode(node)] + "(" + total + ")";
            } else {
                txt = Constants.MONITOR_LABELS[getIndexOfNode(node)];
            }
            itm.setText(txt);

        } else {//CPs
            DefaultMutableTreeNode aChild;
            for (int i = 0; i < Constants.MONITOR_LABELS.length; i++) {
                aChild = (DefaultMutableTreeNode) node.getChildAt(i);
                vector = hashNode.get(new Integer(aChild.hashCode()));
                if (vector == null) {
                    vector = new ArrayList<MonitorBean>();
                }
                total += vector.size();
            }
            if (total > 0) {
                txt = Constants.MONITOR_CPS[getIndexOfNode(node)] + "(" + total + ")";
            } else {
                txt = Constants.MONITOR_CPS[getIndexOfNode(node)];
            }
            itm.setText(txt);
        }
        treeModel.nodeChanged(node);
    }

    private int getIndexOfNode(DefaultMutableTreeNode node) {
        if (node.isRoot()) {
            return 0;
        }
        return treeModel.getIndexOfChild(node.getParent(), node);
    }

    public void updateMonitor(boolean reload) {
        mdiMain.updateConfigFile(monUser);
        jBtnUpdateAddress.setEnabled(false);
        if (reload) {
            mdiMain.loadRabbitClient();
        }
    }

//    public MonitorClientCheck[] getClients() {
//        return SMSMonitor.clients;
//    }
    public MonitorSettingBean getMonitorUser() {
        return this.monUser;
    }

    public MDIMain getMDIMain() {
        return this.mdiMain;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnUpdateAddress;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JList jListMessage;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JScrollPane jScrollPaneLeft;
    private javax.swing.JScrollPane jScrollPaneMain;
    private javax.swing.JScrollPane jScrollPaneRight;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JTree jTreeMonitor;
    // End of variables declaration//GEN-END:variables
    private MDIMain mdiMain;
    private Messaging messageDlg;
    private DefaultTreeModel treeModel;
    private TreeCellRenderer treeRenderer;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode child;
    private DefaultListModel listModel;
    private LinkedHashMap<Integer, ArrayList<MonitorBean>> hashNode;
    private ArrayList<MonitorBean> vSelectedHash;
    private static Server server;
//    private static MonitorClientCheck[] clients; //client request to check SMSC, URL
    private MonitorSettingBean monUser;
}

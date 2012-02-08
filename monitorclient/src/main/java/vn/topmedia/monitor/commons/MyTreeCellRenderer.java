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
package vn.topmedia.monitor.commons;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MyTreeCellRenderer implements TreeCellRenderer {

    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
    Color backgroundSelectionColor;
    Color backgroundNonSelectionColor;
    Color borderSelectionColor;

    public MyTreeCellRenderer() {
        backgroundSelectionColor = renderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = renderer.getBackgroundNonSelectionColor();
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
            boolean expanded, boolean leaf, int row, boolean hasFocus) {

        Object tip = null;
        MyItem item = null;
        if (value != null) {
            if (value instanceof String) {
                tip = (String) value;
            } else if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                tip = (Object) node.getUserObject();
                if (tip instanceof MyItem) {
                    item = (MyItem) tip;
                }
            } else if (value instanceof MyItem) {
                item = (MyItem) value;
            } else {
                tip = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
            }
        }

        if (selected || hasFocus) {
            renderer.setBackgroundNonSelectionColor(backgroundSelectionColor);
        } else {
            renderer.setForeground(Color.BLACK);
            renderer.setBackgroundNonSelectionColor(Color.WHITE);
        }

        if (item != null) {
            renderer.setText(item.getText());
            renderer.setFont(item.getFont());
            renderer.setForeground(item.getColor());
        } else {
            renderer.setText((String) tip);
        }
        return renderer;
    }
}
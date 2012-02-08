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
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class ColorRenderer
        extends DefaultListCellRenderer {

    /**
     * Creates a new instance of ColorRenderer
     */
    public ColorRenderer() {
        initColorMap();
    }

    @Override
    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        super.getListCellRendererComponent(list,
                value,
                index,
                isSelected,
                cellHasFocus);
        if (value instanceof Color) {
            Color color = (Color) value;
            String strColor = (String) colorMap.get(color);
            if (strColor != null) {
                setText(strColor);
            }
            setBackground(color);
        }
        return this;
    }

    private void initColorMap() {
        colorMap = new HashMap();
        for (int x = 0; x < colorAssociation.length; ++x) {
            colorMap.put(colorAssociation[x][0], colorAssociation[x][1]);
        }
        colorAssociation = null;
    }
    private HashMap colorMap;
    private Object[][] colorAssociation = {
        {Color.BLACK, "Black"},
        {Color.BLUE, "Blue"},
        {Color.CYAN, "Cyan"},
        {Color.DARK_GRAY, "Dark Gray"},
        {Color.GRAY, "Gray"},
        {Color.GREEN, "Green"},
        {Color.LIGHT_GRAY, "Light Gray"},
        {Color.MAGENTA, "Magenta"},
        {Color.ORANGE, "Orange"},
        {Color.PINK, "Pink"},
        {Color.RED, "Red"},
        {Color.WHITE, "White"},
        {Color.YELLOW, "Yellow"},};
}

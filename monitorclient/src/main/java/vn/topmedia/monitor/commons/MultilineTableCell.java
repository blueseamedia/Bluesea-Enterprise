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

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MultilineTableCell
        implements TableCellRenderer {

    class CellArea extends DefaultTableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private String text;
        protected int rowIndex;
        protected int columnIndex;
        protected JTable table;
        protected Font font;
        private int paragraphStart, paragraphEnd;
        private LineBreakMeasurer lineMeasurer;

        public CellArea(String s, JTable tab, int row, int column, boolean isSelected) {
            text = s;
            rowIndex = row;
            columnIndex = column;
            table = tab;
            font = table.getFont();
            if (columnIndex == 1) {
                if (s.toUpperCase().equals("ERROR")) {
                    setForeground(isSelected ? table.getSelectionBackground() : Color.RED);
                } else {
                    setForeground(isSelected ? table.getSelectionBackground() : Color.BLUE);
                }
            }
            if (isSelected) {
                setForeground(Color.RED);
            }
        }

        @Override
        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            gr.setFont(font);
            if (text != null && !text.isEmpty()) {
                Graphics2D g = (Graphics2D) gr;
                if (lineMeasurer == null) {
                    AttributedCharacterIterator paragraph = new AttributedString(text).getIterator();
                    paragraphStart = paragraph.getBeginIndex();
                    paragraphEnd = paragraph.getEndIndex();
                    FontRenderContext frc = g.getFontRenderContext();
                    lineMeasurer = new LineBreakMeasurer(paragraph, BreakIterator.getWordInstance(), frc);
                }
                float breakWidth = (float) table.getColumnModel().getColumn(columnIndex).getWidth();
                float drawPosY = 0;
                // Set position to the index of the first character in the paragraph.
                lineMeasurer.setPosition(paragraphStart);
                // Get lines until the entire paragraph has been displayed.
                while (lineMeasurer.getPosition() < paragraphEnd) {
                    // Retrieve next layout. A cleverer program would also cache
                    // these layouts until the component is re-sized.
                    TextLayout layout = lineMeasurer.nextLayout(breakWidth);
                    // Compute pen x position. If the paragraph is right-to-left we
                    // will align the TextLayouts to the right edge of the panel.
                    // Note: this won't occur for the English text in this sample.
                    // Note: drawPosX is always where the LEFT of the text is placed.
                    float drawPosX = layout.isLeftToRight()
                            ? 0 : breakWidth - layout.getAdvance();
                    // Move y-coordinate by the ascent of the layout.
                    drawPosY += layout.getAscent();
                    // Draw the TextLayout at (drawPosX, drawPosY).
                    layout.draw(g, drawPosX, drawPosY);
                    // Move y-coordinate in preparation for next layout.
                    drawPosY += layout.getDescent() + layout.getLeading();
                }
                table.setRowHeight(rowIndex, (int) drawPosY);
            }
        }
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        CellArea area = new CellArea(value.toString(), table, row, column, isSelected);
        return area;
    }
}

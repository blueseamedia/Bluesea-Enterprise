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
import java.awt.Font;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MyItem {

    private String text;
    private String mask;
    private Font font;
    private Color color;
    private boolean isMarked = false;
    private boolean isTicked = false;
    private static Font fontBold = new Font("Tahoma", Font.BOLD, 11);
    private static Font fontPlain = new Font("Tahoma", Font.PLAIN, 11);
    private static Color markColor = new Color(0xCC, 0, 0);
    private static Color tickColor = new Color(0x00, 0x00, 0xCC);

    public MyItem(String txt) {
        this.mask = txt;
        this.text = txt;
        this.font = fontPlain;
    }

    public MyItem(String txt, Font f) {
        this.mask = txt;
        this.text = txt;
        this.font = f;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getText() {
        return this.text;
    }

    public String getMask() {
        return mask;
    }

    public void setFont(Font value) {
        this.font = value;
    }

    public Font getFont() {
        return this.font;
    }

    public void setColor(Color value) {
        this.color = value;
    }

    public Color getColor() {
        return this.color;
    }

    public void markNode() {
        this.color = markColor;
        this.font = fontBold;
        this.isMarked = true;
    }

    public void unMarkNode() {
        this.color = Color.BLACK;
        this.font = fontPlain;
        this.isMarked = false;
    }

    public void tickNode() {
        this.color = tickColor;
        this.font = fontBold;
        this.isTicked = true;
    }

    public void unTickNode() {
        this.color = Color.BLACK;
        this.font = fontPlain;
        this.isTicked = false;
    }

    public void setNodeBold() {
        this.color = Color.BLACK;
        this.font = fontBold;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public boolean isTicked() {
        return this.isTicked;
    }
}

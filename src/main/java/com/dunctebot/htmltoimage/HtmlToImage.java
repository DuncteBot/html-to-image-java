/*
 *    Copyright 2020 Duncan Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.dunctebot.htmltoimage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HtmlToImage {

    public static byte[] htmlToPngBytes(String html, int width, int height) throws IOException {

        final BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration()
            .createCompatibleImage(width, height);

        final JEditorPane pane = new JEditorPane("text/html", html);

        pane.setSize(width, height);
        pane.print(image.createGraphics());

        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);

            return baos.toByteArray();
        }
    }

    public static InputStream htmlToPng(String html, int width, int height) throws IOException {
        return new ByteArrayInputStream(htmlToPngBytes(html, width, height));
    }

}

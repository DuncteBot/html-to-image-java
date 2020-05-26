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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import com.dunctebot.htmltoimage.HtmlToImage;

public class Main {
    public static void main(String[] args) throws IOException {
        final byte[] bytes = HtmlToImage.htmlToPngBytes("<h1 style=\"background: green;\">hello world</h1>", 200, 200);

        try (FileOutputStream stream = new FileOutputStream(new File("image.png"))) {
            stream.write(bytes);
        }

        final InputStream inStream = HtmlToImage.htmlToPng(
            "<style>" +
                "body {" +
                "background: orange;" +
                "}" +
                "</style>" +
                "<h1 style=\"background: yellow;\">hello world</h1>",
            200,
            200
        );

        Files.copy(
            inStream,
            new File("image-input-stream.png").toPath(),
            StandardCopyOption.REPLACE_EXISTING
        );
    }
}

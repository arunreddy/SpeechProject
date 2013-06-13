/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package net.arunreddy.speech;

import java.io.File;
import java.net.URL;

/**
 * @version $Id$
 */
public class TranscribeAudioFile
{

    public void transcribe()
    {

    }

    public void transcribeAllFiles(File directoryPath)
    {
        try {

            URL configURL = this.getClass().getResource("/config.xml");
            SpeechToText stt = new SpeechToText(configURL);
            File[] files = directoryPath.listFiles();
            for (File file : files) {
                if (file.getName().endsWith("wav")) {
                    URL audioURL = file.toURI().toURL();
                    String transcribedText=stt.speechToText(audioURL);
                    System.out.println(file.getName()+"::"+transcribedText);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

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
import java.util.List;

import net.arunreddy.speech.segmenter.AudioFileSegmenter;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version $Id$
 */
public class SpeechToTextTest
{

    @Test
    public void testSpeech()
    {
        try {
            URL audioURL = new File("/home/arun/code/src/acads/SpeechProject/src/test/resources/example.wav").toURL();
            URL configURL = new File("/home/arun/code/src/acads/SpeechProject/src/main/config/config.xml").toURL();

//            Assert.assertNotNull(configURL);
            SpeechToText stt = new SpeechToText(configURL);

            String speechToText = stt.speechToText(audioURL);

            System.out.println(speechToText);
//            Assert.assertEquals("train eight arrives in new orleans at five fifteen", speechToText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

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
public class SpeechToTextTest {

	@Test
	public void testSpeech() {
		try {
			URL audioURL = new File(
					"/home/arun/devel/datasets/speech/DigitData-wavs/101/101_m01.wav")
					.toURI().toURL();

			URL configURL = this.getClass().getResource("/config.xml");

			Assert.assertNotNull(configURL);
			SpeechToText stt = new SpeechToText(configURL);

			String speechToText = stt.speechToText(audioURL);

			Assert.assertEquals(
					"train eight arrives in new orleans at five fifteen",
					speechToText);

			AudioFile audioFile = new AudioFile(audioURL.toURI());
			List<AudioSegment> list = new AudioFileSegmenter()
					.segmentAudioFile(audioFile);
			
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

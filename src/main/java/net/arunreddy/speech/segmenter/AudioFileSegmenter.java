/**
 * 
 */
package net.arunreddy.speech.segmenter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.arunreddy.speech.Aligner;
import net.arunreddy.speech.AudioFile;
import net.arunreddy.speech.AudioSegment;
import net.arunreddy.speech.SpeechToText;
import net.arunreddy.speech.audio.type.WavFile;
import edu.cmu.sphinx.linguist.dictionary.Pronunciation;
import edu.cmu.sphinx.linguist.dictionary.Word;
import edu.cmu.sphinx.result.WordResult;

/**
 * @author arun
 * 
 */
public class AudioFileSegmenter {
	/**
	 * Use default audio segmenter.
	 */
	public AudioFileSegmenter() {
	}

	public List<AudioSegment> segmentAudioFile(AudioFile audioFile)
			throws SegmenterException {
		List<AudioSegment> audioSegments = null;
		try {

			System.out.println("Printing Audio File :" + audioFile);
			audioSegments = new ArrayList<AudioSegment>();

			System.out.println("DURATION:" + audioFile.getDuration());
			Aligner aligner = new Aligner();
			File file = new File(audioFile.getPath());
			ArrayList<WordResult> wordResults = aligner.align(file,
					audioFile.getTranscription());

			// Create output directory.
			File rootDirectory = new File(System.getenv("speech_data_dir"),
					"segments");
			if (!rootDirectory.exists()) {
				rootDirectory.mkdir();
			}

			double[] buffer = new double[(int) (audioFile.getFrames() * audioFile
					.getChannels())];

			WavFile wavFile = WavFile.openWavFile(file);
			long framesRead = wavFile.readFrames(buffer,
					(int) audioFile.getFrames());
			wavFile.close();
			for (WordResult wr : wordResults) {
				System.out.println(wr + "  -- " + wr.isFiller());
				if (!wr.isFiller()) {
					int startTime = wr.getStartFrame();
					int endTime = wr.getEndFrame();

					if (endTime == -1) {
						endTime = (int) audioFile.getDuration();
					}

					System.out.format("%s :: Start:%d  End:%d \n",
							wr.getPronunciation(), startTime, endTime);

					long startFrame = (long) (((double) startTime / audioFile
							.getDuration()) * audioFile.getFrames());
					long endFrame = (long) (((double) endTime / audioFile
							.getDuration()) * audioFile.getFrames());

					Pronunciation pr = wr.getPronunciation();
					Word word = pr.getWord();

					String audioFileInternalPath = file.getPath().replaceAll(
							System.getenv("speech_data_dir"), "");

					// Write file to
					File audioFileDirectory = new File(rootDirectory,
							audioFileInternalPath);

					audioFileDirectory.mkdirs();

					File segmentFile = new File(audioFileDirectory,
							word.getSpelling() + ".wav");

					WavFile writeWavFile = WavFile.newWavFile(segmentFile,
							audioFile.getChannels(), endFrame - startFrame,
							audioFile.getBitDepth(), audioFile.getSampleRate());

					long framesWritten = writeWavFile.writeFrames(buffer,
							(int) ((startFrame - 1) * audioFile.getChannels()),
							(int) (endFrame - startFrame));

					double duration = ((double) framesWritten / audioFile
							.getSampleRate()) * 1000;
					AudioSegment audioSegment = new AudioSegment(
							word.getSpelling(), framesWritten, duration);
					audioSegment.setPath(segmentFile.toURI());

					System.out.format("%s :: Start:%d  End:%d \n",
							word.getSpelling(), startFrame, endFrame);
					System.out.println("Frames written:" + framesWritten);

					writeWavFile.close();
					audioSegments.add(audioSegment);
				}
			}

		} catch (Exception e) {
			throw new SegmenterException("Unable to segment the audio file ["
					+ audioFile + "]", e);
		}

		return audioSegments;
	}
}

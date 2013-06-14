/**
 * 
 */
package net.arunreddy.speech;

import java.io.File;
import java.net.URI;

import net.arunreddy.speech.audio.type.WavFile;

/**
 * @author arun
 * 
 */
public class AudioFile {

	private URI path;
	private String name;
	private String mimetype;
	private int channels;
	private long sampleRate;
	private int bitDepth;
	private long frames;
	private double duration;

	
	public AudioFile(){
		
	}
	
	public AudioFile(URI path) throws AudioException {
		this.path = path;

		// Set name.
		File audioFile = new File(path);
		this.setName(audioFile.getName());

		// Set other Audio Properties.
		try {
			// Set mimetype.
			this.setMimetype(path.toURL().openConnection().getContentType());

			// TODO : Fix and generalize.
			WavFile wavFile = WavFile.openWavFile(audioFile);
			this.setChannels(wavFile.getNumChannels());
			this.setSampleRate(wavFile.getSampleRate());
			this.setFrames(wavFile.getNumFrames());
			this.setBitDepth(wavFile.getValidBits());
			double duration = ((double) this.getFrames() / this.getSampleRate()) * 1000;
			this.setDuration(duration);
			wavFile.close();

		} catch (Exception e) {
			throw new AudioException("Error in fetching audio properties for "
					+ path, e);
		}

	}

	public URI getPath() {
		return path;
	}

	public void setPath(URI path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public long getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(long sampleRate) {
		this.sampleRate = sampleRate;
	}

	public int getBitDepth() {
		return bitDepth;
	}

	public void setBitDepth(int bitDepth) {
		this.bitDepth = bitDepth;
	}

	public long getFrames() {
		return frames;
	}

	public void setFrames(long frames) {
		this.frames = frames;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

}

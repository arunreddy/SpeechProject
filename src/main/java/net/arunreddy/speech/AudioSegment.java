package net.arunreddy.speech;

import java.net.URI;

public class AudioSegment {

	private URI path;
	private String name;
	private long frames;
	private double duration;

	public AudioSegment(String name, long frames, double duration) {
		super();
		this.name = name;
		this.frames = frames;
		this.duration = duration;
	}

	/**
	 * @return the path
	 */
	public URI getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(URI path) {
		this.path = path;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the frames
	 */
	public long getFrames() {
		return frames;
	}

	/**
	 * @param frames
	 *            the frames to set
	 */
	public void setFrames(long frames) {
		this.frames = frames;
	}

	/**
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

}

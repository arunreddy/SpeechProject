/**
 * 
 */
package net.arunreddy.speech.segmenter;

/**
 * @author arun
 * 
 */
public class SegmenterException extends Exception {
	/**
	 * Class version.
	 */
	private static final long serialVersionUID = -8668563628341209654L;

	/**
	 * @param message
	 */
	public SegmenterException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public SegmenterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SegmenterException(String message, Throwable cause) {
		super(message, cause);
	}
}

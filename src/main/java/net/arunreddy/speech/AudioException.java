/**
 * 
 */
package net.arunreddy.speech;

/**
 * @author arun
 * 
 */
public class AudioException extends Exception {
	/**
	 * Class version.
	 */
	private static final long serialVersionUID = -8668563628341209654L;

	/**
	 * @param message
	 */
	public AudioException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public AudioException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AudioException(String message, Throwable cause) {
		super(message, cause);
	}
}

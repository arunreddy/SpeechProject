package net.arunreddy.speech.audio.type;

public class AudioTypeException extends Exception
{
	public AudioTypeException()
	{
		super();
	}

	public AudioTypeException(String message)
	{
		super(message);
	}

	public AudioTypeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public AudioTypeException(Throwable cause) 
	{
		super(cause);
	}
}

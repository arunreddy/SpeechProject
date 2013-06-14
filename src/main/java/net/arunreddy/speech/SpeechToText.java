package net.arunreddy.speech;

import java.net.URL;

import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechToText
{

    private URL configURL;

    private ConfigurationManager cm;

    private Recognizer recognizer;
    
    
    /**
     * Pick the default configuration file.
     */
    public SpeechToText(){
    	this(SpeechToText.class.getResource("/config.xml"));
    }

    /**
     * @param configURL
     */
    public SpeechToText(URL configURL)
    {
        super();
        this.configURL = configURL;
        cm = new ConfigurationManager(this.configURL);
        recognizer = (Recognizer) cm.lookup("recognizer");

    }

    public String speechToText(URL audioURL)
    {

        String speechString = "";

        try {

            // Allocate resources required for recognizer.
            recognizer.allocate();

            // configure the audio input for the recognizer
            AudioFileDataSource dataSource = (AudioFileDataSource) cm.lookup("audioFileDataSource");
            dataSource.setAudioFile(audioURL, null);

            // Loop until last utterance in the audio file has been decoded, in which case the recognizer will return
            // null.
            Result result;

            StringBuilder builder=new StringBuilder();
            while ((result = recognizer.recognize()) != null) {
                builder.append(result.getBestResultNoFiller()+" ");                
            }
            speechString=builder.toString().trim();

            recognizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return speechString;
    }

	public Recognizer getRecognizer() {
		return recognizer;
	}

    
    
    
}

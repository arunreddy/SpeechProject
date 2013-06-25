package net.arunreddy.speech;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import edu.cmu.sphinx.api.GrammarAligner;
import edu.cmu.sphinx.result.WordResult;

/**
 * @version $Id$
 */
public class Aligner
{

    private URL acousticModel;

    private URL dictionary;

    private GrammarAligner aligner;

    public Aligner()
    {
        try {
            acousticModel = this.getClass().getResource("/WSJ_8gau_13dCep_16k_40mel_130Hz_6800Hz");
            dictionary = this.getClass().getResource("/WSJ_8gau_13dCep_16k_40mel_130Hz_6800Hz/dict/cmudict.0.6d");
            aligner = new GrammarAligner(acousticModel, dictionary, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WordResult> align(File audioFile, String transcription)
    {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(audioFile);
            ArrayList<WordResult> results = aligner.align(stream, transcription);

            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

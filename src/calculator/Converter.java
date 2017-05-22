package calculator;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class Converter {

	public static void main(String[] args) {
		System.out.println("Loading Voice Calculator...");
		MainGUI mainFrame = new MainGUI();
		TextToSpeech tts = new TextToSpeech();
		ConfigurationManager cm;

		if (args.length > 0) {
			cm = new ConfigurationManager(args[0]);
		} else {
			cm = new ConfigurationManager(MainGUI.class.getResource("calculator.config.xml"));
			System.out.println("Loading configuration files...");
		}

		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();

		// start the microphone or exit if unable to
		Microphone microphone = (Microphone) cm.lookup("microphone");
		System.out.println("Starting microphone...");
		if (!microphone.startRecording()) {
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}

		System.out.println("Loaded");

		// display the frame
		mainFrame.setVisible(true);

		boolean running = true;
		
		while (running) {
			Result firstResult = recognizer.recognize();
			String firstSpeakingResult = firstResult.getBestFinalResultNoFiller();
			if (firstSpeakingResult.equals("voice mode")) {
				System.out.println("Voice mode activated.");
				mainFrame.micOffLabel.setVisible(false);
				mainFrame.micOnLabel.setVisible(true);
				tts.setVoice("dfki-poppy-hsmm");
				tts.speak("Voice mode activated.", 2.0f, false, true);
				running = false;
			}
			while (!running) {
				Result secondResult = recognizer.recognize();
				String secondSpeakingResult = secondResult.getBestFinalResultNoFiller();

				if (secondSpeakingResult.equals("file")) {
					mainFrame.mnFile.doClick();
					Result thirdResult = recognizer.recognize();
					String thirdSpeakingResult = thirdResult.getBestFinalResultNoFiller();
					if (thirdSpeakingResult.equals("exit")) {
						mainFrame.mntmExit.doClick();
					}
				}
				// Add
				if (secondSpeakingResult.equals("plus") || secondSpeakingResult.equals("add")) {
					mainFrame.buttonAdd.doClick();
				}
				// Minus
				if (secondSpeakingResult.equals("minus") || secondSpeakingResult.equals("take away")) {
					mainFrame.buttonMinus.doClick();
				}
				// Divide
				if (secondSpeakingResult.equals("divided by") || secondSpeakingResult.equals("divide")
						|| secondSpeakingResult.equals("over")) {
					mainFrame.buttonDivide.doClick();
				}
				// Multiply
				if (secondSpeakingResult.equals("multiplied by") || secondSpeakingResult.equals("times")) {
					mainFrame.buttonMultiply.doClick();
				}
				// Clear
				if (secondSpeakingResult.equals("clear all")) {
					mainFrame.btnClear.doClick();
				}
				// Nine
				if (secondSpeakingResult.equals("delete history")) {
					mainFrame.buttonClearHistory.doClick();
				}
				// Backspace
				if (secondSpeakingResult.equals("backspace")) {
					mainFrame.buttonBackspace.doClick();
				}
				// Equals
				if (secondSpeakingResult.equals("equals")) {
					mainFrame.buttonEquals.doClick();
				}
				// Decimal point
				if (secondSpeakingResult.equals("dot") || secondSpeakingResult.equals("point")
						|| secondSpeakingResult.equals("decimal point")) {
					mainFrame.buttonDot.doClick();
				}
				// Zero
				if (secondSpeakingResult.equals("zero")) {
					mainFrame.button0.doClick();
				}
				// One
				if (secondSpeakingResult.equals("one")) {
					mainFrame.button1.doClick();
				}
				// Two
				if (secondSpeakingResult.equals("two")) {
					mainFrame.button2.doClick();
				}
				// Three
				if (secondSpeakingResult.equals("three")) {
					mainFrame.button3.doClick();
				}
				// Four
				if (secondSpeakingResult.equals("four")) {
					mainFrame.button4.doClick();
				}
				// Five
				if (secondSpeakingResult.equals("five")) {
					mainFrame.button5.doClick();
				}
				// Six
				if (secondSpeakingResult.equals("six")) {
					mainFrame.button6.doClick();
				}
				// Seven
				if (secondSpeakingResult.equals("seven")) {
					mainFrame.button7.doClick();
				}
				// Eight
				if (secondSpeakingResult.equals("eight")) {
					mainFrame.button8.doClick();
				}
				// Nine
				if (secondSpeakingResult.equals("nine")) {
					mainFrame.button9.doClick();
				}
				// Repeat Answer
				if (secondSpeakingResult.equals("repeat answer")) {
					tts.setVoice("dfki-poppy-hsmm");
					if (mainFrame.textFieldAnswer.getText().equals("")) {
						tts.speak("No answer available.", 2.0f, false, true);
					} else {
						tts.speak(mainFrame.textFieldAnswer.getText(), 2.0f, false, true);
					}
				}
				// Voice mode off
				if (secondSpeakingResult.equals("voice mode off")) {
					mainFrame.micOffLabel.setVisible(true);
					mainFrame.micOnLabel.setVisible(false);
					tts.setVoice("dfki-poppy-hsmm");
					tts.speak("Voice mode deactivated.", 2.0f, false, true);
					running = true;
				}
				System.out.println("You said: " + secondSpeakingResult);
			}
		}
	}
}
package calculator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.util.*;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class MainGUI extends JFrame {

	private JPanel contentPane, panel, panel_1, panel_2, panel_3;
	JButton button7, button8, button9, button4, button5, button6, button1, button2, button3, button0, buttonMultiply,
			buttonAdd, buttonDivide, buttonMinus, btnClear, buttonC, buttonBackspace, buttonDot, buttonClearHistory,
			buttonEquals;
	JLabel lblHistory, button1Label, button2Label, button3Label, button4Label, button5Label, button6Label, button7Label,
			button8Label, button9Label, button0Label, buttonPlusLabel, buttonMinusLabel, buttonDivideLabel,
			buttonMultiplyLabel, buttonEqualsLabel, buttonBackspaceLabel, buttonClearLabel, buttonDotLabel, micOffLabel,
			micOnLabel;
	JMenu mnFile;
	JMenuItem mntmExit;
	JTextField textFieldExpression, textFieldAnswer;
	JTextArea textAreaHistory;
	private Calculate calculate = new Calculate();;
	private TextToSpeech tts = new TextToSpeech();
	private int decimalCounter;
	private Timer iconTimer;
	public static Stack<String> stack = new Stack<String>();
	private JPanel panel_4;

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 550);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\calculator-icon.png"));
		setTitle("Voice Calculator");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		mnFile.add(mntmExit);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 478, 500);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY));
		contentPane.add(panel);
		panel.setLayout(null);

		// set colour of disabled buttons
		UIManager.getDefaults().put("Button.disabledText", Color.BLACK);
		// set colour when buttons are selected
		UIManager.put("Button.select", SystemColor.controlHighlight.darker());

		buttonDotLabel = new JLabel(new ImageIcon("images/dot_black.png"));
		buttonDotLabel.setBounds(256, 302, 150, 96);
		panel.add(buttonDotLabel);
		buttonDotLabel.setVisible(false);

		button6Label = new JLabel(new ImageIcon("images/six_black.png"));
		button6Label.setBounds(129, 227, 150, 96);
		panel.add(button6Label);
		button6Label.setVisible(false);
		button6Label.setLabelFor(button6);

		button3Label = new JLabel(new ImageIcon("images/three_black.png"));
		button3Label.setBounds(126, 302, 150, 96);
		panel.add(button3Label);
		button3Label.setVisible(false);
		button3Label.setLabelFor(button3);

		buttonBackspaceLabel = new JLabel(new ImageIcon("images/backspace_black.png"));
		buttonBackspaceLabel.setBounds(324, 153, 150, 96);
		panel.add(buttonBackspaceLabel);
		buttonBackspaceLabel.setVisible(false);

		buttonMinusLabel = new JLabel(new ImageIcon("images/minus_black.png"));
		buttonMinusLabel.setBounds(256, 153, 150, 96);
		panel.add(buttonMinusLabel);
		buttonMinusLabel.setVisible(false);
		buttonMinusLabel.setLabelFor(buttonMinus);

		buttonPlusLabel = new JLabel(new ImageIcon("images/plus_black.png"));
		buttonPlusLabel.setBounds(194, 153, 150, 96);
		panel.add(buttonPlusLabel);
		buttonPlusLabel.setVisible(false);
		buttonPlusLabel.setLabelFor(buttonAdd);

		button9Label = new JLabel(new ImageIcon("images/nine_black.png"));
		button9Label.setBounds(126, 153, 150, 96);
		panel.add(button9Label);
		button9Label.setVisible(false);
		button9Label.setLabelFor(button9);

		button8Label = new JLabel(new ImageIcon("images/eight_black.png"));
		button8Label.setBounds(63, 153, 150, 96);
		panel.add(button8Label);
		button8Label.setVisible(false);
		button8Label.setLabelFor(button8);

		button7Label = new JLabel(new ImageIcon("images/seven_black.png"));
		button7Label.setLabelFor(button7Label);
		button7Label.setBounds(0, 153, 150, 96);
		panel.add(button7Label);
		button7Label.setVisible(false);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 43, 476, 180);
		panel.add(panel_1);
		panel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);

		textFieldExpression = new JTextField();
		textFieldExpression.setHorizontalAlignment(JTextField.RIGHT);
		textFieldExpression.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		textFieldExpression.setBounds(10, 11, 441, 57);
		panel_1.add(textFieldExpression);
		textFieldExpression.setBackground(Color.WHITE);
		textFieldExpression.setFont(new Font("Verdana", Font.PLAIN, 33));
		textFieldExpression.setEditable(false);

		textFieldAnswer = new JTextField();
		textFieldAnswer.setHorizontalAlignment(JTextField.RIGHT);
		textFieldAnswer.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textFieldAnswer.setBackground(Color.WHITE);
		textFieldAnswer.setFont(new Font("Verdana", Font.PLAIN, 35));
		textFieldAnswer.setEditable(false);
		textFieldAnswer.setBounds(10, 79, 441, 57);
		panel_1.add(textFieldAnswer);

		buttonClearLabel = new JLabel(new ImageIcon("images/clear_black.png"));
		buttonClearLabel.setBounds(324, 227, 150, 96);
		panel.add(buttonClearLabel);
		buttonClearLabel.setVisible(false);
		buttonClearLabel.setLabelFor(btnClear);

		buttonMultiplyLabel = new JLabel(new ImageIcon("images/multiply_black.png"));
		buttonMultiplyLabel.setBounds(194, 227, 150, 96);
		panel.add(buttonMultiplyLabel);
		buttonMultiplyLabel.setVisible(false);

		buttonDivideLabel = new JLabel(new ImageIcon("images/divide_black.png"));
		buttonDivideLabel.setBounds(256, 227, 150, 96);
		panel.add(buttonDivideLabel);
		buttonDivideLabel.setVisible(false);

		button4Label = new JLabel(new ImageIcon("images/four_black.png"));
		button4Label.setBounds(0, 227, 150, 96);
		panel.add(button4Label);
		button4Label.setVisible(false);

		button5Label = new JLabel(new ImageIcon("images/five_black.png"));
		button5Label.setBounds(65, 227, 150, 96);
		panel.add(button5Label);
		button5Label.setVisible(false);

		button1Label = new JLabel(new ImageIcon("images/one_black.png"));
		button1Label.setBackground(SystemColor.control);
		button1Label.setBounds(0, 302, 150, 96);
		panel.add(button1Label);
		button1Label.setVisible(false);

		button0Label = new JLabel(new ImageIcon("images/zero_black.png"));
		button0Label.setBounds(191, 302, 150, 96);
		panel.add(button0Label);
		button0Label.setVisible(false);
		button0Label.setLabelFor(button0);

		button2Label = new JLabel(new ImageIcon("images/two_black.png"));
		button2Label.setBounds(63, 302, 150, 96);
		panel.add(button2Label);
		button2Label.setVisible(false);

		buttonEqualsLabel = new JLabel(new ImageIcon("images/equals_black.png"));
		buttonEqualsLabel.setBounds(324, 302, 150, 96);
		panel.add(buttonEqualsLabel);
		buttonEqualsLabel.setVisible(false);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(0, 221, 476, 279);
		panel.add(panel_4);
		panel_4.setLayout(null);

		// Button Seven
		button7 = new JButton("7");
		button7.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button7.setBackground(SystemColor.controlHighlight);
				} else {
					button7.setBackground(SystemColor.control);
				}
			}
		});
		button7.setBounds(40, 29, 65, 74);
		panel_4.add(button7);
		button7.setFocusPainted(false);
		button7.setBorderPainted(false);
		button7.setForeground(Color.BLACK);
		button7.setBackground(SystemColor.control);
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// button click
				buttonClickNoise();
				
				// timer
				button7Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				// send to screen
				textFieldExpression.setText(textFieldExpression.getText() + "7");

				// send to console
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				// build equation
				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button7.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button7.getText());
				}
			}
		});
		button7.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Eight
		button8 = new JButton("8");
		button8.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button8.setBackground(SystemColor.controlHighlight);
				} else {
					button8.setBackground(SystemColor.control);
				}
			}
		});
		button8.setBounds(105, 29, 65, 74);
		panel_4.add(button8);
		button8.setBackground(SystemColor.control);
		button8.setFocusPainted(false);
		button8.setBorderPainted(false);
		button8.setForeground(Color.BLACK);
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button8Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText(textFieldExpression.getText() + "8");
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button8.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button8.getText());
				}
			}
		});
		button8.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Nine
		button9 = new JButton("9");
		button9.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button9.setBackground(SystemColor.controlHighlight);
				} else {
					button9.setBackground(SystemColor.control);
				}
			}
		});
		button9.setBounds(170, 29, 65, 74);
		panel_4.add(button9);
		button9.setBackground(SystemColor.control);
		button9.setFocusPainted(false);
		button9.setBorderPainted(false);
		button9.setForeground(Color.BLACK);
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button9Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText(textFieldExpression.getText() + "9");
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button9.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button9.getText());
				}
			}
		});
		button9.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Add Button
		buttonAdd = new JButton("+");
		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonAdd.setBackground(SystemColor.controlHighlight);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonAdd.setBackground(SystemColor.control);
			}
		});
		buttonAdd.setBounds(235, 29, 65, 74);
		panel_4.add(buttonAdd);
		buttonAdd.setBackground(SystemColor.control);
		buttonAdd.setEnabled(false);
		buttonAdd.setFocusPainted(false);
		buttonAdd.setBorderPainted(false);
		buttonAdd.setForeground(Color.BLACK);
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);

				buttonClickNoise();
				buttonPlusLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				calculate.buildExpression(buttonAdd.getText());
				textFieldExpression.setText(textFieldExpression.getText() + "+");
				System.out.println(calculate.linkedList);
				decimalHelper();

			}
		});
		buttonAdd.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Minus Button
		buttonMinus = new JButton("-");
		buttonMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonMinus.setBackground(SystemColor.controlHighlight);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonMinus.setBackground(SystemColor.control);
			}
		});
		buttonMinus.setBounds(300, 29, 65, 74);
		panel_4.add(buttonMinus);
		buttonMinus.setBackground(SystemColor.control);
		buttonMinus.setEnabled(false);
		buttonMinus.setFocusPainted(false);
		buttonMinus.setBorderPainted(false);
		buttonMinus.setForeground(Color.BLACK);
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				buttonMinusLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);

				calculate.buildExpression(buttonMinus.getText());
				textFieldExpression.setText(textFieldExpression.getText() + "-");
				System.out.println(calculate.linkedList);
				decimalHelper();

			}
		});
		buttonMinus.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Clear Button
		btnClear = new JButton("C");
		btnClear.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					btnClear.setBackground(SystemColor.controlHighlight);
				} else {
					btnClear.setBackground(SystemColor.control);
				}
			}
		});
		btnClear.setBounds(365, 104, 65, 74);
		panel_4.add(btnClear);
		btnClear.setBackground(SystemColor.control);
		btnClear.setFocusPainted(false);
		btnClear.setBorderPainted(false);
		btnClear.setForeground(Color.BLACK);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				buttonClearLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText("");
				textFieldAnswer.setText("");

				calculate.clearAll();
				decimalCounter = 0;

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);
			}
		});
		btnClear.setFont(new Font("Verdana", Font.PLAIN, 25));

		// backspace button
		buttonBackspace = new JButton(new ImageIcon("images/backspace.png"));
		buttonBackspace.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					buttonBackspace.setBackground(SystemColor.controlHighlight);
				} else {
					buttonBackspace.setBackground(SystemColor.control);
				}
			}
		});
		buttonBackspace.setBounds(365, 29, 65, 74);
		panel_4.add(buttonBackspace);
		buttonBackspace.setBackground(SystemColor.control);
		buttonBackspace.setFocusPainted(false);
		buttonBackspace.setBorderPainted(false);
		buttonBackspace.setForeground(Color.BLACK);
		buttonBackspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				buttonBackspaceLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				if (calculate.isDecimalPointPressed() == false) {
					calculate.setNumber(calculate.getNumber().substring(0, calculate.getNumber().length() - 1));
				} else {
					decimalCounter--;
					if (decimalCounter == 0) {
						calculate.setDecimalPointPressed(false);
						calculate.setNumber(calculate.getNumber().substring(0, calculate.getNumber().length() - 1));
					}
				}
				// clear text area on screen
				textFieldExpression.setText(
						textFieldExpression.getText().substring(0, textFieldExpression.getText().length() - 1));

				System.out.println(calculate.linkedList);
			}
		});
		buttonBackspace.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Divide Button
		buttonDivide = new JButton("÷");
		buttonDivide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonDivide.setBackground(SystemColor.controlHighlight);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonDivide.setBackground(SystemColor.control);
			}
		});
		buttonDivideLabel.setLabelFor(buttonDivide);
		buttonDivide.setBounds(300, 104, 65, 74);
		panel_4.add(buttonDivide);
		buttonDivide.setEnabled(false);
		buttonDivide.setFocusPainted(false);
		buttonDivide.setBorderPainted(false);
		buttonDivide.setForeground(Color.BLACK);
		buttonDivide.setBackground(SystemColor.control);
		buttonDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);

				buttonClickNoise();
				buttonDivideLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				calculate.buildExpression(buttonDivide.getText());
				textFieldExpression.setText(textFieldExpression.getText() + "÷");
				System.out.println(calculate.linkedList);
				decimalHelper();

			}
		});
		buttonDivide.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button 6
		button6 = new JButton("6");
		button6.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button6.setBackground(SystemColor.controlHighlight);
				} else {
					button6.setBackground(SystemColor.control);
				}
			}
		});
		button6.setBounds(170, 104, 65, 74);
		panel_4.add(button6);
		button6.setBackground(SystemColor.control);
		button6.setFocusPainted(false);
		button6.setBorderPainted(false);
		button6.setForeground(Color.BLACK);
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button6Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				textFieldExpression.setText(textFieldExpression.getText() + "6");
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button6.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button6.getText());
				}
			}
		});
		button6.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Four
		button4 = new JButton("4");
		button4.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button4.setBackground(SystemColor.controlHighlight);
				} else {
					button4.setBackground(SystemColor.control);
				}
			}
		});
		button4Label.setLabelFor(button4);
		button4.setBounds(40, 104, 65, 74);
		panel_4.add(button4);
		button4.setBackground(SystemColor.control);
		button4.setFocusPainted(false);
		button4.setBorderPainted(false);
		button4.setForeground(Color.BLACK);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button4Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText(textFieldExpression.getText() + "4");
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button4.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button4.getText());
				}
			}
		});
		button4.setFont(new Font("Verdana", Font.PLAIN, 25));
		// Button One
		button1 = new JButton("1");
		button1.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button1.setBackground(SystemColor.controlHighlight);
				} else {
					button1.setBackground(SystemColor.control);
				}
			}
		});
		button1Label.setLabelFor(button1);
		button1.setBounds(40, 179, 65, 74);
		panel_4.add(button1);
		button1.setBackground(SystemColor.control);
		button1.setFocusPainted(false);
		button1.setBorderPainted(false);
		button1.setForeground(Color.BLACK);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button1Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText(textFieldExpression.getText() + "1");

				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button1.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button1.getText());
				}

			}
		});
		button1.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Five
		button5 = new JButton("5");
		button5.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button5.setBackground(SystemColor.controlHighlight);
				} else {
					button5.setBackground(SystemColor.control);
				}
			}
		});
		button5Label.setLabelFor(button5);
		button5.setBounds(105, 104, 65, 74);
		panel_4.add(button5);
		button5.setBackground(SystemColor.control);
		button5.setFocusPainted(false);
		button5.setBorderPainted(false);
		button5.setForeground(Color.BLACK);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button5Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				textFieldExpression.setText(textFieldExpression.getText() + "5");
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button5.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button5.getText());
				}
			}
		});
		button5.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Two
		button2 = new JButton("2");
		button2.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button2.setBackground(SystemColor.controlHighlight);
				} else {
					button2.setBackground(SystemColor.control);
				}
			}
		});
		button2Label.setLabelFor(button2);
		button2.setBounds(105, 179, 65, 74);
		panel_4.add(button2);
		button2.setBackground(SystemColor.control);
		button2.setFocusPainted(false);
		button2.setBorderPainted(false);
		button2.setForeground(Color.BLACK);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button2Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				textFieldExpression.setText(textFieldExpression.getText() + "2");
				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button2.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button2.getText());
				}
			}
		});
		button2.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Three
		button3 = new JButton("3");
		button3.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button3.setBackground(SystemColor.controlHighlight);
				} else {
					button3.setBackground(SystemColor.control);
				}
			}
		});
		button3.setBounds(170, 179, 65, 74);
		panel_4.add(button3);
		button3.setBackground(SystemColor.control);
		button3.setFocusPainted(false);
		button3.setBorderPainted(false);
		button3.setForeground(Color.BLACK);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button3Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText(textFieldExpression.getText() + "3");

				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button3.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button3.getText());
				}
			}
		});
		button3.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Button Zero
		button0 = new JButton("0");
		button0.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					button0.setBackground(SystemColor.controlHighlight);
				} else {
					button0.setBackground(SystemColor.control);
				}
			}
		});
		button0.setBounds(235, 179, 65, 74);
		panel_4.add(button0);
		button0.setBackground(SystemColor.control);
		button0.setFocusPainted(false);
		button0.setBorderPainted(false);
		button0.setForeground(Color.BLACK);
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				button0Label.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				textFieldExpression.setText(textFieldExpression.getText() + "0");
				System.out.println(calculate.linkedList);

				// enable buttons
				buttonDivide.setEnabled(true);
				buttonAdd.setEnabled(true);
				buttonMultiply.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (calculate.isDecimalPointPressed() == true) {
					decimalCounter++;
					double temp = (Double.parseDouble(button0.getText())) / Math.pow(10, decimalCounter);
					double operand = Double.parseDouble(calculate.getNumber());
					calculate.setNumber(Double.toString(temp + operand));
				} else {
					calculate.buildNumber(button0.getText());
				}
			}
		});
		button0.setFont(new Font("Verdana", Font.PLAIN, 25));

		// decimal point button
		buttonDot = new JButton(".");
		buttonDot.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					buttonDot.setBackground(SystemColor.controlHighlight);
				} else {
					buttonDot.setBackground(SystemColor.control);
				}
			}
		});
		buttonDot.setBounds(300, 179, 65, 74);
		panel_4.add(buttonDot);
		buttonDot.setBackground(SystemColor.control);
		buttonDot.setFocusPainted(false);
		buttonDot.setBorderPainted(false);
		buttonDot.setForeground(Color.BLACK);
		buttonDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonClickNoise();
				buttonDotLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}
				calculate.setDecimalPointPressed(true);
				buttonDot.setEnabled(false);
				textFieldExpression.setText(textFieldExpression.getText() + ".");

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);

			}
		});
		buttonDot.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Equals Button
		buttonEquals = new JButton("=");
		buttonEquals.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isRollover()) {
					buttonEquals.setBackground(SystemColor.controlHighlight);
				} else {
					buttonEquals.setBackground(SystemColor.control);
				}
			}
		});
		buttonEquals.setBounds(365, 179, 65, 74);
		panel_4.add(buttonEquals);
		buttonEquals.setBackground(SystemColor.control);
		buttonEquals.setFocusPainted(false);
		buttonEquals.setBorderPainted(false);
		buttonEquals.setForeground(Color.BLACK);
		buttonEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// call methods
				buttonClickNoise();
				decimalHelper();
				buttonEqualsLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);

				textFieldAnswer.setText(Double.toString(calculate.performCalculation()));
				tts.setVoice("dfki-poppy-hsmm");
				tts.speak(textFieldAnswer.getText(), 2.0f, false, true);

				String temp = textFieldExpression.getText() + "=" + textFieldAnswer.getText();
				stack.push(temp);

				ArrayList<String> arr = new ArrayList<String>();
				arr.addAll(stack);
				Collections.reverse(arr);
				textAreaHistory.setText("");
				for (String line : arr) {
					textAreaHistory.append(line + "\n");
				}

				buttonClearHistory.setVisible(true);
				buttonClearHistory.setEnabled(true);

				System.out.println(calculate.linkedList);
				textFieldExpression.setText("");
			}
		});
		buttonEquals.setFont(new Font("Verdana", Font.PLAIN, 25));

		// Multiply Button
		buttonMultiply = new JButton("x");
		buttonMultiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonMultiply.setBackground(SystemColor.controlHighlight);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonMultiply.setBackground(SystemColor.control);
			}
		});
		buttonMultiplyLabel.setLabelFor(buttonMultiply);
		buttonMultiply.setBounds(235, 104, 65, 74);
		panel_4.add(buttonMultiply);
		buttonMultiply.setBackground(SystemColor.control);
		buttonMultiply.setEnabled(false);
		buttonMultiply.setFocusPainted(false);
		buttonMultiply.setBorderPainted(false);
		buttonMultiply.setForeground(Color.BLACK);
		buttonMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClickNoise();
				buttonMultiplyLabel.setVisible(true);
				if (iconTimer.isRunning()) {
					iconTimer.restart();
				} else {
					iconTimer.start();
				}

				// disable operator buttons
				buttonDivide.setEnabled(false);
				buttonMinus.setEnabled(false);
				buttonMultiply.setEnabled(false);
				buttonAdd.setEnabled(false);

				calculate.buildExpression(buttonMultiply.getText());
				buttonMultiply.setEnabled(false);

				textFieldExpression.setText(textFieldExpression.getText() + "x");
				System.out.println(calculate.linkedList);
				decimalHelper();

			}
		});
		buttonMultiply.setFont(new Font("Verdana", Font.PLAIN, 25));

		// mic icons
		// grey mic off label
		micOffLabel = new JLabel(new ImageIcon("images/greyMic.png"));
		micOffLabel.setBounds(440, 1, 34, 43);
		panel.add(micOffLabel);

		// red mic on label
		micOnLabel = new JLabel(new ImageIcon("images/redMic.png"));
		micOnLabel.setBounds(440, 1, 34, 43);
		panel.add(micOnLabel);
		micOnLabel.setVisible(false);

		panel_2 = new JPanel();
		panel_2.setBounds(480, 0, 294, 500);
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 5, 290, 44);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		// history label
		lblHistory = new JLabel("History");
		lblHistory.setBounds(10, 0, 117, 34);
		panel_3.add(lblHistory);
		lblHistory.setFont(new Font("Verdana", Font.PLAIN, 24));

		// clean history button
		buttonClearHistory = new JButton(new ImageIcon("images/binIcon.png"));
		buttonClearHistory.setBounds(242, 0, 40, 40);
		panel_3.add(buttonClearHistory);
		buttonClearHistory.setVisible(false);
		buttonClearHistory.setEnabled(false);
		buttonClearHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonClickNoise();
				stack.clear();
				textAreaHistory.setText("");
				buttonClearHistory.setVisible(false);
				buttonClearHistory.setEnabled(false);
			}
		});

		// text area for history
		textAreaHistory = new JTextArea();
		textAreaHistory.setEditable(false);
		textAreaHistory.setBounds(0, 61, 282, 428);
		textAreaHistory.setFont(new Font("Verdana", Font.PLAIN, 29));

		JScrollPane scrollPane = new JScrollPane(textAreaHistory);
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 54, 280, 435);
		panel_2.add(scrollPane);

		// Timer for labels to appear for 1 second above button when clicked
		iconTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (button1Label.isVisible()) {
					button1Label.setVisible(false);
				}
				if (button2Label.isVisible()) {
					button2Label.setVisible(false);
				}
				if (button3Label.isVisible()) {
					button3Label.setVisible(false);
				}
				if (button4Label.isVisible()) {
					button4Label.setVisible(false);
				}
				if (button5Label.isVisible()) {
					button5Label.setVisible(false);
				}
				if (button6Label.isVisible()) {
					button6Label.setVisible(false);
				}
				if (button7Label.isVisible()) {
					button7Label.setVisible(false);
				}
				if (button8Label.isVisible()) {
					button8Label.setVisible(false);
				}
				if (button9Label.isVisible()) {
					button9Label.setVisible(false);
				}

				if (button0Label.isVisible()) {
					button0Label.setVisible(false);
				}

				if (buttonPlusLabel.isVisible()) {
					buttonPlusLabel.setVisible(false);
				}

				if (buttonMinusLabel.isVisible()) {
					buttonMinusLabel.setVisible(false);
				}

				if (buttonClearLabel.isVisible()) {
					buttonClearLabel.setVisible(false);
				}

				if (buttonMultiplyLabel.isVisible()) {
					buttonMultiplyLabel.setVisible(false);
				}

				if (buttonDivideLabel.isVisible()) {
					buttonDivideLabel.setVisible(false);
				}

				if (buttonBackspaceLabel.isVisible()) {
					buttonBackspaceLabel.setVisible(false);
				}

				if (buttonDotLabel.isVisible()) {
					buttonDotLabel.setVisible(false);
				}

				if (buttonEqualsLabel.isVisible()) {
					buttonEqualsLabel.setVisible(false);
				}

			}
		});

	}

	// counter for decimal point
	public void decimalHelper() {
		decimalCounter = 0;
		calculate.setDecimalPointPressed(false);
		buttonDot.setEnabled(true);
	}

	// button click noise
	public void buttonClickNoise() {
		InputStream in;
		String wav_file = "sounds/click.wav";
		try {
			in = new FileInputStream(wav_file);
			AudioStream audio = new AudioStream(in);
			AudioPlayer.player.start(audio);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

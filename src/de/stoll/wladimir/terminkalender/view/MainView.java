package de.stoll.wladimir.terminkalender.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.stoll.wladimir.terminkalender.controller.MainController;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainView implements IMainView{

	private JFrame frame;
	private MainController mainController;


//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainView window = new MainView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @param mainController 
	 */
	public MainView(MainController mainController) {
		super();
		initialize();
		this.mainController = mainController; 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		frame.getContentPane().setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		frame.setBounds(100, 100, 929, 732);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBoxJahr = new JComboBox();
		comboBoxJahr.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040"}));
		comboBoxJahr.setMaximumRowCount(50);
		comboBoxJahr.setBounds(0, 0, 70, 22);
		frame.getContentPane().add(comboBoxJahr);
		
		JComboBox comboBoxMonat = new JComboBox();
		comboBoxMonat.setBounds(67, 0, 97, 22);
		frame.getContentPane().add(comboBoxMonat);
		comboBoxMonat.setModel(new DefaultComboBoxModel(new String[] {"1. Jan.", "2. Febr.", "3. M\u00E4rz", "4. Apr.", "5. Mai", "6. Juni", "7. Juli", "8. Aug.", "9. Sept.", "10. Okt.", "11. Nov.", "12. Dez."}));
		comboBoxMonat.setMaximumRowCount(50);
		
		JComboBox comboBoxTag = new JComboBox();
		comboBoxTag.setMaximumRowCount(50);
		comboBoxTag.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxTag.setBounds(164, 0, 52, 22);
		frame.getContentPane().add(comboBoxTag);
		
		JComboBox comboBoxWoche = new JComboBox();
		comboBoxWoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int weekOfYear = new Integer(((JComboBox)e.getSource()).getModel().getSelectedItem().toString());
				mainController.setWoche(weekOfYear);
			}
		});
		comboBoxWoche.setMaximumRowCount(25);
		comboBoxWoche.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53"}));
		comboBoxWoche.setBounds(216, 0, 41, 22);
		frame.getContentPane().add(comboBoxWoche);
	}

	@Override
	public void repaint() {
		System.out.println("todo 2");
	}

	public JFrame getFrame() {
		return frame;
	}
}

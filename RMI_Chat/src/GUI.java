import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI extends javax.swing.JFrame {
	
	private Client klient; 
	private ServerInterface serwer; 
	private static String imie;
	private static JTextArea historia;
	private JTextField wiadomosc;
	private JScrollPane jScrollPaneHistory;
	private static int port;

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "\nProgram zaliczeniowy \n[Implementacja komunikata mi�dzy 2-ch i wi�cej uzytkownik�w przez RMI]\nWykonali: [Roman Protas] - [Serhii Kets] - Andrii Sydorenko]");
		imie = JOptionPane.showInputDialog("Wpisz imie: ");
		if (imie.length()<2){
			JOptionPane.showMessageDialog(null, " Imie puste ");	
			}
		if (imie != null && !imie.equals("")) {
			try {
				GUI inst = new GUI();
				inst.setVisible(true);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**Wewnatrz tego konstruktora mozemy wywolac metode odnosnika w klasie Naming ktora
	 *odbiera odwolanie do obiektu zdalnego serwera. To zdalne odwolanie
	 *jest uzywane do wywolywania metody zdalnego logowania, ktora dodaje odniesienie do niniejszego
	 *klienta obiektu do istniejacej listy innych klientow. Obiekt zdalnego serwera
	 *jest takze stosowany do transmisji komunikatow **/
	public GUI() throws MalformedURLException, RemoteException, NotBoundException {
		super();
		serwer = (ServerInterface)Naming.lookup("//localhost:6999/HelloServer");
		klient = new Client();
		serwer.zaloguj(klient, imie);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				JMenuBar menuBar = new JMenuBar();
		        setJMenuBar(menuBar);
		        JMenu menu = new JMenu("Info");
		        menuBar.add(menu);
		        JMenuItem mitem = new JMenuItem("Client");
		        mitem.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent event)
		            {
		                JOptionPane.showMessageDialog(null, " Client ");
		            }
		        });
		        menu.add(mitem);
		        JMenuItem mitem1 = new JMenuItem("Serwer");
		        mitem1.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent event)
		            {
		                JOptionPane.showMessageDialog(null, " Serwer ");
		            }
		        });
		        menu.add(mitem1);
		        JMenuItem mitem2 = new JMenuItem("GUI");
		        mitem2.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent event)
		            {
		                JOptionPane.showMessageDialog(null, " GUI ");
		            }
		        });
		        menu.add(mitem2);
				jScrollPaneHistory = new JScrollPane();
				getContentPane().add(jScrollPaneHistory);
				jScrollPaneHistory.setBounds(7, 7, 778, 390);
				jScrollPaneHistory.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				{
					historia = new JTextArea();
					historia.setLineWrap(true);
					historia.setEditable(false);
					jScrollPaneHistory.setViewportView(historia);
				}
			}
			{
				wiadomosc = new JTextField();
				getContentPane().add(wiadomosc);
				wiadomosc.setBounds(7, 400, 778, 103);
				wiadomosc.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						WiadomoscKeyPressed(evt);
					}
				});
			}
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("RMI Chat Implementation - " + imie);
			this.setResizable(true);
			pack();
			setSize(810, 580);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//*Metoda ta nazywana jest metoda getMessage ktora jest zdefiniowana wewnatrz
 	//*Klasy Client. Ona po prostu pisze wiadomosc i wysyla ja do pola TextArea.
 	//*Jesli nickname jest pusty,to oznacza , ze nowy klient dolaczyl sie do czatu.
	public static void showMessage(String message, String nickname) {
		if (!nickname.equals(""))
			historia.append(" " + nickname + " : " + message + "\n");
		else historia.append(message + "\n");
	}
	
	//*Ta metoda jest wywolywana, gdy uzytkownik nacisnie Enter.
	//* Jesli pole tekstowe  nie jest puste,nastepnie wywolujemy metode zdalnego
	//* rozsylania wiadomosci na obiekcie serwera zdalnego, ktora nadaje wprowadzona
	//* wiadomosc do wszystkich innych klientow.
	private void WiadomoscKeyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER && !wiadomosc.getText().equals("")) {
			try {
				serwer.wyslijWiadomosc(wiadomosc.getText(), imie);
				wiadomosc.setText("");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
*Klasa Server implementuje interfejs ServerInterface i rozszerza klasÄ™ UnicastRemoteObject.
*Jego konstruktor i funkcje login i broadcast mogÄ… byÄ‡ dostÄ™pne zdalnie.
*/
public class Server extends UnicastRemoteObject implements ServerInterface {
	/**
     * ArrayList zapisuje do listy wszystkich zalogowanych klientÃ³w
     * pod ich imionami (imie)
     */
	protected ArrayList<ClientInterface> klienty = new ArrayList<ClientInterface>();
	/**
     * Konstruktor Server uÅ¼ywany dla stworzenia zdalnego obiekta tupy Server.
     */
	public Server() throws RemoteException {
	}

	/**
     * Metoda login implementuje RMI. Je¿eli klient wywo³uje ten metod wtedy wszystcy inny klienty
     * które s¹ zalogowani - otrzymaj¹ zawiadomienie ¿e nowy klient zalogowa³ siê. Nowy klient dodaje siê
     * do ArrayLista.
     */
	public void zaloguj(ClientInterface klient, String imie) throws RemoteException {
		wyslijWiadomosc(" U¿ytkownik [" + imie + "] zalogowa³ siê do czatu !", "");	
		klienty.add(klient);
	}
	
	/**
     * Metoda wyslijWiadomosc odczyta i wywoluje wiadomosc i imie od klienta do wszystkich innych zalogowanych
     * klientów.
     */
	public void wyslijWiadomosc(String wiadomosc, String imie) throws RemoteException {
		for (int i = 0; i < klienty.size(); i++) {
			ClientInterface c = klienty.get(i);
			try {
				c.pobierzWiadomosc(wiadomosc, imie);
			} catch (RemoteException e) {
				wyloguj(c);
				i = i - 1;
			} 
		}
	}
	
	/**
     * Metoda wyloguj jest lokaln¹ metod¹ która usuwaje klienta z ArrayLista.
     */
	public void wyloguj(ClientInterface client) {
		klienty.remove(client);
	}
	
	/**
     * G³ówna metoda main rejestruje zdalny serwer.
     */
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(6999);
			Naming.rebind("//localhost:6999/HelloServer", new Server());
			System.out.println("Serwer pracuje . . . ");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

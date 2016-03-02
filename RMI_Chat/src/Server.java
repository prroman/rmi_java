import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
*Klasa Server implementuje interfejs ServerInterface i rozszerza klasę UnicastRemoteObject.
*Jego konstruktor i funkcje login i broadcast mogą być dostępne zdalnie.
*/
public class Server extends UnicastRemoteObject implements ServerInterface {
	/**
     * ArrayList zapisuje do listy wszystkich zalogowanych klientów
     * pod ich imionami (imie)
     */
	protected ArrayList<ClientInterface> klienty = new ArrayList<ClientInterface>();
	/**
     * Konstruktor Server używany dla stworzenia zdalnego obiekta tupy Server.
     */
	public Server() throws RemoteException {
	}

	/**
     * Metoda login implementuje RMI. Je�eli klient wywo�uje ten metod wtedy wszystcy inny klienty
     * kt�re s� zalogowani - otrzymaj� zawiadomienie �e nowy klient zalogowa� si�. Nowy klient dodaje si�
     * do ArrayLista.
     */
	public void zaloguj(ClientInterface klient, String imie) throws RemoteException {
		wyslijWiadomosc(" U�ytkownik [" + imie + "] zalogowa� si� do czatu !", "");	
		klienty.add(klient);
	}
	
	/**
     * Metoda wyslijWiadomosc odczyta i wywoluje wiadomosc i imie od klienta do wszystkich innych zalogowanych
     * klient�w.
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
     * Metoda wyloguj jest lokaln� metod� kt�ra usuwaje klienta z ArrayLista.
     */
	public void wyloguj(ClientInterface client) {
		klienty.remove(client);
	}
	
	/**
     * G��wna metoda main rejestruje zdalny serwer.
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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	/**
	 * Ta klasa implementuje interfejs ClientInterface i rozszerza klase
	 * UnicastRemoteObject. Jego konstruktor i funkcja pobierzWiadomosc mog¹ byæ dostêpne zdalnie.
	 * @autor Roman Protas
	 */
public class Client extends UnicastRemoteObject implements ClientInterface {
	/**
	 * Konstructor dla zdalnego objektu typu Client. Ten konstruktor
	 * To rzuca RemoteException jeœli obiekt nie mo¿e byæ wykonany.
	 */
	public Client() throws RemoteException {
		
	}
	/**
	 * 	Ta metoda implementuje zdalne wywo³ywana metoda. Mo¿e byæ wywo³ywana  przez serwer
	 *  do wysy³ania wiadomoœci i imia jej nadawcy do graficznego interfejsu u¿ytkownika klienta. 
	 *  Rzuca równie¿ RemoteException, gdy wyst¹pi b³¹d podczas zdalnego wywo³ania metody.
	 */
	public void pobierzWiadomosc(String wiadomosc, String imie) throws RemoteException {
		GUI.showMessage(wiadomosc, imie);
	}
}

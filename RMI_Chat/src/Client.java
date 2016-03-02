import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	/**
	 * Ta klasa implementuje interfejs ClientInterface i rozszerza klase
	 * UnicastRemoteObject. Jego konstruktor i funkcja pobierzWiadomosc mog� by� dost�pne zdalnie.
	 * @autor Roman Protas
	 */
public class Client extends UnicastRemoteObject implements ClientInterface {
	/**
	 * Konstructor dla zdalnego objektu typu Client. Ten konstruktor
	 * To rzuca RemoteException je�li obiekt nie mo�e by� wykonany.
	 */
	public Client() throws RemoteException {
		
	}
	/**
	 * 	Ta metoda implementuje zdalne wywo�ywana metoda. Mo�e by� wywo�ywana  przez serwer
	 *  do wysy�ania wiadomo�ci i imia jej nadawcy do graficznego interfejsu u�ytkownika klienta. 
	 *  Rzuca r�wnie� RemoteException, gdy wyst�pi b��d podczas zdalnego wywo�ania metody.
	 */
	public void pobierzWiadomosc(String wiadomosc, String imie) throws RemoteException {
		GUI.showMessage(wiadomosc, imie);
	}
}


import java.rmi.*;
/**
 * Interfejs ServerInterface rozszerza klas� Remote i
 * okre�la metody dost�pne zdalne: logowanie i wysy�anie wiadomo�ci.
 */
public interface ServerInterface extends Remote {
	/**
     * Metoda zaloguj mo�e by� wywolana zdalne z lokalnej virtualnej maszyny.
     * Ta metoda pobiera imie klienta � � odnosi na zdalny obiekt typ klient jako parametr.
     */
	public void zaloguj(ClientInterface klient, String imie) throws RemoteException;
	/**
     * Metoda wyslijWiadomosc mo�e by� wywolana zdalne z lokalnej virtualnej maszyny.
     * Ona nadaje przychodz�cym wiadomo�� do wszystkich pod��czonych klient�w.
     */
	public void wyslijWiadomosc(String wiadomosc, String nickname) throws RemoteException;
}


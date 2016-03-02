
import java.rmi.*;
/**
 * Interfejs ServerInterface rozszerza klasê Remote i
 * okreœla metody dostêpne zdalne: logowanie i wysy³anie wiadomoœci.
 */
public interface ServerInterface extends Remote {
	/**
     * Metoda zaloguj mo¿e byæ wywolana zdalne z lokalnej virtualnej maszyny.
     * Ta metoda pobiera imie klienta Ñ – odnosi na zdalny obiekt typ klient jako parametr.
     */
	public void zaloguj(ClientInterface klient, String imie) throws RemoteException;
	/**
     * Metoda wyslijWiadomosc mo¿e byæ wywolana zdalne z lokalnej virtualnej maszyny.
     * Ona nadaje przychodz¹cym wiadomoœæ do wszystkich pod³¹czonych klientów.
     */
	public void wyslijWiadomosc(String wiadomosc, String nickname) throws RemoteException;
}


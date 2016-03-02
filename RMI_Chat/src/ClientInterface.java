import java.rmi.Remote;
import java.rmi.RemoteException;
	/**
	 ** Ten interfejs rozszerza klasu Remote i przecyzuje metodu getMessage, ktora mo�e by� dost�pna z zewn�trz
	 * @autor Roman Protas
	 */
public interface ClientInterface extends Remote {
	/**
	 *Ta metoda mo�e by� wywo�ana zdalnie z lokalnej VM.
	 */
	public void pobierzWiadomosc(String wiadomosc, String imie) throws RemoteException;
}

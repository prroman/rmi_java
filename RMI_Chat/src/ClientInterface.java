import java.rmi.Remote;
import java.rmi.RemoteException;
	/**
	 ** Ten interfejs rozszerza klasu Remote i przecyzuje metodu getMessage, ktora mo¿e byæ dostêpna z zewn¹trz
	 * @autor Roman Protas
	 */
public interface ClientInterface extends Remote {
	/**
	 *Ta metoda mo¿e byæ wywo³ana zdalnie z lokalnej VM.
	 */
	public void pobierzWiadomosc(String wiadomosc, String imie) throws RemoteException;
}

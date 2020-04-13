import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMI_Interface extends Remote
{
	void HotelClient(int type) throws RemoteException;
	void HotelClientList(int type) throws RemoteException;
	void HotelClientBook(String name, int type) throws RemoteException;
	void HotelClientGuests(String name) throws RemoteException;
	
}

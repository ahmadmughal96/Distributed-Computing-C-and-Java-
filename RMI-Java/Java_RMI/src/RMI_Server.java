import java.rmi.registry.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class RMI_Server extends UnicastRemoteObject implements RMI_Interface
{
	int countType0 = 10, countType1 = 20, countType2 = 5, countType3 = 3, countType4 = 2;
	int countGuests = 0;
	String guests[] = new String[40];
	
	public RMI_Server() throws RemoteException
	{
		super();
	}
	
	public void HotelClient(int type) throws RemoteException
	{
		switch(type)
		{
		case 0:  
				countType0--;
				System.out.println(countType0 + " Rooms are available of type 0");
				break;
		case 1:
				countType1--;
				System.out.println(countType1 + " Rooms are avaliable of type 1");
				break;
		case 2:
				countType2--;
				System.out.println(countType2 + " Rooms are available of type 2");
				break;
		case 3:
				countType3--;
				System.out.println(countType3 + " Rooms are avaliable of type 3");
				break;
		case 4:
				countType4--;
				System.out.println(countType4 + " Rooms are available of type 4");
				break;
		default:
				break;
				
		}
	}
	public void HotelClientList(int type) throws RemoteException
	{
		System.out.println("");
		System.out.println("Following Rooms are available for booking");
		System.out.println(countType0 + " of Type 0 are available for 55 Euros per night ");
		System.out.println(countType1 + " of Type 1 are available for 75 Euros per night ");
		System.out.println(countType2 + " of Type 2 are available for 80 Euros per night ");
		System.out.println(countType3 + " of Type 3 are available for 150 Euros per night ");
		System.out.println(countType4 + " of Type 4 are available for 230 Euros per night ");
		
	}
	public void HotelClientBook(String name, int type) throws RemoteException
	{
		System.out.println("");
		System.out.println(name + " is residing in room of type " + type);
	}
	public void HotelClientGuests(String name) throws RemoteException
	{
		System.out.println("Current Guests are");
		guests[countGuests] = name;
		for (int i = 0; i <= countGuests; i++)
			System.out.println( guests[i]);
		countGuests++;
	}
	public static void main(String args[])
	{
		try
		{
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("RMI_Interface", new RMI_Server());
			System.out.println("Alloting Rooms");
		}
		catch(Exception e)
		{
			System.err.println("This is Exception of type " + e.toString());
			e.printStackTrace();
		}
	}
	
}

import java.rmi.registry.*;
import java.util.*;

public class RMI_Client 
{
	static Scanner scan = new Scanner(System.in);
	
	public RMI_Client() {}
	
	public static void main(String args[])
	{
		String name;
		int type;
		RMI_Client client = new RMI_Client();
		type = client.RoomReservation();
		System.out.println("Enter Your Name");
		name = scan.next();
		client.connectServer(name, type);
	}
	
	private int RoomReservation()
	{
		int type;
		System.out.println("There are Total 5 Room Types are available at different prices");
		System.out.println("We have 10 rooms of type 0 which are single rooms that costs 55 Euros a night");
		System.out.println("We have 20 rooms of type 1 which are single rooms that costs 75 Euros a night");
		System.out.println("We have 5 rooms of type 2 which are single rooms that costs 80 Euros a night");
		System.out.println("We have 3 rooms of type 3 which are single rooms that costs 150 Euros a night");
		System.out.println("We have 2 rooms of type 4 which are single rooms that costs 230 Euros a night");
		System.out.println("Enter The Option");
		type = scan.nextInt();
		return type;
	}
	
	private void connectServer(String name, int type)
	{
		try
		{
			Registry register = LocateRegistry.getRegistry("127.0.0.1", 1099);
			
			RMI_Interface stub = (RMI_Interface)register.lookup("RMI_Interface");
			System.out.println("----We are Checking----");
			stub.HotelClient(type);
			stub.HotelClientBook(name, type);
			stub.HotelClientList(type);
			stub.HotelClientGuests(name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}

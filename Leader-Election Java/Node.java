public class Node
{
	private int value;
	private int next;
	public Node(int v, int n)
	{
		this.value = v;
		this.next = n;
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int v)
	{
		this.value = v;
	}
	public int getNext()
	{
		return next;
	}
}
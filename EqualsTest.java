import java.util.HashSet;
import java.util.Set;

// Implement a java class, LazyFinal<T>, for occasions where the value may not be used and calculating it is expensive. Implement methods, isSet(), get(), hashCode(), and equals() and toString(). Add a main() to exercise it.
public class EqualsTest 
{
//add a main() for running code. 
	public static void main(String[] args)
	{
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		
		e1.setId(101);
		e2.setId(101);
		
		//Prints 'true'
		System.out.println(e1.equals(e2));
		
		Set<Employee> employees = new HashSet<Employee>();
		employees.add(e1);
		employees.add(e2);
		
		System.out.println(employees);
	}
}

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// Implement a java class, LazyFinal<T>, for occasions where the value may not be used and calculating it is expensive. Implement methods, isSet(), get(), hashCode(), and equals() and toString(). Add a main() to exercise it.
public class Employee 
{
	private Integer id;
	private String firstname;
	private String lastName;
	private String department;
	//implement get() method
	public Integer getId() {
		return id;
	}
	//implement
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	//implement hashCode() method
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	//implement equals() method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
		{
			return false;
		}
		if (o == this)
		{
           return true;
        }
		if (getClass() != o.getClass())
		{
	        return false;
		}
		Employee e = (Employee) o;
		return (this.getId() == e.getId());
	}
	
	@Override
	public int hashCode() 
	{
		final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId();
	    return result;
	}
	
	
	@Override
	public int hashCode() 
	{
		final int PRIME = 31;
        return new HashCodeBuilder(getId()%2==0?getId()+1:getId(), PRIME).
            toHashCode();
    }

	@Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != getClass())
            return false;
        Employee e = (Employee) o;
        return new EqualsBuilder().
            append(getId(), e.getId()).
            isEquals();
    }
	
	
}

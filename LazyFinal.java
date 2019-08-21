import java.util.Objects;
import java.util.function.Supplier;

/**
 * Class for final fields that should be lazily populated.
 *
 * @param <T> The type of the field.
 */
public final class LazyFinal<T> {

    private T value;
    private Supplier<T> populator;

    @Override
    public String toString() {
    return this.get().toString();
    }
    
    @Override
    public int hashCode() {
    return this.get().hashCode();
    }

    @Override
    public boolean equals() {
    return this.get().equals();
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
	
	

    /**
     * Constructs a lazy final using the given populator function.
     * <p>
     * Notice that this class makes absolutely no guarantee as to when, or even
     * if the populator is called to populate the field. However, this class
     * does guarantee that the populator is called no more than exactly once,
     * and then is nulled out immediately afterwards.
     *
     * @param populator The <em>non-null</em> populator to be used to populate the field, when it
     * is determined that the field should be populated.
     */
    public LazyFinal(Supplier<T> populator) {
        this.populator = Objects.requireNonNull(populator);
    }

    /**
     * Determines if the field has been populated yet.
     * <p>
     * Notice, this method does is not synchronized, and does not block, to the
     * point that this method may return false even when another thread is in
     * the process of populating the field. As such, this is more of an
     * informative convince method than anything else, and should not be used in
     * mission-critical logic.
     *
     * @return {@literal true} if (in the context of this thread) the internal
     * value field has yet to be set, {@code false} otherwise.
     */
    public boolean isSet() {
        return (this.populator == null);
    }

    /**
     * Forces the field to be populated, if it has not been already.
     * <p>
     * This method <em>does not</em> guarantee that the populator will be
     * invoked, but does guarantee that the value will be set following its
     * invocation.
     * <p>
     * Notice, this method is synchronized, and therefore blocking. Therefore,
     * one should avoid calling this method erratically, as it may cause the
     * thread to wait. Arguably, this method should never be called externally,
     * as a lazy field should only be populated at the last minute possible.
     *
     * @see #get()
     */
    public synchronized void set() {
        if (this.populator != null) {
            this.value = populator.get();
            this.populator = null;
        }
    }

    /**
     * Fetches the value of the field, populating the field if it has not yet
     * been populated.
     * <p>
     * Notice, that this method uses {@link set()} internally to set the field,
     * if when tested the field does not appear to have been set. In this
     * manner, this method may block for the brief duration of the set
     * operation. However, once the field has been set - and the change
     * propagated across all threads - then (and only then) the method is
     * guaranteed to no longer block.
     *
     * @see #set()
     *
     * @return
     */
    public T get() {
        if (this.populator != null) {
            this.set();
        }
        return this.value;
    }

}
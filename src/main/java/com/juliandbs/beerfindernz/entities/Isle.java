package com.juliandbs.beerfindernz.entities;

import java.lang.Comparable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.lang.NullPointerException;

/**
*       This class implements the 'Comparable' interface and represents a record in the 'isle' table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
@Entity
@Table(name = "isle")
public class Isle implements Comparable<Isle> {

        /**
        *       This class property represents the SERIAL PRIMARY KEY column, called 'id', of the 'isle' table in the application PostgreSQL database.
        */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;

        /**
        *       This class property represents the UNIQUE NOT NULL VARCHAR(20) column, called 'name', of the 'isle' table in the application PostgreSQL database.
        */
	@Column(name = "name", nullable = false, length = 20)
	private final String name;

	/**
	*	Empty class Constructor of the Isle Class.
	*/
	public Isle() {
		id = Long.valueOf(0);
		name = "empty";
	}

	/**
	*	Class Constructor of the Isle class.
	*
	*	@param id long value that represents the 'id' column value in the 'isle' table.
	*	@param name String value that represents the 'name' column value in the 'isle' table.
	*	@throws NullPointerException if any of the parameters of this constructor are null.
	*/
	public Isle(long id, String name) throws NullPointerException {
		if (java.util.Objects.isNull(id) || name == null)
			throw new NullPointerException();
		this.id = Long.valueOf(id);
		this.name = name;
	}

	/**
	*	This method returns a Long object that represents the 'id' class property.
	*
	*	@return a Long object representing the 'id' class property value.
	*/
	public Long getId() {return id;}

	/**
	*	This method returns a String object that represents the 'name' class property.
	*
	*	@return a String object representing the 'name' class property value.
	*/
	public String getName() {return name;}

	/**
	*	This method compares two Isle object.
	*	Returns a negative integer, zero, or positive integer as this object is less than, equal to, or greater than the specified object.
	*
	*	@param isle the Isle object to be compared.
	*	@return a negative integer, zero, or a positive integer as this object is less tha, equal to, or greater than the specified object.
	*	@thorws NullPointerException if the 'isle' parameter is null.
	*/
	@Override
	public int compareTo(Isle isle) throws NullPointerException {
		if (isle == null)
			throw new NullPointerException();
		return isle.getId().compareTo(id) + isle.getName().compareTo(name);
	}

	/**
	*	This method compares this Isle object to the specified object.
	*
	*	@param object the object to compare.
	*	@return true if and only if the 'object' property  is not null and is an Isle object that represents an Isle object with the same parameters, otherwise false.
	*/
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object instanceof Isle) {
			Isle isle = (Isle) object;
			result = isle.getId().equals(id) && isle.getName().equals(name);
		}
		return result;
	}

	/**
	*	This method returns a hash code for this object.
	*
	*	@return a hash code for this object.
	*/
	@Override
	public int hashCode() {
		return 31 * Long.valueOf(id).hashCode() + name.hashCode();
	}

	/**
	*	This method returns a String that represents the properties values of this object.
	*
	*	@return a String representation of the properties of this object.
	*/
	@Override
	public String toString() {
		return id + " " + name;
	}
}

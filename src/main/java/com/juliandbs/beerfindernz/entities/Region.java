package com.juliandbs.beerfindernz.entities;

import java.lang.Comparable;
import java.lang.NullPointerException;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
*       This class implements the 'Comparable' interface and represents a record in the 'region' table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
@Entity
@Table(name = "region")
public class Region implements Comparable<Region> {

        /**
        *       This class property represents the SERIAL PRIMARY KEY column, called 'id', of the 'region' table in the application PostgreSQL database.
        */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

       	/**
        *       This class property represents the UNIQUE NOT NULL VARCHAR(60) column, called 'name', of the 'region' table in the application PostgreSQL database.
        */
	@Column(name = "name", nullable=false, length=60)
	private String name;

        /**
        *       This class property represents the NOT NULL VARCHAR(20) column, called 'isle', of the 'region' table in the application PostgreSQL database.
        *       The 'isle' column have a one-way relationship with the 'name' column of the 'isle' table in the application PostgreSQL database.
        */
	@Column(name = "isle", nullable=false, length=20)
	private String isle;

	/**
	*	Empty class Constructor of the Region class.
	*/
	public Region() {}

	/**
	*	Class Constructor of the Region class.
	*
	*	@param id a long value that represents the 'id' column value in the 'region' table.
	*	@param name a String object that represents the 'name' column value in the 'region' table.
	*	@param isle a String object that represents the 'isle' column value in the 'region' table.
	*	@throws NullPointerException if any of the paramteres of this constructor are null.
	*/
	public Region(long id, String name, String isle) throws NullPointerException {
		if (java.util.Objects.isNull(id) || name == null || isle == null)
			throw new NullPointerException();
		this.id = id;
		this.name = name;
		this.isle = isle;
	}

	/**
	*	This method returns a Long object that represents the 'id' class property.
	*
	*	@return a Long object representing the 'id' class property value.
	*/
	public Long getId() {return Long.valueOf(id);}

	/**
	*	This method returns a String object that represents the 'name' class property.
	*
	*	@return a String object representing the 'name' class property value.
	*/
	public String getName() {return name;}

	/**
	*	This method returns a String object that represents the 'isle' class property.
	*
	*	@return a String object representing the 'isle' class property value.
	*/
	public String getIsle() {return isle;}

	/**
        *       This method compares two Region object.
        *       Returns a negative integer, zero, or positive integer as this object is less than, equal to, or greater than the specified object.
	*
        *       @param region the Region object to be compared.
        *       @return a negative integer, zero, or a positive integer as this object is less tha, equal to, or greater than the specified object.
        *       @thorws NullPointerException if the 'region' parameter is null.
        */
        @Override
        public int compareTo(Region region) throws NullPointerException {
                if (region == null)
                        throw new NullPointerException();
                return region.getId().compareTo(id) + region.getName().compareTo(name) + region.getIsle().compareTo(isle);
        }

        /**
        *       This method compares this Region object to the specified object.
	*
        *       @param object the object to compare.
        *       @return true if and only if the 'object' property  is not null and is a Region object that represents a Region object with the same parameters, otherwise false.
        */
        @Override
        public boolean equals(Object object) {
                boolean result = false;
                if (object instanceof Region) {
                        Region region = (Region) object;
                        result = region.getId().equals(id) && region.getName().equals(name) && region.getIsle().equals(isle);
                }
                return result;
        }


        /**
        *       This method returns a hash code for this object.
	*
        *       @return a hash code for this object.
        */
        @Override
        public int hashCode() {
                return 31 * Long.valueOf(id).hashCode() + name.hashCode() + isle.hashCode();
        }

        /**
        *       This method returns a String that represents the properties values of this object.
	*
        *       @return a String representation of the properties of this object.
        */
        @Override
        public String toString() {
                return id + " " + name + " " + isle;
        }
}

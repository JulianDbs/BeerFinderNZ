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
*	This class implements the 'Comparable' interface and represents a record in the 'city' table of the application PostgreSQL database.
*
*	@author JulianDbs
*/
@Entity
@Table(name = "city")
public class City implements Comparable<City> {

	/**
	*	This class property represents the SERIAL PRIMARY KEY column, called 'id', of the 'city' table in the application PostgreSQL database.
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	*	This class property represents the UNIQUE NOT NULL VARCHAR(60) column, called 'name', of the 'city' table in the application PostgreSQL database.
	*/
	@Column(name = "name", nullable = false, length = 60)
	private String name;

	/**
	*	This class property represents the NOT NULL VARCHAR(20) column, called 'isle', of the 'city' table in the application PostgreSQL database.
	*	The 'isle' column have a one-way relationship with the 'name' column of the 'isle' table in the application PostgreSQL database.
	*/
	@Column(name = "isle", nullable = false, length = 20)
	private String isle;

	/**
	*	This class property represents the NOT NULL VARCHAR(60) column, called 'region', of the 'city' table in the application PostgreSQL database.
	*	The 'region' column have a one-way relationship with the 'name' column of the 'region' table in the application PostgreSQL database.
	*/
	@Column(name = "region", nullable = false, length = 60)
	private String region;

	/**
	*	Empty class Construnctor of the City class.
	*/
	public City(){}

	/**
	*	Class Constructor of the City class.
	*
	*	@param id a long value that represents the 'id' column value in the 'city' table.
	*	@param name a String object that represents the 'name' column value in the 'city' table.
	*	@param isle a String object that represents the 'isle' column value in the 'city' table.
	*	@param region a String object that represents the 'region' column value in the 'city' table.
	*	@throws NullPointerException if any of the parameters of this constructor are null.
	*/
	public City(long id, String name, String isle, String region) throws NullPointerException {
		if (java.util.Objects.isNull(id) || name == null || isle == null || region == null)
			throw new NullPointerException();
		this.id = id;
		this.name = name;
		this.isle = isle;
		this.region = region;
		System.out.println(this.toString());
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
	*	This merhod returns a String object that represents the 'isle' class property.
	*
	*	@return a String object representing the 'isle' class property value.
	*/
	public String getIsle() {return isle;}

	/**
	*	This method returns a String that represents the 'region' class property.
	*
	*	@return a String object representing the 'region' class property value.
	*/
	public String getRegion() {return region;}

	/**
        *       This method compares two City object.
        *       Returns a negative integer, zero, or positive integer as this object is less than, equal to, or greater than the specified object.
	*
        *       @param city the City object to be compared.
        *       @return a negative integer, zero, or a positive integer as this object is less tha, equal to, or greater than the specified object.
        *       @thorws NullPointerException if the 'city' parameter is null.
        */
        @Override
        public int compareTo(City city) throws NullPointerException {
                if (city == null)
                        throw new NullPointerException();
                return city.getId().compareTo(id) + city.getName().compareTo(name) + city.getIsle().compareTo(isle) + city.getRegion().compareTo(region);
        }

        /**
        *       This method compares this City object to the specified object.
	*
        *       @param object the object to compare.
        *       @return true if and only if the 'object' property  is not null and is a City object that represents a City object with the same parameters, otherwise false.
        */
        @Override
        public boolean equals(Object object) {
                boolean result = false;
                if (object instanceof City) {
                        City city = (City) object;
                        result = city.getId().equals(id) && city.getName().equals(name) && city.getIsle().equals(isle) && city.getRegion().equals(region);
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
                return 31 * Long.valueOf(id).hashCode() + name.hashCode() + isle.hashCode() + region.hashCode();
        }

        /**
        *       This method returns a String that represents the properties values of this object.
	*
        *       @return a String representation of the properties of this object.
        */
        @Override
        public String toString() {
                return id + " " + name + " " + isle + " " + region;
        }
}

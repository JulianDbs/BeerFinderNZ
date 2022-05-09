package com.juliandbs.beerfindernz.entities;

import com.juliandbs.beerfindernz.entities.MarketInfo;

import java.lang.Comparable;
import java.lang.NullPointerException;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

/**
*       This class implements the 'Comparable' interface and represents a record in the 'market' table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
@Entity
@Table(name = "market")
public class Market implements Comparable<Market> {

        /**
        *       This class property represents the SERIAL PRIMARY KEY column, called 'id', of the 'market' table in the application PostgreSQL database.
        */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

        /**
        *       This class property represents the UNIQUE NOT NULL VARCHAR(60) column, called 'name', of the 'market' table in the application PostgreSQL database.
        */
	@Column(name = "name", nullable = false, length = 60)
	private String name;

        /**
        *       This class property represents the NOT NULL BOOLEAN column, called 'generic_url', of the 'market' table in the application PostgreSQL database.
	*/
	@Column(name = "generic_url", nullable = false)
	private boolean genericUrl;

        /**
        *       This class property represents the VARCHAR(60) column, called 'max_page_add', of the 'market' table in the application PostgreSQL database.
	*/
	@Column(name = "max_page_add", nullable = true, length = 60)
	private String maxPageAdd;

	/**
	*	This class property represents a record in the 'market_info' table related, by a one-way relationship, through the 'name' column of the 'market_info'
	*	table in the application PostgreSQL database.
	*
	*/
	@OneToOne(targetEntity = MarketInfo.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "name", referencedColumnName = "market", insertable = false, updatable = false)
	private MarketInfo marketInfo;

	/**
	*	Empty class Constructor of the Market class.
	*/
	public Market(){}

	/**
	*	Class Constructor of the Market class.
	*
	*	@param id a long value that represents the 'id' column value in the 'market' table.
	*	@param name a String object that represents the 'name' column value in the 'market' table.
	*	@param genericUrl a boolean value that represents the 'generic_url' column value in the 'market' table.
	*	@param maxPageAdd a String object that represents the 'max_page_add' column value in the 'market' table.
	*	@throws NullPointException in any of the constructor properties are null.
	*/
	public Market(long id, String name, boolean genericUrl, String maxPageAdd) throws NullPointerException {
		if (java.util.Objects.isNull(id) || name == null || java.util.Objects.isNull(genericUrl))
			throw new NullPointerException();
		this.id = id;
		this.name = name;
		this.genericUrl = genericUrl;
		this.maxPageAdd = (maxPageAdd == null)? "none" : maxPageAdd;
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
	*	This method returns a String object that represents the 'genericUrl' class property.
	*
	*	@return a Boolean object representing the 'genericUrl' class property value.
	*/
	public Boolean isGenericUrl() {return genericUrl;}

	/**
	*	This method returns a String object that represents the 'maxPageAdd' class property.
	*
	*	@return a String object representing the 'maxPageAdd' class property value.
	*/
	public String getMaxPageAdd() {return maxPageAdd;}

	/**
	*	This method returns a 'MarketInfo' object that represents a record of the 'market_info' table related with the
	*	record of the table 'market' represented by this class.
	*
	*	@return a 'MarketInfo' class instance.
	*/
	public MarketInfo getMarketInfo() {return marketInfo;}

     	/**
        *       This method compares two Market object.
        *       Returns a negative integer, zero, or positive integer as this object is less than, equal to, or greater than the specified object.
	*
        *       @param market the Market object to be compared.
        *       @return a negative integer, zero, or a positive integer as this object is less tha, equal to, or greater than the specified object.
        *       @thorws NullPointerException if the 'market' parameter is null.
        */
        @Override
        public int compareTo(Market market) throws NullPointerException {
                if (market == null)
                        throw new NullPointerException();
                return market.getId().compareTo(id) +
			market.getName().compareTo(name) +
			market.isGenericUrl().compareTo(genericUrl) +
			market.getMaxPageAdd().compareTo(maxPageAdd);
        }

        /**
        *       This method compares this Market object to the specified object.
	*
        *       @param object the object to compare.
        *       @return true if and only if the 'object' property  is not null and is a Market object that represents a Market object with the same parameters, otherwise false.
        */
        @Override
        public boolean equals(Object object) {
                boolean result = false;
                if (object instanceof Market) {
                        Market market = (Market) object;
                        result = market.getId().equals(id) &&
				market.getName().equals(name) &&
				market.isGenericUrl().equals(genericUrl) &&
				market.getMaxPageAdd().equals(maxPageAdd);
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
                return 31 * Long.valueOf(id).hashCode() + name.hashCode() + Boolean.valueOf(genericUrl).hashCode() + maxPageAdd.hashCode();
        }

        /**
        *       This method returns a String that represents the properties values of this object.
	*
        *       @return a String representation of the properties of this object.
        */
        @Override
        public String toString() {
                return id + " " + name + " " + genericUrl + " " + maxPageAdd;
        }
}

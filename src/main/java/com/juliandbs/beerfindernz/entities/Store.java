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
*       This class implements the 'Comparable' interface and represents a record in the 'store' table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
@Entity
@Table(name = "store")
public class Store implements Comparable<Store> {
        /**
        *       This class property represents the SERIAL PRIMARY KEY column, called 'id', of the 'store' table in the application PostgreSQL database.
        */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

        /**
        *       This class property represents the NOT NULL VARCHAR(60) column, called 'market', of the 'store' table in the application PostgreSQL database.
        *       The 'market' column have a one-way relationship with the 'name' column of the 'market' table in the application PostgreSQL database.
        */
	@Column(name = "market", nullable = false, length = 60)
	private String market;

        /**
        *       This class property represents the UNIQUE NOT NULL VARCHAR(60) column, called 'name', of the 'store' table in the application PostgreSQL database.
        */
	@Column(name = "name", nullable = false, length = 60)
	private String name;

        /**
        *       This class property represents the NOT NULL VARCHAR(20) column, called 'isle', of the 'store' table in the application PostgreSQL database.
        *       The 'isle' column have a one-way relationship with the 'name' column of the 'isle' table in the application PostgreSQL database.
        */
	@Column(name = "isle", nullable = false, length = 20)
	private String isle;

        /**
        *       This class property represents the NOT NULL VARCHAR(60) column, called 'region', of the 'store' table in the application PostgreSQL database.
        *       The 'region' column have a one-way relationship with the 'name' column of the 'region' table in the application PostgreSQL database.
        */
	@Column(name = "region", nullable = false, length = 60)
	private String region;

        /**
        *       This class property represents the NOT NULL VARCHAR(60) column, called 'city', of the 'store' table in the application PostgreSQL database.
        *       The 'city' column have a one-way relationship with the 'name' column of the 'city' table in the application PostgreSQL database.
        */
	@Column(name = "city", nullable = false, length = 60)
	private String city;

        /**
        *       This class property represents the NOT NULL VARCHAR(100) column, called 'store_url', of the 'store' table in the application PostgreSQL database.
        */
	@Column(name = "store_url", nullable = false, length = 100)
	private String storeUrl;

        /**
        *       This class property represents the NOT NULL VARCHAR(100) column, called 'store_search_url', of the 'store' table in the application PostgreSQL database.
        */
	@Column(name = "store_search_url", nullable = false, length = 100)
	private String storeSearchUrl;

	/**
	*	Empty class Constructor of the 'Store' class.
	*/
	public Store() {}

	/**
	*	Class Constructor of the 'Store' class.
	*
	*	@param id a long value that represents the 'id' column value in the 'store' table.
	*	@param market a String object that represents the 'market' column value in the 'store' table.
	*	@param name a String object that represents the 'name' column value in the 'store' table.
	*	@param isle a String object that represents the 'isle' column value in the 'store' table.
	*	@param region a String object that represents the 'region' column value in the 'store' table.
	*	@param city a String object that represents the 'city' column value in the 'store' table.
	*	@param storeUrl a String object that represents the 'store_url' column value in the 'store' table.
	*	@param storeSearchUrl a String object that represents the 'store_search_url' column value in the 'store' table.
	*	@thorws NullPointerException if any of the Constructor parameters are null.
	*/
	public Store(
			long id,
			String market,
			String name,
			String isle,
			String region,
			String city,
			String storeUrl,
			String storeSearchUrl
	) throws NullPointerException {
		if (
			java.util.Objects.isNull(id) ||
			market == null ||
			name == null ||
			isle == null ||
			region == null ||
			city == null ||
			storeUrl == null ||
			storeSearchUrl == null
		) throw new NullPointerException();
		this.id = id;
		this.market = market;
		this.name = name;
		this.isle = isle;
		this.region = region;
		this.city = city;
		this.storeUrl = storeUrl;
		this.storeSearchUrl = storeSearchUrl;
	}

	/**
	*	This method returns a Long object that represents the 'id' class property.
	*
	*	@return a String object representing the 'id' class property value.
	*/
	public Long getId() {return Long.valueOf(id);}

	/**
	*	This method returns a String object that represents the 'market' class property.
	*
	*	@return a String object representing the 'market' class property value.
	*/
	public String getMarket() {return market;}

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
	*	This method returns a String object that represents the 'region' class property.
	*
	*	@returns a String object representing the 'region' class property value.
	*/
	public String getRegion() {return region;}

	/**
	*	This method returns a String object that represents the 'city' class property.
	*
	*	@returns a String object representing the 'city' class property value.
	*/
	public String getCity() {return city;}

	/**
	*	This method returns a String object that represents the 'storeUrl' class property.
	*
	*	@return a String object representing the 'storeUrl' class property value.
	*/
	public String getStoreUrl() {return storeUrl;}

	/**
	*	This method returns a String object that represents the 'storeSearchUrl' class property.
	*
	*	@return a String object representing the 'storeSearchUrl' calss property value.
	*/
	public String getStoreSearchUrl() {return storeSearchUrl;}

	/**
        *       This method compares two Store object.
	*
        *       Returns a negative integer, zero, or positive integer as this object is less than, equal to, or greater than the specified object.
        *       @param store the Store object to be compared.
        *       @return a negative integer, zero, or a positive integer as this object is less tha, equal to, or greater than the specified object.
        *       @thorws NullPointerException if the 'store' parameter is null.
        */
        @Override
        public int compareTo(Store store) throws NullPointerException {
                if (store == null)
                        throw new NullPointerException();
                return store.getId().compareTo(id) +
			store.getMarket().compareTo(market) +
			store.getName().compareTo(name) +
			store.getIsle().compareTo(isle) +
			store.getRegion().compareTo(region) +
			store.getCity().compareTo(city) +
			store.getStoreUrl().compareTo(storeUrl) +
			store.getStoreSearchUrl().compareTo(storeSearchUrl);
        }

        /**
        *       This method compares this Store object to the specified object.
	*
        *       @param object the object to compare.
        *       @return true if and only if the 'object' property  is not null and is a Store object that represents a Store object with the same parameters, otherwise false.
        */
        @Override
        public boolean equals(Object object) {
                boolean result = false;
                if (object instanceof Store) {
                        Store store = (Store) object;
                        result = store.getId().equals(id) &&
				store.getMarket().equals(market) &&
				store.getName().equals(name) &&
				store.getIsle().equals(isle) &&
				store.getRegion().equals(region) &&
				store.getCity().equals(city) &&
				store.getStoreUrl().equals(storeUrl) &&
				store.getStoreSearchUrl().equals(storeSearchUrl);
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
                return 31 * Long.valueOf(id).hashCode() +
				market.hashCode() +
				name.hashCode() +
				isle.hashCode() +
				region.hashCode() +
				city.hashCode() +
				storeUrl.hashCode() +
				storeSearchUrl.hashCode();
        }

        /**
        *       This method returns a String that represents the properties values of this object.
	*
        *       @return a String representation of the properties of this object.
        */
        @Override
        public String toString() {
                return id + " " +
			market + " " +
			name + " " +
			isle + " " +
			region + " "+
			city + " " +
			storeUrl + " " +
			storeSearchUrl;
        }
}

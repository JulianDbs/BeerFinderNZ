package com.juliandbs.beerfindernz.tools;

import java.io.Serializable;
import java.lang.Comparable;
import java.lang.NullPointerException;

/**
*	This class inmplements the 'Comparable'and 'Serializable' interfaces and represents a valid product data extracted
*	from a HTML body.
*
*	@author JulianDbs
*/
public class Product implements Comparable<Product>, Serializable{

	/**
	*	This variable represents the product name.
	*/
	private final String name;

	/**
	*	This variable represents the link to the original product.
	*/
	private final String link;

	/**
	*	This variable represents the link to the image of the original product.
	*/
	private final String imageUrl;

	/**
	*	This variable represents the name of the store in which the product was found.
	*/
	private final String store;

	/**
	*	This variable represents the price of the product.
	*/
	private final Float price;

	/**
	*	This variable represents the city/town in which the product was found.
	*/
	private final String location;

	/**
	*	Empty Class Constructor of the Product class.
	*
	*/
	public Product(){
		name = "none";
		link = "none";
		imageUrl = "none";
		store = "none";
		price = Float.valueOf(-1f);
		location = "none";
	}

	/**
	*	Class Constructor of the Product class.
	*
	*	@param name a String object that represents the product name.
	*	@param link a String object that represents the link to the original product.
	*	@param imageUrl a String that represents the link to the image of the original product.
	*	@param store a String that represents the name of the store in which the product was found.
	*	@param price a String that represents the price of the product.
	*	@param location a String that represents the city/town in wich the product was found.
	*	@thows NullPointerException if any of the Constructor parameters are null.
	*/
	public Product(
			String name,
			String link,
			String imageUrl,
			String store,
			Float price,
			String location
	) throws NullPointerException {
		if (name == null || link == null || imageUrl == null || store == null || price == null || location == null)
			throw new NullPointerException();
		this.name = name;
		this.link = link;
		this.imageUrl = imageUrl;
		this.store = store;
		this.price = price;
		this.location = location;
	}

	/**
	*	This method returns the product name.
	*	@return a String instance that represents the product name.
	*/
	public String getName() {return name;}

	/**
	*	This method returns the link to the original product.
	*	@return a String instance that represents the link to the original product.
	*/
	public String getLink() {return link;}

	/**
	*	This method returns the link to the image of the original product.
	*	@return a String instance that represents the link to the image of the original product.
	*/
	public String getImageUrl() {return imageUrl;}

	/**
	*	This method returns the name of the store in which the product was found.
	*	@return a String instance that represents the name of the store in wich the product was found.
	*/
	public String getStore() {return store;}

	/**
	*	This method returns the price of the product.
	*	@return a String instance that represents the price of the product.
	*/
	public Float getPrice() {return price;}

	/**
	*	This method returns the name of the city/town in which the product was found.
	*	@return a String instance that represents then name of the city/town in which the product was found.
	*/
	public String getLocation() {return location;}

	/**
	*	This method checks if the properties of this class instance contains the "none" and "-1" values.
	*	@return True if the properties of this class instance contains the "none" and "-1" values.
	*/
	public boolean isEmpty() {
		return name.equals("none") &&
			link.equals("none") &&
			imageUrl.equals("none") &&
			store.equals("none") &&
			price.equals(Float.valueOf(-1f)) &&
			location.equals("none");
	}

	/**
        *       This method compares two Product objects.
        *       @param product - the Product object to be compared.
        *       @return the value 0 if the properties of the 'product' parameter are equals to this Product properties;
        *               a value less than 0 if this Product properties are lexicographically less than the properties of the 'product' parameter;
        *               and a greater value than 0 if this Product properties are lexicographically greater than the properties of the 'product' parameter.
        *       @throws NullPointerException if the 'product' parameter is null.
        */
	@Override
	public int compareTo(Product product) throws NullPointerException {
		if (product == null)
			throw new NullPointerException();
		return product.getName().compareTo(name) +
			product.getLink().compareTo(link) +
			product.getImageUrl().compareTo(imageUrl) +
			product.getStore().compareTo(store) +
			product.getPrice().compareTo(price) +
			product.getLocation().compareTo(location);
	}

	/**
        *       This method compares this Product to the specified object.
        *       @param object - the object to compare.
        *       @return true if and only if the 'object' property is not null and is a Product object that represents a Product object with the same parameters, otherwise false.
        */
	@Override
	public boolean equals(Object object) throws NullPointerException {
		if (object == null)
			throw new NullPointerException();
		boolean result = false;
		if (object instanceof Product) {
			Product product = (Product) object;
			result = product.getName().equals(name) &&
					product.getLink().equals(link) &&
					product.getImageUrl().equals(imageUrl) &&
					product.getStore().equals(store) &&
					product.getPrice().equals(price) &&
					product.getLocation().equals(location);
		}
		return result;
	}

	/**
        *       This method returns a hash code for this object.
        *       @return a hash code for this object.
        */
	@Override
	public int hashCode() {return 31 * name.hashCode() + link.hashCode() + imageUrl.hashCode() + store.hashCode() + price.hashCode() + location.hashCode();}

	/**
        *       This method return a String that represents the properties values of this object.
        *       @return a String representation of the properties of this object.
        */
	@Override
	public String toString() {
		return name + " " + link + " " + imageUrl + " " + store + " " + price.toString() + " " + location;
	}
}

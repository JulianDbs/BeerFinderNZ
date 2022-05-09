package com.juliandbs.beerfindernz.entities;

import java.io.Serializable;
import java.lang.Comparable;
import java.lang.NullPointerException;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

/**
*       This class implements the 'Comparable' interface and represents a record in the 'market_info' table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
@Entity
@Table(name = "market_info")
public class MarketInfo implements Comparable<MarketInfo>, Serializable {
        /**
        *       This class property represents the SERIAL PRIMARY KEY column, called 'id', of the 'market_info' table in the application PostgreSQL database.
        */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

        /**
        *       This class property represents the NOT NULL VARCHAR(60) column, called 'market', of the 'market_info' table in the application PostgreSQL database.
        *       The 'market' column have a one-way relationship with the 'name' column of the 'market' table in the application PostgreSQL database.
        */
	@Column(name = "market", nullable = false, length = 60)
	private String market;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'search_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "search_container_type", nullable = false, length = 15)
	private String searchContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'search_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "search_container_class", nullable = false, length = 50)
	private String searchContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_container_type", nullable = false, length = 15)
	private String productContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'product_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_container_class", nullable = false, length = 50)
	private String productContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_link_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_link_container_type", nullable = false, length = 15)
	private String linkContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'product_link_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_link_container_class", nullable = false, length = 50)
	private String linkContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_link_element_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_link_element_type", nullable = false, length = 15)
	private String linkElementType;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_image_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_image_container_type", nullable = false, length = 15)
	private String imageContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'product_image_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_image_container_class", nullable = false, length = 50)
	private String imageContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_image_element_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_image_element_type", nullable = false, length = 15)
	private String imageElementType;

        /**
        *       This class property represents the VARCHAR(50) column, called 'product_image_element_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_image_element_class", nullable = true, length = 50)
	private String imageElementClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_image_element_param', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_image_element_param", nullable = false, length = 15)
	private String imageElementParam;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_detail_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_detail_container_type", nullable = false, length = 15)
	private String detailContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'product_detail_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_detail_container_class", nullable = false, length = 50)
	private String detailContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_name_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_name_container_type", nullable = false, length = 15)
	private String nameContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'product_name_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_name_container_class", nullable = false, length = 50)
	private String nameContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_name_element_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_name_element_type", nullable = false, length = 15)
	private String nameElementType;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_price_container_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_price_container_type", nullable = false, length = 15)
	private String priceContainerType;

        /**
        *       This class property represents the NOT NULL VARCHAR(50) column, called 'product_price_container_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_price_container_class", nullable = false, length = 50)
	private String priceContainerClass;

        /**
        *       This class property represents the NOT NULL VARCHAR(15) column, called 'product_price_element_type', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_price_element_type", nullable = false, length = 15)
	private String priceElementType;

        /**
        *       This class property represents the VARCHAR(50) column, called 'product_price_element_class', of the 'market_info' table in the application PostgreSQL database.
        */
	@Column(name = "product_price_element_class", nullable = true, length = 50)
	private String priceElementClass;

	/**
	*	Empty class Constructor of the MarketInfo class.
	*/
	public MarketInfo(){}

	/**
	*	Class Constructor of the 'MarketInfo' class.
	*
	*	@param id a long value that represents the 'id' column value in the 'market_info' table.
	*	@param market a String object that represents the 'market' column value in the 'market_info' table.
	*	@param searchContainerType a String object that represents the 'search_container_type' column value in the 'market_info' table.
	*	@param searchContainerClass a String object that represents the 'search_container_class' column value in the 'market_info' table.
	*	@param productContainerType a String object that represents the 'product_container_type' column value in the 'market_info' table.
	*	@param productContainerClass a String object that represents the 'product_container_class' column value in the 'market_info' table.
	*	@param linkContainerType a String object that represents the 'link_container_type' column value in the 'market_info' table.
	*	@param linkContainerClass a String object that represents the 'link_container_class' column value in the 'market_info' table.
	*	@param linkElementType a String object that represents the 'link_element_type' column value in the 'market_info' table.
	*	@param imageContainerType a String object that represents the 'image_container_type' column value in the 'market_info' table.
	*	@param imageContainerClass a String object that represents the 'image_container_class' column value in the 'market_info' table.
	*	@param imageElementType a String object that represents the 'image_element_type' column value in the 'market_info' table.
	*	@param imageElementClass a String object that represents the 'image_element_class' column value in the 'market_info' table.
	*	@param imageElementParam a String object that represents the 'image_element_param' column value in the 'market_info' table.
	*	@param detailContainerType a String object that represents the 'detail_container_type' column value in the 'market_info' table.
	*	@param detailContainerClass a String object that represents the 'detail_container_class' column value in the 'market_info' table.
	*	@param nameContainerType a String object that represents the 'name_container_type' column value in the 'market_info' table.
	*	@param nameContainerClass a String object that represents the 'name_container_class' column value in the 'market_info' table.
	*	@param nameElementType a String object that represents the 'name_element_type' column value in the 'market_info' table.
	*	@param priceContainerType a String object that represents the 'price_container_type' column value in the 'market_info' table.
	*	@param priceContainerClass a String object that represents the 'price_container_class' column value in the 'market_info' table.
	*	@param priceElementType a String object that represents the 'price_element_type' column value in the 'market_info' table.
	*	@param priceElementClass a String object that represents the 'price_element_class' column value in the 'market_info' table.
	*	@throws NullPointerException if any of the Constructor parameters except 'imageElementClass' and 'priceElementClass' are null.
	*/
	public MarketInfo(
		long id,
		String market,
		String searchContainerType,
		String searchContainerClass,
		String productContainerType,
		String productContainerClass,
		String linkContainerType,
		String linkContainerClass,
		String linkElementType,
		String imageContainerType,
		String imageContainerClass,
		String imageElementType,
		String imageElementClass,
		String imageElementParam,
		String detailContainerType,
		String detailContainerClass,
		String nameContainerType,
		String nameContainerClass,
		String nameElementType,
		String priceContainerType,
		String priceContainerClass,
		String priceElementType,
		String priceElementClass
	) throws NullPointerException {
		if (
			java.util.Objects.isNull(id) ||
			market == null ||
			searchContainerType == null ||
			searchContainerClass == null ||
			productContainerType == null ||
			productContainerClass == null ||
			linkContainerType == null ||
			linkContainerClass == null ||
			linkElementType == null ||
			imageContainerType == null ||
			imageContainerClass == null ||
			imageElementType == null ||
			imageElementParam == null ||
			detailContainerType == null ||
			detailContainerClass == null ||
			nameContainerType == null ||
			nameContainerClass == null ||
			nameElementType == null ||
			priceContainerType == null ||
			priceContainerClass == null ||
			priceElementType == null
		) throw new NullPointerException();
		this.id = id;
		this.market = market;
		this.searchContainerType = searchContainerType;
		this.searchContainerClass = searchContainerClass;
		this.productContainerType = productContainerType;
		this.productContainerClass = productContainerClass;
		this.linkContainerType = linkContainerType;
		this.linkContainerClass = linkContainerClass;
		this.linkElementType = linkElementType;
		this.imageContainerType = imageContainerType;
		this.imageContainerClass = imageContainerClass;
		this.imageElementType = imageElementType;
		this.imageElementClass = imageElementClass;
		this.imageElementParam = imageElementParam;
		this.detailContainerType = detailContainerType;
		this.detailContainerClass = detailContainerClass;
		this.nameContainerType = nameContainerType;
		this.nameContainerClass = nameContainerClass;
		this.nameElementType = nameElementType;
		this.priceContainerType = priceContainerType;
		this.priceContainerClass = priceContainerClass;
		this.priceElementType = priceElementType;
		this.priceElementClass = priceElementClass;
	}

	/**
	*	This method returns a Long object that represents the 'id' class property.
	*
	*	@return a Long object representing the 'id' class property value.
	*/
	public Long getId() {return Long.valueOf(id);}

	/**
	*	This method returns a String object that represents the 'market' class property.
	*
	*	@return a String object representing the 'market' class property value.
	*/
	public String getMarket() {return market;}

	/**
	*	This method returns a String object that represents the 'searchContainerType' class property.
	*
	*	@return a String object representing the 'seachContainerType' class property value.
	*/
	public String getSearchContainerType() {return searchContainerType;}

	/**
	*	This method returns a String object that represents the 'searchContainerClass' class property.
	*
	*	@return a String object representing the 'searchContainerClass' class property value.
	*/
	public String getSearchContainerClass() {return searchContainerClass;}

	/**
	*	This method returns a String object that represents the 'productContainerType' class property.
	*
	*	@return a String object representing the 'productContainerType' class property value.
	*/
	public String getProductContainerType() {return productContainerType;}

	/**
	*	This method returns a String object that represents the 'productContainerClass' class property.
	*
	*	@return a String object representing the 'productContainerClass' class property value.
	*/
	public String getProductContainerClass() {return productContainerClass;}

	/**
	*	This method returns a String object that represents the 'linkContainerType' class property.
	*
	*	@return a String object representing the 'linkContainerType' class property value.
	*/
	public String getLinkContainerType() {return linkContainerType;}

	/**
	*	This method returns a String object that represents the 'linkContainerClass' class property.
	*
	*	@return a String object representing the 'linkContainerClass' class property value.
	*/
	public String getLinkContainerClass() {return linkContainerClass;}

	/**
	*	This method returns a String object that represents the 'linkElementType' class property.
	*
	*	@return a String object representing the 'linkElementType' class property value.
	*/
	public String getLinkElementType() {return linkElementType;}

	/**
	*	This method returns a String object that represents the 'imageContainerType' class property.
	*
	*	@return a String object representing the 'imageContainerType' class property value.
	*/
	public String getImageContainerType() {return imageContainerType;}

	/**
	*	This method returns a String object that represents the 'imageContainerClass' class property.
	*
	*	@return a String object representing the 'imageContainerClass' class property value.
	*/
	public String getImageContainerClass() {return imageContainerClass;}

	/**
	*	This method returns a String object that represents the 'imageElementType' class property.
	*
	*	@return a String object representing the 'imageElementType' class property value.
	*/
	public String getImageElementType() {return imageElementType;}

	/**
	*	This method returns a String object that represents the 'imageElementClass' class property.
	*
	*	@return a String object representing the 'imageElementClass' class property value.
	*/
	public String getImageElementClass() {return imageElementClass;}

	/**
	*	This method returns a String object that represents the 'imageElementParam' class property.
	*
	*	@return a String object representing the 'imageElementParam' class property value.
	*/
	public String getImageElementParam() {return imageElementParam;}

	/**
	*	This method returns a String object that represents the 'detailContainerType' class property.
	*
	*	@return a String object representing the 'detailContainerType' class property value.
	*/
	public String getDetailContainerType() {return detailContainerType;}

	/**
	*	This method returns a String object that represents the 'detailContainerClass' class property.
	*
	*	@return a String object representing the 'detailContainerClass' class property value.
	*/
	public String getDetailContainerClass() {return detailContainerClass;}

	/**
	*	This method returns a String object that represents the 'nameContainerType' class property.
	*
	*	@return a String object representing the 'nameContainerType' class property value.
	*/
	public String getNameContainerType() {return nameContainerType;}

	/**
	*	This method returns a String object that represents the 'nameContainerClass' class property.
	*
	*	@return a String object representing the 'nameContainerClass' class property value.
	*/
	public String getNameContainerClass() {return nameContainerClass;}

	/**
	*	This method returns a String object that represents the 'nameElementType' class property.
	*
	*	@return a String object representing the 'nameElementType' class property value.
	*/
	public String getNameElementType() {return nameElementType;}

	/**
	*	This method returns a String object that represents the 'priceContainerType' class property.
	*
	*	@return a String object representing the 'priceContainerType' class property value.
	*/
	public String getPriceContainerType() {return priceContainerType;}
	/**
	*	This method returns a String object that represents the 'priceContainerClass' class property.
	*
	*	@return a String object representing the 'priceContainerClass' class property value.
	*/

	public String getPriceContainerClass() {return priceContainerClass;}

	/**
	*	This method returns a String object that represents the 'priceElementType' class property.
	*
	*	@return a String object representing the 'priceElementType' class property value.
	*/
	public String getPriceElementType() {return priceElementType;}

	/**
	*	This method returns a String object that represents the 'priceElementClass' class property.
	*
	*	@return a String object representing the 'priceElementClass' class property value.
	*/
	public String getPriceElementClass() {return priceElementClass;}


	/**
        *       This method compares two MarketInfo object.
        *       Returns a negative integer, zero, or positive integer as this object is less than, equal to, or greater than the specified object.
	*
        *       @param marketInfo the MarketInfo object to be compared.
        *       @return a negative integer, zero, or a positive integer as this object is less tha, equal to, or greater than the specified object.
        *       @thorws NullPointerException if the 'marketInfo' parameter is null.
        */
        @Override
        public int compareTo(MarketInfo marketInfo) throws NullPointerException {
                if (marketInfo == null)
                        throw new NullPointerException();
                return marketInfo.getId().compareTo(id) +
			marketInfo.getMarket().compareTo(market) +
			marketInfo.getSearchContainerType().compareTo(searchContainerType) +
			marketInfo.getSearchContainerClass().compareTo(searchContainerClass) +
			marketInfo.getProductContainerType().compareTo(productContainerType) +
			marketInfo.getProductContainerClass().compareTo(productContainerClass) +
			marketInfo.getLinkContainerType().compareTo(linkContainerType) +
			marketInfo.getLinkContainerClass().compareTo(linkContainerClass) +
			marketInfo.getLinkElementType().compareTo(linkElementType) +
			marketInfo.getImageContainerType().compareTo(imageContainerType) +
			marketInfo.getImageContainerClass().compareTo(imageContainerClass) +
			marketInfo.getImageElementType().compareTo(imageElementType) +
			marketInfo.getImageElementClass().compareTo(imageElementClass) +
			marketInfo.getImageElementParam().compareTo(imageElementParam) +
			marketInfo.getDetailContainerType().compareTo(detailContainerType) +
			marketInfo.getDetailContainerClass().compareTo(detailContainerClass) +
			marketInfo.getNameContainerType().compareTo(nameContainerType) +
			marketInfo.getNameContainerClass().compareTo(nameContainerClass) +
			marketInfo.getNameElementType().compareTo(nameElementType) +
			marketInfo.getPriceContainerType().compareTo(priceContainerType) +
			marketInfo.getPriceContainerClass().compareTo(priceContainerClass) +
			marketInfo.getPriceElementType().compareTo(priceElementType) +
			marketInfo.getPriceElementClass().compareTo(priceElementClass);
        }

        /**
        *       This method compares this MarketInfo object to the specified object.
	*
        *       @param object the object to compare.
        *       @return true if and only if the 'object' property  is not null and is a MarketInfo object that represents a MarketInfo object with the same parameters, otherwise false.
        */
        @Override
        public boolean equals(Object object) {
                boolean result = false;
                if (object instanceof MarketInfo) {
                        MarketInfo marketInfo = (MarketInfo) object;
                        result = marketInfo.getId().equals(id) &&
				marketInfo.getMarket().equals(market) &&
				marketInfo.getSearchContainerType().equals(searchContainerType) &&
				marketInfo.getSearchContainerClass().equals(searchContainerClass) &&
				marketInfo.getProductContainerType().equals(productContainerType) &&
				marketInfo.getProductContainerClass().equals(productContainerClass) &&
				marketInfo.getLinkContainerType().equals(linkContainerType) &&
				marketInfo.getLinkContainerClass().equals(linkContainerClass) &&
				marketInfo.getLinkElementType().equals(linkElementType) &&
				marketInfo.getImageContainerType().equals(imageContainerType) &&
				marketInfo.getImageContainerClass().equals(imageContainerClass) &&
				marketInfo.getImageElementType().equals(imageElementType) &&
				marketInfo.getImageElementClass().equals(imageElementClass) &&
				marketInfo.getImageElementParam().equals(imageElementParam) &&
				marketInfo.getDetailContainerType().equals(detailContainerType) &&
				marketInfo.getDetailContainerClass().equals(detailContainerClass) &&
				marketInfo.getNameContainerType().equals(nameContainerType) &&
				marketInfo.getNameContainerClass().equals(nameContainerClass) &&
				marketInfo.getNameElementType().equals(nameElementType) &&
				marketInfo.getPriceContainerType().equals(priceContainerType) &&
				marketInfo.getPriceContainerClass().equals(priceContainerClass) &&
				marketInfo.getPriceElementType().equals(priceElementType) &&
				marketInfo.getPriceElementClass().equals(priceElementClass);
                }
                return result;
        }

 	/**
        *       This method returns a hash code for this object.
	*
        *       @return a hash code for this object.
        */
        @Override
        public int hashCode() { return 31 * Long.valueOf(id).hashCode() + market.hashCode(); }

        /**
        *       This method returns a String that represents the properties values of this object.
	*
        *       @return a String representation of the properties of this object.
        */
        @Override
        public String toString() {
                return String.valueOf(id) + " " +
			market + " " +
			searchContainerType + " " +
			searchContainerClass + " " +
			productContainerType + " " +
			productContainerClass + " " +
			linkContainerType + " " +
			linkContainerClass + " " +
			linkElementType + " " +
			imageContainerType + " " +
			imageContainerClass + " " +
			imageElementType + " " +
			imageElementClass + " " +
			imageElementParam + " " +
			detailContainerType + " " +
			detailContainerClass + " " +
			nameContainerType + " " +
			nameContainerClass + " " +
			nameElementType + " " +
			priceContainerType + " " +
			priceContainerClass + " " +
			priceElementType + " " +
			priceElementClass;
        }
}

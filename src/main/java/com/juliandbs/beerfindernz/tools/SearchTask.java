package com.juliandbs.beerfindernz.tools;

import com.juliandbs.beerfindernz.entities.Market;
import com.juliandbs.beerfindernz.entities.MarketInfo;
import com.juliandbs.beerfindernz.tools.Product;
import com.juliandbs.beerfindernz.entities.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.lang.NullPointerException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

/**
*	This class implemets the 'Callable' Interface and its uses to perform a web scraping task.
*
*	@author JulianDbs
*/
public class SearchTask implements Callable<List<Product>> {

	/**
	*	This variable represents the product name.
	*/
	private final String product;

	/**
	*	This variable is an 'Store' class instance and represents the data of the store in which the product is searched.
	*/
	private final Store store;

	/**
	*	This variable is an 'Market' class instance and represents the data of the market brand who owns the store in which the product is searched.
	*/
	private final Market market;

	/**
	*	Class Construcor of the 'SeachTask' class.
	*
	*	@param product a String object that represents the name if the product to find.
	*	@param store a 'Store' class instance that represents the data of the store in which the product is searched.
	*	@oaram market a 'Market' class instance that represents the data of the marker brand who owns the store in which the product is searched.
	*	@throws NullPointerException if any of the constructor parameters are null.
	*/
	public SearchTask(String product, Store store, Market market) throws NullPointerException {
		if (product == null || store == null || market == null)
			throw new NullPointerException();
		this.product = product;
		this.store = store;
		this.market = market;
	}

	/**
	*	Method Override of the 'call' method of the 'Callable' Interface.
	*/
	@Override
	public List<Product> call() throws java.lang.Exception {
		return searchProduct();
	}

	/**
        *	This method uses the 'store' and 'product' class parameters to create a valid host and uses it
	*	to get the HTML data via Jsoup, if Jsoup producess any Exception this method returns a empty 'List'.
	*	If Jsoup not throws any Exception, this method uses the HTML data and the 'market', 'store' and 'product'
	*	class properies to extract information about the searched product through the 'extractDataFromDocument'
	*	method and returns it as a 'List' of 'Product' class instances.
	*
	*	@return a 'List' of 'Product' class instances.
        */
        public List<Product> searchProduct() {
                StringBuilder host = new StringBuilder();
                host.append(store.getStoreSearchUrl()).append(product.toLowerCase() + " ");
                if (market.getMaxPageAdd() != null)
                        host.append(market.getMaxPageAdd());
                Document document = new Document("");
		List<Product> result = new LinkedList<>();
                try {
                        document = Jsoup.connect(host.toString()).timeout(50000).get();
                } catch (MalformedURLException | HttpStatusException | UnsupportedMimeTypeException | SocketTimeoutException ex) {
			result = new LinkedList<>();
		} catch (IOException e) {
                	result = new LinkedList<>();
		} finally {
                        result = extractDataFromDocument(document, market.getMarketInfo(), store.getStoreUrl(), store.getName(), store.getCity(), product);
                }
		return result;
        }

	/**
        *	This method creates and returns a 'List' of 'Product' class instances using data fetched from a 'Document' class that
	*	represents the HTML data obteined from a Jsoup connection, if and only if the 'Document' class contains the elements
	*	corresponding to the 'search container class' and 'product container class' parameters of the 'marketInfo' property. if not
	*	this method returns a empty 'List'.
	*
	*	First this method checks if the 'document' contains the elements needed for the data extraction, if it contains the elements,
	*	creates a 'Elements' class instance with each HTML element whose class is equal to the 'product container class'.
	*	Then, if the elements class is not empty, creates a 'Product' class instance calling the 'getProductFromElement' method and if
	*	the 'Product' instance is valid and it contains the name of the product adds it to the product's list and finally this method
	*	returns the product's list.
	*
	*	@param document a 'Document' class instance that represents the HTML data return by the Jsoup connection.
	*	@param marketInfo a 'MarketInfo' class instance that represents the information data about the market brand who owns the store in
	*	which the product was searched.
	*	@param linkHead a String object that represents the link to the store web site.
	*	@param storeName a String object that represents the name of the in which the product was searched.
	*	@param city a String object that represents the name of the location in which the store in wich the product was searched.
	*	@param productName a String object thtat represents the name of the product.
	*	@return a 'List' of 'Product' class instances.
        */
        private List<Product> extractDataFromDocument(Document document, MarketInfo marketInfo, String linkHeader, String storeName, String city, String productName) {
                List<Product> result = new ArrayList<>();
                if (
                        !document.getElementsByClass(marketInfo.getSearchContainerClass()).isEmpty() &&
                        !document.getElementsByClass(marketInfo.getProductContainerClass()).isEmpty()
                ) {
                        Elements products = document.getElementsByClass(marketInfo.getProductContainerClass());
                        if (!products.isEmpty()){
                                for (Element element : products) {
                                        Product product = getProductFromElement(element, marketInfo, linkHeader, storeName, city);
                                        if (!product.isEmpty() && product.getName().toLowerCase().contains(productName.toLowerCase()))
                                                result.add(product);
                                }
                        }
                }
                return result;
        }

	/**
        *	This method extracts data from a 'Element' class and uses that data to create a new 'Product' class instance and return it.
	*
	*	First, this method creates four 'Elements' class instance, one for the product name data.
	*	The first for the HTML element that stores the product name data.
	*	The second for the HTML element that stores the product link data.
	*	The third for the HTML element that stores the product image data.
	*	The fourth for the HTML element that stores the product price data.
	*	If any of these 'Elements' class are null, this method returns a empty 'Product' class instance.
	*	If none of these 'Elements' class are null this method :
	*		Extracts the product name using the 'getTextFromFirstElement' method.
	*		Extracts the product link using the 'getAttributeFromFirstElement' method.
	*		Extracts the product image link using the 'findImageSrcInElements' method.
	*		Extracts the product price using the 'findPriceInElements' method'.
	*	If any of this values are null, this method uses to create a new 'Product' class instance and returns it.
	*
	*	@param element an 'Element' object that represents the HTML data obtained by the Jsoup connection.
	*	@param marketInfo a 'MarketInfo' object that represents the information data about the market brand who owns the store in
	*	which the product was searched.
	*	@param linkHead a String object that represents the link to the store web site.
	*	@param storeName a String object that represents the name of the in which the product was searched.
	*	@param city a String object that represents the name of the location in which the store in wich the product was searched.
	*	@return a 'Product' class instance that represents the product data extracted from the 'Element' classs.
        */
        private Product getProductFromElement(Element element, MarketInfo marketInfo, String linkHeader, String storeName, String city) {
                Product result = new Product();
                Elements nameContainers = element.getElementsByClass(marketInfo.getNameContainerClass());
                Elements linkContainers = element.getElementsByClass(marketInfo.getLinkContainerClass());
                Elements imageContainers = element.getElementsByClass(marketInfo.getImageContainerClass());
                Elements priceContainers = element.getElementsByClass(marketInfo.getPriceContainerClass());
                if (nameContainers != null && linkContainers != null && imageContainers != null && priceContainers != null) {
			String imageElementClass = marketInfo.getImageElementClass(); if(imageElementClass == null) imageElementClass = "none";
			String priceElementClass = marketInfo.getPriceElementClass(); if(priceElementClass == null) priceElementClass = "none";
			String name = getTextFromFirstElement(
								nameContainers,
								marketInfo.getNameElementType());
                        String link = linkHeader + getAttributeFromFirstElement(
										linkContainers,
										marketInfo.getLinkElementType(),
										"href");
                        String imageUrl = findImageSrcInElements(
									imageContainers,
									marketInfo.getImageElementType(),
									imageElementClass,
									marketInfo.getImageElementParam());
                        String price = findPriceInElements(
								priceContainers,
								marketInfo.getPriceElementType(),
								priceElementClass);
                        if(price.contains("$"))
                                price = price.replace("$", "");
                        if (!name.equals("none") && !link.equals("none") && !storeName.equals("none") && !imageUrl.equals("none") && !price.equals("none"))
                                result = new Product(name, link, imageUrl, storeName, Float.valueOf(price), city);
                }
                return result;
        }

	/**
	*	This method tries to find the an attribute, specified by the 'attribute' property, in an element that may
	*	or may not be inside the elements given as parameter.
	*
        *	@param elements an 'Elements' class instance that represents a group of elements that may or may not contains
	*	the searched value.
	*	@param elementType a String object that represents the element type that stores the element which may or may not
	*	contains the searched value.
	*	@param attribute a String object that represents the attribute of the element that may or may not stores the
	*	searched value.
	*	@return a String instance that may or may not represents the searched value.
        */
        private String getAttributeFromFirstElement(Elements elements,String elementType, String attribute) {
                String result = "none";
                if (elements.size() == 1) {
                        String t = elements.first().child(0).select(elementType).attr(attribute);
                        if (t != null && !t.isEmpty())
                                result = t;
                } else if (elements.size() > 1) {
                        List<Element> list = new ArrayList<>(
                                elements.stream()
                                        .filter(e -> e.childrenSize() > 0)
                                        .collect(Collectors.toList())
                        );
                        String t = (list.size() > 0)? list.get(0).child(0).select(elementType).attr(attribute) : "none";
                        if (t != null && !t.isEmpty())
                                result = t;
                }
                return result;
        }

	/**
	*	This method tries to find the text content of an element,specified by the 'elementType' property, that may or may not
	*	be inside the elements given as parameter.
	*
        *	@param elements an 'Elements' class instance that represents a group of elements that may or may not contains
	*	the searched value.
	*	@param elementType a String object that represents the element type that stores the element which may or may not
	*	contains the searched value.
	*	@return a String instance that may or may not represents the searched value.
        */
        private String getTextFromFirstElement(Elements elements, String elementType) {
                String result = "none";
                if (elements.size() == 1) {
                        String p = elements.first().child(0).select(elementType).text();
                        if (p != null && !p.isEmpty())
                                result = p;
                } else if (elements.size() > 1) {
                        List<Element> list = new ArrayList<>(
                                elements.stream()
                                        .filter(e -> e.childrenSize() > 0)
                                        .collect(Collectors.toList())
                        );
                        String p = (list.size() > 0)? list.get(0).child(0).select(elementType).text() : "none";
                        if (p != null && !p.isEmpty())
                                result = p;
                }
                return result;

        }

	/**
	*	This method tries to find the an attribute, specified by the 'imageParam' property, in a 'img' element that may
	*	or may not be inside the elements given as parameter.
	*
        *	@param elements an 'Elements' class instance that represents a group of elements that may or may not contains
	*	the searched value.
	*	@param elementType a String object that represents the element type that stores the element which may or may not
	*	contains the searched value.
	*	@param imageElementClass a String object that represents the element class that stores the element which may or may not
	*	contains the element that contains the searched value.
	*	@param imageParam a String object that represents the attribute of the element that may or may not stores the
	*	searched value.
	*	@return a String instance that may or may not represents the searched value.
        *
        */
        private String findImageSrcInElements(Elements elements, String elementType, String imageElementClass,  String imageParam) {
                String result = "none";
		if (elements.size() > 0) {
	                if (elements.size() == 1) {
				if (imageElementClass.equals("none"))
		                        result = findImageSrc(elements.first().child(0).select(elementType), imageParam);
				else
					result = findImageSrc(elements.first().getElementsByClass(imageElementClass), imageParam);
	                } else {
	                        List<Element> list = new ArrayList<>(
	                                elements.stream()
	                                        .filter(e -> e.childrenSize() > 0)
	                                        .collect(Collectors.toList())
	                        );
	                        if (list.size() > 0)
					if (imageElementClass.equals("none"))
			                        result = findImageSrc(list.get(0).child(0).select(elementType), imageParam);
					else
						result = findImageSrc(list.get(0).getElementsByClass(imageElementClass), imageParam);
	                }
		}
                return result;
        }

	/**
	*	This method tries to find the an attribute, specified by the 'imageParam' property, in a 'img' element that may or
	*	may not be inside the elements given as parameter.
	*
        *	@param elements an 'Elements' class instance that represents a group of elements that may or may not contains
	*	the searched value.
	*	@param imageParam a String object that represents the attribute of the element that may or may not stores the
	*	searched value.
	*	@return a String instance that may or may not represents the searched value.
        */
        private String findImageSrc(Elements elements, String imageParam) {
                String result = "none";
                try {
                        result = elements.attr(imageParam);
                        if (result.equals("")) throw new java.lang.NullPointerException();
                } catch (java.lang.NullPointerException e) {
                        try {
                                result = elements.first().absUrl("src");
                                if (result.equals("")) throw new java.lang.NullPointerException();
                        } catch (java.lang.NullPointerException n) {
                                try {
                                        result = elements.attr("src");
                                if (result.equals("")) throw new java.lang.NullPointerException();
                                } catch (java.lang.NullPointerException npe) {
                                        result = "none";
                                }
                        }
                }
                return result;
        }

	/**
	*	This method tries to find the text content of the element that may or may not be inside the elements
	*	given as parameter.
	*
        *	@param elements an 'Elements' class instance that represents a group of elements that may or may not contains
	*	the searched value.
	*	@param elementType a String object that represents the element type that stores the element which may or may not
	*	contains the searched value.
	*	@param elementClass a String object that represents the element class that stores the element which may or may not
	*	contains the element that contains the searched value.
	*	@return a String instance that may or may not represents the searched value.
	*/
	private String findPriceInElements(Elements elements, String elementType, String elementClass) {
		String result = "none";
		if (elements.size() > 0) {
			if(elements.size() == 1)
				result = findPriceInElement(elements.first(), elementType, elementClass);
			if(elements.size() > 1) {
	 			List<Element> list = new ArrayList<>(
	                                elements.stream()
	                                        .filter(e -> e.childrenSize() > 0)
	                                        .collect(Collectors.toList())
	                        );
				if (list.size() >= 1)
					result = findPriceInElement(list.get(0), elementType, elementClass);
			}
		}
                return result;
	}

	/**
	*	This method tries to find the text content of the element that may or may not bee inside the element
	*	given as parameter.
	*
        *	@param element an 'Element' class instance that represents the element that may or may not contains
	*	the searched value.
	*	@param elementType a String object that represents the element type that stores the element which may or may not
	*	contains the searched value.
	*	@param elementClass a String object that represents the element class that stores the element which may or may not
	*	contains the element that contains the searched value.
	*	@return a String instance that may or may not represents the searched value.
	*/
	private String findPriceInElement(Element element, String elementType, String elementClass) {
		String result = "none";
		if (elementClass.equals("none") || elementClass.equals("")) {
			String p = element.child(0).select(elementType).text();
			if (p != null && !p.isEmpty())
				result = p;
		} else {
			Elements elements = element.getElementsByClass(elementClass);
			if (elements.size() >= 1) {
				String p = elements.first().text();
				if (p != null && !p.isEmpty())
					result = p;
			}

		}
		return result;
	}
}

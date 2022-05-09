package com.juliandbs.beerfindernz.services;

import com.juliandbs.beerfindernz.tools.Tools;
import com.juliandbs.beerfindernz.tools.WebScraper;
import com.juliandbs.beerfindernz.tools.Product;
import com.juliandbs.beerfindernz.entities.Isle;
import com.juliandbs.beerfindernz.repositories.IsleRepository;
import com.juliandbs.beerfindernz.entities.Region;
import com.juliandbs.beerfindernz.repositories.RegionRepository;
import com.juliandbs.beerfindernz.entities.City;
import com.juliandbs.beerfindernz.repositories.CityRepository;
import com.juliandbs.beerfindernz.entities.Store;
import com.juliandbs.beerfindernz.repositories.StoreRepository;
import com.juliandbs.beerfindernz.tools.SearchTask;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
*	This class uses the '@Service' annotation and acts as a central point to interact with the JPA Repositories and the Web Scraper tool.
*
*	@author JulianDbs
*/
@Service("searchService")
public class SearchService {

	/**
	*	An injected 'webScrapper' bean.
	*/
	@Autowired
	private WebScraper webScraper;

	/**
	*	An injected 'isleRepository' JPA Repository.
	*/
	@Autowired
	private IsleRepository isleRepository;

	/**
	*	An injected 'regionRepository' JPA Repository.
	*/
	@Autowired
	private RegionRepository regionRepository;

	/**
	*	An injected 'cityRepository' JPA Repository.
	*/
	@Autowired
	private CityRepository cityRepository;

	/**
	*	An injected 'storeRepository' JPA Repository.
	*/
	@Autowired
	private StoreRepository storeRepository;

	/**
	*	This method uses the 'isleRepository' JPA Repository to get all the records in the 'isle' table as 'Isle' instances
	*	from the PostgresSQL DataBase and build a list with the names of each isle.
	*
	*	@return a 'Set' instance containing String instances that represent the names of each isle.
	*/
	public Set<String> getAllIslands() {
		Set<Isle> islands = isleRepository.getAllRecords();
		Set<String> names = new TreeSet<>(islands.stream()
								.map(e -> e.getName())
								.collect(Collectors.toSet()));
		return names;
	}

	/**
	*	This method uses the 'regionRepository' JPA Repository to find all the records in the 'region' table whose 'isle' column
	*	matches the 'isle' parameter of this method.
	*	First this method uses the static 'getParameterFromJSONBody' method of the 'Tools' class to convert the isle parameter to a 'List of
	*	'Map' each one cotaining valid search parameters;
	*	Then this method uses the static 'findParameterInParamList' method of the 'Tools'class to find a parameter called 'isle',
	*	if the return value is not 'none' it uses that value to find the region available through the 'regionRepository'.
	*
	*	@param isle a String object that represents a HttpRequest body that may or may not contain the 'isle' parameter used to find all
	*	the available regions in a isle.
	*	@return a 'Set' instance of String objects representing a list of regions.
	*/
	public Set<String> findRegionsByIsle(String isle) {
		Set<String> result = new TreeSet<>();
                List<Map<String, String>> params = Tools.getParametersFromJSONBody(isle);
                String value = Tools.findParamValueInParamList("isle", params);
                if (value != "none") {
                        Set<Region> regions = regionRepository.getRegionsByIsle(value);
                        result = new TreeSet<>(regions.stream()
                                                                        .map(r -> r.getName())
                                                                        .collect(Collectors.toSet()));
                }
		return result;
	}

	/**
	*	This method uses the 'cityRepository' JPA Repository to find all the records in the 'city' table whose 'region' column
	*	matches the 'region' parameter of this method;
	*	First this method uses the static 'getParametersFromJSonBody' method of the 'Tools' class to convert the region parameter to a 'List' of
	*	'Map' each one containing valid search parameters;
	*	Then this method uses the static 'findParameterInParamList' method of the 'Tools' class to find a parameter called 'region',
	*	if the return value is not 'none' it uses that value to find the cities available through the 'citiesRepository'.
	*
	*	@param region a String object that represents a HttpRequest body that may or may not contain the 'region' parameter used to find all
	*	the available cities in a region.
	*	@return a 'Set' instance of String object representing a list of cities.
	*/
	public Set<String> findCitiesByRegion(String region) {
		Set<String> result = new TreeSet<>();
                List<Map<String, String>> params = Tools.getParametersFromJSONBody(region);
                String value = Tools.findParamValueInParamList("region", params);
                if (value != "none") {
                        TreeSet<City> locations = cityRepository.getCitiesByRegion(value);
                        result = new TreeSet<>(locations.stream()
                                                                                .map( l -> l.getName())
                                                                                .collect(Collectors.toSet()));
                }
		return result;
	}

	/**
	*	This method uses the static 'getMapFromBody' method of the 'Tools' class to cenvert a String that represents
	*	a HttpRequest body into a Map containing all its parameters.
	*	If the map obtained as a result of the above conversion method contains null keys or values, it is repaced with
	*	a map that contains none useful paramters.
	*
	*	@param body a String object that represents a HttpRequest body.
	*	@return a 'Map' containing valid search parameters.
	*/
	public Map<String, String> convertBodyToMap(String body){
		Map<String, String> map = Tools.getMapFromBody(body);
		if (!Tools.isValidSearchMap(map) && Tools.mapContainsNullValues(map))
			map = Tools.validSearchMap;
		return map;
	}

	/**
	*	This method uses a valid 'Map' to build a list of products found through a search using the product name
	*	as the target, in each available store in the specified city and return a list of product lists of each store products founded.
	*	First this method check if the 'Map' instnace given as a paramter is valid, if it is valid, it finds all the
	*	available stores in a specific city using the 'storeRepository' JPA Reposiry's 'findStoresByCity' method using the
	*	map's 'location' key as a parameter.
	*	If the size of the list of stores is 0 this method returns a empty list of lists of product, if not, this method
	*	uses the WebScraper 'getProductsFromStoreList' method to build a list of product lists for each store.
	*
	*	@param params a 'Map' instance that may or may not contains valid parameters.
	*	@return a list of product lists of each available store.
	*/
	public List<List<Product>> searchProduct(Map<String, String> params) {
		List<List<Product>> result = new LinkedList<>();
		if (Tools.isValidSearchMap(params) && !Tools.mapContainsNullValues(params)) {
			Set<Store> storeList = storeRepository.findStoresByCity(params.get("location"));
			if (storeList.size() > 0)
	               		result = webScraper.getProductsFromStoreList(storeList, params.get("brand"));
		}
		return result;
	}

	/**
	*	This method adds the first element in each list in the 'productList' parameter into a single list and return it.
	*
	*	@param productList a 'List' of 'Lists' of 'Product' instances.
	*	@return a 'List' instance containing one 'Product' intance of each list.
	*/
	public static List<Product> getProductList(List<List<Product>> productList) {
                List<Product> result = new LinkedList<>();
                for (List<Product> list : productList) {
                        if (list.size() > 0)
				result.add(list.get(0));
                }
                return result;
        }
}

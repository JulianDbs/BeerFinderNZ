package com.juliandbs.beerfindernz.tools;

import com.juliandbs.beerfindernz.entities.Market;
import com.juliandbs.beerfindernz.repositories.MarketRepository;
import com.juliandbs.beerfindernz.tools.Product;
import com.juliandbs.beerfindernz.entities.Store;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.lang.NullPointerException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.lang.InterruptedException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
*	This class uses the '@Component' Annotation and acts as manager of search tasks.
*
*	@author JulianDbs
*/
@Component("webScraper")
public class WebScraper {

	/**
	*	An injected 'marketRepository' JPA Repository.
	*/
	@Autowired
	private MarketRepository marketRepository;

	/**
	*	This method search a speficied product in a list of stores.
	*	First this method checks if any of its parameters is null, if any are null, it throws an exception.
	*	Then this method build a product 'List', to do this calls the 'getProductListAsync' method if the size of
	*	the store list is equal or greater than 5, otherwise calls its the 'getProductListSync' method.
	*	Finally this method creates a list with the names of each product and uses it to create a list for each
	*	specific product and saves each list to a unique list.
	*
	*	@param storeList a 'Set' object of 'Store' class instances that represents a list og available stores.
	*	@param productToFind a String object that represents the name of the product to find.
	*	@return a list of product lists.
	*	@throws NullPointerException if any of the method's parameters are null.
	*/
	public List<List<Product>> getProductsFromStoreList(Set<Store> storeList, String productToFind) throws NullPointerException {
		List<List<Product>> result = new LinkedList<>();
		if (storeList == null || productToFind == null)
			throw new NullPointerException();
		List<Product> productList = new LinkedList<>();
		if (storeList.size() >= 5)
			try {
				productList = getProductListAsync(storeList, productToFind);
			} catch (InterruptedException e) {
				System.out.println("Async method execution exception : " + e);
			}
		else
			productList = getProductListSync(storeList, productToFind);
		Set<String> productNames = new TreeSet<>(productList.stream()
									.map(p -> p.getName())
									.collect(Collectors.toSet()));
		for (String productName : productNames) {
			List<Product> tempList = productList.stream()
								.filter(p -> productName.equals(p.getName()))
								.sorted( (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
								.collect(Collectors.toCollection(LinkedList<Product>::new));
			result.add(tempList);
		}
		return result;
	}

	/**
	*	This method creates a threads pool and send to it a 'SearchTask' for each 'Store' int the storeList,
	*	when all the tasks are completed it merges all 'SearchTaks' result into one list and returns it.
	*	First this method creates a 'ExecutorService' instance using the 'newFixedThread' method of the Executors class whit
	*	2 threads operating, this threads will be active processing tasks;
	*	Then this method cerates a loop with the storeList and for each store uses the 'findMarketByName' method of the 'marketRepository'
	*	to find the market record, of the 'market' table, that corresponds to each store and use it to create a new 'Market' class instance;
	*	Next this method creates a 'SearchTaks' instance using the 'productToFind' peropery, the current 'Store' and 'Market' instances.
	*	After this method uses the 'SearchTask' to create a new 'Future' class instance and adds it to a list of 'Future' instances.
	*	With the 'Store' loop finished, this method calls the 'awaitTermination' method of the ExecutorService class.
	*	Finally this method gets the result of each 'SearchTask' and adds it to a product list, then returs it.
	*
	*	@param storeList a 'Set' instance that represents a list of available stores.
	*	@param productToFind a String object that represents the name of the product to find.
	*	@return a 'List' instance of the 'Product' class.
	*/
	private List<Product> getProductListAsync(Set<Store> storeList, String productToFind) throws InterruptedException{
		List<Product> result = new LinkedList<>();
		ExecutorService executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<List<Product>>> resultList = new LinkedList<>();
		for (Store store : storeList) {
			Market market = marketRepository.findMarketByName(store.getMarket());
			SearchTask searchTask = new SearchTask(productToFind, store, market);
			Future<List<Product>> futureResult = executor.submit(searchTask);
			resultList.add(futureResult);
		}
		executor.awaitTermination(5, TimeUnit.SECONDS);
		for (Future<List<Product>> futureResult : resultList) {
			Future<List<Product>> future = futureResult;
			List<Product> r = new LinkedList<>();
			try {
				r = future.get();
			} catch (InterruptedException | ExecutionException e){}
			result.addAll(r);
		}
		return result;
	}

	/**
	*	This method receives a list of available stores and uses it to search in each one
	*	for the specified product, then merges all the search results into one list and returns it.
	*
	*	@param storeList a 'List' of 'Stores' class instances.
	*	@param productToFind a String object that represents the product to find.
	*/
	private List<Product> getProductListSync(Set<Store> storeList, String productToFind) {
		List<Product> productList = new LinkedList<>();
		for (Store store : storeList) {
			Market market = marketRepository.findMarketByName(store.getMarket());
			SearchTask searchTask = new SearchTask(productToFind, store, market);
			List<Product>products = searchTask.searchProduct();
			productList.addAll(products);
		}
		return productList;
	}
}

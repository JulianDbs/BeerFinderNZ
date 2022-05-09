package com.juliandbs.beerfindernz.controllers;

import com.juliandbs.beerfindernz.services.SearchService;
import com.juliandbs.beerfindernz.tools.WebScraper;
import com.juliandbs.beerfindernz.tools.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
*	This class uses the '@Controller' Annotation and handles the '/' GET request and the '/' POST request.
*
*	@author JulianDbs
*/
@Controller
public class MainController {

	/**
	*	An injected 'searchService' bean.
	*/
	@Autowired
	private SearchService searchService;

	/**
	*	This method uses the '@GetMapping' Annotation to handle the '/' GET request and return the main page.
	*
	*	@param model an instance of the 'Model' Interface that provides attributes used to render the 'Thymeleaf' template.
	*	@return a String object that represents the local path to the '.html' template.
	*/
	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("islands", searchService.getAllIslands());
		return "index/index.html";
	}

	/**
	*	This method uses the 'PostMapping' Annotation to handle the '/' POST request and return the search result.
	*	First this method extracts the 'POST' request parameters, if these exist, into a 'Map' instance;
	*	Then uses the 'Map', if it is a valid 'map', to get a list of list of product of each store in the chosen location;
	*	After uses the list of list of products to get a list which contains a single product of each one;
	*	Finally adds the product name and the two list as attributes in the model property.
	*
	*	@param body a String intance, tagged with the '@RequestBody' Annotation, that represents 'HttpRequest' body.
	*	@param model an instance of the 'Model' Interface that provides attributes used to render the 'Thymeleaf' template;
	*	@return a String object that represents the local path to the '.html' template.
	*/
	@PostMapping("/")
	public String findBeer(@RequestBody String body, Model model) {
		Map<String, String> params = searchService.convertBodyToMap(body);
		List<List<Product>> products = searchService.searchProduct(params);
		List<Product> productList = searchService.getProductList(products);
		model.addAttribute("product", params.get("brand"));
		model.addAttribute("products", products);
		model.addAttribute("productList", productList);
		return "result/index";
	}
}

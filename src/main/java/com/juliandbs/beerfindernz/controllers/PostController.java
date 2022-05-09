package com.juliandbs.beerfindernz.controllers;

import com.juliandbs.beerfindernz.services.SearchService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
*	This class uses the '@RestController' Annotation and handles the '/getRegions' and '/getLocations' POST requests.
*
*	@author JulianDbs
*/
@RestController
public class PostController {

	/**
	*	An injected 'searchService' bean.
	*/
	@Autowired
	private SearchService searchService;

	/**
	*	This method uses the '@PostMapping' annotation to handle the '/getRegions' POST request and return a 'List' instance
	*	containing the available regions in the chosen island, converted to a String object, via the 'searchService' using the
	*	'HttpRequest' body as parameter.
	*
	*	@param bodyParam a String intance, tagged with the '@RequestBody' Annotation, that represents the 'HttpRequest' body.
	*	@return a String instance that represents the list of available regions converted to a String object.
	*/
	@PostMapping(path = "/getRegions",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRegions(@RequestBody String bodyParam) {
		return searchService.findRegionsByIsle(bodyParam).toString();
	}

	/**
	*	This method uses the '@PostMapping' annotation to handle the '/getLocations' POST request an return a 'List' instance
	*	containing the available location in the chosen region, converted to a String object, via the 'searchService' using the
	*	'HttpRequest' body as parameter.
	*
	*	@param bodyParam a String instance, tagged with the '@RequestBody' Annotation, that represents the 'HttpRequest' body.
	*	@return a String instance that represents the list of available location converted to a String object.
	*/
	@PostMapping(path = "/getLocations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public String getLocations(@RequestBody String bodyParam) {
                return searchService.findCitiesByRegion(bodyParam).toString();
        }
}

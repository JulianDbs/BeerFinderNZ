package com.juliandbs.beerfindernz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.juliandbs.beerfindernz.entities.City;
import java.util.TreeSet;

/**
*	This Interface extends the JPARepository Interface and is used, in this case, to get records from the 'city'
*	table of the application PostgreSQL database.
*
*	@author JulianDbs
*/
public interface CityRepository extends JpaRepository<City, Long> {

	/**
	*	This method returns a 'TreeSet' class instance containing all the records, as 'City' class instance,
	*	that contains the specified region, by the 'region' parameter, in the 'region' column as 'City' class instances.
	*
	*	@param region a String that represents the region in which the cities/towns has to be located.
	*	@return a 'TreeSet' class instance containing 'City' class instances representing each city/town located in
	*	the sepecified region.
	*/
        @Query(nativeQuery = true, value = "SELECT * FROM city WHERE city.region=:region")
        public TreeSet<City> getCitiesByRegion(@Param("region") String region);
}





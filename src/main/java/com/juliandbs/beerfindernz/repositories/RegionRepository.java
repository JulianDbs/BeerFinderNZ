package com.juliandbs.beerfindernz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.juliandbs.beerfindernz.entities.Region;
import java.util.TreeSet;

/**
*       This Interface extends the JPARepository Interface and is used, in this case, to get records from the 'region'
*       table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
public interface RegionRepository extends JpaRepository<Region, Long> {

	/**
        *       This method returns a 'TreeSet' class instance containing all the records, as 'Region' class instance,
        *       that contains the specified isle, by the 'isle' parameter, in the 'isle' column as 'City' class instances.
        *
        *       @param isle a String that represents the isle in which the regions has to be located.
        *       @return a 'TreeSet' class instance containing 'Region' class instances representing each region located in
        *       the sepecified isle.
        */
	@Query(nativeQuery = true, value = "SELECT * FROM region WHERE region.isle=:isle")
	public TreeSet<Region> getRegionsByIsle(@Param("isle") String isle);
}


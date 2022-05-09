package com.juliandbs.beerfindernz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.juliandbs.beerfindernz.entities.Store;
import java.util.TreeSet;

/**
*       This Interface extends the JPARepository Interface and is used, in this case, to get records from the 'city'
*       table of the application PostgreSQL database.
*
*       @author JulianDbs
*/
public interface StoreRepository extends JpaRepository<Store, Long> {

        /**
        *       This method returns a 'TreeSet' class instance containing all the records, as 'Store' class instance,
        *       that contains the specified city, by the 'city' parameter, in the 'city' column as 'Store' class instances.
        *
        *       @param city a String that represents the city in which the stores has to be located.
        *       @return a 'TreeSet' class instance containing 'Store' class instances representing each store located in
        *       the sepecified city.
        */
	@Query(nativeQuery = true, value ="SELECT * FROM store WHERE store.city=:city")
	public TreeSet<Store> findStoresByCity(@Param("city") String city);
}

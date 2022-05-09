package com.juliandbs.beerfindernz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.juliandbs.beerfindernz.entities.Market;

/**
*       This Interface extends the JPARepository Interface and is used, in this case, to get records from the 'market'
*       table of the application PostgreSQL database.
*
*	@author JulianDbs
*/
public interface MarketRepository extends JpaRepository<Market, Long> {

        /**
        *       This method returns a 'Market' class instance that represents the record that contains the specified
	*	name, by the 'name' parameter, in the 'name' column in the 'market' table.
        *
        *       @param name a String that represents the market name.
        *       @return a 'Market' class instance that represents a record in the 'market' table.
        */
	@Query(nativeQuery = true, value = "SELECT * FROM market WHERE market.name=:name")
	public Market findMarketByName(@Param("name") String name);

}

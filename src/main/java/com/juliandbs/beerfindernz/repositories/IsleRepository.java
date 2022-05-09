package com.juliandbs.beerfindernz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.juliandbs.beerfindernz.entities.Isle;
import java.util.TreeSet;

/**
*	This Interface extends the JAPRepository Interface and is used, in this case, to get records from the 'isle'
*	table of the application PostgreSQL database.
*
*	@author JulianDbs
*/
public interface IsleRepository extends JpaRepository<Isle, Long> {

	/**
	*	This method returns a 'TreeSet' class instance containing all the records, as 'Isle' class instances,
	*	from the 'isle' table from the PostgresSQL database.
	*	@return a 'TreeSet' class instance containing 'Isle' class instances representing each isle in the
	*	'isle' table.
	*/
	@Query(nativeQuery = true, value = "SELECT * FROM isle")
	public TreeSet<Isle> getAllRecords();
}

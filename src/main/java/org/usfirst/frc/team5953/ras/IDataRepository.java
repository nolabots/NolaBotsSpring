package org.usfirst.frc.team5953.ras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usfirst.frc.team5953.domain.Data;

import java.util.List;

/**
 * Models the database itself. This interface is automatically implemented by Spring, filling in the signatures with implementations based on the names
 * @author scottrobertson
 *
 */
@Repository
public interface IDataRepository extends JpaRepository<Data, String> {
    List<Data> findByName(String name);

    List<Data> findById(long id);

}

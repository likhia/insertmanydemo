package com.ocbc.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ocbc.data.*;
import java.util.List;

public interface POCRepo extends MongoRepository<POCColl, String> {

    @Query("{ fld0: ?0}")
    List<POCColl> findByFld0(int fld0);

}
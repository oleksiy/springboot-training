package com.olexi.landon.data.repository;

/**
 * Created by oblavat on 4/8/17.
 */
import org.springframework.data.repository.PagingAndSortingRepository;

import com.olexi.landon.data.entity.Guest;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}

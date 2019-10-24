package com.learningprogramming.musicStore.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningprogramming.musicStore.db.entities.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer>{

	Artist findById(int id);
	Artist findByName(String name);
}

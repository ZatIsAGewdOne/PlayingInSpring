package com.learningprogramming.musicStore.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningprogramming.musicStore.db.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>{

	List<Album> findByArtistId(int id);
}

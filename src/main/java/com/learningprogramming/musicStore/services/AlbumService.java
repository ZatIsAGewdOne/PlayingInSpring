package com.learningprogramming.musicStore.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learningprogramming.musicStore.db.entities.Album;
import com.learningprogramming.musicStore.db.repositories.AlbumRepository;

@Service
public class AlbumService {

	private AlbumRepository albumRepository;
	
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}
	
	public Album getAlbumById(int id) {
		Album album = albumRepository.getOne(id);
		
		if (album == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return album;
	}
	
	public List<Album> fetchAlbumArtistJoin(int id) {
		return albumRepository.findByArtistId(id);
	}
}

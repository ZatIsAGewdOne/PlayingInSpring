package com.learningprogramming.musicStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learningprogramming.musicStore.db.entities.Artist;
import com.learningprogramming.musicStore.db.repositories.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	public Artist getArtistById(int id) {
		Artist artist = artistRepository.findById(id);
		if (artist == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return artist;
	}
	
	public Artist getArtistByName(String name) {
		Artist artist = artistRepository.findByName(name);
		if (artist == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return artist;
	}
	
	public List<Artist> getArtistsList() {
		return artistRepository.findAll();
	}
	
	public Page<Artist> getArtistsListPageable(int pageNo, int pageSize, String sortBy) {
		return artistRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
	}
	
	public Page<Artist> getArtistsListPageable(int pageNo, int pageSize) {
		return artistRepository.findAll(PageRequest.of(pageNo, pageSize));
	}
	
	public Artist create(Artist artist) {
		System.out.println("Trying to create " + artist.getName());
		Artist foundArtist = artistRepository.getOne(artist.getId());
		if (foundArtist != null) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Artist already in-use").getBody();
		
		return artistRepository.save(artist);
	}
	
	public void delete(Artist artist) {
		Artist foundArtist = artistRepository.findById(artist.getId());
		
		if (foundArtist == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		artistRepository.delete(foundArtist);
	}
}

package com.learningprogramming.musicStore.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningprogramming.musicStore.db.entities.Artist;
import com.learningprogramming.musicStore.services.ArtistService;

@RestController
@RequestMapping("artist")
public class ArtistAPI {
	private final ArtistService artistService;
	
	public ArtistAPI(ArtistService artistService) {
		this.artistService = artistService;
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Artist> getArtist(@PathVariable int id) {
		Artist artist = artistService.getArtistById(id);
		if (artist == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(artist);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Artist>> getAllArtists() {
		return ResponseEntity.ok(artistService.getArtistsList());
	}
	
	@GetMapping("listPage")
	public ResponseEntity<Iterable<Artist>> getArtistList(@RequestParam(required = false, defaultValue = "0") int pageNo,
										@RequestParam(required = false, defaultValue = "10") int pageSize,
										@RequestParam(required = false, defaultValue = "id") String sortBy) {
		return ResponseEntity.ok(artistService.getArtistsListPageable(pageNo, pageSize, sortBy));
	}
	
	@PostMapping(value = "create", 
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public @ResponseBody ResponseEntity<Artist> create(Artist artist) {
		System.out.println("Trying to create " + artist.getName());
		Artist foundArtist = artistService.getArtistByName(artist.getName());
		
		if (foundArtist != null) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Artist name already in use");
		
		return ResponseEntity.ok(artistService.create(artist));
	}
}

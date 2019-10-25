package com.learningprogramming.musicStore.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningprogramming.musicStore.db.entities.Artist;
import com.learningprogramming.musicStore.services.ArtistService;

@RestController
@RequestMapping("/api")
public class ArtistAPI {
	private final ArtistService artistService;
	
	public ArtistAPI(ArtistService artistService) {
		this.artistService = artistService;
	}
	
	@GetMapping("/artist")
	public ResponseEntity<Object> getArtistRequestParam(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name) {
		Artist foundArtist = findArtist(id, name);
		
		if (foundArtist == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(foundArtist);
	}
	
	@GetMapping("/artist/all")
	public ResponseEntity<List<Artist>> getAllArtists() {
		return ResponseEntity.ok(artistService.getArtistsList());
	}
	
	@GetMapping("/artist/listPage")
	public ResponseEntity<Iterable<Artist>> getArtistList(@RequestParam(required = false, defaultValue = "0") int pageNo,
										@RequestParam(required = false, defaultValue = "10") int pageSize,
										@RequestParam(required = false, defaultValue = "id") String sortBy) {
		return ResponseEntity.ok(artistService.getArtistsListPageable(pageNo, pageSize, sortBy));
	}
	
	@PostMapping(value = "/artist/create", 
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	@ResponseBody
	public ResponseEntity<Object> create(Artist artist) {
		System.out.println("Trying to create " + artist.getName());
		Artist foundArtistByName = artistService.getArtistByName(artist.getName());
		Artist foundArtistById = artistService.getArtistById(artist.getId());
		
		Artist newArtist = new Artist();
		
		if (foundArtistByName != null || foundArtistById != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Artist already in use");
		} else {
			newArtist.setId(artist.getId());
			newArtist.setName(artist.getName());
		}
		
		return ResponseEntity.ok(artistService.create(newArtist));
	}
	
	@DeleteMapping("/artist/delete")
	public ResponseEntity<Object> delete(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name) {
		Artist foundArtist = findArtist(id, name);
		
		if (foundArtist == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found!");
		}
		
		String foundArtistName = foundArtist.getName();
		
		artistService.delete(foundArtist);
		return ResponseEntity.status(200).body(foundArtistName + " was deleted");
	}
	
	@PutMapping(value = "/artist/update", consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> update(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestBody Artist artist) {
		System.out.println(artist.toString());
		
		Artist foundArtist = findArtist(id, name);
		Artist updatedArtist = new Artist();
		
		if (foundArtist == null) {
			return ResponseEntity.status(404).body("Artist to update not found");
		} else {
			updatedArtist.setId(foundArtist.getId());
		}
		
		updatedArtist.setName(artist.getName());
		
		return ResponseEntity.ok(artistService.create(updatedArtist));
	}
	
	private Artist findArtist(Integer id, String name) {
		Artist artist;
		
		if (id == null) {
			artist = artistService.getArtistByName(name);
		} else {
			artist = artistService.getArtistById(id);
		}
		
		return artist;
	}
}

package br.com.jobtechIO.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(String userId, MultipartFile file);

	Stream<Path> loadAll(String userId);

	Path load(String userId, String filename);

	Resource loadAsResource(String userId, String filename);

	void deleteAll(String userId);

}
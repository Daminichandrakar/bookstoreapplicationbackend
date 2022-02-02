package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.property.FileStorageProperty;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class AdminBookService implements IAdminBookService {

    private Path fileStorageLocation;

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Autowired
    FileStorageProperty fileStorageProperty;

    @Override
    public BookDetails addBook(BookDTO bookDTO) {
        BookDetails bookDetails = new BookDetails(bookDTO);
        Optional<BookDetails> byBookName = bookStoreRepository.findBybookName(bookDTO.bookName);
        BookDetails info = bookStoreRepository.save(bookDetails);
        return info;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            this.fileStorageLocation = Paths.get(fileStorageProperty.getUploadDir())
                    .toAbsolutePath()
                    .normalize();
            Files.createDirectories(this.fileStorageLocation);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                throw new BookStoreException(BookStoreException.ExceptionType.INVALID_FILE_NAME, "INVALID_FILE_NAME");
            }
            fileName = (id+"-"+fileName);
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            String imageResponseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("bookstore/image/")
                    .path(fileName)
                    .toUriString();
            return imageResponseUrl;
        } catch (IOException ex) {
            throw new BookStoreException(BookStoreException.ExceptionType.FILE_NOT_STORED, "FILE_NOT_STORE");
        } catch (NullPointerException n) {
            throw new BookStoreException(BookStoreException.ExceptionType.DIRECTORY_NOT_FOUND, "DIRECTORY_NOT_FOUND");
        }
    }


    String line = "";
    public String  loadCSV(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new
                    FileReader("src/main/resources/books_data.csv"));
            while ((line=bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                BookDetails bookEntity = new BookDetails();
                bookEntity.setAuthorName(data[1].replaceAll("'", ""));
                bookEntity.setBookName(data[2].replaceAll("'", ""));
                bookEntity.setNoOfCopies(Double.parseDouble(data[3]));
                bookEntity.setBookImageSrc(data[4]);
                bookEntity.setBookPrice(Double.parseDouble(data[5]));
                IntStream.range(7, data.length - 1).forEach(column -> data[6] += "," + data[column]);
                bookEntity.setBookDetail(data[6]);
                bookStoreRepository.save(bookEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Added";
    }
}

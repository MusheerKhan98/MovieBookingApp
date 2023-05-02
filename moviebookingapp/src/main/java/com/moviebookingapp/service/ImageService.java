package com.moviebookingapp.service;

import com.moviebookingapp.model.ImageModel;
import com.moviebookingapp.repository.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        ImageModel imageModel = new ImageModel();
        imageModel.setTitle(title);
        imageModel.setImage(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
        imageModel = imageRepository.insert(imageModel);
        return imageModel.getId();
    }
    public ImageModel getPhoto(String id){
        return imageRepository.findById(id).get();
    }
}

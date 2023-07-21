package com.damo.gestionDeStock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;

@Api("photos")
public interface PhotoApi {

    @PostMapping(APP_ROOT + "/save/{id}/{titre}/{context}")
    Object savePhoto(@PathVariable("context") String context, @PathVariable("id") Integer id, @RequestPart("file") MultipartFile photo,
                     @PathVariable("titre") String titre) throws IOException, FlickrException;
}

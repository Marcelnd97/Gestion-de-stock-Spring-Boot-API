package com.damo.gestionDeStock.service.impl.strategy;

import com.damo.gestionDeStock.dto.ArticleDto;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.service.ArticleService;
import com.damo.gestionDeStock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto> {

    private FlickrService flickrService;
    private ArticleService articleService;

    @Autowired
    public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {

        //La methode ci-dessous nous assure que l'article existe si non elle renvoie une exception.

        ArticleDto article = articleService.findById(id);

        //On fait appelle au service flickr
        //cette objet renvoie l'url de la photo

        String urlPhoto = flickrService.savePhoto(photo, titre);

        //Une fois la photo est enregistrée
        //On effectue ce teste
        if (!StringUtils.hasLength(urlPhoto)){
            throw new InvalideOperationException("Erreur lors de l'enregistrement de la photo de l'article",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        article.setPhoto(urlPhoto);
        return articleService.save(article);
    }
}

package pl.kafarsoon.craftymarkdown.controller;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kafarsoon.craftymarkdown.feature.image.converter.dto.ImageDTO;
import pl.kafarsoon.craftymarkdown.feature.image.converter.service.ImageConverter;

@RequestMapping("/image")
@RestController
public class ImageController {

    private final ImageConverter imageConverter;

    public ImageController(ImageConverter imageConverter) {
        this.imageConverter = imageConverter;
    }

    @PostMapping
    public String convertImage(@RequestBody ImageDTO imageDTO) throws TesseractException {
        return imageConverter.getImageToText(imageDTO);
    }
}

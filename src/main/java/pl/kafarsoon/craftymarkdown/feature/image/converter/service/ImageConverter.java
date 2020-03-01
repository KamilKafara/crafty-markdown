package pl.kafarsoon.craftymarkdown.feature.image.converter.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kafarsoon.craftymarkdown.feature.image.converter.dto.ImageDTO;

import java.io.File;

@Service
public class ImageConverter {
    @Value("${trained.data.path}")
    private String trainedDataPath;

    public String getImageToText(ImageDTO imageDTO) throws TesseractException {
        ITesseract instance = new Tesseract();
        instance.setDatapath(trainedDataPath);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(trainedDataPath);
        return tesseract.doOCR(new File(imageDTO.getUrl()));
    }
}

package pl.kafarsoon.craftymarkdown.feature.file.loader.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private String filename;
    private String extension;
    private String contextType;
    private long size;
    private List<List<String>> context;
}

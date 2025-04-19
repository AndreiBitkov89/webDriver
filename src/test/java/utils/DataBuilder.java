package utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataBuilder {
    private String text;
    private String pass;
    private String longText;
}

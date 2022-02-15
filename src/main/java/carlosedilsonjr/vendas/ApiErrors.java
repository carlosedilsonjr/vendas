package carlosedilsonjr.vendas;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class ApiErrors {
    
    @Getter
    private List<String> errors;

    public ApiErrors(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }
}

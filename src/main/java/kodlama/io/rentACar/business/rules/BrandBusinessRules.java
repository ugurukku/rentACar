package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {

    private final BrandRepository brandRepository;

    public void checkIfBrandNameExists(String brandName){
        if (brandRepository.existsByName(brandName)){

        }
    }


}

package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.core.utilities.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final ModelMapperService mapperService;

    private BrandRepository brandRepository;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();

        return brands
                .stream()
                .map(brand -> mapperService.forResponse().map(brand, GetAllBrandsResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public GetByIdBrandResponse getBrandById(int id) {
        return mapperService
                .forResponse()
                .map(brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found!"))
                        , GetByIdBrandResponse.class);
    }

    @Override
    public void updateBrand(UpdateBrandRequest updateBrandRequest) {
        boolean exist = brandRepository.existsById(updateBrandRequest.getId());
        if (!exist) {
            throw new IllegalArgumentException("not found!");
        }
        brandRepository.save(mapperService.forRequest().map(updateBrandRequest, Brand.class));
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        brandRepository.save(mapperService.forRequest().map(createBrandRequest, Brand.class));
    }

    @Override
    public void deleteBrandById(int id) {
        boolean exist = brandRepository.existsById(id);

        if (!exist) {
            throw new IllegalArgumentException("not found!");
        } else {
            brandRepository.deleteById(id);
        }


    }
}

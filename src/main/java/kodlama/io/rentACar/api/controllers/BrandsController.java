package kodlama.io.rentACar.api.controllers;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private final BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping()
    public List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdBrandResponse getById(@PathVariable(value = "id") int id){
        return brandService.getBrandById(id);
    }

    @PutMapping
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest){
        brandService.updateBrand(updateBrandRequest);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@Valid CreateBrandRequest brandRequest) {
        this.brandService.add(brandRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        brandService.deleteBrandById(id);
    }

}

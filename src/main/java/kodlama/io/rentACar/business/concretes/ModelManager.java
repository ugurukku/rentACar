package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.core.utilities.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private ModelRepository repository;
    private ModelMapperService mapperService;

    @Override
    public List<GetAllModelsResponse> getAll() {
        return repository
                .findAll()
                .stream()
                .map(brand -> mapperService.forResponse().map(brand, GetAllModelsResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        repository
                .save(mapperService
                        .forRequest()
                        .map(createModelRequest, Model.class)
                );
    }
}

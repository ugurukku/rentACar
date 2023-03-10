package kodlama.io.rentACar.core.utilities;

import org.modelmapper.ModelMapper;

import java.util.function.Function;

public interface ModelMapperService  {

    ModelMapper forResponse();
    ModelMapper forRequest();

}

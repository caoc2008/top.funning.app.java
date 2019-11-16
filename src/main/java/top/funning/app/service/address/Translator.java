package top.funning.app.service.address;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.funning.app.database.table.Address;
import top.funning.app.service.address.getPcd.C1029;

import java.util.List;

@Mapper
public interface Translator {
    Translator INSTANCES = Mappers.getMapper(Translator.class);

    ReturnEntity toAddress(Address address);

    List<ReturnEntity> toAddressList(List<Address> address);

    C1029.Data.Province toProvince(C1029.Province.RowsBean p);

    C1029.Data.Province.City toCity(C1029.City.RowsBean cBean);

    C1029.Data.Province.City.District toDistrict(C1029.District.RowsBean dBean);
}

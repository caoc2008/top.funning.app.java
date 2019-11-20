package top.funning.app.service.identify.login.android;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import top.funning.app.database.table.User;

@Mapper
public interface Translator {

    Translator INSTANCES = Mappers.getMapper(Translator.class);

    C1021.Data toUser(User user);
}

package com.example.practice.model.mapper;

import com.example.practice.view.userview.UserIdView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperFacadeImpl implements CustomMapperFacade {
    @Autowired
    @Qualifier("userMapperFactoryBean")
    private MapperFactory mapperFactory;

    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        if (destinationClass == UserIdView.class) {
            mapperFactory.classMap(sourceObject.getClass(), destinationClass)
                    .exclude("version")
                    .field("userDoc.docId.name", "docName")
                    .field("userDoc.docNumber", "docNumber")
                    .field("userDoc.docDate", "docDate")
                    .field("countryId.name", "citizenshipName")
                    .field("countryId.code", "citizenshipCode")
                    .byDefault().register();
        } else {
            mapperFactory.classMap(sourceObject.getClass(), destinationClass)
                    .exclude("version")
                    .byDefault().register();
        }
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        if (destinationObject.getClass() == UserIdView.class) {
            mapperFactory.classMap(sourceObject.getClass(), destinationObject.getClass())
                    .exclude("version")
                    .field("userDoc.docId.name", "docName")
                    .field("userDoc.docNumber", "docNumber")
                    .field("userDoc.docDate", "docDate")
                    .field("countryId.name", "citizenshipName")
                    .field("countryId.code", "citizenshipCode")
                    .byDefault().register();
        } else {
            mapperFactory.classMap(sourceObject.getClass(), destinationObject.getClass())
                    .exclude("version")
                    .byDefault().register();
        }
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        mapperFactory.classMap(source.getClass(), destinationClass)
                .exclude("version").byDefault().register();
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }
}

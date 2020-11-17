package com.example.practice.service;

import com.example.practice.customexception.WrongPropertyException;
import com.example.practice.daointerface.*;
import com.example.practice.view.officeview.OfficeListFilterDto;
import com.example.practice.view.userview.*;
import com.example.practice.model.*;
import com.example.practice.model.mapper.MapperFacade;
import com.example.practice.serviceinterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final CustomRepository<UserListFilterDto, UserInfo> userInfoRepository;
    private final CountryRepository countryRepository;
    private final DocRepository docRepository;
    private final CustomRepository<OfficeListFilterDto, Office> officeRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserInfoServiceImpl(CustomRepository<UserListFilterDto, UserInfo> userInfoRepository,
                               CountryRepository countryRepository, DocRepository docRepository,
                               CustomRepository<OfficeListFilterDto, Office> officeRepository,
                               MapperFacade mapperFacade) {
        this.userInfoRepository = userInfoRepository;
        this.countryRepository = countryRepository;
        this.docRepository = docRepository;
        this.officeRepository = officeRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<UserListView> findList(UserListFilterDto userDto) {
        List<UserInfo> result = userInfoRepository.findList(userDto);
        if (result.isEmpty()){
            throw new NoSuchElementException("Нет элементов удовлетворяющих данному запросу");
        }
        return mapperFacade.mapAsList(result, UserListView.class);
    }

    @Override
    public UserIdView findById(int id) {
        UserInfo user = userInfoRepository.findById(id);
        return mapperFacade.map(user, UserIdView.class);
    }

    @Override
    public void save(UserSaveDto dto) {
        UserInfo user = mapperFacade.map(dto, UserInfo.class);
        Office office = officeRepository.findById(dto.getOfficeId());
        user.setOffice(office);
        if (dto.getCitizenshipCode()!=null) {
            Country country = countryRepository.findByCode(dto.getCitizenshipCode());
            user.setCountry(country);
        }
        if (dto.getDocCode()!=null) {
            Doc doc = docRepository.findByCode(dto.getDocCode());
            UserDoc userDoc = new UserDoc(dto.getDocNumber(), dto.getDocDate());
            userDoc.setDoc(doc);
            user.setUserDoc(userDoc);
            userDoc.setUserInfo(user);
            if (!doc.getName().equals(dto.getDocName())) {
                throw new WrongPropertyException("Doc name and Doc code not equals.");
            }
        }
        userInfoRepository.save(user);
    }

    @Override
    public void update(UserUpdateDto dto) {
        UserInfo user = userInfoRepository.findById(dto.getId());
        mapperFacade.map(dto, user);
        if (dto.getOfficeId()!=null) {
            Office office = officeRepository.findById(dto.getOfficeId());
            user.setOffice(office);
        }
        if (dto.getDocName()!=null) {
            Doc doc = docRepository.findByName(dto.getDocName());
            UserDoc userDoc = user.getUserDoc();
            userDoc.setDocDate(Date.valueOf(dto.getDocDate()));
            userDoc.setDoc(doc);
            userDoc.setDocNumber(dto.getDocNumber());
            user.setUserDoc(userDoc);
        }
        if (dto.getCitizenshipCode()!=null) {
            Country country = countryRepository.findByCode(dto.getCitizenshipCode());
            user.setCountry(country);
        }
    }
}

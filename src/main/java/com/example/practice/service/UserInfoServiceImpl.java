package com.example.practice.service;

import com.example.practice.daointerface.*;
import com.example.practice.view.officeview.OfficeListFilterDto;
import com.example.practice.view.userview.*;
import com.example.practice.model.*;
import com.example.practice.model.mapper.MapperFacade;
import com.example.practice.serviceinterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            List<UserInfo> result = userInfoRepository.findList(userDto);
            if (result.isEmpty()){
                throw new NoSuchElementException("Нет элементов удовлетворяющих данному запросу");
            }
            return mapperFacade.mapAsList(result, UserListView.class);
        }catch (NoSuchElementException e){
            throw new RuntimeException("Нет элементов удовлетворяющих данному запросу", e);
        }
    }

    @Override
    public UserIdView findById(int id) {
        try {
            UserInfo user = userInfoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Нет элементов удовлетворяющих данному запросу"));
            return mapperFacade.map(user, UserIdView.class);
        }catch (NoSuchElementException e){
            throw new RuntimeException("Нет элементов удовлетворяющих данному запросу", e);
        }
    }

    @Override
    public void save(UserSaveDto dto) {
        try {
            UserInfo user = mapperFacade.map(dto, UserInfo.class);
            Office office = officeRepository.findById(dto.getOfficeId())
                    .orElseThrow(() -> new NoSuchElementException("Нет элементов удовлетворяющих данному запросу"));
            user.setOfficeId(office);
            Country country = countryRepository.findByCode(dto.getCitizenshipCode());
            user.setCountryId(country);
            Doc doc = docRepository.findByCode(dto.getDocCode());
            if (!doc.getName().equals(dto.getDocName())) {
                throw new IllegalStateException("Doc name and Doc code not equals.");
            }
            UserDoc userDoc = new UserDoc(dto.getDocNumber(), dto.getDocDate(), doc);
            userDoc.setUserInfo(user);
            user.setUserDoc(userDoc);
            userInfoRepository.save(user);
        }catch (NoSuchElementException e){
            throw new RuntimeException("Нет элементов удовлетворяющих данному запросу", e);
        }catch (IllegalStateException e){
            throw new RuntimeException("Doc name and Doc code not equals.", e);
        }
    }

    @Override
    public void update(UserUpdateDto dto) {
        try {
            UserInfo user = userInfoRepository.findById(dto.getId())
                    .orElseThrow(() -> new NoSuchElementException("No organization entity by 'id'  " + dto.getId()));
            mapperFacade.map(dto, user);
            Office office = officeRepository.findById(dto.getOfficeId())
                    .orElseThrow(() -> new NoSuchElementException("Нет элементов удовлетворяющих данному запросу"));
            user.setOfficeId(office);
            Doc doc = docRepository.findByName(dto.getDocName());
            UserDoc userDoc = user.getUserDoc();
            userDoc.setDocDate(dto.getDocDate());
            userDoc.setDocId(doc);
            userDoc.setDocNumber(dto.getDocNumber());
            user.setUserDoc(userDoc);
            Country country = countryRepository.findByCode(dto.getCitizenshipCode());
            user.setCountryId(country);
            userInfoRepository.save(user);
        }catch (NoSuchElementException e){
            throw new RuntimeException("Нет элементов удовлетворяющих данному запросу", e);
        }
    }
}

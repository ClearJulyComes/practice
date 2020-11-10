package com.example.practice.service;

import com.example.practice.daointerface.*;
import com.example.practice.view.officeview.OfficeListFilterDto;
import com.example.practice.view.userview.*;
import com.example.practice.model.*;
import com.example.practice.model.mapper.CustomMapperFacade;
import com.example.practice.serviceinterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private CustomRepository<UserListFilterDto, UserInfo> userInfoRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private DocRepository docRepository;
    @Autowired
    private CustomRepository<OfficeListFilterDto, Office> officeRepository;

    @Autowired
    @Qualifier("userMapperFacadeImpl")
    private CustomMapperFacade mapperFacade;

    @Override
    public List<UserListView> findList(UserListFilterDto userDto) {
        List<UserInfo> result = userInfoRepository.findList(userDto)
                .orElseThrow(() -> new NoSuchElementException("Нет элементов удовлетворяющих данному запросу"));
        return mapperFacade.mapAsList(result, UserListView.class);
    }

    @Override
    public UserIdView findById(int id) {
        UserInfo user = userInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Нет элементов удовлетворяющих данному запросу"));
        return mapperFacade.map(user, UserIdView.class);
    }

    @Override
    public void save(UserSaveDto dto) throws NoSuchElementException {
        UserInfo user = mapperFacade.map(dto, UserInfo.class);
        Office office = officeRepository.findById(dto.getOfficeId())
                .orElseThrow(() -> new NoSuchElementException("Нет элементов удовлетворяющих данному запросу"));
        user.setOfficeId(office);
        Country country = countryRepository.findByCode(dto.getCitizenshipCode());
        user.setCountryId(country);
        Doc doc = docRepository.findByCode(dto.getDocCode());
        if (!doc.getName().equals(dto.getDocName())) {
            throw new NoSuchElementException("Doc name and Doc code not equals.");
        }
        UserDoc userDoc = new UserDoc(dto.getDocNumber(), dto.getDocDate(), doc);
        userDoc.setUserInfo(user);
        user.setUserDoc(userDoc);
        userInfoRepository.save(user);
    }

    @Override
    public void update(UserUpdateDto dto) {
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
    }
}

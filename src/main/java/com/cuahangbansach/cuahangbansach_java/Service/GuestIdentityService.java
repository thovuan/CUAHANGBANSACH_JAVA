package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Repository.GuestIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestIdentityService {
    @Autowired
    private GuestIdentityRepository guestIdentityRepository;

    public KHACH FindByGuest(String Username) {
        return guestIdentityRepository.GetByUsername(Username);
    }

    public void Save(KHACH khach) {
        guestIdentityRepository.save(khach);
    }
}

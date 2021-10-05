package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.entity.User;
import com.omniteam.backofisbackend.enums.EnumLogIslemTipi;

public interface LogService {

    public void loglama(EnumLogIslemTipi islemTip, User user);
}

package org.apache.cloudstack.lifespan;

import com.cloud.uservm.UserVm;

import java.util.Date;

/**
 * Created by D063169 on 06.07.2017.
 */
public interface LifespanService {
    void updateExpirationDate(UserVm vm, Date date);

}

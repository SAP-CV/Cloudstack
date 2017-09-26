package org.apache.cloudstack.expiry;

import com.cloud.uservm.UserVm;
import com.cloud.utils.component.ManagerBase;
import com.cloud.utils.concurrency.NamedThreadFactory;
import com.cloud.vm.UserVmVO;
import com.cloud.vm.dao.UserVmDao;
import org.apache.cloudstack.managed.context.ManagedContextRunnable;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class InstanceExpirationServiceImpl extends ManagerBase implements InstanceExpirationService {

    @Inject
    UserVmDao userVmDao;
    protected ScheduledExecutorService _executor = null;

    Integer runInterval = 3 ;//new ConfigKey<Integer>( );
    @Override
    public void updateExpirationDate(UserVm vm, Date date) {
        _executor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("LifeSpan-Enforcer"));
    }

    @Override
    public boolean start() {
        _executor.scheduleWithFixedDelay(new InstanceExpirationEnforcer(), runInterval, runInterval*runInterval, TimeUnit.HOURS);
        return true;
    }

    public class InstanceExpirationEnforcer extends ManagedContextRunnable {
        @Override
        protected void runInContext() {
            // TODO Put in Tasks here
            
            sendNotifications();
            // lcm.ShutdownExpired(true);
            // lcm.DestroyExpired();
            
        }
        
    }

    private void sendNotifications() {
        List<UserVmVO> vmList = userVmDao.getExpiresOn(InstanceExpirationNotificationInterval.value(), InstanceExpirationNotificationQuantity.value());
    }


}

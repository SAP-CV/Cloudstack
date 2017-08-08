package org.apache.cloudstack.lifespan;

import com.cloud.uservm.UserVm;
import com.cloud.utils.component.ManagerBase;
import com.cloud.utils.concurrency.NamedThreadFactory;
import org.apache.cloudstack.framework.config.ConfigKey;
import org.apache.cloudstack.managed.context.ManagedContextRunnable;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by D063169 on 06.07.2017.
 */
public class LifespanServiceImpl extends ManagerBase implements LifespanService {

    protected ScheduledExecutorService _executor = null;

    Integer runInterval = 3 ;//new ConfigKey<Integer>( );
    @Override
    public void updateExpirationDate(UserVm vm, Date date) {
        _executor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("LifeSpan-Enforcer"));
    }


    @Override
    public boolean start() {
        _executor.scheduleWithFixedDelay(new LifespanEnforcer(), runInterval, runInterval*runInterval, TimeUnit.HOURS);
        return true;
    }

        /**
         * Created by d063488 on 07.07.17.
         */
    public class LifespanEnforcer extends ManagedContextRunnable {
        @Override
        protected void runInContext() {
            // TODO Put in Tasks here
        }
    }
}

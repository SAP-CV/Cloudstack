package org.apache.cloudstack.expiry;

import com.cloud.uservm.UserVm;
import org.apache.cloudstack.framework.config.ConfigKey;

import java.sql.Time;
import java.util.Date;

public interface InstanceExpirationService {
    void updateExpirationDate(UserVm vm, Date date);

    static final ConfigKey<Boolean> InstanceExpirationEnabled = new ConfigKey<Boolean>("Advanced", Boolean.class, "instance.expiration.enabled", "false",
            "Determines whether instance expiration is enabled", false, ConfigKey.Scope.Global);

    static final ConfigKey<Integer> InstanceExpirationLifespan = new ConfigKey<Integer>("Advanced", Integer.class, "instance.expiration.lifespan", "259200",
            "Instance lifespan in minutes", true, ConfigKey.Scope.Global);

    static final ConfigKey<Integer> InstanceExpirationNotificationInterval = new ConfigKey<Integer>("Advanced", Integer.class, "instance.expiration.notificationInterval", "4320",
            "Instance expiration notification interval in minutes", true, ConfigKey.Scope.Global);

    static final ConfigKey<Integer> InstanceExpirationNotificationFrequency = new ConfigKey<Integer>("Advanced", Integer.class, "instance.expiration.notificationFrequency", "4320",
            "Instance expiration notification frequency in minutes", true, ConfigKey.Scope.Global);

    static final ConfigKey<Integer> InstanceExpirationGracePeriod = new ConfigKey<Integer>("Advanced", Integer.class, "instance.expiration.gracePeriod", "4320",
            "Instance expiration grace period: time in minutes before an expired instance is being destroyed", true, ConfigKey.Scope.Global);

    static final ConfigKey<Integer> InstanceExpirationInterval = new ConfigKey<Integer>("Advanced", Integer.class, "instance.expiration.interval", "1",
            "Interval in minutes when instance expiration manager checks for expired instances", false, ConfigKey.Scope.Global);

    static final ConfigKey<Time> InstanceExpirationStartTime = new ConfigKey<Time>("Advanced", Time.class, "instance.expiration.starttime","00:30",
            "The time at which the instance expiration manager job will run as an HH24:MM time, e.g. 00:30 to run at 12:30am.", false, ConfigKey.Scope.Global);

}

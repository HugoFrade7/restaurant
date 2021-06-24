package pt._25friday.restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UptimeController {

    private static final long appStartTime = System.currentTimeMillis();

    @GetMapping(value = "api/uptime", produces = "application/json")
    public Map<String, String> getVersionProps() {
        return Collections.singletonMap("uptime", getUptimeString());
    }

    private String getUptimeString() {
        long uptimeSecs = (System.currentTimeMillis() - appStartTime) / 1000;

        long secs = uptimeSecs % 60;
        long minutes = uptimeSecs % 3600 / 60;
        long hours = uptimeSecs % 86400 / 3600;
        long days = uptimeSecs / 86400;

        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, secs);
    }
}

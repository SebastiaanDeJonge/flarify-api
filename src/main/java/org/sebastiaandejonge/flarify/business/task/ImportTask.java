package org.sebastiaandejonge.flarify.business.task;

import org.sebastiaandejonge.flarify.business.model.SolarFlareModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImportTask {

    private SolarFlareModel solarFlareModel;

    public ImportTask(SolarFlareModel solarFlareModel) {
        this.solarFlareModel = solarFlareModel;
    }

    @Scheduled(fixedRate = 300000)
    public void updateSolarFlares() {
        solarFlareModel.updateCache();
    }
}

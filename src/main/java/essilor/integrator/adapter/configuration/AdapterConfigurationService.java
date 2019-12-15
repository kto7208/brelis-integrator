package essilor.integrator.adapter.configuration;

import essilor.integrator.adapter.dao.ConfDao;
import essilor.integrator.adapter.datasource.DataSourceNameHolder;
import essilor.integrator.adapter.domain.AdapterConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdapterConfigurationService {

    @Autowired
    private ConfDao confDao;

    public Map<String, AdapterConfigInfo> getAdapterConfiguration(String dataSources) {
        if (dataSources == null) {
            throw new IllegalStateException("dataSources is null");
        }
        String[]  dsa = dataSources.split(",");
        if (dsa == null || dsa.length == 0) {
            throw new IllegalStateException("no data sources defined");
        }

        Map<String, AdapterConfigInfo> map = new HashMap<>();
        for (String ds : dsa) {
            DataSourceNameHolder.setDataSourceName(ds);
            Map<String, AdapterConfigInfo> m = confDao.getAdapterConfigInfo();
            for (Map.Entry<String, AdapterConfigInfo> e : m.entrySet()) {
                map.put(ds + "-" + e.getKey(), e.getValue());
            }
        }
        return map;
    }
}

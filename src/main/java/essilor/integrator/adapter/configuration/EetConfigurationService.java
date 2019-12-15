package essilor.integrator.adapter.configuration;

import essilor.integrator.adapter.dao.ConfDao;
import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EetConfigurationService {

    @Autowired
    private ConfDao confDao;

    public Map<String, EetConfigInfo> getEetConfiguration() {
        return confDao.getEetConfig();
    }

    public String getEetUri() {
        return confDao.getEetUri();
    }

    public String getEetKeystoreType() {
        return confDao.getEetKeystoreType();
    }

}

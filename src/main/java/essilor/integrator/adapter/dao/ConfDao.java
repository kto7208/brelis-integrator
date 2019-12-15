package essilor.integrator.adapter.dao;

import essilor.integrator.adapter.domain.AdapterConfigInfo;
import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import essilor.integrator.adapter.tools.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConfDao {

    private static final String SQL = "select val from conf_ini where var = ?";

    private static final String GET_ORGANISATION_SQL = "select organizace from c_prevadzky where kod=?";

    private static final String GET_CITY_SQL = "select mesto from c_prevadzky where kod=?";

    private static final String GET_STREET_SQL = "select ulice from c_prevadzky where kod=?";

    private static final String GET_ZIP_SQL = "select psc from c_prevadzky where kod=?";

    private static final String GET_EET_CONFIG_SQL = "select kod,ico,dic,id_provozovny,id_pokl,eet_keystore_path,eet_keystore_pwd,eet_key_alias,dic_poverujiciho from c_prevadzky";

    private static final String GET_ADAPTER_CONFIG_SQL = "select kod,web_adapter_user,web_adapter_pwd,web_adapter_refid,web_adapter_locale,web_adapter_originator,web_adapter_shipto from c_prevadzky";

    @Autowired
	private Encryptor encryptor;

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Value("${adapter.software.originator.name}")
    private String softwareOriginatorName;

    @Value("${adapter.software.sender.name}")
    private String softwareSenderName;

    @Value("${adapter.software.originator.version}")
    private String softwareOriginatorVersion;

    @Value("${adapter.software.sender.version}")
    private String softwareSenderVersion;

	@PostConstruct
	public void init() {
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

    public String getSoftwareOriginatorName() {
        return softwareOriginatorName;
    }

    public String getSoftwareOriginatorVersion() {
        return softwareOriginatorVersion;
    }

    public String getSoftwareSenderName() {
        return softwareSenderName;
    }

    public String getSoftwareSenderVersion() {
        return softwareSenderVersion;
    }

    public String getOrganisation(String branchCode) {
		return jdbcTemplate.queryForObject(GET_ORGANISATION_SQL, String.class, branchCode);
	}

	public String getCity(String branchCode) {
		return jdbcTemplate.queryForObject(GET_CITY_SQL, String.class, branchCode);
	}

	public String getStreet(String branchCode) {
		return jdbcTemplate.queryForObject(GET_STREET_SQL, String.class, branchCode);
	}

	public String getZipCode(String branchCode) {
		return jdbcTemplate.queryForObject(GET_ZIP_SQL, String.class, branchCode);
	}

	public String getOrdersDir() {
		return jdbcTemplate.queryForObject(SQL, String.class, "OBJEDNAVKY_PDF_DIR");
	}


	public String getEetUri() {
		return jdbcTemplate.queryForObject(SQL, String.class, "EET_URI");
	}

	public String getEetKeystoreType() {
		return jdbcTemplate.queryForObject(SQL, String.class, "EET_KEYSTORE_TYPE");
	}

	public void setSoftwareSenderName(String softwareSenderName) {
		this.softwareSenderName = softwareSenderName;
	}

	public Map<String, EetConfigInfo> getEetConfig() {
		Map<String, EetConfigInfo> eetConfig = new HashMap<String, EetConfigInfo>();
		List<Map<String, Object>> rows =  jdbcTemplate.queryForList(GET_EET_CONFIG_SQL);
		for (Map<String, Object> row : rows) {
			EetConfigInfo eetConfigInfo = new EetConfigInfo();
			eetConfigInfo.setKod((String) row.get("kod"));
			eetConfigInfo.setIco((String) row.get("ico"));
			eetConfigInfo.setDic((String) row.get("dic"));
			eetConfigInfo.setId_provoz((String) row.get("id_provozovny"));
            eetConfigInfo.setId_pokl((String) row.get("id_pokl"));
            eetConfigInfo.setKeystorePath((String) row.get("eet_keystore_path"));
			eetConfigInfo.setKeyAlias((String) row.get("eet_key_alias"));
			eetConfigInfo.setDic_poverujuceho((String) row.get("dic_poverujiciho"));
			try {
				String password = encryptor.decrypt((String) row.get("eet_keystore_pwd"));
				eetConfigInfo.setKeystorePwd(password);
			} catch(Exception e) {
            	throw new RuntimeException(e);
			}
            eetConfig.put(eetConfigInfo.getKod(),eetConfigInfo);
		}
		return eetConfig;
	}

	public Map<String, AdapterConfigInfo> getAdapterConfigInfo() {
		Map<String, AdapterConfigInfo> adapterConfig = new HashMap<String, AdapterConfigInfo>();
		List<Map<String, Object>> rows =  jdbcTemplate.queryForList(GET_ADAPTER_CONFIG_SQL);
		for (Map<String, Object> row : rows) {
			AdapterConfigInfo adapterConfigInfo = new AdapterConfigInfo();
			adapterConfigInfo.setKod((String) row.get("kod"));
			adapterConfigInfo.setUser((String) row.get("web_adapter_user"));
			adapterConfigInfo.setRefid((String) row.get("web_adapter_refid"));
			adapterConfigInfo.setLocale((String) row.get("web_adapter_locale"));
			adapterConfigInfo.setOriginator((String) row.get("web_adapter_originator"));
			adapterConfigInfo.setShipto((String) row.get("web_adapter_shipto"));
			try {
			    String p = (String) row.get("web_adapter_pwd");
			    if (p != null && !p.isEmpty()) {
                    adapterConfigInfo.setPassword(encryptor.decrypt(p));
                }
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			adapterConfig.put(adapterConfigInfo.getKod(),adapterConfigInfo);
		}
		return adapterConfig;
	}
}

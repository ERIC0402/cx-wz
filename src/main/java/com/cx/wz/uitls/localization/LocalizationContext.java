package com.cx.wz.uitls.localization;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class LocalizationContext {

    @Value("${platform.sulu.local}")
    private SuluLocale suluLocale;

    private ZoneId zoneId;

    // 事实不可变对象
    private List<ThirdPartyServiceEnum> joinUpServices = new ArrayList<>();

    @PostConstruct
    private void init() {
        switch (suluLocale) {
            case ID:
                zoneId = ZoneId.systemDefault();// 印尼横跨了三个时区，目前部署在+7时区
                break;
            case PH:
                joinUpServices.add(ThirdPartyServiceEnum.SKYPAY);
                joinUpServices.add(ThirdPartyServiceEnum.DRAGONPAY);
                zoneId = ZoneId.of("GMT+08:00");
                break;
            case VN:
                zoneId = ZoneId.of("GMT+07:00");
                break;
            case TH:
                zoneId = ZoneId.of("GMT+07:00");
                break;
            case NG:
                zoneId = ZoneId.of("GMT+01:00");
                break;
            case MY:
                zoneId = ZoneId.of("GMT+08:00");
                break;
            case ZH:
                zoneId = ZoneId.of("GMT+08:00");
                break;
            case IN:
                zoneId = ZoneId.of("Asia/Kolkata");
                break;
            default:
                zoneId = ZoneId.systemDefault();
                break;
        }
    }

    public ZoneId getLocaleZone(){
        return ZoneId.of(zoneId.toString());
    }

    public SuluLocale getSuluLocale(){
    	return this.suluLocale;
    }

    public Currency defaultCurrency() {
        return suluLocale.getCurrency();
    }

    public CredentialType defaultCredentialType() {
        return suluLocale.getCredentialType().get(0);
    }

    public Locale defaultLocale() {
        return suluLocale.getLocale();
    }

    public String getDefaultCallingCode() {
        return suluLocale.getCallingCode();
    }

    /**
	 * 返回是否包含了此第三方服务
	 */
	public boolean isJoinUp(ThirdPartyServiceEnum thridPartyServiceEnum) {
		return joinUpServices.contains(thridPartyServiceEnum);
	}

	public List<CredentialType> getCredentialTypes() {
		return suluLocale.getCredentialType();
	}

	public List<ThirdPartyServiceEnum> getJoinUpServices() {
		return Lists.newArrayList(this.joinUpServices);
	}

}


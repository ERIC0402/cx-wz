package com.cx.wz.uitls.device;

public class GA {
    private String source;
    private String medium;
    private String term;
    private String content;
    private String campaign;
    private String gclid;
    private String partnerId;
    private String partnerClickId;
    private String referrer;

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getGclid() {
        return gclid;
    }

    public void setGclid(String gclid) {
        this.gclid = gclid;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerClickId() {
        return partnerClickId;
    }

    public void setPartnerClickId(String partnerClickId) {
        this.partnerClickId = partnerClickId;
    }

    @Override
    public String toString() {
        return "GA{" +
                "source='" + source + '\'' +
                ", medium='" + medium + '\'' +
                ", term='" + term + '\'' +
                ", content='" + content + '\'' +
                ", campaign='" + campaign + '\'' +
                ", gclid='" + gclid + '\'' +
                ", partnerId=" + partnerId +
                ", partnerClickId='" + partnerClickId + '\'' +
                '}';
    }
}

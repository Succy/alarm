package cn.succy.alarm.receiver;

/**
 * 联系人
 * 主要包括要发送的联系人的各种账号信息
 *
 * @author Succy
 * @date 2017-10-13 16:43
 **/

public class Contact {
    private String contactId;
    private String name;
    private String email;
    private String phone;
    private String wechat;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", wechat='" + wechat + '\'' +
                '}';
    }
}

package cn.succy.alarm.receiver;

import java.util.List;

/**
 * 联系人组
 *  一般情况，对于发送的警报信息，会进行组配置，比如，某些信息发送给运维1组，某些发送给开发2组等
 * @author Succy
 * @date 2017-10-13 17:07
 **/

public class ContactGroup {
    private String groupId;
    private String groupName;
    private List<Contact> contactList;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "ContactGroup{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", contactList=" + contactList +
                '}';
    }
}

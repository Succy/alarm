package cn.succy.alarm.receiver;



import cn.hutool.core.collection.CollUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 警报接收者
 *
 * @author Succy
 * @date 2017-10-13 17:14
 **/

public class Receiver {
    private List<ContactGroup> contactGroupList;
    private Set<Contact> contactSet = new HashSet<>();

    public List<ContactGroup> getContactGroupList() {
        return contactGroupList;
    }

    public void setContactGroupList(List<ContactGroup> contactGroupList) {
        this.contactGroupList = contactGroupList;
        for (ContactGroup contactGroup : contactGroupList) {
            CollUtil.addAll(contactSet, contactGroup.getContactList());
        }
    }

    public Set<String> getEmailSet() {
        Set<String> emailSet = new HashSet<>();
        for (Contact contact : contactSet) {
            emailSet.add(contact.getEmail());
        }

        return emailSet;
    }

    public Set<String> getPhoneSet() {
        Set<String> phoneSet = new HashSet<>();
        for (Contact contact : contactSet) {
            phoneSet.add(contact.getPhone());
        }

        return phoneSet;
    }

    public Set<String> getWeChatSet() {
        Set<String> wechatSet = new HashSet<>();
        for (Contact contact : contactSet) {
            wechatSet.add(contact.getWechat());
        }

        return wechatSet;
    }


    @Override
    public String toString() {
        return "Receiver{" +
                "contactGroupList=" + contactGroupList +
                '}';
    }
}

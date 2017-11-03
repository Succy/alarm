package cn.succy.alarm.provider.impl;

import cn.succy.alarm.config.ConfigManager;
import cn.succy.alarm.config.ProviderConfig;
import cn.succy.alarm.provider.ContactProvider;
import cn.succy.alarm.receiver.Contact;
import cn.succy.alarm.receiver.ContactGroup;
import cn.succy.alarm.receiver.Receiver;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * json配置文件存储联系人信息的实现类
 *
 * @author Succy
 * @date 2017-10-13 17:37
 **/

public class JsonContactProviderImpl implements ContactProvider {
    private static final Logger logger = LoggerFactory.getLogger(JsonContactProviderImpl.class);
    private ProviderConfig providerConfig = ConfigManager.getConfig(ProviderConfig.class);
    // 配置文件中的键值常量

    private class Key {
        static final String RECEIVERS = "receivers";
        static final String GROUPS = "groups";
        static final String CONTACTS = "contacts";
        static final String GROUP_ID = "group_id";
        static final String GROUP_NAME = "group_name";
        static final String CONTACT_ID = "contact_id";
    }

    @Override
    @SuppressWarnings("unchecked")
    public Receiver getReceiver() {
        String jsonfilePath = providerConfig.getJsonfilePath();
        JSONObject root = JSONUtil.readJSONObject(FileUtil.file(jsonfilePath), CharsetUtil.CHARSET_UTF_8);
        List<String> recvGroupList = CollUtil.distinct((List<String>) root.get(Key.RECEIVERS));

        JSONArray groupArr = root.getJSONArray(Key.GROUPS);
        JSONArray contactArr = root.getJSONArray(Key.CONTACTS);

        List<ContactGroup> contactGroupList = new ArrayList<>();

        for (String group : recvGroupList) {
            for (Object obj : groupArr) {
                JSONObject groupJson = (JSONObject) obj;
                if (group.equals(groupJson.getStr(Key.GROUP_NAME))) {
                    ContactGroup contactGroup = parseGroup(groupJson, contactArr);
                    contactGroupList.add(contactGroup);
                    break;
                }
            }
        }
        Receiver receiver = new Receiver();
        receiver.setContactGroupList(contactGroupList);

        return receiver;
    }

    @SuppressWarnings("unchecked")
    private ContactGroup parseGroup(JSONObject groupJson, JSONArray contactArr) {
        ContactGroup contactGroup = new ContactGroup();
        contactGroup.setGroupId(groupJson.getStr(Key.GROUP_ID));
        contactGroup.setGroupName(groupJson.getStr(Key.GROUP_NAME));

        List<String> contactIdList = CollUtil.distinct ((List<String>) groupJson.get(Key.CONTACTS));
        List<Contact> contactList = new ArrayList<>();
        for (String contactId : contactIdList) {
            for (Object obj : contactArr) {
                JSONObject contactJson = (JSONObject) obj;
                if (contactId.equals(contactJson.getStr(Key.CONTACT_ID))) {
                    Contact contact = parseContact(contactJson);
                    contactList.add(contact);
                    break;
                }
            }
        }

        contactGroup.setContactList(contactList);
        return contactGroup;
    }

    private Contact parseContact(JSONObject contactJson) {
        Contact contact = contactJson.toBean(Contact.class, true);
        contact.setContactId(contactJson.getStr(Key.CONTACT_ID));
        return contact;
    }
}

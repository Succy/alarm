package cn.succy.alarm;

/**
 * @author Succy
 */
public interface Constant {
    // 涉及到的表名
    String TABLE_RECEIVER = "receiver";
    String TABLE_GROUP = "group";
    String TABLE_CONTACT = "contact";
    String TABLE_GROUP_CONTACT = "group_contact";

    // 涉及到的字段
    String FIELD_GROUP_NAME = "group_name";
    String FIELD_GROUP_ID = "group_id";
    String FIELD_CONTACT_ID = "contact_id";
    String FIELD_NAME = "name";
    String FIELD_EMAIL = "email";
    String FIELD_PHONE = "phone";
    String FIELD_WECHAT = "wechat";

    String ALAEM_QUEUE_NAME = "alarm_queue";
}

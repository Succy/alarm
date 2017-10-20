#--
    警报模板
    该模板不局限与使用message.tpl这个名字，可以任意名字，
    模板引擎使用Jfinal Enjoy模板，在模板中，可以使用Enjoy的语法进行编写
--#
告警名称: #(alarmName)
级别: #(level)
主机IP: #(StrKit.notBlank(host) ? host : "未知主机")
时间: #(dateTime)
内容: #(content)
错误堆栈: #(traceStack)
异常: #(StrKit.notBlank(exception) ? exception : "暂未上报异常")
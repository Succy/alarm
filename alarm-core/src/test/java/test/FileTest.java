package test;


import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.junit.Test;

/**
 * @author Succy
 * @date 2017-10-17 22:09
 **/

public class FileTest {
    @Test
    public void test() {
        String basePath = "template/msg/message.tpl";
        int index = FileUtil.lastIndexOfSeparator(basePath);
        String path = StrUtil.subPre(basePath, index);
        String filename = StrUtil.subSuf(basePath, index);

        String absolutePath = FileUtil.getAbsolutePath((String) null);
        System.out.println(path + "   ===  " + filename + "  == " + absolutePath);
    }

    @Test
    public void testGetAppName() {

    }
}

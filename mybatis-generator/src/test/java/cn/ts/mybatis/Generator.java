package cn.ts.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         * getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        LOG.info("读取配置文件：{}", url.getFile());
        File file = new File(url.getFile());
        return file;
    }

    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        File file = getFile("mybatis-generator.xml");// new File("mybatis-generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(file);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String string : warnings) {
            LOG.warn("warnings: {}", string);
        }
    }

    public static void main(String[] args) throws Exception {
        LOG.info("执行开始...");
        Generator generator = new Generator();
        generator.generator();
        LOG.info("执行结束");
    }

}

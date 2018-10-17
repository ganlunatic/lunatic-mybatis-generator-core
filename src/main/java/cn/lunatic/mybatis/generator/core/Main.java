package cn.lunatic.mybatis.generator.core;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        File target = new File("target");
        if (!target.exists()) {
            target.mkdir();
        }
        InputStream is = null;
        try {
            is = new FileInputStream("generatorConfig.xml");
            if (flag) {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                is = classloader.getResourceAsStream("config/generatorConfig.xml");
            }
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }
}

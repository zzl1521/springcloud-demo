package com.my.demo.springcloud.mapper.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  zhangzhile
 * @date 20161222
 * myBatis 逆向工程生成类
 */
public class GeneratorSqlmap {


	public void generator() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		//指定 逆向工程配置文件
		File configFile = new File("D:\\workspaces\\springcloud-demo\\springcloud-mapper\\src\\main\\java\\com\\my\\demo\\springcloud\\mapper\\generator\\generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);

	}

    /**
     * 注意****************注意
     *
     * 执行前检查generatorConfig.xml文件中table标签配置,注释掉已生成myBatis的<table>配置,否则会覆盖之前的修改
     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		try {
			GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
			generatorSqlmap.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

package com.hwj.modules.base.util;

import java.util.List;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持通配符的Model匹配方式
 *
 */
public class TypeAliasExpendSqlSessionFactoryBean extends SqlSessionFactoryBean {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 默认的
	 */
	private String defaultTypeAliasesPackage;

	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		List<String> packages = PackageUtil.getPackages(typeAliasesPackage, true);

		String packageStr = "";
		for (int i = 0; i < packages.size(); i++) {
			packageStr += packages.get(i);
			if (i != packages.size() - 1) {
				packageStr += ",";
			}
		}

		if (packages.size() == 0) {
			logger.warn("Parameter 'typeAliasesPackage': " + typeAliasesPackage + ", can not found any packages.");

			if (logger.isDebugEnabled()) {
				logger.info("Start to set parameter 'typeAliasesPackage' as 'defaultTypeAliasesPackage': "
						+ defaultTypeAliasesPackage + ".");
			}
			if (StringUtil.isEmpty(packageStr)) {
				packageStr = defaultTypeAliasesPackage;
			}
		}

		super.setTypeAliasesPackage(packageStr);
	}

	public String getDefaultTypeAliasesPackage() {
		return defaultTypeAliasesPackage;
	}

	public void setDefaultTypeAliasesPackage(String defaultTypeAliasesPackage) {
		this.defaultTypeAliasesPackage = defaultTypeAliasesPackage;
	}

}

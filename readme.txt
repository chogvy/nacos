启动说明

1. checkout master

2. 安装 Protobuf 插件

3. nacos.class 设置单机启动 & 关闭权限校验

    // 通过环境变量的形式设置单机启动
    System.setProperty(Constants.STANDALONE_MODE_PROPERTY_NAME, "true");
    // 通过环境变量的形式设置关闭权限校验
    System.setProperty("nacos.core.auth.enabled", "false");

4. mvn clean compile -Dmaven.test.skip=true


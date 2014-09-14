# 1. 开发入门

- 官方下载并安装Mysql5.5+、jdk1.6、eclipse4.2+、gradle1.8+、nexus2.1+
- 执行nexus-<版本>/bin/jsw/windows-操作系统版本/console-nexus.bat启动私服
- 编辑etc/setenv相关软件路径指向本地安装/下载的版本
- 启动Mysql数据库后执行src/example/scripts/dbini初始化数据库
- 执行src/example/scripts/init初始化环境并自动创建eclipse项目，首次运行要从外网下载依赖的jar，所以较慢，如果没报错请等待，如果报错请选择网络好的环境执行
- 执行src/example/scripts/eclipse启动eclipse，切换到java视图，eclipse中浏览或编辑代码
- 执行src/example/src/mgt/scripts/gradle-jettyRun启动管理平台，浏览器访问http://localhost:8080，使用test/888888登录
- 执行src/example/src/app/scripts/gradle-jettyRun启动交易平台，浏览器访问http://localhost:9080，使用test/888888登录
- 查看代码结构及各部分原理

# 2. 参考资料
- [Gradle](http://www.gradle.org/)
- [Nexus](http://www.sonatype.org/nexus/)
- [Eclipse](http://download.eclipse.org/eclipse/downloads/)
- [Jetty](http://www.gradle.org/docs/current/userguide/userguide_single.html#jetty_plugin)
- [Groovy](http://groovy.codehaus.org/)
- [Spring Data JPA](https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/)
- [Apache Shiro](https://www.ibm.com/developerworks/cn/java/j-lo-shiro/)

# 3. 参考结构
    /
    ├─etc
    │  my.ini
    ├─home
    │  └─ddshell
    │     │  shell.bat
    │     ├─Downloads
    │     │  apache-ant-1.9.4-bin.zip
    │     │  apache-maven-3.2.3-bin.zip
    │     │  apache-tomcat-7.0.55-windows-x64.zip
    │     │  apache-tomcat-7.0.55-windows-x86.zip
    │     │  apache-tomcat-7.0.55.zip
    │     │  eclipse-platform-4.4-macosx-cocoa-x86_64.tar.gz
    │     │  eclipse-platform-4.4-win32-x86_64.zip
    │     │  eclipse-platform-4.4-win32.zip
    │     │  gradle-2.0-all.zip
    │     │  groovy-binary-1.8.9.zip
    │     │  groovy-binary-2.1.9.zip
    │     │  groovy-binary-2.3.6.zip
    │     │  jdk-6u45-windows-i586.exe
    │     │  jdk-7u67-linux-x64.rpm
    │     │  jdk-7u67-windows-i586.exe
    │     │  jdk-7u67-windows-x64.exe
    │     │  jdk-8u20-windows-i586.exe
    │     │  mysql-5.6.20-win32.zip
    │     │  org.eclipse.jdt-4.4.zip
    │     └─Projects
    │        ├─android
    │        ├─iOS
    │        ├─javaee
    │        │  └─ddshell
    │        └─python
    ├─usr
    │  └─local
    │     ├─ant
    │     │  └─apache-ant-1.9.4
    │     ├─eclipse
    │     │  └─eclipse-platform-4.4-win32
    │     │     └─eclipse
    │     ├─gradle
    │     │  └─gradle-2.0
    │     ├─groovy
    │     │  ├─groovy-1.8.9
    │     │  ├─groovy-2.1.9
    │     │  └─groovy-2.3.6
    │     ├─java
    │     │  ├─jdk1.6.0_45
    │     │  ├─jdk1.7.0_67
    │     │  └─jdk1.8.0_20
    │     ├─maven
    │     │  └─apache-maven-3.2.3
    │     ├─mysql
    │     │  └─mysql-5.6.20-win32
    │     ├─nexus
    │     │  ├─nexus-2.8.1-01
    │     │  └─sonatype-work
    │     └─tomcat
    │        └─apache-tomcat-7.0.55
    └─var
       ├─lib
       │  └─mysql
       │     ├─ddshell_example
       │     ├─mysql
       │     ├─performance_schema
       │     ├─test
       │     └─world
       └─log

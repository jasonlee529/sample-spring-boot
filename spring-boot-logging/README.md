
### dependency-spring-boot-starter-logging
spring-boot-starter-logging还有两个很容易混淆的变种的starter  
__spring-boot-starter-log4j__ 和 __spring-boot-starter-log4j2__

前者在版本更新到了1.3之后就停止了，后者持续更新，需要注意下。
#### dependencies
spring-boot-starter-logging 依赖有

| groupId                   | artifactId      | version |
|---------------------------|-----------------|---------|
| ch.qos.logback            | logback-classic | 1.2.3   |
| org.apache.logging.log4j  | log4j-to-slf4j  | 2.10.0  |
| org.slf4j                 | jul-to-slf4j    | 1.7.25  |


上面的列表中可知log的最終打印是使用logback實現的。项目中优先选用 __spring-boot-starter-logging__
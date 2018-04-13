
### dependency-spring-boot-starter-logging
spring-boot-starter-logging还有哦一个变种的starter  spring-boot-starter-log4j
但是版本更新到了1.3之后就停止了，不随着主版本升级，故不选择。

#### dependencies
spring-boot-starter-logging 依赖有

| groupId                   | artifactId      | version |
|---------------------------|-----------------|---------|
| ch.qos.logback            | logback-classic | 1.2.3   |
| org.apache.logging.log4j  | log4j-to-slf4j  | 2.10.0  |
| org.slf4j                 | jul-to-slf4j    | 1.7.25  |


上面的列表中可知log的最終打印是使用logback實現的。
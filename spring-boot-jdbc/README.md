### 数据库
数据库使用h2内存数据库，优点是可内存启动。缺点是一次启动有效，可以用在测试环境中。

### 依赖
CRUD类的应用很简单，主要添加了jpa，使用spring-boot-starter-data-jpa这个启动器。
其实spring-boot-starter-web启动器已经添加了datasource的支持了，上一章已经exclude掉了datasource相关的自动配置，
如果不剔除，会导致启动报错。

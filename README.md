# Springboot-AdvanceCode
#代码进阶项目
####1、添加抽象类实现例子
#####1）方式1
  通过注入的注入方式将各抽象类的实现注入到一个集合中，通过@PostConstruct对象实例初始化到Map中，根据不同的类型进行获取对应的抽象类实现，接着调用类方法进行实现,通过策略模式进行调用

#####2）方式2
  1）定义数据类型，根据类型判断获取不同的连接信息
  2）定义一个连接信息抽象类，里面包含连接的信息，参数，连接连接必须执行的抽象方法
  3）定义一个执行方法接口，该接口的返回类型是各泛型，参数是定义的执行方法
  4）根据不同的数据类型进行获取到对应的连接信息操作类进行执行
  5）根据不同的业务抽象出不同的接口



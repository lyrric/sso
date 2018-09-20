# sso
sso完整列子，spring boot+redis

server模块为sso中心服务，登陆，注销，都在此服务上，此服务需要单独启动

client模块为普通的web服务，通过重定向到server的方式来实现sso单点登陆

整个流程为

  1.用户访问client1(假设为http://localhost:8081) 需要从（http://localhost:8081/page1） 登陆的页面url(http://localhost:8081/page2) 时，拦截器WebInterceptor会判断cookie中是否有uuid值
  
  2.如果有的话就在redis中检验，是否有效，有效则正常返回
  
  3.如果没有uuid或者redis中不存在此值，则重定向到sso服务的(假设为http://localhost:8080)/check api上，并在url上附上callBack=http://localhost:8081/page1
  
  4.sso服务的check api接口，首先会从cookie中获取uuid
  
  5.uuid不存在或者在redis中不存在，则重定向到sso的login页面并在url上附上callBack=http://localhost:8081/page1
  
  6.uuid有效则返回callBack（http://localhost:8081/page1），并附上参数uuid=123456， 接9
  
  7.uuid无效或不存在则跳转到/login(登陆)页面附上callBack=http://localhost:8081/page1
  
  8.登陆成功后，会在sso中写入cookie，uuid=123456，并跳转到callBack（http://localhost:8081/page1），并附上参数uuid=123456
  
  9.client1在收到请求后，WebInterceptor会判断url参数中是否有uuid参数
  
  10.如果有uuid参数的话，则保存cookie，uuid=123456，然后会校验uuid是否有效，接1

# alibabacloud-homelink-java-sdk

智能人居API SDK为用户在构建业务平台提供一种更便利的访问平台API服务的方式。API SDK是基于IoT网关客户端（[api-gateway-client](https://github.com/aliyun/iotx-api-gateway-client)）开发，对智能人居平台开放的API服务进行封装，用户可以忽略API路径、版本等配置，直接访问API服务的最新版本。

API SDK也支持完全的定制化，可以根据自身需求，修改和定制化所有的接口访问参数，如果VPC域名等。

API SDK完全遵守IoT网关协议。如果不想使用，可以根据IoT网关协议和API Reference文档中的API说明，自行开发接口的访问。


# 编写API
下面以“查询用户设备”接口为例，说明如何编写测试API。
## （1）定义API类
定义一个可以分组封装API的类，如DeviceAPI。这个类将要提供设备相关接口的访问能力。

```java
public class DeviceAPI {}
```

##（2）定义API URL
在刚定义的API类上，为新接口定义一个常量URL，这是API的对外接口。URL的配置见接口定义。
```java
public class DeviceAPI {
    public static final String USER_DEVICE_LIST = "/home/paas/user/device/list";
}
```

##（3）添加业务方法
接口的返回值是设备列表，这里可以忽略接口返回的Result模式的结果封装，直接定义业务结果类型。
所以方法的结果类型应该是List<DeviceDTO>。

设计接口参数（详细见定义）
* hid和hidType，其中hidType默认为三方账号，可以不填。
* 页码和页大小，其中页大小使用固定值，如50，只有页码是业务敏感的。
* 所以，业务参数是hid和pageNo。

于是定义业务接口如下：
```java
public class LockAPI {
    public static final String USER_DEVICE_LIST = "/home/paas/user/device/list";
    /**
     * 查询用户的设备列表
     *
     * @param hid
     * @param pageNo
     */
    public static 
    ApiCommand<List<DeviceDTO>> getUserDeviceList(String hid, Integer pageNo) {}
  }
}
```
能看到方法的返回值是ApiCommand，参数类型即接口的业务类型List<DeviceDTO>>。
接口中只定义了API不变的部分，可以用static定义方法。
##（4）编写API命令
这里要想几个问题，在调用API时，什么是变化的，什么是不变的。
* 变化的是环境、参数和结果；
* 不变化的是接口定义，结果解析过程；
所以编写API命令时，要定义的内容包括：
* API URL & VERSION
* 如何拼装请求参数
* 如何校验结果
* 如何解析有效的结果

于是有下面的API命令的实现（下面代码忽略类定义部分，见上节）：

```java
public static
ApiCommand<List<DeviceDTO>> getUserDeviceList(String hid,Integer pageNo) {
  
    return new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
        // 定义版本
        .apiVer("1.0.0")
        // 定义参数拼装方法的实现
        .paramSupplier(() -> {
            IdentityDTO target= new IdentityDTO();
            target.setHid(hid);
            HashMap<String, Object> res = Maps.newHashMap();
            res.put("target", target);
            res.put("pageNo", pageNo);
            res.put("pageSize", 50);
            return res;
        })
        // 定义校验方法的实现
        .assertFunc((code, msg, localizedMsg, data) -> {
            assertThat(code).isEqualTo(200);
        })
        // 定义结果解析方法的实现
        .convertFunc(jo -> {
            PageDTO<DeviceDTO> page = ((JSONObject)jo).toJavaObject(tr);
            List<DeviceDTO> list = page.getData();
            System.out.println(JSON.toJSONString(list, true));
            return list;
        });
}
```
方法只有一个return语句，返回类型是ApiCommand<List<DeviceDTO>>。

ApiCommond提供了链式API，可以直接设置所有参数。
* 第4行，构造ApiCommand时，参数是API的URL
* 第6行，设备API的版本
* 第8行，为ApiCommand提供了参数的拼接方法。这个方法把暴露出来的少量业务参数，拼装成完整的接口请求参数，封装细节。
* 第18行，为ApiCommand提供了结果的校验方法。由于结果是Result模式的设计，所以校验code值为200。很容易看出来的是，校验方法的参数，就是IoTxResult内部的封装参数。
* 第22行，为ApiCommand提供了结果的解析方法，从结果的JSONObject或JSONArray中解析出业务对象。这个业务对象就是ApiCommand的泛型参数。

##（5）调用API
调用API时，才需要给提供变化的参数，包括：
* 环境
* 身份（需要登录态时使用，需要提供iotToken）
* 参数

ApiCommand提供了充分的运行时参数
* apiEnv：运行时连接参数
* https：是否使用https（默认为true, 使用https）

ApiEnvironment提供了连接参数定义。默认情况下，在ApiHelper中提供了组测试用的appKey/appSecret的配置，可以直接使用。
* host 主机
* appKey 
* appSecret
* requestTimeout 请求超时

对结果
* 只执行而不需要返回结果时，调用 execute()
* 执行并返回结果时，调用 executeAndGet()

而在第(4)步定义的其它参数，也可以在调用时修改。

示例：
验证正确的结果：
```java
@Test
public void testGetUserDeviceListOK(){
    List<DeviceDTO> abc = getUserDeviceList("abc", 2).executeAndGet();
}
```
验证错误的结果：
```java
@Test
public void testGetUserDeviceListError() {
    getUserDeviceList("hidNotExist", 2)
        .assertFunc((code, msg, localizedMsg, data) -> {
            assertThat(code).isNotEqualTo(200);
        })
        .execute();
}
```
由于错误结果中不会有数据，所以调用方法改为execute()
## 完整示例
### API定义

```java
public class DeviceAPI {
    public static final String USER_DEVICE_LIST = "/home/paas/user/device/list";
    /**
     * 查询用户的设备列表
     *
     * @param hid
     * @param pageNo
     */
    public static
    ApiCommand<List<DeviceDTO>> getUserDeviceList(String hid,Integer pageNo) {
        return new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
            .apiVer("1.0.0")
            .paramSupplier(() -> {
                IdentityDTO target= new IdentityDTO();
                target.setHid(hid);
    
                HashMap<String, Object> res = Maps.newHashMap();
                res.put("target", target);
                res.put("pageNo", pageNo);
                res.put("pageSize", 50);
                return res;
            }).assertFunc((code, msg, localizedMsg, data) -> {
                assertThat(code).isEqualTo(200);
            }).convertFunc(jo -> {
                PageDTO<DeviceDTO> page = ((JSONObject)jo).toJavaObject(tr);
                List<DeviceDTO> list = page.getData();
                System.out.println(JSON.toJSONString(list, true));
                return list;
            });
    }   
  }
}
```

单元测试
```java
public class DeviceAPITest{
  
    /**
     * 测试"查询用户设备列表"的正确调用结果
     */
    @Test
    public void testGetUserDeviceListOK() {
        List<DeviceDTO> abc = getUserDeviceList("abc", 2).executeAndGet();
    }
    /**
     * 验证"查询用户设备列表"的异常调用，期望返回非200的结果
     */
    @Test
    public void testGetUserDeviceListError() {
        getUserDeviceList("hidNotExist", 2)
            .assertFunc((code, msg, localizedMsg, data) -> {
                assertThat(code).isNotEqualTo(200);
            })
            .execute();
    }
}
```

# 功能
下面就每项功能给出具体的说明和示例。
## 配置连接参数

如果在测试时需要切换不同的租户，可以预先定义好不同租户的appKey/appSecret以备用。
当运行一个API时，使用`ApiCommand::apiEnv`方法指定请问接口的连接配置。

定义一个连接参数：
```java
ApiEnvironment env = ApiEnvironment.builder()
    .name("alios-env")
    .appKey("xxx")
    .appSecret("xxx")
    .host("api.link.aliyun.com")
    .requestTimeout(30_000L)
    .build();
```

## 配置默认连接参数
很多情况下，我们需要一个默认的连接参数，以满足大多数的访问。
可以使用`ApiContext#setEnv`设置默认的连接参数，而不必每次都指定。



## 创建接口
### 创建ApiCommand
每个API接口被抽象成一个ApiCommand对象。
这个对象的Api被设计成简短名名的流式风格，方便连接配置参数和运行，最后得到接口的返回值。
创建ApiCommand最少要提供两个参数，一个是接口的URL，一个是接口返回值的类型。

如`快速上手（4）节`创建的ApiCommand：
```java
new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
```

- USER_DEVICE_LIST是访问URL
- List<DeviceDTO>是根据接口文档把JSON结果转换之后的返回类型。这个返回类型也可以进一步自定义来满足接口的业务需求，例如把List类型改为Set。其核心是如何定义转换函数，

### 设置版本 apiVer
使用ApiCommand::apiVer设置接口的版本号

```java
new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
    // 定义版本
    .apiVer("1.0.0");
```

### 设置参数提供器 paramSupplier
paramSupplier是参数的提供器，它负责把简单的业务参数封装成接口使用的完全参数，使得对外提供的API封装接口可以面向业务有更简单的接口设计。

paramSupplier的返回类型，就是构造方法里指定的接口返回类型。

例：在使用分页查询接口时，pageSize一般不用每次调用时都指定。
```java
new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
    // 定义版本
    .apiVer("1.0.0")
    // 定义参数拼装方法的实现
    .paramSupplier(() -> {
        IdentityDTO target= new IdentityDTO();
        target.setHid(hid);
        HashMap<String, Object> res = Maps.newHashMap();
        res.put("target", target);
        res.put("pageNo", pageNo);
        res.put("pageSize", 50);
        return res;
    });
```

### 设置接口校验方法 assertFunc
接口请求后会有多种返回值，对于测试环境来说，有些错误的返回结果是期待的。对业务环境来说，可能只期待正确的返回结果。

使用asertFunc提供校验方法的实现，具体实现根据使用环境而定。对业务环境来说，只简单的校验code值等于200即可。对测试环境来说，可以在接口执行前，传入新的校验方法，检查期望的其它错误码。

例，见assertFunc的使用：
```java
new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
    // 定义版本
    .apiVer("1.0.0")
    // 定义参数拼装方法的实现
    .paramSupplier(() -> {
        IdentityDTO target= new IdentityDTO();
        target.setHid(hid);
        HashMap<String, Object> res = Maps.newHashMap();
        res.put("target", target);
        res.put("pageNo", pageNo);
        res.put("pageSize", 50);
        return res;
    })
    // 定义校验方法的实现
    .assertFunc((code, msg, localizedMsg, data) -> {
        assertThat(code).isEqualTo(200);
    });
```

### 设置结果转换方法 convertFunc
结果转换方法是在接口请求成功后，把JSON格式的结果转换成业务类型。
其入参是JSONObject和JSONArray的父类型，方法中需要根据接口文档的说明，决定把JSON的类型强制转换成JSONObject或JSONArray。

convertFunc的返回结果，就是`构造方法`中指定的接口返回类型。

例，见convertFunc的使用：
```java
new ApiCommand<List<DeviceDTO>>(USER_DEVICE_LIST)
    // 定义版本
    .apiVer("1.0.0")
    // 定义参数拼装方法的实现
    .paramSupplier(() -> {
        IdentityDTO target= new IdentityDTO();
        target.setHid(hid);
        HashMap<String, Object> res = Maps.newHashMap();
        res.put("target", target);
        res.put("pageNo", pageNo);
        res.put("pageSize", 50);
        return res;
    })
    // 定义校验方法的实现
    .assertFunc((code, msg, localizedMsg, data) -> {
        assertThat(code).isEqualTo(200);
    })
    // 定义结果解析方法的实现
    .convertFunc(jo -> {
        PageDTO<DeviceDTO> page = ((JSONObject)jo).toJavaObject(tr);
        List<DeviceDTO> list = page.getData();
        System.out.println(JSON.toJSONString(list, true));
        return list;
    });
```

### 执行接口 execute/executeAndGet
ApiCommand提供了两个执行方法：

- execute方法，执行请求，并调用校验方法（assertFunc）验证返回结果。
- executeAndGet方法，执行请求，并根据返回的code值有不同的处理：
  - 返回200时，**调用结果转换方法（convertFunc）获得返回结果，再调用校验方法（assertFunc）验证返回值。
**
  - 返回非200时，直接调用校验方法（assertFunc）验证返回值，不再调用结果转换方法。

一般execute/executeAndGet方法会在调用接口时使用，而不会在定义的时候使用。

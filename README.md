# sm_encrypt



FlutterSM2,SM4加解密插件封装



**1.引入插件**

```
sm_encrypt:
  git: https://github.com/Poker-J/flutter_sm_encrypt.git
```

**2.如何使用**

```
 注:秘钥都采用16进制
 /**
   * 获取SM2秘钥对
   * return 秘钥对
   */
 SmEncrypt.getSM2KeyPair();
   /**
   * sm2加密
   * source:加密数据
   * encryptionStr:秘钥(公钥)
   * return String
   */
 SmEncrypt.sm2Encryption(String source,String encryptionStr);
  /**
   * sm2解密
   * source:解密数据
   * decodeStr:秘钥(私钥)
   * return String
   */
 SmEncrypt.sm2Decode(String source,String decodeStr);
   /**
   * sm4加密
   * source:加密数据
   * encryptionStr:秘钥
   * return String
   */
 SmEncrypt.sm4Encryption(String source,String encryptionStr);
   /**
   * sm4解密
   * source:解密数据
   * encryptionStr:秘钥
   * return String
   */
 SmEncrypt.sm4Decode(String source,String decodeStr);
```

**3.Example**

```
import 'package:flutter/material.dart';

import 'package:sm_encrypt/sm_encrypt.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();

  }



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('SM加解密示例'),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(height: 20,),
            RaisedButton(
              child: Text('获取SM2秘钥对'),
              onPressed: () {
                SmEncrypt.getSM2KeyPair();
              },
            ),
            SizedBox(height: 20,),
            RaisedButton(
              child: Text('SM2加密'),
              onPressed: () {
                var  publicKey =  '3059301306072a8648ce3d020106082a811ccf5501822d03420004499bfabc81089097dba1cb68028991fe1272af00b8a6c9bccafd1fff1366da23fa1fdce68893d4e0017869262df8c792b9d3b6a5d214f343b45e5cacaa5d93ce';
                SmEncrypt.sm2Encryption('123456',publicKey).then((value){
                  print('-----------加密------------->${value}');
                });
              },
            ),
            SizedBox(height: 20,),
            RaisedButton(
              child: Text('SM2解密'),
              onPressed: () {
                var privateKey = '308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104205772a882d99a87f3809bbf82613ca6f6b1f2fd283106d70385e74dc69a83d725a00a06082a811ccf5501822da14403420004499bfabc81089097dba1cb68028991fe1272af00b8a6c9bccafd1fff1366da23fa1fdce68893d4e0017869262df8c792b9d3b6a5d214f343b45e5cacaa5d93ce';
                var data = '042cd162bff6df3a82188d0283a1af86512cf6e136bd66548d75b9864e20cd71d3db777b9ceae8dd29b575839b13675dfcc5e8bc4df7476da3600136ffbf3dd72b624ba74df5b1006084a45bddd935e0ec57175157be95a35a0c4cdd9a092f6cb178ce8aa8c42d';
                SmEncrypt.sm2Decode(data,privateKey).then((value){
                  print('-----------解密------------->${value}');
                });
              },
            ),
            SizedBox(height: 20,),
            RaisedButton(
              child: Text('SM4加密'),
              onPressed: () {
                var privateKey = 'xxxxxxxxxxxxxxxxx';
                var data = '123456';
                SmEncrypt.sm4Encryption(data,privateKey).then((value){
                  print('-----------加密------------->${value}');
                });
              },
            ),
            SizedBox(height: 20,),
            RaisedButton(
              child: Text('SM4解密'),
              onPressed: () {
                var privateKey = 'xxxxx';
                var data = '123456';
                SmEncrypt.sm4Decode(data,privateKey).then((value){
                  print('-----------解密------------->${value}');
                });
              },
            ),
          ],
        ),
      ),
    );
  }
}
```

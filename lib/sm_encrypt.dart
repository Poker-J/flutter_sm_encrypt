
import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';

import 'model/sm2_keypair_model.dart';

class SmEncrypt {
  static const MethodChannel _channel =
      const MethodChannel('sm_encrypt');


  /**
   * 获取SM2秘钥对
   * return 秘钥对
   */
  static Future<Sm2KeypairModel>  getSM2KeyPair() async {
    final String jsonStr = await _channel.invokeMethod('getSM2KeyPair');
    var res = jsonDecode(jsonStr.toString());
    return Sm2KeypairModel.fromJson(res);
  }

  /**
   * sm2加密
   * source:加密数据
   * encryptionStr:秘钥(公钥)
   * return String
   */
  static Future<String>  sm2Encryption(String source,String encryptionStr) async {
    return await _channel.invokeMethod('sm2Encryption',{
      'source':source,
      'encryptionStr':encryptionStr
    });
  }


  /**
   * sm2解密
   * source:解密数据
   * decodeStr:秘钥(私钥)
   * return String
   */
  static Future<String>  sm2Decode(String source,String decodeStr) async {
    return await _channel.invokeMethod('sm2Decode',{
      'source':source,
      'decodeStr':decodeStr
    });
  }


  /**
   * sm4加密
   * source:加密数据
   * encryptionStr:秘钥
   * return String
   */
  static Future<String>  sm4Encryption(String source,String encryptionStr) async {
    return await _channel.invokeMethod('sm4Encryption',{
      'source':source,
      'encryptionStr':encryptionStr
    });
  }

  /**
   * sm4解密
   * source:解密数据
   * encryptionStr:秘钥
   * return String
   */
  static Future<String>  sm4Decode(String source,String decodeStr) async {
    return await _channel.invokeMethod('sm4Decode',{
      'source':source,
      'decodeStr':decodeStr
    });
  }



}

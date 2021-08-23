package com.wit.encrypt.sm_encrypt;

import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;


import com.wit.encrypt.sm_encrypt.model.Sm2KeyPairModel;


import org.json.JSONObject;

import java.io.IOException;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** SmEncryptPlugin */
public class SmEncryptPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "sm_encrypt");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("calculate")) {
      int a = call.argument("a");
      int b = call.argument("b");
      int r = a + b;
      result.success("" + r);
    } else if (call.method.equals("sm2")) {
//      SM2Test sm2Test = new SM2Test();
//      sm2Test.testSM2Creator();
    }else if (call.method.equals("sm3")) {
    //      new SM3Test().testSM3();
    } else if (call.method.equals("sm4")) {
      //new SM4Test().testSM4();
    }else if (call.method.equals("getSM2KeyPair")) {
      //获取秘钥对
      result.success(getSm2KeyPair());
    }else if (call.method.equals("sm2Encryption")) {
      //加密
      String source = call.argument("source");
      String encryptionStr = call.argument("encryptionStr");
      result.success(sm2Encryption(source,encryptionStr));
    }else if (call.method.equals("sm2Decode")) {
      //解密
      String source = call.argument("source");
      String encryptionStr = call.argument("decodeStr");
      result.success(sm2Decode(source,encryptionStr));
    }else if (call.method.equals("sm4Encryption")) {
      //加密
      String source = call.argument("source");
      String encryptionStr = call.argument("encryptionStr");
      result.success(sm4Encryption(source,encryptionStr));
    }else if (call.method.equals("sm4Decode")) {
      //解密
      String source = call.argument("source");
      String encryptionStr = call.argument("decodeStr");
      result.success(sm4Decode(source,encryptionStr));
    }else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  //sm4加密
  String sm4Encryption(String source,String encryptionStr){
    if(source == null || encryptionStr == null){
      Log.e("flutter","参数为空!");
      return  "";
    }
    SymmetricCrypto sm4 = SmUtil.sm4(HexUtil.decodeHex(encryptionStr));
    String encryptHex = sm4.encryptHex(source);
    return  encryptHex;
  }

  //sm4解密
  String sm4Decode(String source,String decodeStr){
    if(source == null || decodeStr == null){
      Log.e("flutter","参数为空!");
      return  "";
    }
    SymmetricCrypto sm4 = SmUtil.sm4(HexUtil.decodeHex(decodeStr));
    String encryptHex = sm4.decryptStr(source, CharsetUtil.CHARSET_UTF_8);
    return  encryptHex;
  }

  //sm2解密
  String sm2Decode(String source,String decodeStr){
    if(source == null || decodeStr == null){
      Log.e("flutter","参数为空!");
      return  "";
    }
    SM2 sm2 = SmUtil.sm2(decodeStr,null);
    //加密
    String encryptStr = sm2.decryptStr(source, KeyType.PrivateKey);
    return  encryptStr;
  }

  //sm2加密
  String sm2Encryption(String source,String encryptionStr){
    if(source == null || encryptionStr == null){
      Log.e("flutter","参数为空!");
      return  "";
    }
    SM2 sm2 = SmUtil.sm2(null,encryptionStr);
    //加密
    String encryptStr = sm2.encryptHex(source, KeyType.PublicKey);
    return  encryptStr;
  }
  
  //获取Sm2秘钥对
  String getSm2KeyPair(){
    String json = "";
    SM2 sm2 = SmUtil.sm2();
    String privateKey = HexUtil.encodeHexStr(sm2.getPrivateKey().getEncoded());
    String publicKey = HexUtil.encodeHexStr(sm2.getPublicKey().getEncoded());
    //Log.e("flutter","publicKey--->" + publicKey);
    //Log.e("flutter","privateKey--->" + privateKey);
    json = "{\"privateKey\":\""+privateKey+"\",\"publicKey\":\""+publicKey+"\"}";
    Log.e("flutter",json);
    return  json;
  }
}

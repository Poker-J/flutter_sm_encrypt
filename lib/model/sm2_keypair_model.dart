

class Sm2KeypairModel {
	String privateKey;
	String publicKey;

	Sm2KeypairModel({this.privateKey , this.publicKey });

	Sm2KeypairModel.fromJson(Map<String, dynamic> json) {
		privateKey = json['privateKey'];
		publicKey = json['publicKey'];
	}

	Map<String, dynamic> toJson() {
		final Map<String, dynamic> data = Map<String, dynamic>();
		data['privateKey'] = this.privateKey;
		data['publicKey'] = this.publicKey;
		return data;
	}
}

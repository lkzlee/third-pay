package com.lkzlee.pay.constant;

/**
 * 常量
 * @author lkzlee
 *
 */
public class ConfigConstant
{

	/***
	 * 支付宝相关参数
	 */
	public static final String ALIPAY_PARTNER = "partner";
	/***
	 * 支付宝商户私钥
	 */
	public static final String ALIPAY_PRIVATE_KEY = "aliPayPrivateKey";
	/***
	 * 支付宝商户ID
	 */
	public static final String ALIPAY_SELLER_ID = "sellerId";
	/***
	 * 可不配置，默认值= directPay^bankPay^cartoon^cash
	 */
	public static final String ALIPAY_ENABLE_PAY_METHOD = "enablePayMethod";
	/***
	 * 可不配置，默认值=1h
	 * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点，如1.5h，可转换为90m。
	 */
	public static final String ALIPAY_IT_PAY_TIMEOUT = "it_pay_timeout";

	public static final String ALIPAY_NOTIFY_URL = "aliPayNotfiyUrl";

	public static final String ALIPAY_RETURN_URL = "aliPayReturnUrl";

	public static final String ALIPAY_REFUND_URL = "aliPayRefundNotifyUrl";
	/***
	 * 微信相关配置参数
	 */
	/***
	 * 微信商户私钥
	 */
	public static final String WEIXIN_APP_KEY = "weiXinAppKey";
	/***
	 * 微信商户id
	 */
	public static final String WEIXIN_MCH_ID = "weixinMchId";
	/**
	 * 微信appId
	 */
	public static final String WEIXIN_APP_ID = "weiXinAppId";

	public static final String WEIXIN_PAY_NOTIFY_URL = "weiXinPayNotifyUrl";

}

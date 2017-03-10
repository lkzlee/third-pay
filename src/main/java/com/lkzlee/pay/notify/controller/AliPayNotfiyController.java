package com.lkzlee.pay.notify.controller;

import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.bean.AlipayConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.third.alipay.dto.response.AliPayOrderNotifyDto;
import com.lkzlee.pay.third.alipay.enums.AliPayTradeStatusEnum;
import com.lkzlee.pay.third.dto.AbstThirdPayDto;
import com.lkzlee.pay.utils.TreeMapUtil;

/***
 * 支付宝支付通知业务抽象处理类-如果处理支付宝支付通知，只需要继承该类实现相关业务处理即可
 * @author lkzlee
 *
 */
public abstract class AliPayNotfiyController extends AbstPayNotfiyController
{
	private final static Log LOG = LogFactory.getLog(AliPayNotfiyController.class);
	// 支付宝返回 fail 失败，success 成功
	private static final String STATUS_SUCCESS = "success";
	private static final String STATUS_FAIL = "fail";

	@Override
	protected boolean isSuccessOrder(AbstThirdPayDto payNotifyDto)
	{
		AliPayOrderNotifyDto aliPayNotifyDto = (AliPayOrderNotifyDto) payNotifyDto;
		if (AliPayTradeStatusEnum.TRADE_SUCCESS.getTradeStatus().equals(aliPayNotifyDto.getTrade_status())
				|| AliPayTradeStatusEnum.TRADE_FINISHED.getTradeStatus().equals(aliPayNotifyDto.getTrade_status()))
		{
			return true;
		}
		return false;
	}

	@Override
	protected String getSuccessMsgResponse(AbstThirdPayDto payNotifyDto)
	{
		return STATUS_SUCCESS;
	}

	@Override
	protected String getFailMsgResponse()
	{
		return STATUS_FAIL;
	}

	@Override
	protected boolean isSignRight(AbstThirdPayDto payNotifyDto) throws IllegalAccessException
	{
		AliPayOrderNotifyDto aliPayNotifyDto = (AliPayOrderNotifyDto) payNotifyDto;
		TreeMap<String, String> sourceMap = TreeMapUtil.getInitTreeMapAsc();
		TreeMapUtil.setFiledParamToMapInfo(aliPayNotifyDto, sourceMap, aliPayNotifyDto.getClass());
		String sign = null;
		if (sourceMap.containsKey("sign"))
			sign = sourceMap.get("sign");
		sourceMap.remove("sign");
		sourceMap.remove("sign_type");
		String source = TreeMapUtil.getTreeMapString(sourceMap);
		boolean isRight = SignTypeEnum.RSA.verifySign(source,
				AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_PUBLIC_KEY), sign);

		if (!isRight)
		{
			LOG.fatal("验签不通过，请检查，isRight=" + isRight + ",sign=" + sign + ",source=" + source);
			return false;
		}
		return true;
	}
}

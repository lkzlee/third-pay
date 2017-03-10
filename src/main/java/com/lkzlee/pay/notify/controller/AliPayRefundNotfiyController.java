package com.lkzlee.pay.notify.controller;

import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.bean.AlipayConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.third.alipay.dto.response.AliPayRefundNotifyDto;
import com.lkzlee.pay.third.dto.AbstThirdPayDto;
import com.lkzlee.pay.utils.TreeMapUtil;

/***
 * 支付宝退款通知业务抽象处理类-如果处理支付宝退款通知，只需要继承该类实现相关业务处理即可
 * @author lkzlee
 *
 */
public abstract class AliPayRefundNotfiyController extends AbstPayNotfiyController
{
	private final static Log LOG = LogFactory.getLog(AliPayRefundNotfiyController.class);
	// 支付宝返回 fail 失败，success 成功
	private static final String STATUS_SUCCESS = "success";
	private static final String STATUS_FAIL = "fail";

	@Override
	protected boolean isSuccessOrder(AbstThirdPayDto payNotifyDto)
	{
		AliPayRefundNotifyDto aliPayNotifyDto = (AliPayRefundNotifyDto) payNotifyDto;
		if (StringUtils.isEmpty(aliPayNotifyDto.getBatch_no()) || StringUtils.isEmpty(aliPayNotifyDto.getSuccess_num()))
		{
			return false;
		}
		Integer successNum = Integer.parseInt(aliPayNotifyDto.getSuccess_num());
		if (successNum <= 0)
			return false;
		//		String dataDetail = aliPayNotifyDto.getResult_details();
		//		String details[] = dataDetail.split("\\$");
		//		String orderDetail[] = details[0].split("\\^");
		//		String refundDetails[] = details[1].split("\\^");
		return true;
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
		AliPayRefundNotifyDto aliPayNotifyDto = (AliPayRefundNotifyDto) payNotifyDto;
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

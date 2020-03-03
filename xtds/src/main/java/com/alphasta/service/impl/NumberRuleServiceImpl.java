package com.alphasta.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.mapper.NumberDetailMapper;
import com.alphasta.mapper.NumberRuleMapper;
import com.alphasta.mapper.RuleDetailMapper;
import com.alphasta.model.NumberDetail;
import com.alphasta.model.NumberRule;
import com.alphasta.model.RuleDetail;
import com.alphasta.model.User;
import com.alphasta.service.NumberRuleService;

/**
 * 编号规则service
 * 
 * @author LiYunhao
 * 
 */
@Service("numberRuleService")
public class NumberRuleServiceImpl implements NumberRuleService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(NumberRuleServiceImpl.class);
	@Autowired
	private NumberRuleMapper numberRuleMapper;
	@Autowired
	private RuleDetailMapper ruleDetailMapper;
	@Autowired
	private NumberDetailMapper numberDetailMapper;

	@Override
	public List<NumberRule> findNumberRule() {
		return numberRuleMapper.findNumberRule();
	}

	@Override
	public NumberRule findNumberRuleById(Long id) {
		return numberRuleMapper.findNumberRuleById(id);
	}

	@Override
	public int insert(NumberRule numberRule) {
		int result = numberRuleMapper.insert(numberRule);
		if (result != 1) {
			LOGGER.warn("新增编号规则信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int update(NumberRule numberRule) {
		int result = numberRuleMapper.update(numberRule);
		if (result != 1) {
			LOGGER.warn("更新编号规则信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		ruleDetailMapper.deleteByRuleIds(ids);
		return numberRuleMapper.deleteByIds(ids);
	}

	@Override
	public List<RuleDetail> findRuleDetailByRuleId(Long ruleId) {
		return ruleDetailMapper.findRuleDetailByRuleId(ruleId);
	}

	@Override
	public RuleDetail findRuleDetailById(Long id) {
		return ruleDetailMapper.findRuleDetailById(id);
	}

	@Override
	public int insertDetail(RuleDetail ruleDetail) {
		int result = ruleDetailMapper.insert(ruleDetail);
		if (result != 1) {
			LOGGER.warn("新增规则详情信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int updateDetail(RuleDetail ruleDetail) {
		int result = ruleDetailMapper.update(ruleDetail);
		if (result != 1) {
			LOGGER.warn("更新规则详情信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteDetailByIds(String ids) {
		return ruleDetailMapper.deleteByIds(ids);
	}

	@Override
	public NumberDetail getNumber(Long ruleId, User user) {
		List<RuleDetail> list = ruleDetailMapper.findRuleDetailByRuleId(ruleId);
		String number = "";
		Integer type;
		Integer subType;
		NumberDetail numberDetail = new NumberDetail();
		for (RuleDetail ruleDetail : list) {
			type = ruleDetail.getType();
			subType = ruleDetail.getSubType();
			if (type == 0) {
				if (subType == 0) {
					number = number + ruleDetail.getValue();
				} else if (subType == 1) {
					number = number + user.getLoginname();
				} else if (subType == 2) {
					number = number + user.getName();
				}
			} else if (type == 1) {
				SimpleDateFormat sdf = null;
				if (subType == 0) {
					sdf = new SimpleDateFormat("yyyyMMdd");
				} else if (subType == 1) {
					sdf = new SimpleDateFormat("yyyyMM");
				} else if (subType == 2) {
					sdf = new SimpleDateFormat("MMdd");
				}
				number = number + sdf.format(new Date());
			} else if (type == 2) {
				numberDetail.setRuleId(ruleId);
				numberDetail.setFixedNumber(number);
				List<NumberDetail> detailList = numberDetailMapper
						.findNumberDetailByRuleId(numberDetail);
				if (detailList.size() > 0) {
					numberDetail = detailList.get(0);
					String seqNumber = numberDetail.getSeqNumber();
					for (int i = 0; i < seqNumber.length(); i++) {
						if (!seqNumber.substring(i, i + 1).equals("0")) {
							Long seq = Long.parseLong(seqNumber.substring(1)) + 1;
							seqNumber = "";
							for (int j = 0; j < i; j++) {
								seqNumber = seqNumber + "0";
							}
							numberDetail.setSeqNumber(seqNumber + seq);
						}
					}
				} else {
					numberDetail.setSeqNumber(ruleDetail.getValue());
				}
			}
		}
		return numberDetail;
	}

	@Override
	public int saveNumberDetail(Long ruleId, User user) {
		NumberDetail numberDetail = this.getNumber(ruleId, user);
		if (numberDetail.getId() == null) {
			return numberDetailMapper.insert(numberDetail);
		} else {
			return numberDetailMapper.update(numberDetail);
		}
	}
}

package com.alphasta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionnaireExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public QuestionnaireExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("ID like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("ID not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andActivitiesIsNull() {
			addCriterion("ACTIVITIES is null");
			return (Criteria) this;
		}

		public Criteria andActivitiesIsNotNull() {
			addCriterion("ACTIVITIES is not null");
			return (Criteria) this;
		}

		public Criteria andActivitiesEqualTo(String value) {
			addCriterion("ACTIVITIES =", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesNotEqualTo(String value) {
			addCriterion("ACTIVITIES <>", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesGreaterThan(String value) {
			addCriterion("ACTIVITIES >", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesGreaterThanOrEqualTo(String value) {
			addCriterion("ACTIVITIES >=", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesLessThan(String value) {
			addCriterion("ACTIVITIES <", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesLessThanOrEqualTo(String value) {
			addCriterion("ACTIVITIES <=", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesLike(String value) {
			addCriterion("ACTIVITIES like", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesNotLike(String value) {
			addCriterion("ACTIVITIES not like", value, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesIn(List<String> values) {
			addCriterion("ACTIVITIES in", values, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesNotIn(List<String> values) {
			addCriterion("ACTIVITIES not in", values, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesBetween(String value1, String value2) {
			addCriterion("ACTIVITIES between", value1, value2, "activities");
			return (Criteria) this;
		}

		public Criteria andActivitiesNotBetween(String value1, String value2) {
			addCriterion("ACTIVITIES not between", value1, value2, "activities");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("NAME is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("NAME is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("NAME =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("NAME <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("NAME >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("NAME >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("NAME <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("NAME <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("NAME like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("NAME not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("NAME in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("NAME not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("NAME between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("NAME not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andPubshlisherIsNull() {
			addCriterion("PUBSHLISHER is null");
			return (Criteria) this;
		}

		public Criteria andPubshlisherIsNotNull() {
			addCriterion("PUBSHLISHER is not null");
			return (Criteria) this;
		}

		public Criteria andPubshlisherEqualTo(String value) {
			addCriterion("PUBSHLISHER =", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherNotEqualTo(String value) {
			addCriterion("PUBSHLISHER <>", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherGreaterThan(String value) {
			addCriterion("PUBSHLISHER >", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherGreaterThanOrEqualTo(String value) {
			addCriterion("PUBSHLISHER >=", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherLessThan(String value) {
			addCriterion("PUBSHLISHER <", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherLessThanOrEqualTo(String value) {
			addCriterion("PUBSHLISHER <=", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherLike(String value) {
			addCriterion("PUBSHLISHER like", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherNotLike(String value) {
			addCriterion("PUBSHLISHER not like", value, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherIn(List<String> values) {
			addCriterion("PUBSHLISHER in", values, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherNotIn(List<String> values) {
			addCriterion("PUBSHLISHER not in", values, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherBetween(String value1, String value2) {
			addCriterion("PUBSHLISHER between", value1, value2, "pubshlisher");
			return (Criteria) this;
		}

		public Criteria andPubshlisherNotBetween(String value1, String value2) {
			addCriterion("PUBSHLISHER not between", value1, value2,
					"pubshlisher");
			return (Criteria) this;
		}

		public Criteria andCtimeIsNull() {
			addCriterion("CTIME is null");
			return (Criteria) this;
		}

		public Criteria andCtimeIsNotNull() {
			addCriterion("CTIME is not null");
			return (Criteria) this;
		}

		public Criteria andCtimeEqualTo(Date value) {
			addCriterion("CTIME =", value, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeNotEqualTo(Date value) {
			addCriterion("CTIME <>", value, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeGreaterThan(Date value) {
			addCriterion("CTIME >", value, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("CTIME >=", value, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeLessThan(Date value) {
			addCriterion("CTIME <", value, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeLessThanOrEqualTo(Date value) {
			addCriterion("CTIME <=", value, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeIn(List<Date> values) {
			addCriterion("CTIME in", values, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeNotIn(List<Date> values) {
			addCriterion("CTIME not in", values, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeBetween(Date value1, Date value2) {
			addCriterion("CTIME between", value1, value2, "ctime");
			return (Criteria) this;
		}

		public Criteria andCtimeNotBetween(Date value1, Date value2) {
			addCriterion("CTIME not between", value1, value2, "ctime");
			return (Criteria) this;
		}

		public Criteria andNumsIsNull() {
			addCriterion("NUMS is null");
			return (Criteria) this;
		}

		public Criteria andNumsIsNotNull() {
			addCriterion("NUMS is not null");
			return (Criteria) this;
		}

		public Criteria andNumsEqualTo(Integer value) {
			addCriterion("NUMS =", value, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsNotEqualTo(Integer value) {
			addCriterion("NUMS <>", value, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsGreaterThan(Integer value) {
			addCriterion("NUMS >", value, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsGreaterThanOrEqualTo(Integer value) {
			addCriterion("NUMS >=", value, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsLessThan(Integer value) {
			addCriterion("NUMS <", value, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsLessThanOrEqualTo(Integer value) {
			addCriterion("NUMS <=", value, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsIn(List<Integer> values) {
			addCriterion("NUMS in", values, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsNotIn(List<Integer> values) {
			addCriterion("NUMS not in", values, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsBetween(Integer value1, Integer value2) {
			addCriterion("NUMS between", value1, value2, "nums");
			return (Criteria) this;
		}

		public Criteria andNumsNotBetween(Integer value1, Integer value2) {
			addCriterion("NUMS not between", value1, value2, "nums");
			return (Criteria) this;
		}

		public Criteria andAmountIsNull() {
			addCriterion("AMOUNT is null");
			return (Criteria) this;
		}

		public Criteria andAmountIsNotNull() {
			addCriterion("AMOUNT is not null");
			return (Criteria) this;
		}

		public Criteria andAmountEqualTo(Integer value) {
			addCriterion("AMOUNT =", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotEqualTo(Integer value) {
			addCriterion("AMOUNT <>", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountGreaterThan(Integer value) {
			addCriterion("AMOUNT >", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
			addCriterion("AMOUNT >=", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLessThan(Integer value) {
			addCriterion("AMOUNT <", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLessThanOrEqualTo(Integer value) {
			addCriterion("AMOUNT <=", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountIn(List<Integer> values) {
			addCriterion("AMOUNT in", values, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotIn(List<Integer> values) {
			addCriterion("AMOUNT not in", values, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountBetween(Integer value1, Integer value2) {
			addCriterion("AMOUNT between", value1, value2, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotBetween(Integer value1, Integer value2) {
			addCriterion("AMOUNT not between", value1, value2, "amount");
			return (Criteria) this;
		}

		public Criteria andBtimeIsNull() {
			addCriterion("BTIME is null");
			return (Criteria) this;
		}

		public Criteria andBtimeIsNotNull() {
			addCriterion("BTIME is not null");
			return (Criteria) this;
		}

		public Criteria andBtimeEqualTo(Date value) {
			addCriterion("BTIME =", value, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeNotEqualTo(Date value) {
			addCriterion("BTIME <>", value, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeGreaterThan(Date value) {
			addCriterion("BTIME >", value, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("BTIME >=", value, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeLessThan(Date value) {
			addCriterion("BTIME <", value, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeLessThanOrEqualTo(Date value) {
			addCriterion("BTIME <=", value, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeIn(List<Date> values) {
			addCriterion("BTIME in", values, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeNotIn(List<Date> values) {
			addCriterion("BTIME not in", values, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeBetween(Date value1, Date value2) {
			addCriterion("BTIME between", value1, value2, "btime");
			return (Criteria) this;
		}

		public Criteria andBtimeNotBetween(Date value1, Date value2) {
			addCriterion("BTIME not between", value1, value2, "btime");
			return (Criteria) this;
		}

		public Criteria andEtimeIsNull() {
			addCriterion("ETIME is null");
			return (Criteria) this;
		}

		public Criteria andEtimeIsNotNull() {
			addCriterion("ETIME is not null");
			return (Criteria) this;
		}

		public Criteria andEtimeEqualTo(Date value) {
			addCriterion("ETIME =", value, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeNotEqualTo(Date value) {
			addCriterion("ETIME <>", value, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeGreaterThan(Date value) {
			addCriterion("ETIME >", value, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("ETIME >=", value, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeLessThan(Date value) {
			addCriterion("ETIME <", value, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeLessThanOrEqualTo(Date value) {
			addCriterion("ETIME <=", value, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeIn(List<Date> values) {
			addCriterion("ETIME in", values, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeNotIn(List<Date> values) {
			addCriterion("ETIME not in", values, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeBetween(Date value1, Date value2) {
			addCriterion("ETIME between", value1, value2, "etime");
			return (Criteria) this;
		}

		public Criteria andEtimeNotBetween(Date value1, Date value2) {
			addCriterion("ETIME not between", value1, value2, "etime");
			return (Criteria) this;
		}

		public Criteria andScoresIsNull() {
			addCriterion("SCORES is null");
			return (Criteria) this;
		}

		public Criteria andScoresIsNotNull() {
			addCriterion("SCORES is not null");
			return (Criteria) this;
		}

		public Criteria andScoresEqualTo(String value) {
			addCriterion("SCORES =", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresNotEqualTo(String value) {
			addCriterion("SCORES <>", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresGreaterThan(String value) {
			addCriterion("SCORES >", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresGreaterThanOrEqualTo(String value) {
			addCriterion("SCORES >=", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresLessThan(String value) {
			addCriterion("SCORES <", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresLessThanOrEqualTo(String value) {
			addCriterion("SCORES <=", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresLike(String value) {
			addCriterion("SCORES like", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresNotLike(String value) {
			addCriterion("SCORES not like", value, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresIn(List<String> values) {
			addCriterion("SCORES in", values, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresNotIn(List<String> values) {
			addCriterion("SCORES not in", values, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresBetween(String value1, String value2) {
			addCriterion("SCORES between", value1, value2, "scores");
			return (Criteria) this;
		}

		public Criteria andScoresNotBetween(String value1, String value2) {
			addCriterion("SCORES not between", value1, value2, "scores");
			return (Criteria) this;
		}

		public Criteria andAwardIsNull() {
			addCriterion("AWARD is null");
			return (Criteria) this;
		}

		public Criteria andAwardIsNotNull() {
			addCriterion("AWARD is not null");
			return (Criteria) this;
		}

		public Criteria andAwardEqualTo(String value) {
			addCriterion("AWARD =", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardNotEqualTo(String value) {
			addCriterion("AWARD <>", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardGreaterThan(String value) {
			addCriterion("AWARD >", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardGreaterThanOrEqualTo(String value) {
			addCriterion("AWARD >=", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardLessThan(String value) {
			addCriterion("AWARD <", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardLessThanOrEqualTo(String value) {
			addCriterion("AWARD <=", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardLike(String value) {
			addCriterion("AWARD like", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardNotLike(String value) {
			addCriterion("AWARD not like", value, "award");
			return (Criteria) this;
		}

		public Criteria andAwardIn(List<String> values) {
			addCriterion("AWARD in", values, "award");
			return (Criteria) this;
		}

		public Criteria andAwardNotIn(List<String> values) {
			addCriterion("AWARD not in", values, "award");
			return (Criteria) this;
		}

		public Criteria andAwardBetween(String value1, String value2) {
			addCriterion("AWARD between", value1, value2, "award");
			return (Criteria) this;
		}

		public Criteria andAwardNotBetween(String value1, String value2) {
			addCriterion("AWARD not between", value1, value2, "award");
			return (Criteria) this;
		}

		public Criteria andScorestypeIsNull() {
			addCriterion("SCORESTYPE is null");
			return (Criteria) this;
		}

		public Criteria andScorestypeIsNotNull() {
			addCriterion("SCORESTYPE is not null");
			return (Criteria) this;
		}

		public Criteria andScorestypeEqualTo(Integer value) {
			addCriterion("SCORESTYPE =", value, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeNotEqualTo(Integer value) {
			addCriterion("SCORESTYPE <>", value, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeGreaterThan(Integer value) {
			addCriterion("SCORESTYPE >", value, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("SCORESTYPE >=", value, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeLessThan(Integer value) {
			addCriterion("SCORESTYPE <", value, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeLessThanOrEqualTo(Integer value) {
			addCriterion("SCORESTYPE <=", value, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeIn(List<Integer> values) {
			addCriterion("SCORESTYPE in", values, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeNotIn(List<Integer> values) {
			addCriterion("SCORESTYPE not in", values, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeBetween(Integer value1, Integer value2) {
			addCriterion("SCORESTYPE between", value1, value2, "scorestype");
			return (Criteria) this;
		}

		public Criteria andScorestypeNotBetween(Integer value1, Integer value2) {
			addCriterion("SCORESTYPE not between", value1, value2, "scorestype");
			return (Criteria) this;
		}

		public Criteria andTotalIsNull() {
			addCriterion("TOTAL is null");
			return (Criteria) this;
		}

		public Criteria andTotalIsNotNull() {
			addCriterion("TOTAL is not null");
			return (Criteria) this;
		}

		public Criteria andTotalEqualTo(String value) {
			addCriterion("TOTAL =", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotEqualTo(String value) {
			addCriterion("TOTAL <>", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalGreaterThan(String value) {
			addCriterion("TOTAL >", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalGreaterThanOrEqualTo(String value) {
			addCriterion("TOTAL >=", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLessThan(String value) {
			addCriterion("TOTAL <", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLessThanOrEqualTo(String value) {
			addCriterion("TOTAL <=", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLike(String value) {
			addCriterion("TOTAL like", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotLike(String value) {
			addCriterion("TOTAL not like", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalIn(List<String> values) {
			addCriterion("TOTAL in", values, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotIn(List<String> values) {
			addCriterion("TOTAL not in", values, "total");
			return (Criteria) this;
		}

		public Criteria andTotalBetween(String value1, String value2) {
			addCriterion("TOTAL between", value1, value2, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotBetween(String value1, String value2) {
			addCriterion("TOTAL not between", value1, value2, "total");
			return (Criteria) this;
		}

		public Criteria andQgpIsNull() {
			addCriterion("QGP is null");
			return (Criteria) this;
		}

		public Criteria andQgpIsNotNull() {
			addCriterion("QGP is not null");
			return (Criteria) this;
		}

		public Criteria andQgpEqualTo(String value) {
			addCriterion("QGP =", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpNotEqualTo(String value) {
			addCriterion("QGP <>", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpGreaterThan(String value) {
			addCriterion("QGP >", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpGreaterThanOrEqualTo(String value) {
			addCriterion("QGP >=", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpLessThan(String value) {
			addCriterion("QGP <", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpLessThanOrEqualTo(String value) {
			addCriterion("QGP <=", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpLike(String value) {
			addCriterion("QGP like", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpNotLike(String value) {
			addCriterion("QGP not like", value, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpIn(List<String> values) {
			addCriterion("QGP in", values, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpNotIn(List<String> values) {
			addCriterion("QGP not in", values, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpBetween(String value1, String value2) {
			addCriterion("QGP between", value1, value2, "qgp");
			return (Criteria) this;
		}

		public Criteria andQgpNotBetween(String value1, String value2) {
			addCriterion("QGP not between", value1, value2, "qgp");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}
package com.alphasta.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public QuestionExample() {
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

		public Criteria andMethodIsNull() {
			addCriterion("METHOD is null");
			return (Criteria) this;
		}

		public Criteria andMethodIsNotNull() {
			addCriterion("METHOD is not null");
			return (Criteria) this;
		}

		public Criteria andMethodEqualTo(Integer value) {
			addCriterion("METHOD =", value, "method");
			return (Criteria) this;
		}

		public Criteria andMethodNotEqualTo(Integer value) {
			addCriterion("METHOD <>", value, "method");
			return (Criteria) this;
		}

		public Criteria andMethodGreaterThan(Integer value) {
			addCriterion("METHOD >", value, "method");
			return (Criteria) this;
		}

		public Criteria andMethodGreaterThanOrEqualTo(Integer value) {
			addCriterion("METHOD >=", value, "method");
			return (Criteria) this;
		}

		public Criteria andMethodLessThan(Integer value) {
			addCriterion("METHOD <", value, "method");
			return (Criteria) this;
		}

		public Criteria andMethodLessThanOrEqualTo(Integer value) {
			addCriterion("METHOD <=", value, "method");
			return (Criteria) this;
		}

		public Criteria andMethodIn(List<Integer> values) {
			addCriterion("METHOD in", values, "method");
			return (Criteria) this;
		}

		public Criteria andMethodNotIn(List<Integer> values) {
			addCriterion("METHOD not in", values, "method");
			return (Criteria) this;
		}

		public Criteria andMethodBetween(Integer value1, Integer value2) {
			addCriterion("METHOD between", value1, value2, "method");
			return (Criteria) this;
		}

		public Criteria andMethodNotBetween(Integer value1, Integer value2) {
			addCriterion("METHOD not between", value1, value2, "method");
			return (Criteria) this;
		}

		public Criteria andTopicIsNull() {
			addCriterion("TOPIC is null");
			return (Criteria) this;
		}

		public Criteria andTopicIsNotNull() {
			addCriterion("TOPIC is not null");
			return (Criteria) this;
		}

		public Criteria andTopicEqualTo(String value) {
			addCriterion("TOPIC =", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicNotEqualTo(String value) {
			addCriterion("TOPIC <>", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicGreaterThan(String value) {
			addCriterion("TOPIC >", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicGreaterThanOrEqualTo(String value) {
			addCriterion("TOPIC >=", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicLessThan(String value) {
			addCriterion("TOPIC <", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicLessThanOrEqualTo(String value) {
			addCriterion("TOPIC <=", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicLike(String value) {
			addCriterion("TOPIC like", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicNotLike(String value) {
			addCriterion("TOPIC not like", value, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicIn(List<String> values) {
			addCriterion("TOPIC in", values, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicNotIn(List<String> values) {
			addCriterion("TOPIC not in", values, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicBetween(String value1, String value2) {
			addCriterion("TOPIC between", value1, value2, "topic");
			return (Criteria) this;
		}

		public Criteria andTopicNotBetween(String value1, String value2) {
			addCriterion("TOPIC not between", value1, value2, "topic");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireIsNull() {
			addCriterion("QUESTIONNAIRE is null");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireIsNotNull() {
			addCriterion("QUESTIONNAIRE is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireEqualTo(String value) {
			addCriterion("QUESTIONNAIRE =", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireNotEqualTo(String value) {
			addCriterion("QUESTIONNAIRE <>", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireGreaterThan(String value) {
			addCriterion("QUESTIONNAIRE >", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireGreaterThanOrEqualTo(String value) {
			addCriterion("QUESTIONNAIRE >=", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireLessThan(String value) {
			addCriterion("QUESTIONNAIRE <", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireLessThanOrEqualTo(String value) {
			addCriterion("QUESTIONNAIRE <=", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireLike(String value) {
			addCriterion("QUESTIONNAIRE like", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireNotLike(String value) {
			addCriterion("QUESTIONNAIRE not like", value, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireIn(List<String> values) {
			addCriterion("QUESTIONNAIRE in", values, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireNotIn(List<String> values) {
			addCriterion("QUESTIONNAIRE not in", values, "questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireBetween(String value1, String value2) {
			addCriterion("QUESTIONNAIRE between", value1, value2,
					"questionnaire");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireNotBetween(String value1, String value2) {
			addCriterion("QUESTIONNAIRE not between", value1, value2,
					"questionnaire");
			return (Criteria) this;
		}

		public Criteria andScoreIsNull() {
			addCriterion("SCORE is null");
			return (Criteria) this;
		}

		public Criteria andScoreIsNotNull() {
			addCriterion("SCORE is not null");
			return (Criteria) this;
		}

		public Criteria andScoreEqualTo(String value) {
			addCriterion("SCORE =", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotEqualTo(String value) {
			addCriterion("SCORE <>", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThan(String value) {
			addCriterion("SCORE >", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThanOrEqualTo(String value) {
			addCriterion("SCORE >=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThan(String value) {
			addCriterion("SCORE <", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThanOrEqualTo(String value) {
			addCriterion("SCORE <=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLike(String value) {
			addCriterion("SCORE like", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotLike(String value) {
			addCriterion("SCORE not like", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreIn(List<String> values) {
			addCriterion("SCORE in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotIn(List<String> values) {
			addCriterion("SCORE not in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreBetween(String value1, String value2) {
			addCriterion("SCORE between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotBetween(String value1, String value2) {
			addCriterion("SCORE not between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("TYPE is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("TYPE =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("TYPE <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("TYPE >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("TYPE >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("TYPE <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("TYPE <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("TYPE in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("TYPE not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("TYPE between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("TYPE not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andAnswerIsNull() {
			addCriterion("ANSWER is null");
			return (Criteria) this;
		}

		public Criteria andAnswerIsNotNull() {
			addCriterion("ANSWER is not null");
			return (Criteria) this;
		}

		public Criteria andAnswerEqualTo(String value) {
			addCriterion("ANSWER =", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotEqualTo(String value) {
			addCriterion("ANSWER <>", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerGreaterThan(String value) {
			addCriterion("ANSWER >", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerGreaterThanOrEqualTo(String value) {
			addCriterion("ANSWER >=", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLessThan(String value) {
			addCriterion("ANSWER <", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLessThanOrEqualTo(String value) {
			addCriterion("ANSWER <=", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLike(String value) {
			addCriterion("ANSWER like", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotLike(String value) {
			addCriterion("ANSWER not like", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerIn(List<String> values) {
			addCriterion("ANSWER in", values, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotIn(List<String> values) {
			addCriterion("ANSWER not in", values, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerBetween(String value1, String value2) {
			addCriterion("ANSWER between", value1, value2, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotBetween(String value1, String value2) {
			addCriterion("ANSWER not between", value1, value2, "answer");
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
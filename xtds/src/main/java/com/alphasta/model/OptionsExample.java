package com.alphasta.model;

import java.util.ArrayList;
import java.util.List;

public class OptionsExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public OptionsExample() {
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

		public Criteria andQuestionIsNull() {
			addCriterion("QUESTION is null");
			return (Criteria) this;
		}

		public Criteria andQuestionIsNotNull() {
			addCriterion("QUESTION is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionEqualTo(String value) {
			addCriterion("QUESTION =", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotEqualTo(String value) {
			addCriterion("QUESTION <>", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionGreaterThan(String value) {
			addCriterion("QUESTION >", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionGreaterThanOrEqualTo(String value) {
			addCriterion("QUESTION >=", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionLessThan(String value) {
			addCriterion("QUESTION <", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionLessThanOrEqualTo(String value) {
			addCriterion("QUESTION <=", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionLike(String value) {
			addCriterion("QUESTION like", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotLike(String value) {
			addCriterion("QUESTION not like", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionIn(List<String> values) {
			addCriterion("QUESTION in", values, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotIn(List<String> values) {
			addCriterion("QUESTION not in", values, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionBetween(String value1, String value2) {
			addCriterion("QUESTION between", value1, value2, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotBetween(String value1, String value2) {
			addCriterion("QUESTION not between", value1, value2, "question");
			return (Criteria) this;
		}

		public Criteria andNumIsNull() {
			addCriterion("NUM is null");
			return (Criteria) this;
		}

		public Criteria andNumIsNotNull() {
			addCriterion("NUM is not null");
			return (Criteria) this;
		}

		public Criteria andNumEqualTo(String value) {
			addCriterion("NUM =", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotEqualTo(String value) {
			addCriterion("NUM <>", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumGreaterThan(String value) {
			addCriterion("NUM >", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumGreaterThanOrEqualTo(String value) {
			addCriterion("NUM >=", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLessThan(String value) {
			addCriterion("NUM <", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLessThanOrEqualTo(String value) {
			addCriterion("NUM <=", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLike(String value) {
			addCriterion("NUM like", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotLike(String value) {
			addCriterion("NUM not like", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumIn(List<String> values) {
			addCriterion("NUM in", values, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotIn(List<String> values) {
			addCriterion("NUM not in", values, "num");
			return (Criteria) this;
		}

		public Criteria andNumBetween(String value1, String value2) {
			addCriterion("NUM between", value1, value2, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotBetween(String value1, String value2) {
			addCriterion("NUM not between", value1, value2, "num");
			return (Criteria) this;
		}

		public Criteria andContentIsNull() {
			addCriterion("CONTENT is null");
			return (Criteria) this;
		}

		public Criteria andContentIsNotNull() {
			addCriterion("CONTENT is not null");
			return (Criteria) this;
		}

		public Criteria andContentEqualTo(String value) {
			addCriterion("CONTENT =", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotEqualTo(String value) {
			addCriterion("CONTENT <>", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThan(String value) {
			addCriterion("CONTENT >", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThanOrEqualTo(String value) {
			addCriterion("CONTENT >=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThan(String value) {
			addCriterion("CONTENT <", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThanOrEqualTo(String value) {
			addCriterion("CONTENT <=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLike(String value) {
			addCriterion("CONTENT like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotLike(String value) {
			addCriterion("CONTENT not like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentIn(List<String> values) {
			addCriterion("CONTENT in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotIn(List<String> values) {
			addCriterion("CONTENT not in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentBetween(String value1, String value2) {
			addCriterion("CONTENT between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotBetween(String value1, String value2) {
			addCriterion("CONTENT not between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andIsanswerIsNull() {
			addCriterion("ISANSWER is null");
			return (Criteria) this;
		}

		public Criteria andIsanswerIsNotNull() {
			addCriterion("ISANSWER is not null");
			return (Criteria) this;
		}

		public Criteria andIsanswerEqualTo(Integer value) {
			addCriterion("ISANSWER =", value, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerNotEqualTo(Integer value) {
			addCriterion("ISANSWER <>", value, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerGreaterThan(Integer value) {
			addCriterion("ISANSWER >", value, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerGreaterThanOrEqualTo(Integer value) {
			addCriterion("ISANSWER >=", value, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerLessThan(Integer value) {
			addCriterion("ISANSWER <", value, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerLessThanOrEqualTo(Integer value) {
			addCriterion("ISANSWER <=", value, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerIn(List<Integer> values) {
			addCriterion("ISANSWER in", values, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerNotIn(List<Integer> values) {
			addCriterion("ISANSWER not in", values, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerBetween(Integer value1, Integer value2) {
			addCriterion("ISANSWER between", value1, value2, "isanswer");
			return (Criteria) this;
		}

		public Criteria andIsanswerNotBetween(Integer value1, Integer value2) {
			addCriterion("ISANSWER not between", value1, value2, "isanswer");
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
package com.taotao.manage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample() {
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNull() {
            addCriterion("order_number is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNotNull() {
            addCriterion("order_number is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberEqualTo(String value) {
            addCriterion("order_number =", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotEqualTo(String value) {
            addCriterion("order_number <>", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThan(String value) {
            addCriterion("order_number >", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThanOrEqualTo(String value) {
            addCriterion("order_number >=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThan(String value) {
            addCriterion("order_number <", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThanOrEqualTo(String value) {
            addCriterion("order_number <=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLike(String value) {
            addCriterion("order_number like", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotLike(String value) {
            addCriterion("order_number not like", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIn(List<String> values) {
            addCriterion("order_number in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotIn(List<String> values) {
            addCriterion("order_number not in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberBetween(String value1, String value2) {
            addCriterion("order_number between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotBetween(String value1, String value2) {
            addCriterion("order_number not between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNull() {
            addCriterion("pic_path is null");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNotNull() {
            addCriterion("pic_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicPathEqualTo(String value) {
            addCriterion("pic_path =", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotEqualTo(String value) {
            addCriterion("pic_path <>", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThan(String value) {
            addCriterion("pic_path >", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThanOrEqualTo(String value) {
            addCriterion("pic_path >=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThan(String value) {
            addCriterion("pic_path <", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThanOrEqualTo(String value) {
            addCriterion("pic_path <=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLike(String value) {
            addCriterion("pic_path like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotLike(String value) {
            addCriterion("pic_path not like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathIn(List<String> values) {
            addCriterion("pic_path in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotIn(List<String> values) {
            addCriterion("pic_path not in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathBetween(String value1, String value2) {
            addCriterion("pic_path between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotBetween(String value1, String value2) {
            addCriterion("pic_path not between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andBuyUserIsNull() {
            addCriterion("buy_user is null");
            return (Criteria) this;
        }

        public Criteria andBuyUserIsNotNull() {
            addCriterion("buy_user is not null");
            return (Criteria) this;
        }

        public Criteria andBuyUserEqualTo(String value) {
            addCriterion("buy_user =", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserNotEqualTo(String value) {
            addCriterion("buy_user <>", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserGreaterThan(String value) {
            addCriterion("buy_user >", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserGreaterThanOrEqualTo(String value) {
            addCriterion("buy_user >=", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserLessThan(String value) {
            addCriterion("buy_user <", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserLessThanOrEqualTo(String value) {
            addCriterion("buy_user <=", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserLike(String value) {
            addCriterion("buy_user like", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserNotLike(String value) {
            addCriterion("buy_user not like", value, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserIn(List<String> values) {
            addCriterion("buy_user in", values, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserNotIn(List<String> values) {
            addCriterion("buy_user not in", values, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserBetween(String value1, String value2) {
            addCriterion("buy_user between", value1, value2, "buyUser");
            return (Criteria) this;
        }

        public Criteria andBuyUserNotBetween(String value1, String value2) {
            addCriterion("buy_user not between", value1, value2, "buyUser");
            return (Criteria) this;
        }

        public Criteria andSellUserIsNull() {
            addCriterion("sell_user is null");
            return (Criteria) this;
        }

        public Criteria andSellUserIsNotNull() {
            addCriterion("sell_user is not null");
            return (Criteria) this;
        }

        public Criteria andSellUserEqualTo(String value) {
            addCriterion("sell_user =", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotEqualTo(String value) {
            addCriterion("sell_user <>", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserGreaterThan(String value) {
            addCriterion("sell_user >", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserGreaterThanOrEqualTo(String value) {
            addCriterion("sell_user >=", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserLessThan(String value) {
            addCriterion("sell_user <", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserLessThanOrEqualTo(String value) {
            addCriterion("sell_user <=", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserLike(String value) {
            addCriterion("sell_user like", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotLike(String value) {
            addCriterion("sell_user not like", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserIn(List<String> values) {
            addCriterion("sell_user in", values, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotIn(List<String> values) {
            addCriterion("sell_user not in", values, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserBetween(String value1, String value2) {
            addCriterion("sell_user between", value1, value2, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotBetween(String value1, String value2) {
            addCriterion("sell_user not between", value1, value2, "sellUser");
            return (Criteria) this;
        }

        public Criteria andAdminIsNull() {
            addCriterion("admin is null");
            return (Criteria) this;
        }

        public Criteria andAdminIsNotNull() {
            addCriterion("admin is not null");
            return (Criteria) this;
        }

        public Criteria andAdminEqualTo(String value) {
            addCriterion("admin =", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminNotEqualTo(String value) {
            addCriterion("admin <>", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminGreaterThan(String value) {
            addCriterion("admin >", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminGreaterThanOrEqualTo(String value) {
            addCriterion("admin >=", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminLessThan(String value) {
            addCriterion("admin <", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminLessThanOrEqualTo(String value) {
            addCriterion("admin <=", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminLike(String value) {
            addCriterion("admin like", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminNotLike(String value) {
            addCriterion("admin not like", value, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminIn(List<String> values) {
            addCriterion("admin in", values, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminNotIn(List<String> values) {
            addCriterion("admin not in", values, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminBetween(String value1, String value2) {
            addCriterion("admin between", value1, value2, "admin");
            return (Criteria) this;
        }

        public Criteria andAdminNotBetween(String value1, String value2) {
            addCriterion("admin not between", value1, value2, "admin");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberIsNull() {
            addCriterion("tracking_number is null");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberIsNotNull() {
            addCriterion("tracking_number is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberEqualTo(String value) {
            addCriterion("tracking_number =", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotEqualTo(String value) {
            addCriterion("tracking_number <>", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberGreaterThan(String value) {
            addCriterion("tracking_number >", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tracking_number >=", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberLessThan(String value) {
            addCriterion("tracking_number <", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberLessThanOrEqualTo(String value) {
            addCriterion("tracking_number <=", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberLike(String value) {
            addCriterion("tracking_number like", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotLike(String value) {
            addCriterion("tracking_number not like", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberIn(List<String> values) {
            addCriterion("tracking_number in", values, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotIn(List<String> values) {
            addCriterion("tracking_number not in", values, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberBetween(String value1, String value2) {
            addCriterion("tracking_number between", value1, value2, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotBetween(String value1, String value2) {
            addCriterion("tracking_number not between", value1, value2, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingIsNull() {
            addCriterion("admin_ack_tracking is null");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingIsNotNull() {
            addCriterion("admin_ack_tracking is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingEqualTo(String value) {
            addCriterion("admin_ack_tracking =", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingNotEqualTo(String value) {
            addCriterion("admin_ack_tracking <>", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingGreaterThan(String value) {
            addCriterion("admin_ack_tracking >", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingGreaterThanOrEqualTo(String value) {
            addCriterion("admin_ack_tracking >=", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingLessThan(String value) {
            addCriterion("admin_ack_tracking <", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingLessThanOrEqualTo(String value) {
            addCriterion("admin_ack_tracking <=", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingLike(String value) {
            addCriterion("admin_ack_tracking like", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingNotLike(String value) {
            addCriterion("admin_ack_tracking not like", value, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingIn(List<String> values) {
            addCriterion("admin_ack_tracking in", values, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingNotIn(List<String> values) {
            addCriterion("admin_ack_tracking not in", values, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingBetween(String value1, String value2) {
            addCriterion("admin_ack_tracking between", value1, value2, "adminAckTracking");
            return (Criteria) this;
        }

        public Criteria andAdminAckTrackingNotBetween(String value1, String value2) {
            addCriterion("admin_ack_tracking not between", value1, value2, "adminAckTracking");
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
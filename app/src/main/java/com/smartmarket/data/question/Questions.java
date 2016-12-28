package com.smartmarket.data.question;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("available_filters")
    @Expose
    private List<AvailableFilter> availableFilters = null;
    @SerializedName("available_sorts")
    @Expose
    private List<String> availableSorts = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<AvailableFilter> getAvailableFilters() {
        return availableFilters;
    }

    public void setAvailableFilters(List<AvailableFilter> availableFilters) {
        this.availableFilters = availableFilters;
    }

    public List<String> getAvailableSorts() {
        return availableSorts;
    }

    public void setAvailableSorts(List<String> availableSorts) {
        this.availableSorts = availableSorts;
    }

    class AvailableFilter {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("values")
        @Expose
        private List<String> values = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

    }

    class Filters {

        @SerializedName("limit")
        @Expose
        private Integer limit;
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("is_admin")
        @Expose
        private Boolean isAdmin;
        @SerializedName("sorts")
        @Expose
        private List<Object> sorts = null;
        @SerializedName("caller")
        @Expose
        private Integer caller;
        @SerializedName("seller")
        @Expose
        private String seller;
        @SerializedName("item")
        @Expose
        private String item;
        @SerializedName("from")
        @Expose
        private String from;

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Boolean getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(Boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

        public List<Object> getSorts() {
            return sorts;
        }

        public void setSorts(List<Object> sorts) {
            this.sorts = sorts;
        }

        public Integer getCaller() {
            return caller;
        }

        public void setCaller(Integer caller) {
            this.caller = caller;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }


    }

    public class From {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("answered_questions")
        @Expose
        private Integer answeredQuestions;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAnsweredQuestions() {
            return answeredQuestions;
        }

        public void setAnsweredQuestions(Integer answeredQuestions) {
            this.answeredQuestions = answeredQuestions;
        }

    }

    public class Question {

        @SerializedName("date_created")
        @Expose
        private String dateCreated;
        @SerializedName("item_id")
        @Expose
        private String itemId;
        @SerializedName("seller_id")
        @Expose
        private Integer sellerId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("id")
        @Expose
        private Long id;
        @SerializedName("deleted_from_listing")
        @Expose
        private Boolean deletedFromListing;
        @SerializedName("hold")
        @Expose
        private Boolean hold;
        @SerializedName("answer")
        @Expose
        private Answer answer;
        @SerializedName("from")
        @Expose
        private From from;

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public Integer getSellerId() {
            return sellerId;
        }

        public void setSellerId(Integer sellerId) {
            this.sellerId = sellerId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Boolean getDeletedFromListing() {
            return deletedFromListing;
        }

        public void setDeletedFromListing(Boolean deletedFromListing) {
            this.deletedFromListing = deletedFromListing;
        }

        public Boolean getHold() {
            return hold;
        }

        public void setHold(Boolean hold) {
            this.hold = hold;
        }

        public Answer getAnswer() {
            return answer;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public From getFrom() {
            return from;
        }

        public void setFrom(From from) {
            this.from = from;
        }

    }

    public class Answer {
        @SerializedName("text")
        @Expose
        String text;
        @SerializedName("status")
        @Expose
        String status;
        @SerializedName("date_created")
        @Expose
        String date_created;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate_created() {
            return date_created;
        }

        public void setDate_created(String date_created) {
            this.date_created = date_created;
        }
    }
}

package nl.openb.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "narrative",
    "comments",
    "tags",
    "images",
    "where"
})
public class Metadata_ {

    @JsonProperty("narrative")
    private Object narrative;
    @JsonProperty("comments")
    private List<Object> comments = new ArrayList<Object>();
    @JsonProperty("tags")
    private List<Object> tags = new ArrayList<Object>();
    @JsonProperty("images")
    private List<Object> images = new ArrayList<Object>();
    @JsonProperty("where")
    private Object where;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("narrative")
    public Object getNarrative() {
        return narrative;
    }

    @JsonProperty("narrative")
    public void setNarrative(Object narrative) {
        this.narrative = narrative;
    }

    @JsonProperty("comments")
    public List<Object> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("images")
    public List<Object> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Object> images) {
        this.images = images;
    }

    @JsonProperty("where")
    public Object getWhere() {
        return where;
    }

    @JsonProperty("where")
    public void setWhere(Object where) {
        this.where = where;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(narrative).append(comments).append(tags).append(images).append(where).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metadata_) == false) {
            return false;
        }
        Metadata_ rhs = ((Metadata_) other);
        return new EqualsBuilder().append(narrative, rhs.narrative).append(comments, rhs.comments).append(tags, rhs.tags).append(images, rhs.images).append(where, rhs.where).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}

package io.cosmos.msg.utils.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import io.cosmos.types.CommissionMsg;
import io.cosmos.types.Description;
import io.cosmos.types.Token;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgEditValidatorValue {
    @JsonProperty("Description")
    @SerializedName("Description")
    private Description description;

    private String address;

    @JsonProperty("commission_rate")
    @SerializedName("commission_rate")
    private String commissionRate;

    @JsonProperty("min_self_delegation")
    @SerializedName("min_self_delegation")
    private String minSelfDelegation;



    private String moniker;
    private String identity;
    private String website;
    private String details;

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setDetails(String details) {
        this.details = details;
    }


    public void setDescription(Description description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public void setMinSelfDelegation(String minSelfDelegation) {
        this.minSelfDelegation = minSelfDelegation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("address", address)
                .append("min_self_delegation", this.minSelfDelegation)
                .append("Description", this.description)
//                .append("moniker", this.moniker)
//                .append("identity", this.identity)
//                .append("website", this.website)
//                .append("details", this.details)
                .append("commissionRate", this.commissionRate)
                .toString();
    }
}


    // MsgEditValidator - struct for editing a validator
//    type MsgEditValidator struct {
//        Description
//        ValidatorAddress sdk.ValAddress `json:"address"`
//
//        // We pass a reference to the new commission rate and min self delegation as it's not mandatory to
//        // update. If not updated, the deserialized rate will be zero with no way to
//        // distinguish if an update was intended.
//        //
//        // REF: #2373
//        CommissionRate    *sdk.Dec `json:""`
//        MinSelfDelegation *sdk.Int `json:"min_self_delegation"`
//        }
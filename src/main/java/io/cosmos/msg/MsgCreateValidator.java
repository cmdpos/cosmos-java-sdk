package io.cosmos.msg;

import io.cosmos.common.EnvInstance;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgCreateValidatorValue;
import io.cosmos.types.CommissionMsg;
import io.cosmos.types.Description;
import io.cosmos.types.Token;

import java.util.ArrayList;
import java.util.List;


public class MsgCreateValidator extends MsgBase{

    public static void main(String[] args) {
        EnvInstance.setEnv("ok");
        MsgCreateValidator msg = new MsgCreateValidator();
        msg.setMsgType("cosmos-sdk/MsgCreateValidator");
        msg.initMnemonic(EnvInstance.getEnv().GetNode0Mnmonic());
        Message messages = msg.produceMsg();
        msg.submit(messages, "6", "200000", "");
    }

    public Message produceMsg() {
        MsgCreateValidatorValue value = new MsgCreateValidatorValue();
        value.setDelegatorAddress(this.address);
        value.setValidatorAddress(this.operAddress);
        value.setPubKey("okchainvalconspub1zcjduepqwfr8lelpqerf8xyc63vqtje0wvhd68h7uce6ludygc28uj5hc9ushev2kp");

        Description d = new Description();
        d.setDetails("1");
        d.setIdentity("2");
        d.setMoniker("3");
        d.setWebsite("4");

        CommissionMsg c = new CommissionMsg();
        c.setMaxChangeRate("0.05");
        c.setMaxRate("0.05");
        c.setRate("0.05");

        Token t = new Token();
        t.setAmount("68");
        t.setDenom(EnvInstance.getEnv().GetDenom());

        value.setValue(t);
        value.setCommission(c);
        value.setDescription(d);
        value.setMinSelfDelegation("1");


        Message<MsgCreateValidatorValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}

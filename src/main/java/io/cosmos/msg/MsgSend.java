package io.cosmos.msg;

import io.cosmos.msg.utils.*;
import io.cosmos.msg.utils.type.MsgSendValue;
import io.cosmos.types.*;

import java.util.ArrayList;
import java.util.List;


// eva1hg40dv5e237qy28vtyum52ygke32ez35syykpz
//"depart neither they audit pen exile fire smart tongue express blanket burden culture shove curve address together pottery suggest lady sell clap seek whisper",
//
//                 eva1geyy4wtak2q9effnfhze9u4htd8yxxma0jmgl6
//                "country live width exotic behind mad belt bachelor later outside forget rude pudding material orbit shoot kind curve endless prosper make exotic welcome maple",

public class MsgSend extends MsgBase {

    public static void main(String[] args) {
        MsgSend msg = new MsgSend();
        msg.setMsgType("cosmos-sdk/MsgSend");

        msg.initMnemonic(
                "depart neither they audit pen exile " +
                        "fire smart tongue express blanket burden " +
                        "culture shove curve address together pottery " +
                        "suggest lady sell clap seek whisper");

//        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceSendMsg("neva",
                "16",
                "eva1geyy4wtak2q9effnfhze9u4htd8yxxma0jmgl6");

        msg.submit(messages,
                "6",
                "200000",
                "cosmos transfer");
    }

    public Message produceSendMsg(String denom, String amountDenom, String to) {

        List<Token> amountList = new ArrayList<>();
        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(amountDenom);
        amountList.add(amount);

        MsgSendValue value = new MsgSendValue();
        value.setFromAddress(this.address);
        value.setToAddress(to);
        value.setAmount(amountList);

        Message<MsgSendValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}

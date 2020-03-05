package io.cosmos.msg;

import io.cosmos.msg.utils.*;
import io.cosmos.msg.utils.type.MsgSendValue;
import io.cosmos.types.*;

import java.util.ArrayList;
import java.util.List;

public class MsgSend extends MsgBase {

    public static void main(String[] args) {
        MsgSend msg = new MsgSend();
        msg.setMsgType("cosmos-sdk/MsgSend");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceSendMsg("neva",
                "16",
                "eva1pn80qt83wzk9w4gs3muc8hw26cexlgav75mar0");

        msg.submit(messages,
                "neva",
                "6",
                "200000",
                "evaio",
                "cosmos transfer");
    }

//        msg.setRestServerUrl("http://localhost:1317");
//
//        msg.submit("neva",
//                "6",
//                "200000",
//                "evaio",
//                "neva",
//                "16",
//                "eva1pn80qt83wzk9w4gs3muc8hw26cexlgav75mar0",
//                "cosmos transfer",
//                "6dd0081f36e66121e8140ff85648d28dc0e01cb337a15a6f965e742b815ffdac"
////                "9357d721b9bec0c5c0983763428222c8ce4413e14a97b2c7c6ff121809cf67ab"
//                );
//}
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

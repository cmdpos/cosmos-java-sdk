package io.cosmos.msg;

import io.cosmos.common.EnvInstance;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgBurnValue;
import io.cosmos.msg.utils.type.MsgSendValue;
import io.cosmos.types.Token;

import java.util.ArrayList;
import java.util.List;

public class MsgBurn extends MsgBase {

    public static void main(String[] args) {
        EnvInstance.setEnv("okl");
        MsgBurn msg = new MsgBurn();

        msg.setMsgType("cosmos-sdk/MsgBurn");

        msg.initMnemonic(EnvInstance.getEnv().GetNode0Mnmonic());

        Message messages = msg.produceMsg(
                EnvInstance.getEnv().GetDenom(),
                "6",
                EnvInstance.getEnv().GetNode0Addr());

        msg.submit(messages,"","200000", "cosmos transfer!");
    }

    public Message produceMsg(String denom, String amountDenom, String addr) {

        List<Token> amountList = new ArrayList<>();
        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(amountDenom);
        amountList.add(amount);

        MsgBurnValue value = new MsgBurnValue();
        value.setAddress(addr);
        value.setAmount(amountList);

        Message<MsgBurnValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}

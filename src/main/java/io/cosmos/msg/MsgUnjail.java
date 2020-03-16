package io.cosmos.msg;

import io.cosmos.common.EnvInstance;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.Message35;
import io.cosmos.msg.utils.type.MsgCreateValidatorValue;
import io.cosmos.msg.utils.type.MsgUnjailValue;
import io.cosmos.types.CommissionMsg;
import io.cosmos.types.Description;
import io.cosmos.types.Token;

public class MsgUnjail  extends MsgBase {

    public static void main(String[] args) {
        EnvInstance.setEnv("okl");
        MsgUnjail msg = new MsgUnjail();
        msg.setMsgType("cosmos-sdk/MsgUnjail");
        msg.initMnemonic(EnvInstance.getEnv().GetNode0Mnmonic());
        Message messages = msg.produceMsg();
        msg.submit(messages, "10"+"000000000", "200000", "");
    }

    public Message produceMsg() {
        MsgUnjailValue value = new MsgUnjailValue();
        value.setAddress(this.operAddress);

        Message<MsgUnjailValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}


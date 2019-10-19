package io.cosmos.msg;

import io.cosmos.msg.utils.*;
import io.cosmos.msg.utils.type.MsgBeginRedelegateValue;
import io.cosmos.types.*;

public class MsgRedelegate extends MsgDelegate {

    public static void main(String[] args) {
        MsgRedelegate msg = new MsgRedelegate();
        msg.setMsgType("cosmos-sdk/MsgBeginRedelegate");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceDelegateMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083",
                "cosmosvaloper1uhfa9260p3xhnac74wgx5er7tpltww78gxeac0",
                "stake", "100");

        msg.submit(messages,
                "stake",
                "3",
                "200000",
                "testchain",
                "Delegate memo");
    }

    protected Message produceDelegateMsg(String validatorSrcAddress,
                                            String validatorDstAddress,
                                            String delegateDenom,
                                            String delegateAmount) {

        MsgBeginRedelegateValue delegateValue = new MsgBeginRedelegateValue();
        delegateValue.setValidatorSrcAddress(validatorSrcAddress);
        delegateValue.setValidatorDstAddress(validatorDstAddress);
        delegateValue.setDelegatorAddress(address);
        Token token = new Token();
        token.setDenom(delegateDenom);
        token.setAmount(delegateAmount);
        delegateValue.setAmount(token);
        Message<MsgBeginRedelegateValue> messageDelegateMulti = new Message<>();
        messageDelegateMulti.setType(msgType);
        messageDelegateMulti.setValue(delegateValue);
        return messageDelegateMulti;
    }

}
